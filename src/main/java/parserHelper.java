import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import products.Category;
import products.Comment;
import products.Processor;
import products.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class parserHelper {

    private static ArrayList<Product> getAllProcessors() throws IOException {
        String dir = "RawHTML/Processor/";
        return doAllJob(dir, Category.PROCESSOR);
    }

    private static ArrayList<Product> getAllHDD() throws IOException {
        String dir = "RawHTML/HDD/";
        return doAllJob(dir, Category.HDD);
    }

    private static ArrayList<Product> getAllMotherboard() throws IOException {
        String dir = "RawHTML/Motherboard/";
        return doAllJob(dir, Category.MOTHERBOARD);
    }

    private static ArrayList<Product> getAllVideoCard() throws IOException {
        String dir = "RawHTML/VideoCard/";
        return doAllJob(dir, Category.VIDEO_CARD);
    }

    private static ArrayList<Product> getAllPowerSupply() throws IOException {
        String dir = "RawHTML/PowerSupply/";
        return doAllJob(dir, Category.POWER_SUPPLY);
    }

    private static ArrayList<Product> getAllRAM() throws IOException {
        String dir = "RawHTML/RAM/";
        return doAllJob(dir, Category.RAM);
    }

    private static ArrayList<Product> doAllJob(String dir, Category category) throws IOException {
        ArrayList<Product> result = new ArrayList<>();

        Files.list(Paths.get(dir)).forEach(item -> {
            ArrayList<File> files = new ArrayList<>();
            listf(item.toString(), files);

            try {
                Document mainPage = Jsoup.parse(files.get(1), "UTF-8");
                Document characteristicPage = Jsoup.parse(files.get(0), "UTF-8");

                String title = getTitle(mainPage);
                String description = getDescription(mainPage);
                int price = getPrice();

                ArrayList<String> images = getImages(mainPage);
                ArrayList<Comment> comments = getComments(mainPage);
                HashMap<String, String> attributes = getCharacteristics(characteristicPage);

                result.add(new Processor(category, title, description, price, images, attributes, comments));
            } catch (Exception e) {
                System.err.println("Ошибка во время парса файла:");
                e.printStackTrace();
                System.err.println("Пропускаем файл ...");
                System.err.println("----------------------------------------------");
            }
        });

        return result;
    }

    private static HashMap<String, String> getCharacteristics(Document characteristicPage) {

        HashMap<String, String> result = new HashMap<>();

        Elements allAttribute = characteristicPage.select(".chars-t tr");
        for (Element element : allAttribute) {
            String attr = element.getElementsByClass("chars-title").text();
            String value = element.getElementsByClass("chars-value").text();

            result.put(attr, value);
        }

        return result;
    }

    private static void listf(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
    }

    private static ArrayList<Comment> getComments(Document doc) {
        ArrayList<Comment> result = new ArrayList<>();
        Elements commentElements = doc.select(".pp-review-i");

        for (int i = 0; i < 3 && i < commentElements.size(); i++) {
            String nickName = commentElements.get(i).getElementsByClass("pp-review-author-name").text();
            String rawDate = commentElements.get(i).getElementsByClass("pp-review-date-text").text();
            Date date = stringToDate(rawDate);
            String message = commentElements.get(i).getElementsByClass("pp-review-text-i").text();

            int rating = 0;
            Elements ratingElement = commentElements.get(i).getElementsByClass("sprite g-rating-stars-i");
            if (ratingElement.size() > 0)
                rating = Integer.parseInt(ratingElement.attr("content"));

            result.add(new Comment(nickName, rating, date, message));
        }

        return result;
    }

    private static ArrayList<String> getImages(Document doc) {
        ArrayList<String> result = new ArrayList<>();
        result.add(doc.select("#detail_image_container img").attr("src"));

        Elements otherImages = doc.getElementsByClass("detail-img-thumbs-l-i");
        for (int i = 1; i < otherImages.size(); i++) {
            result.add(otherImages.get(i).child(0).attr("data-accord-url"));
        }

        return result;
    }

    private static int getPrice() {
        return new Random().nextInt(20000); // Слишком геморно доставать, будет рандомом
    }

    private static String getDescription(Document doc) throws Exception {
        return doc.getElementById("short_text").text();
    }

    private static String getTitle(Document doc) {
        return doc.getElementsByClass("detail-title").text();
    }

    public static ArrayList<Product> getProduct(Category category) {
        try {
            switch (category) {
                case PROCESSOR:
                    return getAllProcessors();
                case RAM:
                    return getAllRAM();
                case POWER_SUPPLY:
                    return getAllPowerSupply();
                case VIDEO_CARD:
                    return getAllVideoCard();
                case MOTHERBOARD:
                    return getAllMotherboard();
                case HDD:
                    return getAllHDD();
            }
        } catch (Exception e) {
            System.err.println("Не удалось получить список продуктов");
            e.printStackTrace();
            System.err.println("----------------------------------------------");
        }

        return new ArrayList<>();
    }

    private static Date stringToDate(String rawDate) {
        List<String> mouths = Arrays.asList("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря");

        try {
            String[] splitDate = rawDate.split(" ");
            String date = String.format("%s %02d %s", splitDate[0], mouths.indexOf(splitDate[1]) + 1, splitDate[2]);
            return new SimpleDateFormat("dd MM yyyy").parse(date);
        } catch (Exception e) {
            System.err.println("Не удалось спарсить дату коментария");
            System.err.println("Date: " + rawDate);
//            e.printStackTrace();
        }

        Date defaultDate = new Date();
        System.err.println("Возвращаю дату по умолчанию: " + defaultDate);
        System.err.println("----------------------------------------------");
        return defaultDate;
    }
}