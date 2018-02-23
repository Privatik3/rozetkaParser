package products;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Product {

    private Category type;
    private String title;
    private String description;
    private int price;

    private ArrayList<String> images = new ArrayList<String>();
    private HashMap<String, String> attributes = new HashMap<String, String>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    Product(Category type, String title, String description, int price, ArrayList<String> images, HashMap<String, String> attributes, ArrayList<Comment> comments) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.price = price;
        this.images = images;
        this.attributes = attributes;
        this.comments = comments;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public Category getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
