import java.io.*;

public class Main 
{
    public static void main(String[] args) throws IOException 
	{	
		// 1. read configurations		
		ReadConfigurations.readConfigurations(args);

		// 2. read training data and test data
        ReadData.readData();
               
		// 3. apply initialization
		Initialization.initialization();
		
		long time1 = System.currentTimeMillis();
		// 4. train the auxiliary
		Auxiliary.train(Data.num_iterations);

		Train_target.train(Data.num_iterations);
		

		// 5. test
		Test.test();	
		long time2 = System.currentTimeMillis();
		System.out.println("spend time"+(time2 - time1));
    }
}
