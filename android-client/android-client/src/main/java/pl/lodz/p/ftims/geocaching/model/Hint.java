package pl.lodz.p.ftims.geocaching.model;

/**
 * Created by michal on 11/19/14.
 */
public class Hint {

    /**
     * odległości od lokalizacji skrytki staje się dostępna ta wskazówka
     */
    private int distance;
    private String description;
//    private Image photo;  // TODO: Trzeba wybrać jakąś opdpowiednią klasę

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

}

