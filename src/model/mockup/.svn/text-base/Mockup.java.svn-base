package mockup;

import java.util.HashSet;
import java.util.Set;

import aau.Resource;
import aau.Tag;
import aau.User;

public class Mockup {
	
	public Set<Resource> loadTestData() {

		User fred = new User("fred","fred@gmail.com");
		
		User karten = new User("rong","rong@gmail.com");
		
		User peter = new User("peter","peter@gmail.com");
		
		Tag tag1 = new Tag("rules",fred);
		Tag tag2 = new Tag("code",fred);
		Tag tag3 = new Tag("java",fred);
		
		
		Tag tag4 = new Tag("wiki",karten);
		Tag tag5 = new Tag("semantic",karten);
		Tag tag6 = new Tag("web",karten);
		
		
		Tag tag7 = new Tag("semantic",peter);
		Tag tag8 = new Tag("java",peter);
		Tag tag9 = new Tag("cripto",peter);
		Tag tag10 = new Tag("web",peter);
		
		
		Tag tag11 = new Tag("programming",peter);
		Tag tag12 = new Tag("java",peter);
		Tag tag13 = new Tag("code",peter);
		Tag tag14 = new Tag("design",peter);

		
		
		
		Set<Tag> tagSet1 = new HashSet<Tag>();
		tagSet1.add(tag1);		
		tagSet1.add(tag2);
		tagSet1.add(tag3);
		tagSet1.add(tag11);
		tagSet1.add(tag12);
		
		
		Set<Tag> tagsSet2 = new HashSet<Tag>();
		tagsSet2.add(tag4);
		tagsSet2.add(tag5);
		tagsSet2.add(tag6);
		tagsSet2.add(tag7);		
		
		
		Set<Tag> tagsSet3 = new HashSet<Tag>();
		tagsSet3.add(tag8);
		tagsSet3.add(tag9);
		tagsSet3.add(tag10);
		tagSet1.add(tag13);
		tagSet1.add(tag14);		
		
		
		Resource kiWiResource1 = new Resource("java",tagSet1,fred);

		kiWiResource1.setUri("www.java.com");
		kiWiResource1.setLink("www.java.com");
		kiWiResource1.setTitle("java");
		
		
		Resource kiWiResource2 = new Resource("cnn",tagsSet2,karten);
		kiWiResource2.setUri("www.cnn.com");
		kiWiResource2.setLink("www.cnn.com");
		kiWiResource2.setTitle("cnn");
		
		
		Resource kiWiResource3 = new Resource("iwis",tagsSet3,peter);
		
		kiWiResource3.setUri("http://iwis.cs.aau.dk/");
		kiWiResource3.setLink("http://iwis.cs.aau.dk/");
		kiWiResource3.setTitle("iwis");
		
		
		
		Set<Resource> kiwiAllResources = new HashSet<Resource>();
		
		kiwiAllResources.add(kiWiResource1);
		kiwiAllResources.add(kiWiResource2);
		kiwiAllResources.add(kiWiResource3);
		
		return kiwiAllResources;
	}
	

}
