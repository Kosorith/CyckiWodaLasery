package dataModel;

import java.awt.Image;

public class KHint {
	private String text;
	private Image photo;
	private int distance;
	
	public KHint(String text, Image photo, int distance) {
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
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}
