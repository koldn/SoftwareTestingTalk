package org.testingtalk.account;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountInfoServiceImpl implements AccountInfoService
{
    private final AtomicInteger idSequence = new AtomicInteger(1);

    private final ConcurrentHashMap<Long, AccountInfo> accountsData = new ConcurrentHashMap<Long, AccountInfo>();

    @Override
    public AccountInfo createNewAccountInfo(){
        AccountInfo newInfo = new AccountInfo(idSequence.getAndIncrement());
        accountsData.put(newInfo.getAccountId(), newInfo);
        return newInfo;
    }

    @Override
    public void updateBalance(long infoId, double newBalance) throws IllegalArgumentException{
        AccountInfo accountInfo = this.accountsData.get(infoId);
        if(accountInfo == null){
            throw new IllegalArgumentException("Account info with id "+infoId+"not found");
        }
        accountInfo.updateBalance(newBalance);
        accountsData.put(infoId, accountInfo);
    }

    @Override
    public AccountInfo getInfo(long id){
        return accountsData.get(id);
    }

}
