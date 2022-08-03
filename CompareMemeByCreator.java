import java.util.Comparator;

public class CompareMemeByCreator implements Comparator<Meme> {
	/**
	 * Uses comparator to compare two meme objects; order : creator --> rating --> caption --> bg image --> shared 
	 */
	@Override
	public int compare(Meme o1, Meme o2) {
		int retVal = o1.getCreator().compareTo(o2.getCreator());
		if(retVal != 0) return retVal;
		double retValDouble = o2.calculateOverallRating() - o1.calculateOverallRating();
		if (retValDouble != 0.0) {
			if (retValDouble > 0.0 ) {
				return 1;
			}
			if(retValDouble<0.0) {
				return -1;
			}
		}
		retVal = o1.getCaption().compareTo(o2.getCaption()) ;
		if(retVal != 0) return retVal;
		retVal = o1.getBackgroundImage().compareTo(o2.getBackgroundImage());
		if(retVal != 0) return retVal;
		
		if(o1.getShared() == true && o2.getShared() == false) {
			return -1;
		}
		if(o1.getShared() == false && o2.getShared() == true) {
				return 1;
		}
			
		
		return 0;
}

}
