/**
 * BankAccount class
 * This class simulates a bank account.
 */


public abstract class BankAccount
{
	private double balance;				//Account balance
	private double annualRate;			//Annual interest rate
	private int depositCount = 0;		//Number of deposits
	private int withdrawCount = 0;		//Number of withdrawals
	private int serviceCharge = 0;		//Monthly service charge

	/**
	 * Constructor initializes balance and annualRate
	 */

	public BankAccount(double balance, double annualRate)
	{
		this.balance = balance;
		this.annualRate = annualRate;
	}

	/**
	 * Retrieves current balance
	 */

	public double getBalance()
	{
		return balance;
	}

	/**
	 * Retrieves withdraw count
	 */

	public int getWithdrawals()
	{
		return withdrawCount;
	}

	/**
	 * Sets withdraw count to 0
	 */

	public void resetWithdrawals()
	{
		withdrawCount = 0;
	}

	/**
	 * Retrieves deposit count
	 */

	public int getDeposits()
	{
		return depositCount;
	}

	/**
	 * Sets deposit count to 0
	 */

	public void resetDeposits()
	{
		depositCount = 0;
	}

	/**
	 * Deposits funds to balance
	 */

	public void deposit(double amount)
	{
		balance += amount;
		depositCount++;
	}

	/**
	 * Withdraws funds from balance
	 */

	public void withdraw(double amount)
	{
		balance -= amount;
		withdrawCount++;
	}

	/**
	 * Calculates monthly interest earned and adds to balance
	 */

	public void calcInterest()
	{
		double interestEarned = (annualRate/12) * balance;
		balance += interestEarned;
	}

	/**
	 * Processes monthly charges, interests and resets transaction counts
	 */

	public void monthlyProcess()
	{
		balance -= serviceCharge;
		calcInterest();
		depositCount = 0;
		withdrawCount = 0; 
	}
}