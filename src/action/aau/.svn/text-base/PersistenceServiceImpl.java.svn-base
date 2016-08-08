package aau;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.NonUniqueObjectException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateless
@Name("persistenceService")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class PersistenceServiceImpl implements PersistenceService {

	@Logger
	private static Log log;

	@In
	FacesMessages facesMessages;
	
	// the entity manager used by this KiWi system
	@In(create=true)
	private EntityManager entityManager;

	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getStatisticByResources(aau.Resource, aau.Resource)
	 */
	public Statistic getStatisticByResources(Resource firstResource, Resource secondResource) {
		Statistic result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Statistic k where k.firstResource = :firstResource and k.secondResource = :secondResource");
		q.setParameter("firstResource",firstResource);
		q.setParameter("secondResource", secondResource);
		try {
			result = (Statistic)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}

	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#saveResource(aau.Resource)
	 */
	public void saveResource(Resource kiWiResource) {
		try {
			
			if (getResourceByDesc(kiWiResource)==null) {
				entityManager.persist(kiWiResource);
			}
		} catch (NonUniqueObjectException e) {
			
		}
	}
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceByDesc(Resource resource) {
		Resource result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.Resource k where k.description = :desc");
		q.setParameter("desc",resource.getDescription());
		try {
		result = (Resource)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}		
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceByTitle(String title) {
		Resource result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.Resource k where k.title = :title");
		q.setParameter("title",title);
		try {
		result = (Resource)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResourceByFlexibleTitle(String title) {
		List<Resource> result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.Resource k where k.title like :title");
		q.setParameter("title",'%' + title + '%');
		try {
		result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceById(Long id) {
		Resource result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.Resource k where k.id = :id");
		q.setParameter("id",id);
		try {
		result = (Resource)q.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public IndexTerm getIndexTermById(Long id) {
		IndexTerm result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.IndexTerm k where k.id = :id");
		q.setParameter("id",id);
		try {
		result = (IndexTerm)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}		
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Tag getTagById(Long id) {
		Tag result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.Tag k where k.id = :id");
		q.setParameter("id",id);
		try {
		result = (Tag)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	

	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagsById(Long id) {
		List<Tag> result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.Tag k where k.id = :id");
		q.setParameter("id",id);
		try {
		result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getRelevantItemById(java.lang.Long)
	 */
	public RelevantItem getRelevantItemById(Long id) {
		RelevantItem result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.RelevantItem k where k.id = :id");
		q.setParameter("id",id);
		try {
		result = (RelevantItem)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	

	/* (non-Javadoc)
	 * @see aau.PersistenceService#getUserById(java.lang.Long)
	 */
	public User getUserById(Long id) {
		User result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.User k where k.id = :id");
		q.setParameter("id",id);
		try {
		result = (User)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}		

	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceByIdAndTag(Long id, String queryTag) {
		Resource result;
		javax.persistence.Query q = entityManager
				.createQuery("select k from aau.Resource k left outer join k.tags as tag where k.id = :id and tag.tagName like :queryTag");

		//getHibernateTemplate().findByNamedParam(query, "name", '%' + str + '%');
	
		q.setParameter("id",id);
		q.setParameter("queryTag",'%' + queryTag + '%');
		try {
		result = (Resource)q.getSingleResult();
		} catch (NoResultException ex) {
			//System.out.println("Resource "+id +" query "+queryTag);
			result = null;
		}
		return result;
	}	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceByIdAndFlexibleTitle(Long id, String queryTag) {
		Resource result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Resource k where k.id =:id and k.title like :title");
		q.setParameter("id",id);
		q.setParameter("title",'%' + queryTag + '%');
		try {
		result = (Resource)q.getSingleResult();
		} catch (NoResultException ex) {
			//System.out.println("Resource "+id +" query "+queryTag);
			result = null;
		}
		return result;
	}	

	/* (non-Javadoc)
	 * @see aau.PersistenceService#getRateByResource(aau.Resource)
	 */
	public List<Rate> getRateByResource(Resource resource) {
		List<Rate> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Rate t where t.resource =:resource");
		
		q.setParameter("resource",resource);
		try {
			result = (List<Rate>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}
	
	
	
	public List<Tag> getTagLabelsFromRatedResourcesAndUser(float ratingValue, User user) {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select s.tags from aau.Rate r, aau.Resource s" +
				" where s.id = r.resource.id and r.value > :ratingValue and r.rateAuthor =:user");
		q.setParameter("ratingValue",ratingValue);
		q.setParameter("user",user);
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	
	
	/**
	 * @param resource
	 * @return
	 */
	public double getAverageRatingByResource(Resource resource) {
		Double averageRating = 0d;
		javax.persistence.Query q = entityManager.createQuery("select avg(t.value) from aau.Rate t where t.resource =:resource");
		q.setParameter("resource",resource);
		try {
			averageRating = (Double)q.getSingleResult();
		} catch (NoResultException ex) {
			averageRating = 0d;
		}
		return averageRating;
	}
	
	
	/**
	 * @param userid
	 * @param ratings
	 * @return
	 */
	public Long getTagCountFromRatedItemsByUser(long userid, float ratings) {
		Long averageRating;
		javax.persistence.Query q = 
			entityManager.createQuery("select count(t) from aau.Tag t, aau.Rate r where t.resource = r.resource  and  r.rateAuthor.id=:userid and r.value > :ratings");
		q.setParameter("userid",userid);
		q.setParameter("ratings",ratings);
		try {
			averageRating = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			averageRating = null;
		}
		return averageRating;
	}	
	
	
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getAllTags()
	 */
	public List<User> getAllUsers() {
		List<User> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.User t");
		try {
			result = (List<User>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
		
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getAllRelevantItems()
	 */
	public List<RelevantItem> getAllRelevantItems() {
		List<RelevantItem> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.RelevantItem t");
		try {
			result = (List<RelevantItem>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getRelevantItemByQuery(java.lang.String)
	 */
	public RelevantItem getRelevantItemByQuery(String query) {
		RelevantItem result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.RelevantItem k where k.query =:query");
		q.setParameter("query",query);
		try {
		result = (RelevantItem)q.getSingleResult();
		} catch (NoResultException ex) {
			//System.out.println("Resource "+id +" query "+queryTag);
			result = null;
		}
		return result;
	}	
	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagsByName(String tagName) {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Tag t where t.tagName = :tagParameter");
		q.setParameter("tagParameter", tagName);		
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}
	
	public List<Object> getTagNeighbours(String tagName) {
		List<Object> result = new ArrayList<Object>(); 
		javax.persistence.Query q = entityManager.createNativeQuery("select t1.tagname, (t1.tagfrequency * t1.weight) as rank" +
				" from tag t1, tag t2 " +
				" where t1.tagroot_id = t2.id " +
				"	and t2.tagname = :tagName " +
				"	and t1.tagfrequency is not null " +
				" order by rank DESC");
		q.setParameter("tagName", tagName);		
		try {
			result = (List<Object>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList();
		}
		return result;
	}	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Tag getTagByAuthorAndName(User authorTag, String tagName) {
		Tag result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Tag t where t.tagName = :tagParameter and t.tagAuthor = :tagAuthor ");
		q.setParameter("tagParameter", tagName);
		q.setParameter("tagAuthor", authorTag);
		
		try {
			result = (Tag)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagsByUser(User authorTag) {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Tag t where t.tagAuthor = :tagAuthor ");
		q.setParameter("tagAuthor", authorTag);
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList<Tag>();
		}
		return result;
	}	

	
	
	/**
	 * @param userId
	 * @return
	 */
	public Long getTagCountByUserId(long userId) {
		Long amountOfTags;
		javax.persistence.Query q = entityManager.createQuery("select count(t) from aau.Tag t where t.tagAuthor.id =:userId");
		q.setParameter("userId", userId);
		try {
			amountOfTags = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfTags = null;
		}
		return amountOfTags;
	}
	
	
	
	/**
	 * @param userId
	 * @return
	 */
	public Long getTagCountByTagName(String tagName) {
		Long amountOfTags;
		javax.persistence.Query q = entityManager.createQuery("select count(t) from aau.Tag t where t.tagName =:tagName");
		q.setParameter("tagName", tagName);
		try {
			amountOfTags = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfTags = null;
		}
		return amountOfTags;
	}	
	
	
	/**
	 * @param tagName
	 * @param Resource
	 * @param amountOfTags
	 * @return
	 */
	public float getTagFrequencyByResource(String tagName, Resource resource, Float amountOfTags) {
		Long amountOfIndividualTag;
		Float tagFrequency = 0f;
		String s2 = "select count (t) from aau.Tag t " +
		   "where t.tagName =:tagName and t.resource =:resource";
		javax.persistence.Query q = entityManager.createQuery(s2);		
		q = entityManager.createQuery(s2);
		q.setParameter("tagName", tagName);
		q.setParameter("resource", resource);
		try {
			amountOfIndividualTag = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfIndividualTag = 0l;
		} 
		
	    if ( Double.isNaN(amountOfIndividualTag)|| Double.isNaN(amountOfIndividualTag) || Double.isInfinite(amountOfIndividualTag)|| Double.isInfinite(amountOfIndividualTag)){
	    	tagFrequency = 0f;
	    }else{
	    	tagFrequency = amountOfIndividualTag.floatValue() / amountOfTags.floatValue(); 
	    	if (tagFrequency.equals(Float.NaN) ){
		    	tagFrequency = 0f;
	    	  }
	    }
		return tagFrequency.floatValue();
	}	
	
	
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getRelevantItemsByRatings(aau.User)
	 */
	public List<Resource> getRelevantItemsByRatings(User user) {
		List<Resource> result; 
		javax.persistence.Query q = entityManager.createQuery("select t.resource from aau.Rate t where t.rateAuthor = :rateAuthor and value >=3");
		q.setParameter("rateAuthor", user);
		try {
			result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList<Resource>();
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getRelevantItemsByRatings(aau.User)
	 */
	public List<Resource> getRelevantItemsByRatingsAndTitle(User user, String query) {
		List<Resource> result; 
		javax.persistence.Query q = entityManager.createQuery("select t.resource from aau.Rate t where t.rateAuthor = :rateAuthor and value >=3 ant t.resource.title like :query");
		q.setParameter("rateAuthor", user);
		q.setParameter("query",'%' + query + '%');			
		try {
			result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList<Resource>();
		}
		return result;
	}	
	
	
	/**
	 * @param user
	 * @param tag
	 * @return
	 */
	public List<UserPreference> getUserPreferenceByTagAndUser(Tag tag,User user) {
		List<UserPreference> result; 
		javax.persistence.Query q = entityManager.createQuery("select distinct t from aau.UserPreference t where t.tag = :tag and t.user = :user");
		q.setParameter("user", user);
		q.setParameter("tag", tag);
		try {
			result = (List<UserPreference>)q.getResultList();			
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getUserPreferenceByTagNameAndUser(java.lang.String, aau.User)
	 */
	public UserPreference getUserPreferenceByTagNameAndUser(String tagName,User user) {
		UserPreference result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.UserPreference t where t.tag.tagName = :tagName and t.user = :user");
		q.setParameter("user", user);
		q.setParameter("tag", tagName);
		try {
			result = (UserPreference)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	

	/**
	 * @param tag
	 * @return
	 */
	public List<UserPreference> getUserPreferenceByTag(Tag tag) {
		List<UserPreference> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.UserPreference t where t.tag = :tag");
		q.setParameter("tag", tag);
		try {
			result = (List<UserPreference>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList<UserPreference>();
		}
		return result;
	}	
	
	/**
	 * @param user
	 * @return
	 */
	public List<UserPreference> getUserPreferenceByUser(User user) {
		List<UserPreference> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.UserPreference t where t.user = :user");
		q.setParameter("user", user);
		try {
			result = (List<UserPreference>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList<UserPreference>();
		}
		return result;
	}
	
	/**
	 * @param user
	 * @return
	 */
	public EvaluationUser getEvaluationUserById(Long id) {
		EvaluationUser result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.EvaluationUser t where t.id = :id");
		q.setParameter("id", id);
		try {
			result = (EvaluationUser)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param id
	 * @return
	 */
	public EvaluationUser getEvaluationUserByUserId(Long id) {
		EvaluationUser result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.EvaluationUser t where t.user.id = :id");
		q.setParameter("id", id);
		try {
			result = (EvaluationUser)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}		
	

	
	/**
	 * @param user
	 * @return
	 */
	public List<Tag> getTagsFromUserPreferenceByUser(User user) {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select t.tag from aau.UserPreference t where t.user = :user");
		q.setParameter("user", user);
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList<Tag>();
		}
		return result;
	}
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagsByResourceId(Long resourceId) {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Tag t where t.resource.id = :resourceId");
		q.setParameter("resourceId", resourceId);
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = new ArrayList<Tag>();
		}
		return result;
	}	
	
	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Resource getResourceByUser(User author) {
		Resource result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Resource k where k.author = :author");
		q.setParameter("author",author);
		try {
			result = (Resource)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Statistic> getKiWiStatistics() {
		List<Statistic> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.KiWiStatistic k order by k.comparasionValue");
		try {
			result = (List<Statistic>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResources() {
		List<Resource> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Resource k");
		try {
			result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}
	
	
	public List<Resource> getResourcesBySize(int maxSize) {
		List<Resource> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Resource k");
		q.setMaxResults(maxSize);
		try {
			result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResources(long begin, long end) {
		List<Resource> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Resource k where k.id >= :begin and k.id < :end");
		q.setParameter("begin", begin);
		q.setParameter("end", end);
		try {
			result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}		
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTags(long begin, long end) {
		List<Tag> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Tag k where k.id >= :begin and k.id < :end");
		q.setParameter("begin", begin);
		q.setParameter("end", end);
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	public Tag getTagWithoutNeighbour() {
		Tag result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Tag k where tagRoot is null and hasNeighbours is null order by k.id");
		q.setMaxResults(1); 
		try {
			result = (Tag)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	public Tag getTagWithoutNeighbour(String startId, String endId) {
		Tag result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Tag k where tagRoot is null and hasNeighbours is null and k.id >= " + startId + " and k.id <= " + endId);
		q.setMaxResults(1); 
		try {
			result = (Tag)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}		
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResourcesPrevious() {
		List<Resource> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Resource k where k.uri like '%rdfkiwi%'");
		try {
			result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		System.out.println("total previous "+result.size());
		return result;
	}

	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Resource> getResourcesPreviousByUser(User user) {
		List<Resource> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Resource k where k.uri like '%rdfkiwi%' and k.author = :user");
		q.setParameter("user", user);
		try {
			result = (List<Resource>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}

		return result;
	}

	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagPreviousByUser(User user) {
		List<Tag> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Tag k where k.uri like '%rdfkiwi%' and k.tagAuthor = :user");
		q.setParameter("user", user);
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagPrevious() {
		List<Tag> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Tag k where k.uri like '%rdfkiwi%'");
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public float getTagFrequency(String tagName) {
		Long amountOfTags;
		Long amountOfIndividualTag;
		Float tagFrequency;

		javax.persistence.Query q = entityManager.createQuery("select count(t) from aau.Tag t");
		try {
			amountOfTags = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfTags = null;
		}
				
		q = entityManager.createQuery("select count(t) from aau.Tag t where t.tagName = :tagParameter");
		q.setParameter("tagParameter", tagName);
		try {
			amountOfIndividualTag = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfIndividualTag = null;
		}
		
		tagFrequency = amountOfIndividualTag.floatValue() / amountOfTags.floatValue(); 
		
		return tagFrequency.floatValue();
	}
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	
	
	public Float getNeighbourFrequency(Tag tagRoot, String tagName) {
//		Long amountOfNeighbours;
//		Long amountOfIndividualNeighbours;
		Float neighbourFrequency;
		List<BigDecimal> values = null;
		
		//javax.persistence.Query q = entityManager.createQuery("select CAST((COUNT(element)) from aau.TagNeighbour element where element.element =:tagNeighbour");
		try {
			javax.persistence.Query q = entityManager
				.createNativeQuery("" +
						" SELECT cast(count(*) as numeric)/ " +
						"	(" +
						"		select count(*) " +
						"		from tag " +
						"		where tagroot_id = t.tagroot_id" +
						"	) " +
						" from tag t " +
						" where tagRoot_id = :tagRoot " +
						"	and tagname = :tagNeighbour " +
						" group by tagRoot_id, tagname ");
			
			q.setParameter("tagRoot", tagRoot.getId());
			q.setParameter("tagNeighbour", tagName);
			
						/*"" +
						"select cast(count(t1))/(select count(t1) from aau.Tag t1 where t1.tagRoot=:tagRoot) " +
																"from aau.Tag t2 " +
																"where t2.tagName = :tagNeighbour " +
																"	and t2.tagRoot = t1.tagRoot");*/

			values = q.getResultList();


		} catch (NoResultException ex) {
			ex.printStackTrace();
			neighbourFrequency = null;
		} catch (Exception exception) {
			exception.printStackTrace();
			neighbourFrequency = null;
		}
		
		float returnValue = values.get(0).floatValue();
		
		return returnValue;
			/*SELECT  tag_id,element,Count(element),CAST((COUNT(element))AS FLOAT)/
		(SELECT COUNT(tag_id) FROM tag_tagneighbours where tag_id=tn.tag_id)
		as Probabilty
		FROM tag_tagneighbours tn
		GROUP BY tag_id,element HAVING ( COUNT(element) >= 1 and tag_id>=1)
		ORDER BY TAG_ID
			 * */	
		//q = entityManager.createQuery("select count(tag_id) from aau.TagNeighbour tag_id where tag_id.neighbourName = :element and tag_id.element =:tagNeighbour");
//		q = entityManager.createQuery("(SELECT COUNT(tag_id) FROM aau.TagNeighbour where tag_id=tn.tag_id) FROM aau.TagNeighbour tn where GROUP BY tag_id,element HAVING ( COUNT(element) >= 1 and tag_id>=1)");
//		q.setParameter("element", tag_id);
//		q.setParameter("tagNeighbour", element);
//		try {
//			amountOfIndividualNeighbours = (Long)q.getSingleResult();
//		} catch (NoResultException ex) {
//			amountOfIndividualNeighbours = null;
//		}
//		
//		neighbourFrequency = amountOfIndividualNeighbours.floatValue() / amountOfNeighbours.floatValue(); 
		
		//return neighbourFrequency.floatValue();
	}
	
	/**
	 * @param tagName
	 * @param user
	 * @return
	 */
	public float getTagFrequencyByUser(String tagName, User user) {
		Long amountOfTags;
		Long amountOfIndividualTag;
		Float tagFrequency;

		javax.persistence.Query q = entityManager.createQuery("select count(t) from aau.Tag t where t.tagAuthor =:user");
		q.setParameter("user", user);
		try {
			amountOfTags = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfTags = null;
		}
				
		q = entityManager.createQuery("select count(t) from aau.Tag t where t.tagName = :tagParameter and t.tagAuthor =:user");
		q.setParameter("tagParameter", tagName);
		q.setParameter("user", user);
		try {
			amountOfIndividualTag = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfIndividualTag = null;
		}
		
		tagFrequency = amountOfIndividualTag.floatValue() / amountOfTags.floatValue(); 
		
		return tagFrequency.floatValue();
	}	
	
	
	/**
	 * computing tag popularity by individual no of tags divided by total number of resources
	 * @param 
	 * @return
	 */
	public float getTagWeight(String tagName) {
		Long amountOfResources;
		Long amountOfIndividualTag;
		Float weight;

		
		
		javax.persistence.Query q = entityManager.createQuery("select count(k) from aau.Resource k");
		try {
			amountOfResources = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfResources = null;
		}
				
		q = entityManager.createQuery("select count(t) from aau.Tag t where t.tagName = :tagParameter");
		q.setParameter("tagParameter", tagName);
		try {
			amountOfIndividualTag = (Long)q.getSingleResult();
		} catch (NoResultException ex) {
			amountOfIndividualTag = null;
		}
		
		weight = amountOfIndividualTag.floatValue() / amountOfResources.floatValue(); 
		
		return weight.floatValue();
	
	}

	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getTagsById(long id) {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Tag t where t.id = :id");
		q.setParameter("id", id);		
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public Tag getTagById(long id) {
		Tag result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Tag t where t.id = :id");
		q.setParameter("id", id);		
		try {
			result = (Tag)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> getAllTags() {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.Tag t");
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	public List<Tag> getAllNeighbours() {
		List<Tag> result; 
		javax.persistence.Query q = entityManager.createQuery("select n from aau.Tag n where n.tagRoot is not null and n.neighbourFrequency is null");
		// and n.id > 438153
		q.setMaxResults(1000);
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see aau.PersistenceService#getIndexAllTerms()
	 */
	public List<IndexTerm> getIndexAllTerms() {
		List<IndexTerm> result; 
		javax.persistence.Query q = entityManager.createQuery("select t from aau.IndexTerm t");
		try {
			result = (List<IndexTerm>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<String> getDistinctTagNames() {
		List<String> result; 
		javax.persistence.Query q = entityManager.createQuery("select distinct t.tagName from aau.Tag t");
		try {
			result = (List<String>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public User getUserByLogin(User user) {
		User result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.User k where k.name = :name");
		q.setParameter("name", user.getName());
		try {
			result = (User)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<User> getUserByName(String name) {
		List<User> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.User k where k.name = :name");
		q.setParameter("name", name);
		try {
			result = (List<User>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public User getUserByUri(String uri) {
		User result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.User k where k.uri = :uri");
		q.setParameter("uri", uri);
		try {
			result = (User)q.getSingleResult();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<Tag> listOddTags() {
		List<Tag> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.Tag k where k.tagName = '-'");
		try {
			result = (List<Tag>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<User> getUserFromPrevious() {
		List<User> result;
		javax.persistence.Query q = entityManager
		.createQuery("select k from aau.User k where k.name like '%user_2%' ");
		try {
			result = (List<User>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}
	
	/**
	 * @param kiWiResource
	 * @return
	 */
	public List<User> getUsersWithTagAndRatings() {
		//select count(u) from users u, rate r, tag t where u.id=r.id and r.value > 3 and t.tagauthor_id = u.id  46925
		//select count(u) from users u, rate r, tag t where u.id=r.id and r.value > 3
		List<User> result;
		javax.persistence.Query q = entityManager
		.createQuery("select u from aau.User u, aau.Rate r, aau.Tag t where u.id=r.id and r.value > 3 and t.tagAuthor.id = u.id");
		try {
			result = (List<User>)q.getResultList();
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}


	
	public List<Tag> getNeighboursByTag() {
		// TODO Auto-generated method stub
		return null;
	}		
	


}
