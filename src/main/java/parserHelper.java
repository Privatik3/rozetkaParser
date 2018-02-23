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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class parserHelper {

    private static ArrayList<Product> getAllProcessors() {

        ArrayList<Product> result = new ArrayList<Product>();

        ArrayList<String> images = new ArrayList<String>();
        images.add("https://ссылка на картинку...");
        images.add("https://ещё одна картинка...");

        HashMap<String, String> attributes = new HashMap<String, String>();
        attributes.put("Типа атрибут:", "значение атрибута");
        attributes.put("Ещё один атрибут:", "значение атрибута");

        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments.add(new Comment("Ник отправителя", 5, new Date(1000005515), "Содержание комента..."));

        Processor processor = new Processor(
                Category.PROCESSOR, "Название товара","здесь будет описание товара",
                5500, images, attributes, comments);

        result.add(processor);
        return result;
    }

    public static ArrayList<Product> getProduct(Category category) {
        return getAllProcessors();
    }
}
