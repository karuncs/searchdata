package delicious;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import sesame.IConstant;

import aau.Resource;
import aau.Tag;
import aau.User;
import del.icio.us.Delicious;
import del.icio.us.beans.Post;

public class DeliciousData {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		DeliciousData.reportUsersBookmarking();
//		DeliciousData deliciousData = new DeliciousData();
//		deliciousData.getDeliciousBookmarkings();
		
		
		
//		Delicious del = new Delicious("freddurao", "qwert1710");
//
//		for (Iterator<Post> iterator = del.getPosts().iterator(); iterator.hasNext();) {
//			Post post = (Post) iterator.next();
//			System.out.println(" description  "+post.getDescription());
//			System.out.println(" href  "+post.getHref());
//			System.out.println(" tag  "+post.getTag());
//		}
//		
//		

		//		for (Iterator iterator = del.getBundles().iterator(); iterator.hasNext();) {
//			Bundle name = (Bundle) iterator.next();
//			System.out.println(" here  "+name.getName());
//		}
//
//		for (Iterator iterator = del.getPostForURL("http://delicious.com/tag/recipe").iterator(); iterator.hasNext();) {
//			Post name = (Post) iterator.next();
//			System.out.println(name.getDescription()+"----------------------"+name.getTag());
//		}
//		
//		System.out.println(del.getPostsForTag("food").size());
//		
//		for (Iterator iterator = del.getPostsForTag("food").iterator(); iterator.hasNext();) {
//			Post name = (Post) iterator.next();
//			System.out.println(name.getDescription()+"----------------------"+name.getTag());
//		}
//		
//		for (Iterator iterator = del.getRecentPosts().iterator(); iterator.hasNext();) {
//			Post name = (Post) iterator.next();
//			System.out.println(name.getDescription()+"----------------------"+name.getTag());
//		}		
//		
//		
//		for (Iterator iterator = del.getTags().iterator(); iterator.hasNext();) {
//			Tag name = (Tag) iterator.next();
//			System.out.println(name);
//			System.out.println(name.getCount());
//		}		
	}

	/**
	 * @return
	 */
	public Set<Resource> getDeliciousBookmarkings() {
		Set<Resource> kiwiAllResources = new HashSet<Resource>();	
			for (int i = 2000; i < 2045; i++) {
			//for (int i = 2017; i < 2019; i++) {
				String userName = "user_"+i;
				if (i!=2007 && i!=2041) {
					Delicious del = new Delicious(userName, "aau.tag.test");
			        int numOfPost = del.getPosts().size();
					if (numOfPost>0) {
						for (Iterator<Post> iterator = del.getPosts().iterator(); iterator.hasNext();) {
							Post post = (Post) iterator.next();
							Resource kiWiResource = new Resource();						
							kiWiResource.setDescription(post.getDescription());
							kiWiResource.setLink(post.getHref());
							User user = new User(userName,"aau@email");							
							kiWiResource.setTags(getPostTag(post,user));
							kiWiResource.setAuthor(user);
							kiwiAllResources.add(kiWiResource);						
						}					
				}
			}
		}
		return kiwiAllResources;
	}	
	int count = 0;
	/**
	 * @param post
	 * @return
	 */
	private Set<Tag> getPostTag(Post post,User user) {
		
		String[] tags = post.getTagsAsArray(" ");
		Set<Tag> colTags = new HashSet<Tag>();
		for (int i = 0; i <tags.length; i++) {
			String currentTag = tags[i];
			if (isValidTag(currentTag)) {
				Tag tag = new Tag();
				tag.setTagAuthor(user);
				tag.setTagName(tags[i]);
				colTags.add(tag);
				
			}
		}
		return colTags;
	}
	
	
	/**
	 * @param tagName
	 * @return
	 */
	public boolean isValidTag(String tagName){
		boolean valid = true;
		if ((tagName.equalsIgnoreCase(IConstant.INVALID_TAG_BOOKMARK))
				|| (tagName.equalsIgnoreCase(IConstant.INVALID_TAG_IMPORTED))
						|| (tagName.equalsIgnoreCase(IConstant.INVALID_TAG_BOOKMARK_MENU))) {
					valid = false;
		} 
		return valid;
	}

	/**
	 * reportUsersBookmarking
	 */
	public static void reportUsersBookmarking() {
		Set<String> userWithPost = new TreeSet<String>();
		Set<String> userWithoutPost = new TreeSet<String>();
	
		int totalBM = 0;
		for (int i = 2000; i < 2045; i++) {
			System.out.println("user_"+i);
			if (i!=2007 && i!=2041) {
				Delicious del = new Delicious("user_"+i, "aau.tag.test");
				//String url = "http://delicious.com/user_"+i;
				//System.out.println(url);
		        int numOfPost = del.getPosts().size();		        
				if (numOfPost>0) {
					//int numOfPost = del.getPostForURL(url).size();
					//userWithPost.add("user_"+i+" has posted "+numOfPost+" bookmarkings");
					userWithPost.add("user_"+i+" \t "+numOfPost);					
					totalBM = totalBM + numOfPost;
				}else {
					userWithoutPost.add("user_"+i+" has not posted yet");
				}
			}
		}
		
		System.out.println(" Total of Bookmarks "+totalBM);
		System.out.println("  ");		
		System.out.println(userWithPost.size()+" users have bookmarked so far");
		System.out.println("  ");
		System.out.println(userWithoutPost.size()+" users have not bookmarked yet");
		System.out.println("  ");
		System.out.println(" USER WITH POST ");
		for (String string : userWithPost) {
			System.out.println(string);
		}
		System.out.println("  ");		

		System.out.println(" USER WITHOU  POST ");
		System.out.println("  ");		
		for (String string : userWithoutPost) {
			System.out.println(string);
		}		
	}
}
