package pl.lodz.p.ftims.geocaching.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class Challenge {

    private ChallengeStub stub;
    private List<Hint> hints;
    private Bitmap photo;
    private int points;

    public Challenge() {
    }

    public ChallengeStub getStub() {
        return stub;
    }

    public void setStub(ChallengeStub stub) {
        this.stub = stub;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Zwraca wszystkie wskazówki znajdujące się między distA, a distB
     * Jest left-inclusive, right-exclusive, tj. [A, b)
     * Są one uszeregowane w kolejności od najdalszego do najbliższego.
     * @param distA Dolny kraniec przedziału odległości.
     * @param distB Górny kranie przedziału odległości.
     * @return Wskazówki w żądanym przedziale.
     */
    public List<Hint> hintsBetween(int distA, int distB) {
        if (distB < distA) {
            int t = distB;
            distB = distA;
            distA = t;
        }

        List<Hint> filtered = new ArrayList<Hint>();
        for (Hint hint : hints) {
            if (hint.getDistance() >= distA && hint.getDistance() < distB) {
                filtered.add(hint);
            }
        }

        Collections.sort(filtered, new Comparator<Hint>() {
            @Override
            public int compare(Hint lhs, Hint rhs) {
                return rhs.getDistance() - lhs.getDistance();
            }
        });

        return filtered;
    }

    public Hint maxDistanceHint() {
        return Collections.max(hints, new Comparator<Hint>() {
            @Override
            public int compare(Hint lhs, Hint rhs) {
                return lhs.getDistance() - rhs.getDistance();
            }
        });
    }
}
