package org.testingtalk.account;

public interface AccountInfoService
{
    AccountInfo createNewAccountInfo();

    void updateBalance(long infoId, double newBalance) throws IllegalArgumentException;

    AccountInfo getInfo(long id);
}
