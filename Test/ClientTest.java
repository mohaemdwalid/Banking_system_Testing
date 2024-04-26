import com.example.national_bank_of_egypt.Models.Account;
import com.example.national_bank_of_egypt.Models.Client;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void testClientConstructorAndGetters() {
        String fName = "John";
        String lName = "Doe";
        String uName = "johndoe";
        LocalDate dateCreated = LocalDate.of(2022, 4, 26);
        Account checkingAccount = new Account("John Doe", "123456789", 1000.0);
        Account savingAccount = new Account("John Doe", "987654321", 2000.0);

        Client client = new Client(fName, lName, uName, checkingAccount, savingAccount, dateCreated);

        assertEquals(fName, client.firstNameProperty().get());
        assertEquals(lName, client.lastNameProperty().get());
        assertEquals(uName, client.userNameProperty().get());
        assertEquals(checkingAccount, client.checkingAccountProperty().get());
        assertEquals(savingAccount, client.savingAccountProperty().get());
        assertEquals(dateCreated, client.dataCreatedProperty().get());
    }

    @Test
    public void testGetterMethods() {
        String fName = "John";
        String lName = "Doe";
        String uName = "johndoe";
        LocalDate dateCreated = LocalDate.of(2022, 4, 26);
        Account checkingAccount = new Account("John Doe", "123456789", 1000.0);
        Account savingAccount = new Account("John Doe", "987654321", 2000.0);

        Client client = new Client(fName, lName, uName, checkingAccount, savingAccount, dateCreated);

        assertEquals(fName, client.getFirstName());
        assertEquals(lName, client.getLastName());
        assertEquals(uName, client.getUserName());
        assertEquals(checkingAccount, client.getCheckingAccount());
        assertEquals(savingAccount, client.getSavingAccount());
        assertEquals(dateCreated, client.getDataCreated());
    }

    // Add more tests as needed
}