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

		//DecimalFormat to format money amount.
		DecimalFormat money = new DecimalFormat("#,##0.00");

		//Prompt user for starting balance.
		System.out.print("Thank you for opening a new savings account!\nPlease enter your starting balance (Must have an minimum initial deposit of $25): ");
		balance = startingBalance(keyboard);

		//Prompt user for starting annual interest rate.
		System.out.print("Please enter your annual interest rate: ");
		annualRate = startingRate(keyboard);

		System.out.print("\nYour account has been opened successfully!\n");

		//Create new account
		account = new SavingsAccount(balance, annualRate);

		//Format balance and display choices
		choicePrompt(money.format(account.getBalance()));

		boolean running = true;		//Determines when to exit main prompt loop
		int choice = 0;

		//Main prompt loop
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
					deposit(account, keyboard, money);
				}
				else if (choice == 2)
				{
					withdraw(account, keyboard, money);
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
				System.out.print("That is invalid input. ");
				//Consume stray input
				keyboard.nextLine();
			}
		}

	}

	public static void choicePrompt(String formattedBalance)
	{
		System.out.print("\nSimuBank Savings Account Balance: $" + formattedBalance);
		System.out.print("\nWhat would you like to do?\n1.Deposit\n2.Withdraw\n3.View Statement\n4.Proceed to next month\n5.Log out\n");
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

	public static void deposit(SavingsAccount account, Scanner keyboard, DecimalFormat money)
	{
		double amount = -1;

		System.out.print("\nHow much would you like to deposit?");

		while (amount < 0)
		{
			try
			{
				System.out.print("\nEnter amount: $");
				amount = keyboard.nextDouble();
				//Consume stray input
				keyboard.nextLine();

				if (amount < 0)
				{
					System.out.print("Please enter a non-negative amount to deposit.");
				}
			}
			catch(InputMismatchException e)
			{
				System.out.print("Invalid input. Please try again.");
				//Consume stray input
				keyboard.nextLine();
			}
		}

		//Deposit amount
		account.deposit(amount);

		//Success prompt
		System.out.print("\n$" + money.format(amount) + " have been successfully deposited into your account!\n");

		//Re-prompt choices
		choicePrompt(money.format(account.getBalance()));
	}

	public static void withdraw(SavingsAccount account, Scanner keyboard, DecimalFormat money)
	{
		double amount = -1;
		if (account.checkStatus())
		{
			System.out.print("\nHow much would you like to withdraw?");

			while (amount < 0 || amount > account.getBalance())
			{
				try
				{
					System.out.print("\nEnter amount: $");
					amount = keyboard.nextDouble();
					//Consume stray input
					keyboard.nextLine();

					if (amount < 0)
					{
						System.out.print("Please enter a non-negative amount to withdraw.");
					}
					else if (amount > account.getBalance())
					{
						System.out.print("Insufficient balance to complete withdrawal. Please try again.");
					}
				}
				catch (InputMismatchException e)
				{
					System.out.print("Invalid input. Please try again.");
					//Consume stray input
					keyboard.nextLine();
				}
			}

			//Withdraw amount
			account.withdraw(amount);

			//Success prompt
			System.out.print("\n$" + money.format(amount) + " have been successfully withdrawn from your account!\n");
		}
		else
		{
			//Failure prompt
			System.out.print("\nYou are unable to withdraw with an inactive account. To re-activate your account, please deposit funds to reach a minimum balance of $25.00 (Press Enter to continue)");

			//Wait for Enter
			keyboard.nextLine();
		}

		//Re-prompt choices
		choicePrompt(money.format(account.getBalance()));
	}

}