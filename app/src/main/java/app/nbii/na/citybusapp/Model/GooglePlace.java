package app.nbii.na.citybusapp.Model;

import java.io.Serializable;

/**
 * Created by Lameck on 23/07/2015.
 */
public class GooglePlace implements Serializable {

    private String name;
    private String category;
    private String rating;
    private String open;

    public GooglePlace() {
        this.name = "";
        this.rating = "";
        this.open = "";
        this.setCategory("");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setOpenNow(String open) {
        this.open = open;
    }

    public String getOpenNow() {
        return open;
    }

}
