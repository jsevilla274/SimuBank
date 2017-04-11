/**
 * SavingsAccount class
 * Inherits from BankAccount to simulate a savings account.
 */

public class SavingsAccount extends BankAccount
{
	private boolean accountActive = true;		// Indicator of account status

	/**
	 * Calls superclass constructor to initialize balance and annualRate
	 */

	public SavingsAccount(double balance, double annualRate)
	{
		super(balance, annualRate);
	}

	/**
	 * Calculates and checks account status
	 */

	public boolean checkStatus()
	{
		//Minimum balance before account becomes frozen is $25
		return accountActive = (getBalance() >= 25);
	}

	/**
	 * Deposits and determines status
	 */

	public void deposit(double amount)
	{
		super.deposit(amount);
		checkStatus();
	}

	/**
	 * Withdraws given an active account
	 */

	public void withdraw(double amount)
	{
		if (accountActive)
		{
			super.withdraw(amount);
			checkStatus();
		}
	}

	/**
	 * Calculates service charge and processes monthly fees
	 */

	public double monthlyProcess()
	{
		int totalWithdraw = getWithdrawals();

		//Service charge after 4 withdrawals
		if (totalWithdraw > 4)
		{
			//$1 charge for each withdrawal after 4
			setCharges(totalWithdraw - 4);
		}
		return super.monthlyProcess();
	}

}