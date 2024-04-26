import com.example.national_bank_of_egypt.Models.SavingAccount;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {
    @Test
    public void testSavingAccountConstructorAndGetters() {
        String owner = "John Doe";
        String accountNumber = "123456789";
        double balance = 1000.0;
        double withdrawLimit = 500.0;

        SavingAccount savingAccount = new SavingAccount(owner, accountNumber, balance, withdrawLimit);

        assertEquals(owner, savingAccount.ownerProperty().get());
        assertEquals(accountNumber, savingAccount.account_numberProperty().get());
        assertEquals(balance, savingAccount.balanceProperty().get(), 0.01); // Adding a delta for double comparison
        assertEquals(withdrawLimit, savingAccount.withdrawLimitProperty().get(), 0.01); // Adding a delta for double comparison
    }

    @Test
    public void testToString() {
        String owner = "John Doe";
        String accountNumber = "123456789";
        double balance = 1000.0;
        double withdrawLimit = 500.0;

        SavingAccount savingAccount = new SavingAccount(owner, accountNumber, balance, withdrawLimit);

        assertEquals(accountNumber, savingAccount.toString());
    }
}