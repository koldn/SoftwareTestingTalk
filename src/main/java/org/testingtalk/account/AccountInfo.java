package org.testingtalk.account;

public class AccountInfo
{
    private double currentBalance;
    private final long accountId;

    public AccountInfo(long id)
    {
        this.currentBalance = 0;
        this.accountId = id;
    }

    public AccountInfo(long id, double balance){
        this.currentBalance = balance;
        this.accountId = id;
    }

    public long getAccountId()
    {
        return accountId;
    }

    public double getCurrentBalance()
    {
        return currentBalance;
    }

    void updateBalance(double newBalance){
        this.currentBalance = newBalance;
    }

    public AccountInfo clone(){
        return new AccountInfo(this.getAccountId(), this.getCurrentBalance());
    }

}
