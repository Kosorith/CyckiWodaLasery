package pl.lodz.p.ftims.geocaching.model;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public class Challenge {

    private int id;
    private String description;
//    private Image photo;  // TODO: Trzeba wybrać jakąś opdpowiednią klasę
    private String name;
    private List<Hint> hints;
//    private points;  // TODO: co to ma być?

    public Challenge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }
}
