import com.example.national_bank_of_egypt.Models.Account;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    @Test
    public void testAccountConstructorAndGetters() {
        String owner = "John Doe";
        String accountNumber = "123456789";
        double balance = 1000.0;

        Account account = new Account(owner, accountNumber, balance);

        assertEquals(owner, account.ownerProperty().get());
        assertEquals(accountNumber, account.account_numberProperty().get());
        assertEquals(balance, account.balanceProperty().get(), 0.01); // Adding a delta for double comparison
    }

    @Test
    public void testSetBalance() {
        Account account = new Account("John Doe", "123456789", 1000.0);

        double newBalance = 1500.0;
        account.setBalance(newBalance);

        assertEquals(newBalance, account.balanceProperty().get(), 0.01); // Adding a delta for double comparison
    }
}