import java.util.Comparator;

public class CompareMemeByRating implements Comparator<Meme> {
	/**
	 * Uses comparator to compare two meme objects; order : rating --> caption --> bg image --> creator 
	 */
	@Override
	public int compare(Meme o1, Meme o2) {
		double retValDouble = o2.calculateOverallRating() - o1.calculateOverallRating();
		if (retValDouble != 0.0) {
			if (retValDouble > 0.0 ) {
				return 1;
			}
			if(retValDouble<0.0) {
				return -1;
			}
		}
		int retVal = o1.getCaption().compareTo(o2.getCaption()) ;
		if(retVal != 0) return retVal;
		retVal = o1.getBackgroundImage().compareTo(o2.getBackgroundImage());
		if(retVal != 0) return retVal;
		retVal = o1.getCreator().compareTo(o2.getCreator());
		if(retVal != 0) return retVal;
	
	return 0;
}
}
