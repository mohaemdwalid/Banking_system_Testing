import com.example.national_bank_of_egypt.Models.CheckingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountTest {
    String owner = "MahmoudWage";
    String accountNumber = "123456789";
    double balance = 1000.0;
    int transactionLimit = 5;
    @Test
    public void testCheckingAccountConstructorAndGetters() {
        CheckingAccount checkingAccount = new CheckingAccount(owner, accountNumber, balance, transactionLimit);
        assertEquals(owner, checkingAccount.ownerProperty().get());
        assertEquals(accountNumber, checkingAccount.account_numberProperty().get());
        assertEquals(balance, checkingAccount.balanceProperty().get(), 0.01);
        assertEquals(transactionLimit, checkingAccount.TransactionLimitProp().get(),0.01);
    }
    @Test
    public void testSetBalance() {
        CheckingAccount checkingAccount = new CheckingAccount(owner, accountNumber, balance, transactionLimit);
        double newBalance = 1500.0;
        checkingAccount.setBalance(newBalance);
        assertEquals(newBalance, checkingAccount.balanceProperty().get(), 0.01);
    }
    @Test
    public void testToString() {
        CheckingAccount checkingAccount = new CheckingAccount(owner, accountNumber, balance, transactionLimit);
        assertEquals(accountNumber, checkingAccount.toString());
    }
}