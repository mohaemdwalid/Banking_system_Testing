import com.example.national_bank_of_egypt.Models.CheckingAccount;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {
    @Test
    public void testCheckingAccountConstructorAndGetters() {
        String owner = "John Doe";
        String accountNumber = "123456789";
        double balance = 1000.0;
        int transactionLimit = 5;

        CheckingAccount checkingAccount = new CheckingAccount(owner, accountNumber, balance, transactionLimit);

        assertEquals(owner, checkingAccount.ownerProperty().get());
        assertEquals(accountNumber, checkingAccount.account_numberProperty().get());
        assertEquals(balance, checkingAccount.balanceProperty().get(), 0.01); // Adding a delta for double comparison
        assertEquals(transactionLimit, checkingAccount.TransactionLimitProp().get());
    }

    @Test
    public void testToString() {
        String owner = "John Doe";
        String accountNumber = "123456789";
        double balance = 1000.0;
        int transactionLimit = 5;

        CheckingAccount checkingAccount = new CheckingAccount(owner, accountNumber, balance, transactionLimit);

        assertEquals(accountNumber, checkingAccount.toString());
    }
}