import com.example.national_bank_of_egypt.Models.Account;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    String owner = "JohnDoe";
    String accountNumber = "123456789";
    double balance = 1000.0;
    @Test
    public void testAccountConstructorAndGetters() {
        Account account = new Account(owner, accountNumber, balance);
        assertEquals(owner, account.ownerProperty().get());
        assertEquals(accountNumber, account.account_numberProperty().get());
        assertEquals(balance, account.balanceProperty().get(), 0.01);
    }
    @Test
    public void testSetBalance() {
        Account account = new Account(owner, accountNumber, balance);
        double newBalance = 1500.0;
        account.setBalance(newBalance);
        assertEquals(newBalance, account.balanceProperty().get(), 0.01);
    }
}