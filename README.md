##Colt performance example
Compare the performance between O(n) "optimized" algorithms and Colt functions

###Task
Provided with an array of integers of variable length (int[] input), provide a method that calculates the (1) median, (2) mean, (3) mode and (4) range for the array and outputs these four values to the command line.

###Usage and Results
```java
//load the results from a big computation into mybigarray
int[] mybigarray = generateSomeHugeArray();

//print out some basic stats on the array
PrimitiveMath arrayManipulator = new PrimitiveMath();
arrayManipulator.getStats(mybigarray);

//print out the Colt results
DescriptiveTest arrayManipulator2 = new DescriptiveTest();
arrayManipulator2.getStats(mybigarray);
```