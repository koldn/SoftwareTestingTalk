package org.testingtalk.transfer;

import org.testingtalk.account.AccountInfoService;
import org.testingtalk.log.TransferLogService;
import org.testingtalk.account.AccountInfo;
import org.testingtalk.account.AccountInfoServiceImpl;

public class TransferService
{
    private final AccountInfoService accountInfoService;
    private final TransferLogService logService;

    private int successFullTransfers = 0;
    private int failedTransfers =0;

    public TransferService(AccountInfoService accountInfoService, TransferLogService logService){
        this.accountInfoService = accountInfoService;
        this.logService = logService;
    }

    public void makeTransfer(long sourceAccount, long targetAccount, double moneyAmount){
        //Проверить аккаунт источник
        if(sourceAccount <= 0){
            throw new IllegalArgumentException("Invalid source account id: " + sourceAccount);
        }
        //Проверить целевой аккаунт
        if(targetAccount <= 0){
            throw new IllegalArgumentException("Invalid target account id: "+targetAccount);
        }
        //Проверить сумму перевода
        if(moneyAmount <= 0){
            throw new IllegalArgumentException("Non positive transfer amount is specified");
        }
        //Проверить наличие суммы у аккаунта источника
        AccountInfo fromAccountInfo = accountInfoService.getInfo(sourceAccount);
        if (fromAccountInfo.getCurrentBalance() < moneyAmount)
        {
            throw new UnsupportedOperationException("Not enough credit balance");
        }

        try
        {
            performTransfer(sourceAccount, targetAccount, moneyAmount, fromAccountInfo);
            logService.logTransfer(sourceAccount, targetAccount, moneyAmount);
            successFullTransfers++;
        }
        catch (Exception e)
        {
            failedTransfers ++;
            e.printStackTrace();
        }
    }

    private void performTransfer(long sourceAccount, long targetAccount, double moneyAmount,
            AccountInfo fromAccountInfo)
    {
        double newSourceBalance = fromAccountInfo.getCurrentBalance() - moneyAmount;
        accountInfoService.updateBalance(sourceAccount, newSourceBalance);

        double newTargetBalance = accountInfoService.getInfo(targetAccount).getCurrentBalance() + moneyAmount;
        accountInfoService.updateBalance(targetAccount, newTargetBalance);
    }

    public int getSuccessFullTransfers()
    {
        return successFullTransfers;
    }

    public int getFailedTransfers()
    {
        return failedTransfers;
    }
}
