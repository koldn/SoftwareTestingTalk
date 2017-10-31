package org.testingtalk.transfer;

import org.testingtalk.account.AccountInfo;
import org.testingtalk.account.AccountInfoService;

public class AccountInfoStub implements AccountInfoService
{
    private final static AccountInfo STUB = new AccountInfo(999, 10.00);
    private final static AccountInfoStub INSTANCE = new AccountInfoStub();

    private AccountInfoStub()
    {

    }

    public static AccountInfoStub get()
    {
        return INSTANCE;
    }

    @Override
    public AccountInfo createNewAccountInfo()
    {
        return STUB.clone();
    }

    @Override
    public void updateBalance(long infoId, double newBalance) throws IllegalArgumentException
    {

    }

    @Override
    public AccountInfo getInfo(long id)
    {
        return STUB.clone();
    }
}
