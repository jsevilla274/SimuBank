import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This program demonstrates the SavingsAccount class
 */

public class Driver
{
	public static void main(String[] args)
	{
		SavingsAccount account; 	//References the user's account
		double balance;				//Starting balance
		double annualRate;			//Annual interest rate

		//Scanner object for keyboard input.
		Scanner keyboard = new Scanner(System.in);

		//Prompt user for starting balance.
		System.out.print("Thank you for opening a new savings account!\nPlease enter your starting balance (Must have an minimum initial deposit of $25): ");
		balance = startingBalance(keyboard);

		//Prompt user for starting annual interest rate.
		System.out.print("Please enter your annual interest rate: ");
		annualRate = startingRate(keyboard);

		//Create savings account
		account = new SavingsAccount(balance, annualRate);

		System.out.print("Your account has been opened successfully!");
	}

	public static double startingBalance(Scanner keyboard)
	{
		double balance = 0;
		while (balance < 25)
		{
			try
			{
				balance = keyboard.nextDouble();

				if (balance < 25) {
					System.out.print("Insufficient funds to open the account. Please enter a different starting balance: ");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.print("I'm sorry, that is not valid input. Please enter your starting balance: ");
			}
			finally
			{
				//Consume stray input
				keyboard.nextLine();
			}
		}
		return balance;
	}

	public static double startingRate(Scanner keyboard)
	{
		double annualRate = -1;		//-1 enters into while loop and still allows 0 annual rate
		while (annualRate < 0)
		{
			try
			{
				annualRate = keyboard.nextDouble();

				if (annualRate < 0)
				{
					System.out.print("Please enter a non-negative annual interest rate: ");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.print("I'm sorry, that is not valid input. Please enter your annual interest rate: ");
			}
			finally
			{
				//Consume stray input
				keyboard.nextLine();
			}
		}
		return annualRate;
	}
}