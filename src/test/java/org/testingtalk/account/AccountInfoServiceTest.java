package org.testingtalk.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AccountInfoServiceTest
{
    private AccountInfoServiceImpl service;

    @Before
    public void setup(){
        this.service = new AccountInfoServiceImpl();
    }

    @Test
    public void createNewAccountInfo() throws Exception
    {
        AccountInfo newAccountInfo = service.createNewAccountInfo();
        assertEquals(0, newAccountInfo.getCurrentBalance(),0.0);
    }

    @Test
    public void updateBalance() throws Exception
    {
        AccountInfo newAccountInfo = service.createNewAccountInfo();
        service.updateBalance(newAccountInfo.getAccountId(), 10.00);
        assertEquals(10.00, service.getInfo(newAccountInfo.getAccountId()).getCurrentBalance(), 0.0);
    }
}