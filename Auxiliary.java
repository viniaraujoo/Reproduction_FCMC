import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Auxiliary {
	private static float[][] W = new float[Data.n+1][Data.d];
	private static HashMap<Integer, HashMap<Integer, Float>> auxliary_train = new HashMap<Integer, HashMap<Integer, Float>>();
	private static int train_number = 0;
	private  static int[] indexUserTrain; // start from index "0"
	private static int[] indexItemTrain; 
	private static float[] ratingTrain;
	public static float[] userRatingSumTrain;
	public static float[] itemRatingSumTrain;
	public static int[] userRatingNumTrain;
	public static int[] itemRatingNumTrain;
	public static int[] user_rating_number;
	
	static void initialize() throws NumberFormatException, IOException{
		for (int u=1; u<Data.n+1; u++)
		{
			for (int f=0; f<Data.d; f++)
			{
				W[u][f] = (float) ( (Math.random()-0.5)*0.01 );
			}
		}
		
		// ----------------------------------------------------    	
    	// Auxiliary data: (userID,itemID,rating)
		BufferedReader brAuxiliary = new BufferedReader(new FileReader(Data.fnAuxiliaryData));
    	String line = null;
    	while ((line = brAuxiliary.readLine())  != null){
    		train_number ++;
    	}
    	
    	brAuxiliary = new BufferedReader(new FileReader(Data.fnAuxiliaryData));
    	line = null;
    	indexUserTrain = new int[train_number];
    	indexItemTrain = new int[train_number];
    	ratingTrain = new float[train_number];
    	
    	
    	userRatingNumTrain = new int[Data.n+1];
        itemRatingNumTrain = new int[Data.m+1];
        user_rating_number = new int[Data.n+1];
    	int index = 0;
    	while ((line = brAuxiliary.readLine())!=null)
    	{
    		String[] terms = line.split("\\s+|,|;");
    		int userID = Integer.parseInt(terms[0]);
    		int itemID = Integer.parseInt(terms[1]);
    		float rating = Float.parseFloat(terms[2]);
    		indexUserTrain[index] = userID;
    		indexItemTrain[index] = itemID;
    		ratingTrain[index] = rating;
    		// 
    		if (auxliary_train.containsKey(userID)){
    			HashMap<Integer, Float> set = auxliary_train.get(userID);
    			set.put(itemID, rating);
    			auxliary_train.put(userID, set);

    		}
    		else{
    			HashMap<Integer, Float> set = new HashMap<Integer, Float>();
    			set.put(itemID, rating);
    			auxliary_train.put(userID, set);
    		}
    		index ++;
    	}
    	brAuxiliary.close();
    	System.out.println("Finished reading the auxiliary training data");
	}
	
	
	
	public static void train(int iterations) {
		if (train_number == 0){
			try {
				initialize();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int iter = 0; iter <= iterations; iter++){
			if (iter %10 == 0){
				System.out.print("Auxiliary Iter:" + Integer.toString(iter) + "| ");	
				try {
					Test.test();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int iter_rand = 0; iter_rand < train_number; iter_rand++) 
			{   	    		
				// ===========================================
				// --- random sampling one triple of (userID,itemID,rating), Math.random(): [0.0, 1.0)
				int rand_case = (int) Math.floor( Math.random() * train_number );
				int userID = indexUserTrain[rand_case];	    		
				int itemID = indexItemTrain[rand_case];
				float rating = ratingTrain[rand_case];
				// ===========================================	    		
	
				// ===========================================
	
				float auxiliary_pred = 0;
				
	
				for (int f=0; f<Data.d; f++)
				{
					auxiliary_pred += W[userID][f] * Data.V[itemID][f];
				}
				
				float error = 0;
				if (rating == 1)
					error = 5 - auxiliary_pred;
				else if(rating == -1)
					error = 1 - auxiliary_pred;
					// -----------------------
	
				// -----------------------
				// --- update W, V
				for (int f=0; f<Data.d; f++)
				{
					float grad_W_f = - error * Data.V[itemID][f] + Data.alpha_w * W[userID][f];
					float grad_V_f = - error * W[userID][f] + Data.alpha_v * Data.V[itemID][f];
					W[userID][f] = W[userID][f] - Data.gamma1 * grad_W_f;
					Data.V[itemID][f] = Data.V[itemID][f] - Data.gamma1 * grad_V_f;
				}
				// -----------------------	    			
				
					
			}
				// ===========================================
			
			
			Data.gamma1 = (float) (Data.gamma1 * 0.9);
		}
	
	}
	
	
	

}
