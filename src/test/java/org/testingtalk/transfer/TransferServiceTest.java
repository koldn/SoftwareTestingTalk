package org.testingtalk.transfer;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.testingtalk.account.AccountInfoService;
import org.testingtalk.log.TransferLogService;
import org.testingtalk.account.AccountInfo;
import org.testingtalk.account.AccountInfoServiceImpl;

public class TransferServiceTest
{
    @Test
    public void testSuccessfullTransfers() throws Exception
    {
        //Подготовка
        TransferService transfers = new TransferService(AccountInfoStub.get(), new TransferLogService());

        //Действия
        transfers.makeTransfer(1, 2, 2.0);

        //Проверка
        Assert.assertEquals(1, transfers.getSuccessFullTransfers());
    }

    @Test
    public void testTransferLogged(){
        //Подготовка
        TransferLogService mockedLogService = Mockito.mock(TransferLogService.class);
        TransferService transfers = new TransferService(AccountInfoStub.get(), mockedLogService);

        //Действия
        transfers.makeTransfer(1, 2, 2.0);

        //Проверка
        //Проверяем, что метод logTransfer вызвался единожды с аргументами 1, 2, 2.0
        Mockito.verify(mockedLogService, Mockito.times(1)).logTransfer(1,2,2.0);
    }

    @Test
    public void testFailedTransfer(){
        //Подготовка
        AccountInfoService mockInfoService = Mockito.mock(AccountInfoService.class);
        Mockito.when(mockInfoService.getInfo(1)).thenReturn(new AccountInfo(1, 10.0));
        Mockito.when(mockInfoService.getInfo(2)).thenThrow(RuntimeException.class);

        //Действия
        TransferService transferService = new TransferService(mockInfoService, new TransferLogService());
        transferService.makeTransfer(1,2, 2.0);

        //Проверки
        Assert.assertEquals(1, transferService.getFailedTransfers());
    }

    @Test
    public void transferTest(){
        //Подготовка
        AccountInfoService service = new AccountInfoServiceImpl();
        AccountInfo sourceInfo = service.createNewAccountInfo();
        service.updateBalance(sourceInfo.getAccountId(), 10.0);
        AccountInfo targetInfo = service.createNewAccountInfo();
        TransferService transferService = new TransferService(service, new TransferLogService());

        //Действия
        transferService.makeTransfer(sourceInfo.getAccountId(), targetInfo.getAccountId(), 5.0);

        //Проверки
        Assert.assertEquals(1, transferService.getSuccessFullTransfers());
        Assert.assertEquals(5.0, service.getInfo(sourceInfo.getAccountId()).getCurrentBalance(), 0.0);
        Assert.assertEquals(5.0, service.getInfo(targetInfo.getAccountId()).getCurrentBalance(), 0.0);
    }

}