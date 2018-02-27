import products.Category;
import products.Comment;
import products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> characteristics = Arrays.asList("Интерфейс", "Частота ядра", "Дополнительное питание", "Разъемы", "Подключение моста Nvidia SLI", "Частота памяти", "Размеры", "Минимально необходимая мощность БП", "Графический чип", "Пропускная способность памяти", "Комплект поставки", "Разрядность шины памяти", "Поддержка AMD CrossFireX", "Страна-производитель", "Особенности", "Максимально поддерживаемое разрешение", "Тип системы охлаждения", "Частота RAMDAC", "Тип памяти", "Гарантия", "Выходы DVI", "Форм-фактор", "Поддерживаемые 3D API", "Количество выходов VGA (D-Sub)", "Объем памяти", "Система охлаждения");

        // Подставляй любую из доступных категорий что бы получить все категории этого товара
        ArrayList<Product> videoCard = parserHelper.getProduct(Category.VIDEO_CARD);
//        ArrayList<Product> motherBoards = parserHelper.getProduct(Category.MOTHERBOARD);

        System.setOut(new PrintStream(new File("fillDB.sql"))); // Перенаправляем вывод с консоли в файл

        int attrID = 1, imageID = 1, commentID = 1;
        for (int prodID = 0; prodID < videoCard.size(); prodID++) {
            String prodRequest = String.format("INSERT INTO mydb.products (ProductID, ProductTypeID, Title, Description, Price) VALUES (%d, %d, \"%s\", \"%s\", %d);",
                    prodID + 1, 6, videoCard.get(prodID).getTitle(), videoCard.get(prodID).getDescription(), videoCard.get(prodID).getPrice());
            System.out.println(prodRequest);

            HashMap<String, String> attributes = videoCard.get(prodID).getAttributes();
            for (Map.Entry<String, String> attr : attributes.entrySet()) {
                String attrRequest = String.format("INSERT INTO mydb.characteristics (CharacteristicID, CharacteristicTypeID, Value, ProductID) VALUES (%d, %d, \"%s\", %d);",
                        attrID++, characteristics.indexOf(attr.getKey()) + 1, attr.getValue(), prodID + 1);
                System.out.println(attrRequest);
            }

            ArrayList<String> images = videoCard.get(prodID).getImages();
            for (String img : images) {
                String imgRequest = String.format("INSERT INTO mydb.images (ImageID, Url, ProductID) VALUES (%d, \"%s\", %d);",
                        imageID++, img, prodID + 1);
                System.out.println(imgRequest);
            }

            ArrayList<Comment> comments = videoCard.get(prodID).getComments();
            for (Comment comment : comments) {
                String commentRequest = String.format("INSERT INTO mydb.comments (CommentID, Nickname, Rating, Message, Date, ProductID) VALUES (%d, \"%s\", %d, \"%s\", \"%s\", %d);",
                        commentID++, comment.getName(), comment.getRating(), comment.getMessage(),comment.getDate() , prodID + 1);
                System.out.println(commentRequest);
            }

            System.out.println("-- =====================================================================");
        }
    }
}
