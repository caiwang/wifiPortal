package net.wyun.util;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class RandomNum_test {

	@Test
	public void testShowRandomInteger() {
		/** Generate random integers in a certain range. */
		 
		    int START = 10000;
		    int END = 88888;
		    Random random = new Random();
		    for (int idx = 1; idx <= 10; ++idx){
		      int rn = RandomNumFactory.getRandomInteger(START, END, random);
		      System.out.print("random number: " + rn + '\n');
		    }
		    
		  }
	
}


