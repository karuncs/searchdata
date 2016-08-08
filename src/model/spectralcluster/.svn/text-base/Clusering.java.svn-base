package spectralcluster;

import java.io.File;
import java.io.IOException;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

public class Clusering {
	public static void main(String[] args) throws IOException
	{
	/* Load a dataset */
	Dataset data = FileHandler.loadDataset(new File ("resources/iris.data"), 4, ",");
	// System.out.println(data);
	/* Create a new instance of the KMeans algorithm, with no options
	 * specified. By default this will generate 4 clusters. */
	Clusterer kMeans = new KMeans(4);
	/* Cluster the data, it will be returned as an array of data sets, with
	* each dataset representing a cluster. */
	Dataset[] clusters = kMeans.cluster(data);
	/* Create a measure for the cluster quality */
	ClusterEvaluation sse= new SumOfSquaredErrors();
	/* Measure the quality of the clustering */
	double score=sse.score(clusters);
	System.out.println(score);

	//FileHandler.exportDataset(data, new File ("resources/iris.txt"));
	}
}
