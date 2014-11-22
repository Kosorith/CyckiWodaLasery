package dataModel;

import java.awt.Image;
import java.util.List;

public class Challenge {
	private int id;
	private String desciption;
	private Image photo;
	private List<KHint> hints;
	private int point;
	private String name;
	
	public Challenge(int id, String desciption, Image photo, List<KHint> hints, int point, String name) {
		this.id = id;
		this.desciption = desciption;
		this.photo = photo;
		this.hints = hints;
		this.point = point;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public List<KHint> getHints() {
		return hints;
	}
	public void setHints(List<KHint> hints) {
		this.hints = hints;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
