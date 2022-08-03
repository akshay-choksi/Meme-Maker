
import java.util.*;

public class Feed {
	private ArrayList<Meme> memes;

	public Feed() {
		this.memes = new ArrayList<Meme>();
	}
	
	public ArrayList<Meme> getMemes() {
		return memes;
	}
	
	public void setMemes(ArrayList<Meme> memelist) {
		this.memes = memelist;
	}
	
	/**
	 * Returns a Meme from the current Feed that the User hasn't seen and didn't create
	 * @param user - User object
	 * @return Meme 
	 */
	public Meme getNewMeme(User user) {
		ArrayList<Meme> memeMain = getMemes();
		ArrayList<Meme> memesViewed = user.getMemesViewed();
		ArrayList<Meme> memesCreated = user.getMemesCreated();
		
		for(Meme ele:memeMain) {
			if(!memesViewed.contains(ele) && (!memesCreated.contains(ele))){
				return ele;			
	}
	}
		return null;
	}
@Override
	public String toString() {
	
	String feedTotal = "";
		for (Meme ele : memes) {
			feedTotal += ele.toString() + "\n"; 
		}
		return feedTotal;
}
	
}