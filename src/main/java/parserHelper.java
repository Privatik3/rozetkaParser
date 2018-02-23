import products.Category;
import products.Comment;
import products.Processor;
import products.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
                images, attributes, comments);

        result.add(processor);
        return result;
    }

    public static ArrayList<Product> getProduct(Category category) {
        return getAllProcessors();
    }
}
