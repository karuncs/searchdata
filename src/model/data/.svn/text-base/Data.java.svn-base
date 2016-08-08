package data;

import java.util.HashSet;
import java.util.Set;

import aau.Resource;
import aau.Tag;
import aau.User;



public class Data {

	
	public Set<Resource> getData() {

		User fred = new User("fred","fred@gmail.com");
		User karten = new User("karten","karten@gmail.com");
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

		
		
		
		Set<Tag> tags1 = new HashSet<Tag>();
		tags1.add(tag1);		
		tags1.add(tag2);
		tags1.add(tag3);
		tags1.add(tag11);
		tags1.add(tag12);
		
		
		Set<Tag> tags2 = new HashSet<Tag>();
		tags2.add(tag4);
		tags2.add(tag5);
		tags2.add(tag6);
		tags2.add(tag7);		
		
		
		Set<Tag> tags3 = new HashSet<Tag>();
		tags3.add(tag7);
		tags3.add(tag8);
		tags3.add(tag9);
		tags3.add(tag10);
		tags1.add(tag13);
		tags1.add(tag14);		
		
		
		Resource kiWiResource1 = new Resource("1111",tags1,fred);
		kiWiResource1.setUri("www.java.com.br");
		kiWiResource1.setLink("www.java.com.br");
		Resource kiWiResource2 = new Resource("2222",tags2,karten);
		kiWiResource2.setUri("www.cnn.com");
		kiWiResource2.setLink("www.cnn.com");
		Resource kiWiResource3 = new Resource("3333",tags3,peter);
		kiWiResource3.setUri("www.uol.com");
		kiWiResource3.setLink("www.uol.com");
		
		
		
		Set<Resource> kiwiAllResources = new HashSet<Resource>();
		
		kiwiAllResources.add(kiWiResource1);
		kiwiAllResources.add(kiWiResource2);
		kiwiAllResources.add(kiWiResource3);
		
		return kiwiAllResources;
	}
}
