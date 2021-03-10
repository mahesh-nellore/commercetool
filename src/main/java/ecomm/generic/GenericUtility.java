package ecomm.generic;

import java.util.Random;

public class GenericUtility {
	
	public static String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}

}
