import java.lang.Math;

/**
 * Cash class
 * Manipulates double-type inputs of money as integers to prevent decimal place
 * and rounding errors.
 */

public class Cash
{
	/**
	 * Converts double amount into integer
	 */

	public static int convert(double amount)
	{
		return (int)Math.floor(amount*100);
	}

	/**
	 * Converts integer dollars into two decimal-place double
	 */

	public static double convert(int amount)
	{
		return amount/100.00;
	}
}