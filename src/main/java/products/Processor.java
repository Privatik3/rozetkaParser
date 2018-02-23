package products;

import java.util.ArrayList;
import java.util.HashMap;

public class Processor extends Product {

    public Processor(Category type, String title, String description, int price, ArrayList<String> images, HashMap<String, String> attributes, ArrayList<Comment> comments) {
        super(type, title, description, price, images, attributes, comments);
    }
}
