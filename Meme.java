import java.util.*;
public class Meme implements Comparable<Meme> {
	private User creator;
	private BackgroundImage backgroundImage;
	private Rating[] ratings; 
	private String caption, captionVerticalAlign;
	private boolean shared;
	
	public Meme() {
		this.creator = new User();
		this.backgroundImage  = new BackgroundImage();
		this.ratings = new Rating[10];
		this.caption = "";
		this.captionVerticalAlign = "bottom";
		this.shared = false;
	}
	
	public Meme(BackgroundImage image, String caption, User creator) {
		this.ratings = new Rating[10] ;
		this.captionVerticalAlign = "bottom";
		this.backgroundImage = image;
		this.caption = caption;
		this.creator = creator;			
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BackgroundImage image) {
		this.backgroundImage = image;
	}

	public Rating[] getRatings() {
		return ratings;
	}

	public void setRatings(Rating[] rating) {
		this.ratings = rating;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}

	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		if (captionVerticalAlign.equals("top") ||captionVerticalAlign.equals("middle") ||captionVerticalAlign.equals("bottom")) {
			this.captionVerticalAlign = captionVerticalAlign;
			return true;
		}
			return false;
		}

	public boolean getShared() {
		return shared;
	}

	public void setShared(boolean value) {
		this.shared = value;
	}
	

	public boolean addRating(Rating rating) {
		if(ratings[9] == null) {
			for(int i = 0; i<10;i++) {
				if(ratings[i] == null) {
					ratings[i] = rating;
					return true;
				}
			}
			} else {
				for (int a=0 ; a<ratings.length-1 ; a++) {
					ratings[a] = ratings[a+1];
				ratings[ratings.length-1] = rating;
				}
				return true;

			}
		return false;
	}
	private int getPositiveRatings() {
		int total = 0;
		for (int a=0 ; a<ratings.length ; a++) {
			if (ratings[a] != null) {
				if (ratings[a].getScore() > 0) total++;
			}
		}
		return total;
	}
	private int getNegativeRatings() {
		int total = 0;
		for (int a=0 ; a<ratings.length ; a++) {
			if (ratings[a] != null) {
				if (ratings[a].getScore() < 0) total++;
			}
		}
		return total;
	}
		

	public double calculateOverallRating() {
		double sum = 0.0;
		for (int a=0 ; a<ratings.length ; a++) {
			if (ratings[a] != null) {
				sum += ratings[a].getScore();
			}
			if (ratings[a] == null) {
				sum += 0.0;
			}
		}
		return sum;
	}


	@Override
	public String toString() {
		return backgroundImage.toString() + " '" + caption + "' " + 
				calculateOverallRating() + " [+1: " + getPositiveRatings() + ", -1: " + 
				getNegativeRatings() + "]" + " - created by " + getCreator().getUserName();
	}
	

	@Override
	public boolean equals(Object object){
		if( object instanceof Meme) {
			if (((Meme)object).getCreator().equals(creator) && ((Meme)object).getCaption().equals(caption) && ((Meme)object).getBackgroundImage().equals(backgroundImage)) { 
				return true;
		}
		return false;
	}
		return false;
}
	/**
	 * Uses compareTo override to compare two Meme objects; order : caption --> bg image --> rating --> shared 
	 */
	@Override
	public int compareTo(Meme o) {
		// TODO Auto-generated method stub
		int retVal = this.caption.compareTo(o.caption);
		if(retVal != 0 ) return retVal;
		retVal = this.backgroundImage.compareTo(o.backgroundImage);
		if(retVal != 0 ) return retVal;
		double retValDouble = o.calculateOverallRating() - this.calculateOverallRating();
		if (retValDouble != 0.0) {
			if (retValDouble > 0.0 ) {
				return 1;
			}
			if(retValDouble<0.0) {
				return -1;
			}
		}
		if(this.shared == true && o.shared == false) {
			return -1;
		}
		if(this.shared == false && o.shared == true) {
				return 1;
		}
			
		return 0;
		}
	

	
	public static void main(String[] args) {
		
		//testing BackgroundImage Constructor
		BackgroundImage bgimage1 = new BackgroundImage("Joker", "How bots laugh", "Image of Joquain Phoenix in his role as Joker, laughing maniacally" );
		BackgroundImage bgimage2 = new BackgroundImage("fish", "How fish swim", "Image of Fish" );
		BackgroundImage bgimage3 = new BackgroundImage("fish", "How fish swim", "Image of Fish" );
/*
		//toString
		System.out.println(bgimage1.toString());
		System.out.println(bgimage2.toString());
		//equals
		System.out.println(bgimage1.equals(bgimage2));
		System.out.println(bgimage2.equals(bgimage3));
		
		
	
		//testing Rating Constructor
		User ak = new User();
		User Choksi = new User();
		User shay = new User();
		ak.setUserName("ak");
		Choksi.setUserName("Choksi");
		shay.setUserName("ak");
		//testing Ratings Constructor, toString
		Rating[] ratings = new Rating[10];
		for(int x = 0; x < ratings.length; x++) {
			ratings[x] = new Rating(ak, (int)(Math.random()*4-2));
			System.out.println(ratings[x].toString());
		}
		Rating[] ratings1 = new Rating[10];
		for(int x = 0; x < ratings1.length; x++) {
			ratings1[x] = new Rating(ak, (int)(Math.random()*4-2));
			System.out.println(ratings1[x].toString());
		}
		
		//equals test
		System.out.println(ratings.equals(ratings1));
			
		//Meme Constructor
		Meme meme1 = new Meme(bgimage1, "fish", ak);
		Meme meme2 = new Meme(bgimage2, "animals", Choksi);
		Meme meme3 = new Meme(bgimage1, "fish", ak);
		//Meme toString()
		System.out.println(meme1.toString());
		System.out.println(meme2.toString());
		//Meme equals()
		System.out.println(meme1.equals(meme2));
		System.out.println(meme1.equals(meme3));
		
		//calculateOverallRating
		System.out.println(meme1.calculateOverallRating());
		System.out.println(meme2.calculateOverallRating());
		
		//addRating
		System.out.println(meme1.addRating(new Rating(ak, 1)));
		System.out.println(meme2.addRating(new Rating(Choksi, -1)));
		
		//captionVerticalAlign
		System.out.println(meme1.setCaptionVerticalAlign("top"));
		System.out.println(meme1.setCaptionVerticalAlign("corner"));
		
		//setScore
//		System.out.println(ratings1.setScore(1));
//		System.out.println(ratings.setScore(-1));
		*/
		/*
		 * 

		*/
		// User Constructor
		User Akshay = new User("akshayc123");
//		System.out.println(Akshay.getUserName());
		User John = new User("doglover");
//		System.out.println(John.getUserName());
		
		//User, Feed, Meme, Rating toString() methods
		Meme meme1 = new Meme(bgimage1, "fish", Akshay);
		Meme meme2 = new Meme(bgimage2, "animals", John);
		
		Feed feed1 = new Feed();
		Feed feed2 = new Feed();
		
		ArrayList<Meme> memelist1 = new ArrayList<Meme>();
		memelist1.add(meme1);
		memelist1.add(meme2);
		Akshay.setMemesCreated(memelist1);
		feed1.setMemes(memelist1);
		
//		System.out.println(feed1.toString());
//		System.out.println(feed2.toString());
//		
//		System.out.println(meme1.toString());
//		System.out.println(meme2.toString());
//		
//		System.out.println(Akshay.toString());
//		System.out.println(John.toString());
//		
		
		//Ratings
		Rating[] ratings = new Rating[10];
		for(int x = 0; x < ratings.length; x++) {
			ratings[x] = new Rating(Akshay, (int)(Math.random()*4-2));
//			System.out.println(ratings[x].toString());
		}
			
		Rating[] ratings1 = new Rating[10];
			for(int x = 0; x < ratings1.length; x++) {
				ratings1[x] = new Rating(John, (int)(Math.random()*4-2));
//				System.out.println(ratings1[x].toString());
			}	
		// User equals() method
		User Jake = new User("doglover");
//		System.out.println(Akshay.equals(John));	
//		System.out.println(John.equals(Jake));
			
		// Feed getNewMeme()
//		System.out.println(feed1.getNewMeme(Akshay));
//		System.out.println(feed1.getNewMeme(John));
//		
		// User rateMeme()
		John.rateMeme(meme2, 1);
//		System.out.println(meme2.toString());
//		System.out.println(John.getMemesViewed());
		Akshay.rateMeme(meme1,0);
//		System.out.println(meme1.toString());
//		System.out.println(Akshay.getMemesViewed());
	
		
		
		// User rateNextMemeFromFeed()
//		System.out.println(Jake.rateNextMemeFromFeed(feed1, -1)); // output should be true (confirmed)
//		System.out.println(Akshay.rateNextMemeFromFeed(feed1, 0)); // output should be false (confirmed)
		
		//	 User createMeme()
		John.createMeme(bgimage3, "the right output");
//		System.out.println(John.getMemesCreated());
		BackgroundImage bgimage4 = new BackgroundImage("Testers Practice", "Akshay likes using testers", "Fun");
		Akshay.createMeme(bgimage4, "testing");
//		System.out.println(Akshay.getMemesCreated());
		
		 //User deleteMeme()
		Meme meme4 = new Meme();
//		System.out.println(Akshay.deleteMeme(meme1)); // should output true (confirmed)
//		System.out.println(Akshay.deleteMeme(meme4)); // output should be false since its not created by Akshay (confirmed)
		
		// User shareMeme()
		John.shareMeme(meme4,feed1);
//		System.out.println(meme4.shared); // should output true 
//		System.out.println(feed1.toString()); // should print the feed with the added Meme (meme4 is empty but still prints)
		Meme meme3 = new Meme();
//		System.out.println(meme3.shared); //should output false (default value is unshared = false for this test) 
		
		// User calculateReputation();
//		System.out.println(Akshay.calculateReputation()); //output a nonzero number
//		System.out.println(John.calculateReputation());	 //ouput 0.0
		
		// BgImage, Meme, User compareTo methods
		System.out.println(bgimage1.compareTo(bgimage2)); // Should output a negative number - confirmed
		System.out.println(bgimage2.compareTo(bgimage1)); //Should be positive number - confirmed 
		System.out.println(bgimage1.compareTo(bgimage1)); // Should be 0 - confirmed 
		
		System.out.println(meme1.compareTo(meme2)); //Should output a positive number - confirmed 
		System.out.println(meme2.compareTo(meme1));  //Should output a negative number - confirmed
		System.out.println(meme1.compareTo(meme1)); // Should output 0 - confirmed 
		
		System.out.println(Akshay.compareTo(John)); //Should output a negative number - confirmed
		System.out.println(Jake.compareTo(John));	//Should output a positive number - confirmed 
		System.out.println(Akshay.compareTo(Akshay)); // Should output 0 - confirmed 
		
		//CompareMemeByRating and CompareMemeByCreator compare methods
		
		CompareMemeByRating comparison1 = new CompareMemeByRating();
		System.out.println(comparison1.compare(meme1, meme2)); // output should be positive - confirmed
		System.out.println(comparison1.compare(meme2, meme2)); // ouput should be 0 - confirmed 
		System.out.println(comparison1.compare(meme2, meme1)); //output shoudl be negative - confirmed
		
		CompareMemeByCreator comparison2 = new CompareMemeByCreator();
		System.out.println(comparison2.compare(meme1, meme2)); // output should be negative - confirmed
		System.out.println(comparison2.compare(meme2, meme2)); // ouput should be 0 - confirmed 
		System.out.println(comparison2.compare(meme2, meme1)); //output shoudl be positive - confirmed
	
		//OrderableFeed's Sort Methods
		OrderableFeed feedOrdered1 = new OrderableFeed();
		feedOrdered1.sortByCaption();
		System.out.println(feedOrdered1);
		
		OrderableFeed feedOrdered2 = new OrderableFeed();
		feedOrdered2.sortByRating();
		System.out.println(feedOrdered2);
		
		OrderableFeed feedOrdered3 = new OrderableFeed();
		feedOrdered3.sortByCreator();
		System.out.println(feedOrdered3);
		
		
//		//OrderableFeed getNewMeme Testers	
		Akshay.shareMeme(meme1, feedOrdered1);
		Akshay.shareMeme(meme2, feedOrdered1);
		System.out.println(feedOrdered1.getNewMeme(John).toString());
		
		John.shareMeme(meme3, feedOrdered2);
		Akshay.shareMeme(meme1, feedOrdered2);
		System.out.println(feedOrdered2.getNewMeme(John).toString());
//		
		
	
		
		
	}
	
	
	}



