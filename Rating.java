import java.util.*;
public class Rating {
	private int score;
	private User user;

	public Rating() {
		score = 0;
		user = new User();
	}
	
	public Rating(User user, int score) {
		this.user = user;
		this.score = score;
	}
	public int getScore() {
		return score;
	}

	public boolean setScore(int score) {
		if (score == -1||score == 1 ||score == 0) {
			this.score = score;
			return true;
		}
			return false;
		}

	public User getUser() {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		String type_rating = "";
		
		if (this.getScore() == 1) {
			type_rating = "an upvote";
		}
		
		if (this.getScore() == 0) {
			type_rating = "a pass";
		}
		
		if (this.getScore() == -1) {
			type_rating = "a downvote";
		}
		
		return getUser().getUserName() + " rated as " + type_rating;
	}
	@Override
	public boolean equals(Object object) {
		if(object instanceof Rating) {
			if((((Rating) object).getScore() == score) && ((Rating) object).getUser().equals(user)){
				return true;
			}
		}
		if(object == null) {
			return false;
		
		}
		else {
			return false;
		
	}
	}
}
