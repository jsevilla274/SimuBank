import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.DecimalFormat;

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

		System.out.println("Your account has been opened successfully!");
		//call decision prompt with new savings account

	}

	public static double startingBalance(Scanner keyboard)
	{
		double balance = 0;
		while (balance < 25)
		{
			try
			{
				balance = keyboard.nextDouble();

				//Consume stray input
				keyboard.nextLine();

				if (balance < 25) {
					System.out.print("Insufficient funds to open the account. Please enter a different starting balance: ");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.print("I'm sorry, that is not valid input. Please enter your starting balance: ");

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

				//Consume stray input
				keyboard.nextLine();

				if (annualRate < 0)
				{
					System.out.print("Please enter a non-negative annual interest rate: ");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.print("I'm sorry, that is not valid input. Please enter your annual interest rate: ");

				//Consume stray input
				keyboard.nextLine();
			}
		}
		return annualRate;
	}

	public static void decisionPrompt(SavingsAccount account, Scanner keyboard)
	{	
		boolean running = true;		//determines when to exit program
		int choice = 0;

		//DecimalFormat to format money amount.
		DecimalFormat money = new DecimalFormat("#,##0.00");

		//Retrieve balance and format it before inserting it in the prompt
		accountPrompt(money.format(account.getBalance()));

		while(running)
		{
			try
			{
				System.out.print("Please enter a choice number: ");
				choice = keyboard.nextInt();

				//Consume stray input
				keyboard.nextLine();

				if (choice == 1)
				{
					System.out.print("")
				}
				else if (choice == 2)
				{

				}
				else if (choice == 3)
				{

				}
				else if (choice == 4)
				{

				}
				else if (choice == 5)
				{
					running = false;
				}
				else
				{
					System.out.print("That is an invalid choice. ");
				}

			}
			catch (InputMismatchException e)
			{
				//Consume stray input
				keyboard.nextLine();
			}
		}
	}

	public static void accountPrompt(String formattedBalance)
	{
		System.out.println("SimuBank Savings Account Balance: $" + formattedBalance);
		System.out.print("What would you like to do?\n1.Deposit\n2.Withdraw\n3.View Statement\n4.Proceed to the next month\n5.Log out\n\n");
	}
}