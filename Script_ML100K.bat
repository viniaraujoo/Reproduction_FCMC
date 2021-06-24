javac -cp .;.\commons-math3-3.6.1.jar Main.java

java -Xmx4096m -cp .;.\commons-math3-3.6.1.jar Main -d 20 -fnTrainData "../Dataset/ML100K/copy1.target" -fnAuxiliaryData "../Dataset/ML100K/copy1_01.auxiliary" -fnTestData "../Dataset/ML100K/copy1.test" -fnOutputData "../Dataset/ML100K/result.txt" -n 943 -m 1682 -alpha 0.001 -beta 0.001 -gamma 0.01 -num_iterations 50 -MinRating 1.0f -epsilon 2.0

pause

