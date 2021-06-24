import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;

public class Data 
{
	// === Configurations	
	// the number of latent dimensions
	public static int d = 20; 
	public static int num_rating_types = 5; // should be different for different data
	
//	public static float epsilon = 0.5f;

	// tradeoff $\alpha_u$
	public static float alpha_u = 0.01f;
	// tradeoff $\alpha_v$
	public static float alpha_v = 0.01f;
//	public static float alpha_v1 = 0.001f;

	// tradeoff $\alpha_g$
	public static float alpha_g = 0.01f; // graded observation
	// tradeoff $\alpha_w$
	public static float alpha_w = 0.01f;

	
	// tradeoff $\beta_u$
	public static float beta_u = 0.01f;
	// tradeoff $\beta_v$
	public static float beta_v = 0.01f; 

	 
	// learning rate $\gamma$
	public static float gamma = 0.01f;
	public static float gamma1 = 0.01f;
	

	 // === Input data files
	public static String fnTrainData = "C:\\Users\\LMD\\Desktop\\Code-FCMF\\Dataset\\ML100K\\copy1.target";
	public static String fnAuxiliaryData = "C:\\\\Users\\\\LMD\\\\Desktop\\\\Code-FCMF\\\\Dataset\\\\ML100K\\\\copy1_01.auxiliary";
	public static String fnTestData = "C:\\\\Users\\\\LMD\\\\Desktop\\\\Code-FCMF\\\\Dataset\\\\ML100K\\\\copy1.test";
	public static String fnOutputData = "C:\\\\Users\\\\LMD\\\\Desktop\\\\Code-FCMF\\\\Dataset\\\\ML100K\\\\d20_alpha001_beta001_result.txt";

	// 
	public static int n = 943;//71567; // number of users
	public static int m = 1682;//10681; // number of items
	public static int num_train_auxiliary; // number of auxiliary training triples of (user,item,rating)
	public static int num_train; // number of training triples of (user,item,rating), num_train = num_train_target+num_train_auxiliary
	public static int num_test; // number of test triples of (user,item,rating)

	public static float MinRating = 1.0f; // minimum rating value (0.5 for ML10M, Flixter; 1 for Netflix)
	public static float MaxRating = 5.0f; // maximum rating value

	// scan number over the whole data
	public static int num_iterations = 50; 
	

	// === training data (target data and auxiliary dta)
	public static int[] indexUserTrain; // start from index "0"
	public static int[] indexItemTrain; 
	public static float[] ratingTrain;

	public static HashMap<Integer, HashMap<Integer, Float>> Train_ExplicitFeedbacks 
	= new HashMap<Integer, HashMap<Integer, Float>>();

	// === test data
	public static int[] indexUserTest;
	public static int[] indexItemTest;
	public static float[] ratingTest;

	// === some statistics, start from index "1"
	public static float[] userRatingSumTrain;
	public static float[] itemRatingSumTrain;
	public static int[] userRatingNumTrain;
	public static int[] itemRatingNumTrain;
	public static int[][] user_graded_rating_number;
	public static int[] user_rating_number;

	// === model parameters to learn, start from index "1"
	public static float[][] U;
	public static float[][] V;
	public static float[][] V1;
	public static float[][] G;  

	public static float g_avg; // global average rating $\mu$
	public static float[] biasU;  // bias of user
	public static float[] biasV;  // bias of item

	// === file operation
	public static FileWriter fw ;
	public static BufferedWriter bw;
}
