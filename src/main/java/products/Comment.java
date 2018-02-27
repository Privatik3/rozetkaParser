package products;

import java.util.Date;

public class Comment {

    private String name;
    private int rating; // От 0 до 5 ( это количество звезд )
    private Date date;
    private String message;

    public Comment(String name, int rating, Date date, String message) {
        this.name = name;
        this.rating = rating;
        this.date = date;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getDate() {
        return 1900 + date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
    }

    public String getMessage() {
        return message.replaceAll("\"", "”");
    }
}
