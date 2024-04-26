import com.example.national_bank_of_egypt.Models.DataBaseDriver;
import org.junit.jupiter.api.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class databasedrivertest {
    private DataBaseDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new DataBaseDriver();
    }

    @Test
    public void testGetClientData() {
        ResultSet resultSet = driver.getAllClientData();
        assertNotNull(resultSet);
    }


   @Test
    public void testCreateClient() throws SQLException {
        String fName = "John";
        String lName = "Doe";
        String uName = "@johndoe30";
        String password = "password123";
        LocalDate dateCreated = LocalDate.now();

        driver.createClient(fName, lName, uName, password, dateCreated);

        ResultSet resultSet = driver.searchClient(uName);
        assertTrue(resultSet.next());
        assertEquals(fName, resultSet.getString("FirstName"));
        assertEquals(lName, resultSet.getString("LastName"));
        assertEquals(uName, resultSet.getString("PayeeAddress"));
        assertEquals(password, resultSet.getString("Password"));
        // Add more assertions if needed
    }

    @Test
    public void testCreateCheckingAccount() throws SQLException {
        String owner = "John";
        String number = "123456";
        double tLimit = 1000.0;
        double balance = 5000.0;

        driver.createCheckingAccount(owner, number, tLimit, balance);

        ResultSet resultSet = driver.getCheckingAccountData(owner);
        assertTrue(resultSet.next());
        assertEquals(owner, resultSet.getString("Owner"));
        assertEquals(number, resultSet.getString("AccountNumber"));
        assertEquals(tLimit, resultSet.getDouble("TransactionLimit"));
        assertEquals(balance, resultSet.getDouble("Balance"));
        // Add more assertions if needed
    }

    @Test
    public void testCreateSavingAccount() throws SQLException {
        String owner = "johndoe";
        String number = "123456";
        double wLimit = 500.0;
        double balance = 10000.0;

        driver.createSavingAccount(owner, number, wLimit, balance);

        ResultSet resultSet = driver.getSavingAccountData(owner);
        assertTrue(resultSet.next());
        assertEquals(owner, resultSet.getString("Owner"));
        assertEquals(number, resultSet.getString("AccountNumber"));
        assertEquals(wLimit, resultSet.getDouble("WithdrawalLimit"));
        assertEquals(balance, resultSet.getDouble("Balance"));
        // Add more assertions if needed
    }




    @AfterEach
    public void tearDown() {
        // Clean up resources if needed
    }
}