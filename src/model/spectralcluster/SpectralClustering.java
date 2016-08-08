package spectralcluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;
import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class SpectralClustering {
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SpectralClustering spectralClustering = new SpectralClustering();
		//read data file x means users y means tag frequency
		BufferedReader su = new BufferedReader(new InputStreamReader(new FileInputStream("resources/test.dat")));
		Matrix userTagFrequencyMatrix = Matrix.read(su);
		Matrix sigularValueDecompositionMatrixU = spectralClustering.computeLeftSigularValueDecompositionMatrix(userTagFrequencyMatrix);
		spectralClustering.cluster(sigularValueDecompositionMatrixU);
		
	}
	
	public void cluster(Matrix sigularValueDecompositionMatrixU) throws IOException
	{
		List<Instance> instances = new ArrayList<Instance>();
		double[][] sigularValueDecompositionArrayU = sigularValueDecompositionMatrixU.getArray();
		
		for (int i = 0; i < sigularValueDecompositionMatrixU.getRowDimension(); i++) {
			double[] rowArray = new double[sigularValueDecompositionMatrixU.getColumnDimension()+1];
			for (int j = 0; j < sigularValueDecompositionMatrixU.getColumnDimension(); j++) {
				rowArray[j]=sigularValueDecompositionArrayU[i][j];
			}

			instances.add(new DenseInstance(rowArray,"class"));
		}		
		
		
		
		
		
		//0.7642 -0.2418 0.2779 0.5294
		Dataset data = new DefaultDataset(instances);
		
//		for (int i = 0; i < data.size(); i++) {
//			System.out.println(data.get(i));
//		}
		
		
		/* Load a dataset */
		//Dataset data = FileHandler.loadDataset(new File ("resources/iris.data"), 4, ",");
		//Dataset data = FileHandler.loadDataset(new File ("resources/2Umatrix.ou")," ");

		
		for (int i = 0; i < data.size(); i++) {
			System.out.println("dataset               "+data.get(i));
	    }
		System.out.println(" ");
		System.out.println(" ");
		// System.out.println(data);
		/* Create a new instance of the KMeans algorithm, with no options
		 * specified. By default this will generate 4 clusters. */
		Clusterer kMeans = new KMeans(instances.size()-1);
		/* Cluster the data, it will be returned as an array of data sets, with
		* each dataset representing a cluster. */
		Dataset[] clusters = kMeans.cluster(data);
		
		for (int i = 0; i < clusters.length; i++) {
			System.out.println(clusters[i]);
		}
		
		/* Create a measure for the cluster quality */
		ClusterEvaluation sse= new SumOfSquaredErrors();
		/* Measure the quality of the clustering */
		double score=sse.score(clusters);
		System.out.println(score);
	
		//FileHandler.exportDataset(data, new File ("resources/iris.txt"));
	}	
	
	/**
	 * @param xyMatrix
	 * @return
	 * @throws IOException
	 */
	public Matrix computeLeftSigularValueDecompositionMatrix( Matrix xyMatrix) throws IOException {
		Matrix userTagFrequencyMatrix = xyMatrix;
		System.out.println("display the read file. x means users y means tag frequency");
		userTagFrequencyMatrix.print(5, 4);
		// transform to double array and determine the row and column size
		double[][] dimensionMatrix = userTagFrequencyMatrix.getArray();
		int M = dimensionMatrix.length;
		int N = dimensionMatrix[0].length;
		// calculate the cosine similarity matrix
		double[][] cosineSimilairity2DArray = new double [M][M];
		double sm1 = 0; double sm2 = 0; double sm3= 0;
		for (int i=0; i<M; i++)
		{
			for(int j=i+1; j<M; j++)
			{
				sm1=0; sm2=0; sm3 = 0;
				for (int k = 0; k<N; k++)
				{
					sm1 += dimensionMatrix[i][k]*dimensionMatrix[j][k];
					sm2 += dimensionMatrix[i][k]*dimensionMatrix[i][k];
					sm3 += dimensionMatrix[j][k]*dimensionMatrix[j][k];
				}
				cosineSimilairity2DArray[i][j]=sm1/Math.sqrt(sm2)/Math.sqrt(sm3);
			}
		}
		//symmetrize the similarity matrix
		for (int i=0; i<M; i++)
		{
			cosineSimilairity2DArray[i][i]=1;
			for (int j=i+1; j<M; j++)
			{
				cosineSimilairity2DArray[j][i]=cosineSimilairity2DArray[i][j];
			}
		}
		Matrix cosineSimilarityMatrix = new Matrix(cosineSimilairity2DArray);
		System.out.print("The similarity matrix is");
		cosineSimilarityMatrix.print(5, 4);
        // spectrum projection
		int columnDimensionSize = cosineSimilarityMatrix.getColumnDimension();
		System.out.println("The similarity matrix length is "+columnDimensionSize);
		// calculate D_{ii}=\sigma_j{cs{ij}}
		double [][] sigmaCosineSimilarity2DArray = new double[columnDimensionSize][columnDimensionSize];
		for (int i =0; i<columnDimensionSize; i++)
		{
			for (int j=0; j<columnDimensionSize; j++)
			{
				sigmaCosineSimilarity2DArray[i][i] =	sigmaCosineSimilarity2DArray[i][i]+cosineSimilairity2DArray[i][j];
			}
			// System.out.println(d1[i][i]);
		}
		Matrix sigmaCosineSimilarityMatrix = new Matrix(sigmaCosineSimilarity2DArray);
		System.out.println("SigmaCosineSimilarityMatrix matrix is");
		sigmaCosineSimilarityMatrix.print(5, 4);
		// calculate sigmaCosineSimilarity^{-1/2}
		double squaredSigmaCosineSimilarity2DArray[][] = new double[columnDimensionSize][columnDimensionSize];
		for (int i=0; i<columnDimensionSize; i++)
		{
			double dij = sigmaCosineSimilarityMatrix.get(i, i);
			// System.out.println(dij);
			squaredSigmaCosineSimilarity2DArray[i][i] = 1/Math.sqrt(dij);
		}
		Matrix squaredSigmaCosineSimilarityMatrix = new Matrix(squaredSigmaCosineSimilarity2DArray);
		System.out.println("M;atrix squaredSigmaCosineSimilarityMatrix ^(-1/2) is");
		squaredSigmaCosineSimilarityMatrix.print(5, 4);
		//calculate D^(-1/2)(D-CS)D^(-1/2)
		Matrix laplacianMatrix = calculateLaplacianMatrix(cosineSimilarityMatrix,sigmaCosineSimilarityMatrix, squaredSigmaCosineSimilarityMatrix);
		System.out.print("Laplacian Matrix is");
		laplacianMatrix.print(5, 4);
		
		SingularValueDecomposition sigularValueDecompositionMatrix = laplacianMatrix.svd();
		double[] sigma = sigularValueDecompositionMatrix.getSingularValues();
		for (int i=0; i<columnDimensionSize; i++)
		{
			//System.out.println("Eigenvalue is "+ sigma[i]);
		}
		Matrix sigularValueDecompositionMatrixU = sigularValueDecompositionMatrix.getU();
		Matrix sigularValueDecompositionMatrixV = sigularValueDecompositionMatrix.getV();
		//System.out.println("Matrix sigularValueDecompositionMatrixU is");
		//sigularValueDecompositionMatrixU.print(5, 4);
		// save the left eigenvector matrix U

		//printMatrix(sigularValueDecompositionMatrixU);
		
		//System.out.println("Matrix sigularValueDecompositionMatrixV is");
		//sigularValueDecompositionMatrixV.print(5, 4);
		
		return sigularValueDecompositionMatrixU;
	}

	/**
	 * @param sigularValueDecompositionMatrixU
	 * @throws FileNotFoundException
	 */
	private static void printMatrix(Matrix sigularValueDecompositionMatrixU) throws FileNotFoundException {
		PrintWriter u = new PrintWriter (new FileOutputStream("resources/Umatrix.out"),true);
		sigularValueDecompositionMatrixU.print(u, 5, 4);
	}

	/**
	 * @param cosineSimilarityMatrix
	 * @param sigmaCosineSimilarityMatrix
	 * @param squaredSigmaCosineSimilarityMatrix
	 * @return
	 */
	private static Matrix calculateLaplacianMatrix(Matrix cosineSimilarityMatrix, Matrix sigmaCosineSimilarityMatrix,Matrix squaredSigmaCosineSimilarityMatrix) {
		Matrix minusMatrix = sigmaCosineSimilarityMatrix.minus(cosineSimilarityMatrix);
		System.out.println("minusMatrix is");
		minusMatrix.print(5, 4);
		Matrix timesMatrix = squaredSigmaCosineSimilarityMatrix.times(minusMatrix);
		Matrix laplacianMatrix = timesMatrix.times(squaredSigmaCosineSimilarityMatrix);
		return laplacianMatrix;
	}
	
	
	
	
}
