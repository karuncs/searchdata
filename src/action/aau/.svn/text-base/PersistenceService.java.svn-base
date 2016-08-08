
package aau;

import java.util.List;




/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface PersistenceService {
	
	/**
	 * @param firstResource
	 * @param secondResource
	 * @return
	 */
	public Statistic getStatisticByResources(Resource firstResource, Resource secondResource);

	/**
	 * @param kiWiResource
	 */
	public void saveResource(Resource kiWiResource);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceByDesc(Resource kiWiResource);
	
	/**
	 * @param title
	 * @return
	 */
	public Resource getResourceByTitle(String title);

	/**
	 * @param title
	 * @return
	 */
	public List<Resource> getResourceByFlexibleTitle(String title);	

	public Resource getResourceById(Long id);
	
	public Tag getTagById(Long id);
	
	public List<Tag> getTagsById(Long id);
	
	public RelevantItem getRelevantItemById(Long id);

	public List<Tag> getAllTags();
	
	public List<Tag>getAllNeighbours();
	
	
	public List<Tag> getNeighboursByTag();	
	
	public Resource getResourceByIdAndTag(Long id, String queryTag);
	
	public Resource getResourceByIdAndFlexibleTitle(Long id, String queryTag);
	
	public List<RelevantItem> getAllRelevantItems();
	
	public RelevantItem getRelevantItemByQuery(String query);
	
	public List<Tag> getTagsByName(String tagName);
	
	public List<User> getAllUsers();
	
	public User getUserById(Long id);

	public Tag getTagByAuthorAndName(User authorTag, String tagName);
	
	public List<Tag> getTagsByUser(User authorTag);
	
	public List<Resource> getRelevantItemsByRatings(User user);
	
	public List<UserPreference> getUserPreferenceByTagAndUser(Tag tag, User user);
	
	public UserPreference getUserPreferenceByTagNameAndUser(String tagLabel, User user);

	public List<UserPreference> getUserPreferenceByUser(User user);
	
	/**
	 * @param user
	 * @return
	 */
	public List<Tag> getTagsFromUserPreferenceByUser(User user);
	
	/**
	 * @param tag
	 * @return
	 */
	public List<UserPreference> getUserPreferenceByTag(Tag tag);
	
	/**
	 * @param resource
	 * @return
	 */
	public List<Rate> getRateByResource(Resource resource);
	
	/**
	 * @param resource
	 * @return
	 */
	public double getAverageRatingByResource(Resource resource);
	
	/**
	 * @param resourceId
	 * @return
	 */
	public List<Tag> getTagsByResourceId(Long resourceId);

	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceByUser(User author);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Statistic> getKiWiStatistics();
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResources();
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResources(long begin, long end);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTags(long begin, long end);
	
	public Tag getTagWithoutNeighbour();
	
	public Tag getTagWithoutNeighbour(String startId, String endId);
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResourcesPrevious();

	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResourcesPreviousByUser(User user);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagPreviousByUser(User user);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagPrevious();
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public float getTagFrequency(String tagName);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Float getNeighbourFrequency(Tag tagRoot, String tagName);
	
	/**
	 * @param tagName
	 * @param user
	 * @return
	 */
	public float getTagFrequencyByUser(String tagName, User user);
	
	
	/**
	 * computing tag popularity by individual no of tags divided by total number of resources
	 * @param 
	 * @return
	 */
	public float getTagWeight(String tagName);
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagsById(long id);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Tag getTagById(long id);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<String> getDistinctTagNames();
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public User getUserByLogin(User user);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<User> getUserByName(String name);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public User getUserByUri(String uri);
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> listOddTags();
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<User> getUserFromPrevious();
	
	/**
	 * @param id
	 * @return
	 */
	public IndexTerm getIndexTermById(Long id);
	
	/**
	 * @return
	 */
	public List<IndexTerm> getIndexAllTerms();
	
	/**
	 * @param id
	 * @return
	 */
	public EvaluationUser getEvaluationUserById(Long id);
	
	/**
	 * @param user
	 * @param query
	 * @return
	 */
	public List<Resource> getRelevantItemsByRatingsAndTitle(User user, String query);
	
	/**
	 * @param id
	 * @return
	 */
	public EvaluationUser getEvaluationUserByUserId(Long id);
	
	/**
	 * @param ratingValue
	 * @param user
	 * @return
	 */
	public List<Tag> getTagLabelsFromRatedResourcesAndUser(float ratingValue, User user);
	
	/**
	 * @param userId
	 * @return
	 */
	public Long getTagCountByUserId(long userId);
	/**
	 * @param userid
	 * @param ratings
	 * @return
	 */
	public Long getTagCountFromRatedItemsByUser(long userid, float ratings);

	/**
	 * @param tagName
	 * @return
	 */
	public List<Object> getTagNeighbours(String tagName);
	/**
	 * @param tagName
	 * @return
	 */
	public Long getTagCountByTagName(String tagName);	
	
	/**
	 * @param tagName
	 * @param resource
	 * @param amountOfTags
	 * @return
	 */
	public float getTagFrequencyByResource(String tagName, Resource resource, Float amountOfTags);
	
	
	public List<Resource> getResourcesBySize(int maxSize);

}
