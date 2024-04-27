import com.example.national_bank_of_egypt.Models.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingAccountTest {
    String owner = "@mosalah10";
    String accountNumber = "123456789";
    double balance = 1000.0;
    double withdrawLimit = 500.0;
    @Test
    public void testSavingAccountConstructorAndGetters() {
        SavingAccount savingAccount = new SavingAccount(owner, accountNumber, balance, withdrawLimit);
        assertEquals(owner, savingAccount.ownerProperty().get());
        assertEquals(accountNumber, savingAccount.account_numberProperty().get());
        assertEquals(balance, savingAccount.balanceProperty().get(), 0.01);
        assertEquals(withdrawLimit, savingAccount.withdrawLimitProperty().get(), 0.01);
    }
    @Test
    public void testSetBalance() {
        SavingAccount savingAccount = new SavingAccount(owner, accountNumber, balance, withdrawLimit);
        double newBalance = 1500.0;
        savingAccount.setBalance(newBalance);
        assertEquals(newBalance, savingAccount.balanceProperty().get(), 0.01);
    }

    @Test
    public void testToString() {
        SavingAccount savingAccount = new SavingAccount(owner, accountNumber, balance, withdrawLimit);
        assertEquals(accountNumber, savingAccount.toString());
    }
}