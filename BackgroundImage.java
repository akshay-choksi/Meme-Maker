import java.awt.Point;
import java.util.*;



public class BackgroundImage implements Comparable<BackgroundImage>{
	private String imageFileName;
	private String title;
	private String description;

	public BackgroundImage() {
		imageFileName = "";
		title = "";
		description = "";
	}
	
	public BackgroundImage(String imageFileName, String title, String description) {
		this.imageFileName = imageFileName;
		this.title = title;
		this.description = description;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String name) {
		this.imageFileName = name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return title + " <" + description + ">";
	}
	
	@Override
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		if(object instanceof BackgroundImage) {
			BackgroundImage image2 = (BackgroundImage)object;
			return (this.imageFileName.equals(image2.imageFileName) && this.title.equals(image2.title) && this.description.equals(image2.description));
		}
		else {
			return false;
		}
	}
	/**
	 * Uses compareTo to compare two bgImage objects; order : imageFileName --> title --> description
	 */
	@Override
	public int compareTo(BackgroundImage o) {
		int retVal = this.imageFileName.compareTo(o.imageFileName);
		if (retVal!= 0) return retVal;
		retVal = this.title.compareTo(o.title);
		if(retVal!= 0) return retVal;
		retVal = this.description.compareTo(o.description);
		if (retVal!= 0) return retVal;
		return 0;
	}

	
}


