import java.util.*;
import java.util.ArrayList;

public class User implements Comparable<User> {
	private String userName;
	private ArrayList<Meme> memesCreated;
	private TreeSet<Meme> memesViewed;

	public User() {
		this.userName = "";
		this.memesViewed = new TreeSet<Meme>();
		this.memesCreated  = new ArrayList<Meme>();
	}
	
	public User(String userName) {
		this.userName = userName;
		this.memesViewed = new TreeSet<Meme>();
		this.memesCreated  = new ArrayList<Meme>();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Meme> getMemesCreated() {
		return memesCreated;
	}

	public void setMemesCreated(ArrayList<Meme> memelist) {
		this.memesCreated = memelist;
	}

	public ArrayList<Meme> getMemesViewed() {
		ArrayList<Meme> memesViewedArrayList = new ArrayList<Meme>();
		memesViewedArrayList.addAll (memesViewed);
		return memesViewedArrayList;
	}

	public void setMemesViewed(ArrayList<Meme> memelist) {
		TreeSet<Meme> memesViewedTreeSet = new TreeSet<Meme>();
		memesViewedTreeSet.addAll (memesViewed);
		this.memesViewed = memesViewedTreeSet;
	}
/**
 * No return value, adds a rating (int input) to the meme input
 * @param meme - Meme (object)
 * @param rating - integer (primitive type) used to set a param (score) of Rating object
 */
	
	public void rateMeme(Meme meme, int rating) {
		ArrayList<Meme> memelist = getMemesViewed();
		memelist.add(meme);	
		setMemesViewed(memelist); 
		
		Rating rating1 = new Rating();
		rating1.setScore(rating);
		rating1.setUser(this);
		meme.addRating(rating1);
		
	}
	/**
	 * Uses a while loop to guarantee there the new meme isn't null 
	 * @param feed - contains an arraylist of memes
	 * @param ratingScore - uses this input to set a score for a rating (rateMeme method)
	 * @return boolean
	 */
	
	public boolean rateNextMemeFromFeed(Feed feed, int ratingScore) {
			
			while (feed.getNewMeme(this) != null) {
				rateMeme(feed.getNewMeme(this), ratingScore);
				return true;
			}
			
			return false;
		}
	/**
	 * Creates a new Meme object using the supplied args (and current user)
	 * @param bgimage - BackgroundImage object
	 * @param caption - a String
	 * @return Meme
	 */
	
	public Meme createMeme(BackgroundImage bgimage, String caption) {
		Meme meme = new Meme();
		meme.setBackgroundImage(bgimage);
		meme.setCaption(caption);
		meme.setCreator(this);
		ArrayList<Meme> memelist = getMemesCreated();
		memelist.add(meme);
		setMemesViewed(memelist);
		
		return meme;
	}
 /**
  * deletes this Meme if found in the memesCreated field for the current user, only if the shared field is false
  * @param meme - Supplies Meme object
  * @return boolean value
  */
	public boolean deleteMeme(Meme meme) {
		ArrayList<Meme> memelist = getMemesCreated();
		if(memelist.contains(meme) && (meme.getShared() == false)) {
			memelist.remove(meme);
			return true;
		}
		
		return false;
	}
/**
 * marks the Meme as shared and copies it to the ArrayList<meme>
 * @param meme - Meme object
 * @param feed - Feed object
 */

	public void shareMeme(Meme meme, Feed feed) {
		meme.setShared(true);
		ArrayList<Meme> memelist = feed.getMemes();
		memelist.add(meme);
	}

/**
 * calculated the average of all overall ratings for Memes created by this User
 * @return double
 */
	public double calculateReputation() { 
		double sum = 0.0;
		double numberOfRatings = 0.0;
		if ((getMemesCreated().size() != 0)||(getMemesViewed().size() != 0)) {
			for (Meme ele:getMemesCreated()) {		
				sum += ele.calculateOverallRating();
				numberOfRatings += 1;
		}
		return sum/numberOfRatings;
	} else {
		return sum;
	}
	}

	@Override
	public String toString() {
		String userName = getUserName();
		int numberOfElements = getMemesViewed().size();
		String i = String.valueOf(numberOfElements);
		double reputationValue = calculateReputation();
		double roundedReputationValue = Math.round(reputationValue * 10.0)/10.0;
		String d = String.valueOf(roundedReputationValue);
		
		
		return userName + " has rated (" + i + ") memes, " + "(" + d + ")" ;
	}

	@Override
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		if(object instanceof User) {
			User user1 = (User)object;
			return (this.userName.equals(user1.userName));
		}else {
			return false;
		}
	}
	/**
	 * Uses compareTo override to compare two User objects; order : username --> # of memes created
	 */
	@Override
	public int compareTo(User o) {
		int retVal = this.userName.compareTo(o.userName);
		if(retVal != 0) return retVal;
		retVal = o.memesCreated.size() - this.memesCreated.size();
		if(retVal != 0) return retVal;
		return 0;
	}
	
}



