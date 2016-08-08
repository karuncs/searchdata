/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright (c) 2008-2009, The KiWi Project (http://www.kiwi-project.eu)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * - Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 * - Neither the name of the KiWi Project nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Contributor(s):
 * 
 * 
 */


package aau;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lucene.LuceneManagerService;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import constant.IConstant;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

/**
 * @author Fred Durao
 *
 */
@Stateless
@Scope(ScopeType.STATELESS)
@AutoCreate
@Name("clusterService")
public class ClusterServiceImpl implements ClusterService {
	
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@In
	private PersistenceService persistenceService;
	
	@In(create=true)
	private LuceneManagerService luceneManagerService;	
	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Resource getPagesById(Long id) {
		String s = "select ci from Resource ci where ci.id = :id";		
		javax.persistence.Query q = entityManager.createQuery(s);
		q.setParameter("id", id);		
		return (Resource)q.getSingleResult();
	}	
	
	
	
	public List<SearchResult> regularsearch(String query){
		//List<SearchResult> regularSearchResults = luceneManagerService.performSearchMedworm(query,null,false,IConstant.SEARCH_SCORE);
		int i = 0;
		Set<String> uniqueURL = new HashSet<String>();
		List<SearchResult> regularSearchResults = luceneManagerService.performTopSearchMedworm(query,null,false,IConstant.SEARCH_SCORE);
		List<SearchResult> finalSearchResults = new ArrayList<SearchResult>();
		for (SearchResult searchResult : regularSearchResults) {
			Resource resource = persistenceService.getResourceById(searchResult.getResource().getId());
			if (resource!=null) {
				if (!uniqueURL.contains(resource.getLink())) {
					uniqueURL.add(resource.getLink());
					resource.setTagsText(resource.getTagString());
					//resource.setContexts(resource.getContexts());
					searchResult.setResource(resource);	
					searchResult.setRank(""+i++);
					finalSearchResults.add(searchResult);
				}
			}

		}
		Collections.sort(finalSearchResults);
		regularSearchResults = null;
		return finalSearchResults;
	}	
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,List<Resource>> getTagDocCluster() {
		List<Tag> tagResources = persistenceService.getAllTags();
		List<Resource> pagesContent = persistenceService.getResources();
		return getTagDocCluster(tagResources, pagesContent);
	}
	
	
	/* (non-Javadoc)
	 * @see aau.ClusterService#calculateSpectralClusterFromSearch(java.util.List)
	 */
	public void calculateSpectralClusterFromSearch(List<SearchResult> searchResults) {
		List<Resource> resources = new ArrayList<Resource>();
		Set<Tag> tagResources = new HashSet<Tag>();
		for (SearchResult searchResult : searchResults) {
			resources.add(searchResult.getResource());
			List<Tag> tagsFromResources = persistenceService.getTagsByResourceId(searchResult.getResource().getId());
			//Set<Tag> tagsFromResources = searchResult.getResource().getTags();
			if (tagsFromResources!=null && !tagsFromResources.isEmpty()) {
				tagResources.addAll(tagsFromResources);
			}
			
		}
		calculateSpectralCluster(getTagDocCluster(new ArrayList<Tag>(tagResources),resources));
	}
	
	public void calculateSpectralClusterFromResources(List<Resource> resources) {
		Set<Tag> tagResources = new HashSet<Tag>();
		for (Resource resource : resources) {
			List<Tag> tagsFromResources = persistenceService.getTagsByResourceId(resource.getId());
			//Set<Tag> tagsFromResources = searchResult.getResource().getTags();
			if (tagsFromResources!=null && !tagsFromResources.isEmpty()) {
				tagResources.addAll(tagsFromResources);
			}
			
		}
		calculateSpectralCluster(getTagDocCluster(new ArrayList<Tag>(tagResources),resources));
	}	
	
	
	/* (non-Javadoc)
	 * @see aau.ClusterService#calculateSpectralClusterFromSearch(java.util.List)
	 */
	public void calculateSpectralClusterSampleForMECO() {
		//calculateSpectralClusterFromSearch(regularsearch("herpes"));
		calculateSpectralClusterFromResources(persistenceService.getResourcesBySize(20));
		
	}	
	
	
	
	
	/**
	 * @param tagClusters
	 */
	public void calculateSpectralCluster(Map<String,List<Resource>> tagClusters) {
		if (deleteSpectralCluster()) {
			for (String tagKey : tagClusters.keySet()) {
				TagCluster tagCluster = new TagCluster();
				tagCluster.setCluster(tagKey);
				tagCluster.setResources(tagClusters.get(tagKey));
				entityManager.persist(tagCluster);
				entityManager.flush();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see aau.ClusterService#calculateSpectralCluster()
	 */
	public void calculateSpectralCluster() {
		calculateSpectralCluster(getTagDocCluster());
	}	
	
	
	@SuppressWarnings("unchecked")
	public Map<String,List<Resource>> getTaMapgDocCluster() {
		List<Tag> tagResources = persistenceService.getAllTags();
		List<Resource> pagesContent = persistenceService.getResources();
		return getTagDocCluster(tagResources, pagesContent);
	}	
	
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
    public Map<String,List<Resource>> getTagDocCluster(List<Tag> tagResources,List<Resource> pagesContent) {		

		Set<String> tagLabels = new HashSet<String>();
		
		for (Resource xx : pagesContent) {
				System.out.println(xx.getTitle());
		}		
		
		Set<Resource> pagesX = new HashSet<Resource>();
		for (Resource Resource2 : pagesContent) {
			if (Resource2.getTags().size()>0){
						pagesX.add(Resource2);	
			}
		}

		for (Tag tag : tagResources) {
			if (!tagLabels.contains(tag.getTagName())) {
				tagLabels.add(tag.getTagName());
			}
		}		

		List<Resource> pages = new ArrayList<Resource>(pagesX); 

		SpectralClusterBean [][] spectralClusterBeans = new SpectralClusterBean[tagLabels.size()][pages.size()];
		
		List<String> tagCollection = new ArrayList(tagLabels);
		

		for (int i = 0; i < tagCollection.size(); i++) {
			for (int j = 0; j < pages.size(); j++) {
				float tagFrequency = persistenceService.getTagFrequencyByResource(((String)tagCollection.get(i)).toLowerCase() , pages.get(j), new Float(pages.size()));
		//		System.out.println(" tag "+((String)tagCollection.get(i)).toLowerCase() +" has freq " + tagFrequency+" for page "+pages.get(j).getTitle());
				spectralClusterBeans[i][j] = new SpectralClusterBean(pages.get(j).getId(),(String)tagCollection.get(i),i,j,tagFrequency);
			}			
		}		

		double [][] userTags = new double[tagCollection.size()][pages.size()];
		for (int i = 0; i < tagCollection.size(); i++) {
			for (int j = 0; j < pages.size(); j++) {
				userTags[i][j] = (double)spectralClusterBeans[i][j].getScore();					
			}			
		}
		cluster.SpectralClusteringModel spectralClusteringRecommendation = new cluster.SpectralClusteringModel();
		Matrix frequencyMatrix = new Matrix(userTags);
		
		SingularValueDecomposition singularValueDecomposition=null;
		
		try {
			singularValueDecomposition = spectralClusteringRecommendation.computeLeftSigularValueDecompositionMatrix(frequencyMatrix);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dataset[] dataSets = null;
		try {
			dataSets = spectralClusteringRecommendation.clusterSpectralClusteringMatrix(singularValueDecomposition);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<Set<Double>,Set<Double>>  mapClusters = getMappingPositions(dataSets);
		
		Map<String,List<Resource>> showCLuster  = new HashMap<String, List<Resource>>();	

		for (Set<Double> keys : mapClusters.keySet()) {
			StringBuffer tagStringBuffer = new StringBuffer();
			for (Double tagPos : keys) {
				tagStringBuffer.append((spectralClusterBeans[tagPos.intValue()][0]).getTagLabel());
				tagStringBuffer.append("+");
			}
			Set<Resource> cis = new HashSet<Resource>(); 
			
			for (Double tagPos : keys) {
				for (int i = 0; i < pages.size(); i++) {
					if (spectralClusterBeans[tagPos.intValue()][i].getScore()>0) {
						Long ciId = spectralClusterBeans[tagPos.intValue()][i].getResourceId();
						Resource Resource = getPagesById(ciId);
						cis.add(Resource);						
					}
				}
			}		
			showCLuster.put(tagStringBuffer.substring(0,tagStringBuffer.lastIndexOf("+")), new ArrayList(cis));
		}
		
		
		return showCLuster;
	}

	/**
	 * @param dataSets
	 * @return
	 */
	private Map<Set<Double>,Set<Double>> getMappingPositions(Dataset[] dataSets) {
		
		Map<Set<Double>,Set<Double>> mapClusters  = new HashMap<Set<Double>, Set<Double>>();
		for (int i = 0; i < dataSets.length; i++) {
			Dataset dataSet = dataSets[i];
			Set<Double> tagsCluster = new HashSet<Double>();
			Set<Double> cissCluster = new HashSet<Double>();			
			for (int j = 0; j < dataSet.size(); j++) {
				Instance instance = dataSet.get(j);
				System.out.println(instance.toString());
				if (instance.classValue().toString().contains("tag")) {
					Double dv = new Double(instance.classValue().toString().replace("tag_pos_", ""));
					tagsCluster.add(dv);
				}
				if (instance.classValue().toString().contains("page")) {
					Double dv = new Double(instance.classValue().toString().replace("page_pos_", ""));
					cissCluster.add(dv);
				}
				if (!mapClusters.keySet().containsAll(tagsCluster) && tagsCluster.size()>1 && cissCluster.size()>1) {
					mapClusters.put(tagsCluster, cissCluster);
				}
				
			}
		}
		
		for (Set<Double> keys : mapClusters.keySet()) {
			System.out.println("for "+keys+ " we have "+mapClusters.get(keys));
		}
		return mapClusters;
	}	
	
	
	/**
	 * It gets the result by skill
	 */
	public boolean deleteSpectralCluster(){
		List<TagCluster> tagClusters = this.listTagCluster();
		for (TagCluster tagCluster : tagClusters) {
			entityManager.remove(tagCluster);
			entityManager.flush();
		}
		return listTagCluster().isEmpty();
	} 	


	/* (non-Javadoc)
	 * @see kiwi.api.cluster.ClusterService#listTagCluster()
	 */
	@SuppressWarnings("unchecked")
	public List<TagCluster> listTagCluster() {
			log.info("listing  tag clusters...");
			Query q = entityManager.createNamedQuery("cluster.listTagCluster");
			q.setHint("org.hibernate.cacheable", true);
			return (List<TagCluster>)q.getResultList();
		}
}
