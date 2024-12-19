package Application;

import Controller.IMBankController;
import Repository.*;
import Service.IMBankServiceImpl;

public class IMBankApplication {
    public static void main(String[] args) {
        IMBankServiceImpl bankService = new IMBankServiceImpl(
                new AffiliatedBankRepository(), new BankAccountRepository(),
                new CardInfoRepository(), new CustomerRepository(),
                new PersonRepository(), new TransactionRepository(), new FlagRepository()
        );

        IMBankController bankController =
                new IMBankController(bankService);

        bankController.showLoginWindow();
    }
}
