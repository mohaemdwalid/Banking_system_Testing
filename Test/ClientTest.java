import com.example.national_bank_of_egypt.Models.Account;
import com.example.national_bank_of_egypt.Models.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    String fName = "John";
    String lName = "Doe";
    String uName = "johndoe";
    LocalDate dateCreated = LocalDate.of(2022, 4, 26);
    Account checkingAccount = new Account("John Doe", "123456789", 1000.0);
    Account savingAccount = new Account("John Doe", "987654321", 2000.0);
    @Test
    public void testClientConstructorAndGetters() {
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
        Client client = new Client(fName, lName, uName, checkingAccount, savingAccount, dateCreated);
        assertEquals(fName, client.getFirstName());
        assertEquals(lName, client.getLastName());
        assertEquals(uName, client.getUserName());
        assertEquals(checkingAccount, client.getCheckingAccount());
        assertEquals(savingAccount, client.getSavingAccount());
        assertEquals(dateCreated, client.getDataCreated());
    }


}