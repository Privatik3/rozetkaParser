import products.Category;
import products.Comment;
import products.Product;

import java.util.ArrayList;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        // Подставляй любую из доступных категорий что бы получить все категории этого товара
        ArrayList<Product> processors = parserHelper.getProduct(Category.PROCESSOR);
//        ArrayList<Product> motherBoards = parserHelper.getProduct(Category.MOTHERBOARD);

        // Для примера, вывод всей информации о процессорах
        System.out.println("Print processors list:");
        for (Product proc : processors) {
            System.out.println("\tType: " + proc.getType());
            System.out.println("\tTitle: " + proc.getTitle());
            System.out.println("\tDescription: " + proc.getDescription());

            System.out.println("\tPrint images list:");
            for (String img : proc.getImages()) {
                System.out.println("\t\tImage: " + img);
            }

            System.out.println("\tPrint attributes list:");
            for (Map.Entry<String, String> attribute : proc.getAttributes().entrySet()) {
                System.out.println("\t\t" + attribute.getKey() + ": " + attribute.getValue());
            }

            System.out.println("\tPrint comment list:");
            for (Comment comment : proc.getComments()) {
                System.out.println("\t\tNickname: " + comment.getName());
                System.out.println("\t\tRating: " + comment.getRating());
                System.out.println("\t\tDate: " + comment.getDate());
                System.out.println("\t\tMassage: " + comment.getMessage());
                System.out.println("\t\t===================================");
            }
            System.out.println("\t=======================================");
        }

    }
}
