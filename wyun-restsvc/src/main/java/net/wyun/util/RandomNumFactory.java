/**
 * 
 */
package net.wyun.util;

import java.util.Random;

/**
 * @author Xuecheng
 *
 */
public class RandomNumFactory {
	
	

	  public static int getRandomInteger(int aStart, int aEnd, Random aRandom){
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    //log("Generated : " + randomNumber);
	    return randomNumber;
	  }
	  
	  private static void log(String aMessage){
	    System.out.println(aMessage);
	  }
	

}
