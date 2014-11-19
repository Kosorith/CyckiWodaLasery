package pl.lodz.p.ftims.geocaching.model;

/**
 * Created by michal on 11/19/14.
 */
public class Hint {

    private int position;
    private String description;
//    private Image photo;  // TODO: Trzeba wybrać jakąś opdpowiednią klasę

    public Hint() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

