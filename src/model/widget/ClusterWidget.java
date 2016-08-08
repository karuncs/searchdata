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
package widget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import tree.BaseBean;
import tree.ClusterUserObject;
import tree.FacesUtils;
import tree.NodeUserObject;
import aau.ClusterService;
import aau.Resource;
import aau.TagCluster;

import com.icesoft.faces.component.dragdrop.DropEvent;
import com.icesoft.faces.component.ext.HtmlPanelGroup;
import com.icesoft.faces.component.tree.IceUserObject;

/**
 * An action component for backing the spectral cluster widget.
 * 
 * @author Fred Durao
 *
 */
@Name("clusterWidget")
@Scope(ScopeType.CONVERSATION)
@AutoCreate
public class ClusterWidget extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int MAX_TITLE_LENGTH = 25;	

	@In(create=true)
	private ClusterService clusterService;	

	private List<ClusterNode> clusterNode;

	public List<ClusterNode> getClusterNode() {
		if(clusterNode == null) {
			clusterNode = new ArrayList<ClusterNode>();

			List<TagCluster> tagClusters = clusterService.listTagCluster();
			
			for (TagCluster tagCluster: tagClusters) {
					// create new node for property
					ClusterNode node = new ClusterNode(tagCluster.getCluster());
					clusterNode.add(node);
					
					// add all objects reachable by this property to the children of the node
					for(Resource resource : tagCluster.getResources()) {
						ClusterNode child = new ClusterNode(resource);
						if (child.getFilhos()==null) {
							child.setFilhos(new ArrayList<ClusterNode>());
						}
						node.getFilhos().add(child);
					}
			}			
		}
		
		
		
		for (ClusterNode cn : clusterNode) {
			System.out.println("node "+cn.getTagKeys()+" has childrens ");
			for (ClusterNode child : cn.getFilhos()) {
				System.out.println("name "+child.getResource().getTitle());
			}
		}
		return clusterNode;
	}

	public void clear() {
		clusterNode = null;
	}
	
	/**
	 * Compute the tag x doc cluster
	 */
	public void calculateSpectralCluster(){
		String message = "Tag cluster is calculated. See the results at any WiKi page.";
		try {
			//clusterService.calculateSpectralCluster();
			clusterService.calculateSpectralClusterSampleForMECO();
			
		} catch (Exception e) {
			message = "Spectral Cluster could not be computed due to "+e.getMessage();
		}
		FacesMessages.instance().add(message);		

	}
	
	/**
	 * @return
	 */
	public String ellipseString(String s) {
		if (s.equals("")) {
			s = "empty";
		}
		if(s.length() > MAX_TITLE_LENGTH) {
			return s.substring(0, MAX_TITLE_LENGTH) + "...";
		} else {
			return s;
		}
	}

	
	private DefaultTreeModel model = null;
	
    public DefaultTreeModel getTreeModel() {
    	if (model==null) {
	        /* code from tree tutorial */
	        DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode();
	        ClusterUserObject rootObject = new ClusterUserObject(rootTreeNode);
	        rootObject.setText("Tag Clustering");
	        rootObject.setExpanded(true);
	        rootTreeNode.setUserObject(rootObject);
	        selectedUserObject = (ClusterUserObject) rootTreeNode.getUserObject();
	        selectedUserObject.setExpanded(true);		        
	
	        // model is accessed by by the ice:tree component via a getter method
	        model = new DefaultTreeModel(rootTreeNode);
	
	        for (ClusterNode cn : this.getClusterNode()) {
	        	DefaultMutableTreeNode branchNode  = addNode(rootTreeNode,cn.getTagKeys(), cn);	        	
//	            DefaultMutableTreeNode branchNode = new DefaultMutableTreeNode();
//	            ClusterUserObject branchObject = new ClusterUserObject(branchNode);
//	            branchObject.setText(cn.getTagKeys());
//	            branchNode.setUserObject(branchObject);
//	            rootTreeNode.add(branchNode);
				for (ClusterNode child : cn.getFilhos()) {
					DefaultMutableTreeNode childNode = addNode(branchNode,child.getResource().getTitle(), child);
					((ClusterUserObject)childNode.getUserObject()).setLeaf(true);
//		            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode();
//		            ClusterUserObject childObject = new ClusterUserObject(childNode);
//		            childObject.setText(child.getResource().getTitle());
//		            childNode.setUserObject(childObject);
//		            branchNode.add(childNode);
				}            
			}    
    	}
        return model;
    }
	
	
	public ClusterWidget(){
		//init();
	}	

	private ClusterUserObject selectedUserObject;

	public DefaultTreeModel getModel() {
        return model;
    }

    public void setModel(DefaultTreeModel model) {
        this.model = model;
    }

    public NodeUserObject getSelectedUserObject() {
        return selectedUserObject;
    }

    public void clusterNodeSelected(ActionEvent event) {
        // get employee id
        String clusterKey = FacesUtils.getRequestParameter("clusterId");

        // find employee node by id and make it the selected node
        DefaultMutableTreeNode node = findTreeNode(clusterKey);
        selectedUserObject = (ClusterUserObject) node.getUserObject();
        // fire effects.);
        valueChangeEffect.setFired(false);
    }

    public ArrayList getSelectedTreePath() {
        Object[] objectPath = selectedUserObject.getWrapper().getUserObjectPath();
        ArrayList treePath = new ArrayList();
        Object anObjectPath;
        for(int i= 0, max = objectPath.length; i < max; i++){
            anObjectPath = objectPath[i];
            IceUserObject userObject = (IceUserObject) anObjectPath;
            treePath.add(userObject.getText());
        }
        return treePath;
    }

    public boolean isMoveUpDisabled() {
        DefaultMutableTreeNode selectedNode = selectedUserObject.getWrapper();
        return isMoveDisabled(selectedNode, selectedNode.getPreviousNode());
    }

    public boolean isMoveDownDisabled() {
        DefaultMutableTreeNode selectedNode = selectedUserObject.getWrapper();
        return isMoveDisabled(selectedNode, selectedNode.getNextNode());
    }

    public boolean isMoveDisabled(DefaultMutableTreeNode selected, DefaultMutableTreeNode swapper) {
        return selected == null || swapper == null || selected.getAllowsChildren() || swapper.isRoot();
    }

    public void moveUp(ActionEvent event) {
        DefaultMutableTreeNode selectedNode = selectedUserObject.getWrapper();
        exchangeNodes(selectedNode.getPreviousNode(), selectedNode);
    }

    public void moveDown(ActionEvent event) {
        DefaultMutableTreeNode selectedNode = selectedUserObject.getWrapper();
        exchangeNodes(selectedNode, selectedNode.getNextNode());
    }

    public void exchangeNodes(DefaultMutableTreeNode node1, DefaultMutableTreeNode node2) {
        DefaultMutableTreeNode node1Parent = (DefaultMutableTreeNode) node1.getParent();
        DefaultMutableTreeNode node2Parent = (DefaultMutableTreeNode) node2.getParent();
        DefaultMutableTreeNode node1PrevNode = node1.getPreviousNode();
        DefaultMutableTreeNode node1PrevNodeParent = (DefaultMutableTreeNode) node1PrevNode.getParent();
        int childCount = 0;
        
        if (node1.isNodeDescendant(node2)) {
            while (node2.getChildCount() > 0) {
                node1.insert((MutableTreeNode) node2.getFirstChild(), childCount++);
            }
            if (node1PrevNode == node1Parent ||
                    (node1PrevNode.isNodeSibling(node1) && !node1PrevNode.getAllowsChildren())) {
                node1Parent.insert(node2, node1Parent.getIndex(node1));
            } else if (node1PrevNode.getAllowsChildren()) {
                node1PrevNode.add(node2);
            } else {
                node1PrevNodeParent.add(node2);
            }

            return;
        }

        if (node2.getAllowsChildren()) {
            node2.insert(node1, 0);
        } else {
            node1.removeFromParent();
            node2Parent.insert(node1, node2Parent.getIndex(node2) + 1);
        }
    }

    public void dropListener(DropEvent event) {
        HtmlPanelGroup panelGroup = (HtmlPanelGroup) event.getComponent();

        DefaultMutableTreeNode dragNode = (DefaultMutableTreeNode) event.getTargetDragValue();
        DefaultMutableTreeNode dropNode = (DefaultMutableTreeNode) panelGroup.getDropValue();
        DefaultMutableTreeNode dropNodeParent = (DefaultMutableTreeNode) dropNode.getParent();

        if (dragNode.isNodeDescendant(dropNode)) return;

        if (dropNode.getAllowsChildren()) {
            dropNode.insert(dragNode, 0);
        } else {
            dragNode.removeFromParent();
            dropNodeParent.insert(dragNode, dropNodeParent.getIndex(dropNode) + 1);
        }
    }

    private DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent,
                                           String title,
                                           ClusterNode clusterNode) {
        
    	DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        ClusterUserObject userObject = new ClusterUserObject(node);
        node.setUserObject(userObject);
        userObject.setClusterNode(clusterNode);

        // non-employee node or branch
        if (title != null) {
            userObject.setText(title);
            userObject.setLeaf(false);
            userObject.setExpanded(true);
            node.setAllowsChildren(true);
        }
        // employee node
        else {
            userObject.setText(clusterNode.getTagKeys());
            userObject.setLeaf(true);
            node.setAllowsChildren(false);
        }
        // finally add the node to the parent. 
        if (parent != null) {
            parent.add(node);
        }
  

        return node;
    }

    protected void init() {
        DefaultMutableTreeNode rootNode = addNode(null, "Employees",null);
        model = new DefaultTreeModel(rootNode);
        selectedUserObject = (ClusterUserObject) rootNode.getUserObject();
        selectedUserObject.setExpanded(true);
        DefaultMutableTreeNode regionNode = addNode(rootNode, "Employees",null);
 

    }

    private DefaultMutableTreeNode findTreeNode(String nodeId) {
        DefaultMutableTreeNode rootNode =
                (DefaultMutableTreeNode) model.getRoot();
        DefaultMutableTreeNode node;
        ClusterUserObject tmp;
        Enumeration nodes = rootNode.depthFirstEnumeration();
        while (nodes.hasMoreElements()) {
            node = (DefaultMutableTreeNode) nodes.nextElement();
            tmp = (ClusterUserObject) node.getUserObject();
            if (nodeId.equals(String.valueOf(tmp.getClusterNode().getTagKeys()))) {
                return node;
            }
        }
        return null;
    }	
	
	
	/**
	 * A bean class to represent tree nodes using the rich:recursiveTreeAdaptor.
	 * 
	 * @author Fred Durao
	 *
	 */
	public static class ClusterNode {
		
		private Resource resource;
		
		private String tagKeys;		

		private List<ClusterNode> filhos;

		private ClusterNode(Resource resource) {
			this.resource = resource;
			this.tagKeys = resource.getTitle();
			this.filhos = new ArrayList<ClusterNode>();
		}
		
		private ClusterNode(String tagKeys) {
			this.tagKeys = tagKeys;
			this.filhos = new ArrayList<ClusterNode>();
		}	
		
		public String getTagKeys() {
			return tagKeys;
		}


		public void setTagKeys(String tagKeys) {
			this.tagKeys = tagKeys;
		}

		public Resource getResource() {
			return resource;
		}

		public void setResource(Resource resource) {
			this.resource = resource;
		}

		public List<ClusterNode> getFilhos() {
			return filhos;
		}

		public void setFilhos(List<ClusterNode> filhos) {
			this.filhos = filhos;
		}		
		
	}
}
