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
	 * Calculates monthly interest earned and adds to balance
	 */

	public int calcInterest()
	{
		return Cash.convert((annualRate/12) * balance);
	}

	/**
	 * Processes monthly charges, interests and resets transaction counts
	 */

	public void monthlyProcess()
	{
		balance -= serviceCharge * 100;
		balance += calcInterest();
		depositCount = 0;
		withdrawCount = 0;
		serviceCharge = 0;
	}
}