package org.testingtalk.log;

public class TransferLogService
{
    public void logTransfer(long sourceAccountId, long targetAccountId, double transferAmount){
        String message = "Account " + targetAccountId + " received " + transferAmount + " from " + sourceAccountId;
        System.out.println(message);
    }
}
