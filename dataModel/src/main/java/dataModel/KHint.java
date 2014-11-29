package dataModel;

public class KHint {

    private String text;
    private byte[] photo;
    private int distance;

    public KHint(String text, byte[] photo, int distance) {
        this.text = text;
        this.photo = photo;
        this.distance = distance;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
