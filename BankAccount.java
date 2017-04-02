/**
 * BankAccount class
 * This class simulates a bank account.
 */


public abstract class BankAccount
{
	private double balance;				//Account balance
	private double interestRate;		//Interest rate
	private int depositCount = 0;		//Number of deposits
	private int withdrawalCount = 0;	//Number of withdrawals
	private int serviceCharge = 0;		//Monthly service charge

	/**
	 * Constructor initializes balance and interestRate
	 */

	public BankAccount(double balance, double interestRate)
	{
		this.balance = balance;
		this.interestRate = interestRate;
	}

}