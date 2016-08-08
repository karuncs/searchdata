package aau;

public class Recommendation implements Comparable<Recommendation> {
	
	private Resource KiWiResource;
	
	private User user;

	private float recomendationScore;
	
	private Statistic kiWiStatistic;

	
	public Resource getKiWiResource() {
		return KiWiResource;
	}
	public void setKiWiResource(Resource kiWiResource) {
		KiWiResource = kiWiResource;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	public float getRecomendationScore() {
		return recomendationScore;
	}
	public void setRecomendationScore(float recomendationScore) {
		this.recomendationScore = recomendationScore;
	}
	

    public int compareTo(Recommendation recommendation) {
        if (this.recomendationScore == recommendation.getRecomendationScore())
            return 0;
        else if (this.recomendationScore < recommendation.getRecomendationScore())
            return 1;
        else
            return -1;
    }
	public Statistic getKiWiStatistic() {
		return kiWiStatistic;
	}
	public void setKiWiStatistic(Statistic kiWiStatistic) {
		this.kiWiStatistic = kiWiStatistic;
	}	
}
