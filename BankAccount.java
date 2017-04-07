/**
 * BankAccount class
 * This class simulates a bank account.
 */


public abstract class BankAccount
{
	private int balance;				//Account balance
	private double annualRate;			//Annual interest rate
	private int depositCount = 0;		//Number of deposits
	private int withdrawCount = 0;		//Number of withdrawals
	private int serviceCharge = 0;		//Monthly service charge

	/**
	 * Constructor initializes balance and annualRate
	 */

	public BankAccount(double balance, double annualRate)
	{
		this.balance = Cash.convert(balance);
		this.annualRate = annualRate;
	}

	/**
	 * Retrieves current balance
	 */

	public double getBalance()
	{
		return Cash.convert(balance);
	}

	/**
	 * Sets monthly service charge
	 */

	public void setCharges(int amount)
	{
		serviceCharge = amount;
	}

	/**
	 * Retrieves withdraw count
	 */

	public int getWithdrawals()
	{
		return withdrawCount;
	}

	/**
	 * Retrieves deposit count
	 */

	public int getDeposits()
	{
		return depositCount;
	}

	/**
	 * Deposits funds to balance
	 */

	public void deposit(double amount)
	{
		balance += Cash.convert(amount);
		depositCount++;
	}

	/**
	 * Withdraws funds from balance
	 */

	public void withdraw(double amount)
	{
		balance -= Cash.convert(amount);
		withdrawCount++;
	}

	/**
	 * Subtracts the monthly service charges from the balance, then calculates interest
	 */

	public int calcInterest()
	{
		return (int)((annualRate/12) * balance);
	}

	/**
	 * Processes monthly charges, interests and resets transaction counts
	 */

	public double monthlyProcess()
	{
		balance -= serviceCharge * 100;

		//Stores earned interest after calculating service charges
		int earned = calcInterest();

		balance += earned;
		depositCount = 0;
		withdrawCount = 0;
		serviceCharge = 0;

		return Cash.convert(earned);
	}
}