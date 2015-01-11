package pl.lodz.p.ftims.geocaching.model;

import android.graphics.Bitmap;

/**
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class Hint {

    /**
     * odległości od lokalizacji skrytki w jakiej staje się dostępna ta wskazówka
     */
    private int distance;
    private String description;
    private Bitmap photo;

    public Hint() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

}

