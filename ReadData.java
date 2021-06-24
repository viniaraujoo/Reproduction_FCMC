import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class ReadData
{
    public static void readData() throws IOException 
	{
        // --- some statistics, start from index "1"
    	Data.userRatingSumTrain = new float[Data.n+1];
    	Data.itemRatingSumTrain = new float[Data.m+1];
        Data.userRatingNumTrain = new int[Data.n+1];
        Data.itemRatingNumTrain = new int[Data.m+1];
        Data.user_graded_rating_number = new int[Data.n+1][Data.num_rating_types+1];
        Data.user_rating_number = new int[Data.n+1];
        
        // ----------------------------------------------------  
        // global average rating $\mu$
        Data.g_avg = 0;  
        
    	// --- number of target training records

       	System.out.print(Data.fnTrainData);
    	System.out.print("***");
        
        Data.num_train = 0;	
    	BufferedReader brTrain = new BufferedReader(new FileReader(Data.fnTrainData));    	
    	String line = null;
    	
    	while ((line = brTrain.readLine())!=null)
    	{
    		Data.num_train += 1;
    	}
    	
    	    	
    	// --- number of test records
    	Data.num_test = 0;
    	BufferedReader brTest = new BufferedReader(new FileReader(Data.fnTestData));
    	line = null;
    	while ((line = brTest.readLine())!=null)
    	{
    		Data.num_test += 1;
    	}
    	System.out.println("num_test: " + Data.num_test);
    	// ----------------------------------------------------
    	
    	// ----------------------------------------------------
		// --- Locate memory for the data structure    	
        // --- train data
    	Data.indexUserTrain = new int[Data.num_train]; // start from index "0"
    	Data.indexItemTrain = new int[Data.num_train];
    	Data.ratingTrain = new float[Data.num_train];
        
        // --- test data
    	Data.indexUserTest = new int[Data.num_test];
    	Data.indexItemTest = new int[Data.num_test];
    	Data.ratingTest = new float[Data.num_test];
    	// ----------------------------------------------------

        
    	// ----------------------------------------------------        
        int id_case=0;
    	double ratingSum=0;
    	int gradedNum[] = new int[Data.num_rating_types + 1];

    	// ----------------------------------------------------
    	// Training data: (userID,itemID,rating)
 
		brTrain = new BufferedReader(new FileReader(Data.fnTrainData));    	
    	line = null;
    	while ((line = brTrain.readLine())!=null)
    	{	
    		String[] terms = line.split("\\s+|,|;");
    		int userID = Integer.parseInt(terms[0]);
    		int itemID = Integer.parseInt(terms[1]);
    		float rating = Float.parseFloat(terms[2]);
    		Data.indexUserTrain[id_case] = userID;
    		Data.indexItemTrain[id_case] = itemID;
    		Data.ratingTrain[id_case] = rating;
    		id_case+=1;
    		    		
    		// ---
    		Data.userRatingSumTrain[userID] += rating;
    		Data.userRatingNumTrain[userID] += 1;    			
    		Data.itemRatingSumTrain[itemID] += rating;
    		Data.itemRatingNumTrain[itemID] += 1;
    		
    		ratingSum+=rating;
    			
    		if(Data.Train_ExplicitFeedbacks.containsKey(userID))
			{
				HashMap<Integer, Float> itemMap = Data.Train_ExplicitFeedbacks.get(userID);
				itemMap.put(itemID, rating);
				Data.Train_ExplicitFeedbacks.put(userID, itemMap);
			}
			else
			{
				HashMap<Integer, Float> itemMap = new HashMap();
				itemMap.put(itemID, rating);
				Data.Train_ExplicitFeedbacks.put(userID, itemMap);
			}
    		
    	}
    	brTrain.close();
    	System.out.println("Finished reading the target training data");
    	
    	Data.g_avg = (float) (ratingSum/Data.num_train);
    	System.out.println(	"average rating value: " + Float.toString(Data.g_avg));
    	// ----------------------------------------------------    	

    	
    	// ----------------------------------------------------
    	// --- normalization    	
    	// ----------------------------------------------------
    	// Test data: (userID,itemID,rating)   	
    	id_case = 0; // initialize it to zero
    	brTest = new BufferedReader(new FileReader(Data.fnTestData));
    	line = null;
    	while ((line = brTest.readLine())!=null)
    	{	
    		String[] terms = line.split("\\s+|,|;");
    		int userID = Integer.parseInt(terms[0]);    		
    		int itemID = Integer.parseInt(terms[1]);
    		float rating = Float.parseFloat(terms[2]);
    		Data.indexUserTest[id_case] = userID;
    		Data.indexItemTest[id_case] = itemID;
    		Data.ratingTest[id_case] = rating;
    		id_case+=1;
    	}
    	brTest.close();
    	System.out.println("Finished reading the target test data");
    	// ----------------------------------------------------


    }    
}
