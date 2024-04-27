import com.example.national_bank_of_egypt.Models.DataBaseDriver;
import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Models.SavingAccount;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseDriverTest {
    private static DataBaseDriver dbDriver;
    private SavingAccount savingAccount;
    private Model model;

    @BeforeAll
    public static void setUp() {
        dbDriver = new DataBaseDriver();
    }

    @AfterAll
    public static void tearDown() {
        // Perform cleanup, if any
    }
    //Deposit
    @Test
    public void testDepositSaving() throws SQLException {
        dbDriver.DepositSaving("@bBaker1", 10000.0);
        double balance = dbDriver.getSavingsAccountBalance("@bBaker1");
        assertEquals(10000.0, balance);
    }
    //Loan
    @Test
    public void testRequestLoan() throws SQLException {
        dbDriver.RequestLoan("@mwalid1", 500.0);
        double balance = dbDriver.getCheckingccountBalance("@mwalid1");
        assertEquals(500.0, balance);
    }


    //Getters Balance
    @Test
    public void testGetSavingsAccountBalance() throws SQLException {
        // Assuming the initial balance for the savings account is 100.0
        double expectedBalance = 100.0;
        double balance = dbDriver.getSavingsAccountBalance("@ymo24");
        assertEquals(expectedBalance, balance);
    }
    @Test
    public void testGetCheckingAccountBalance() throws SQLException {
        // Assuming the initial balance for the checking account is 5000.0
        double expectedBalance = 5000.0;
        double balance = dbDriver.getCheckingccountBalance("John");
        assertEquals(expectedBalance, balance);
    }
    //Getters Data
    @Test
    public void testGetCheckingAccountData() throws SQLException {
        String owner = "owner";
        ResultSet resultSet = dbDriver.getCheckingAccountData(owner);
        assertNotNull(resultSet);
        assertTrue(resultSet.next()); // Move cursor to the  row
        assertEquals(owner, resultSet.getString("Owner"));
    }

    @Test
    public void testGetSavingAccountData() throws SQLException {
        // Assuming there is a savings account associated with the specified owner
        String owner = "owner";
        ResultSet resultSet = dbDriver.getSavingAccountData(owner);
        assertTrue(resultSet.next()); // Move cursor to the  row
        assertNotNull(resultSet);
        assertEquals(owner, resultSet.getString("Owner"));
    }

    //Test Getter Last Id
//    @Test
//    public void testGetLastClientsId() throws SQLException {
//        int lastClientId = dbDriver.getLatClientsId();
//        // Verify if the retrieved last client ID matches the expected value
//        assertEquals(40, lastClientId);
//    }

    @Test
    public void testGetClientData() throws SQLException {
        ResultSet resultSet = dbDriver.getClientDate("@bBaker1", "123456");
        assertNotNull(resultSet);
         assertTrue(resultSet.next()); // Move cursor to the first row
    }

    @Test
    public void testGetTransaction() throws SQLException {
        ResultSet resultSet = dbDriver.getTransaction("sender1", 10);
        assertNotNull(resultSet);
    }

    @Test
    public void testGetAdminData() throws SQLException {
        ResultSet resultSet = dbDriver.getAdminDate("Admin", "123456");
        assertNotNull(resultSet);
    }

    @Test
    public void testCreateClient() throws SQLException {
        String fName = "John";
        String lName = "Doe";
        String uName = "@johndoe1";
        String password = "password123";
        LocalDate dateCreated = LocalDate.now();
        // Create client
        dbDriver.createClient(fName, lName, uName, password, dateCreated);
        ResultSet resultSet = dbDriver.getClientDate(uName, password);
        assertNotNull(resultSet);
    }

    @Test
    public void testCreateCheckingAccount() throws SQLException {
        String owner = "owner";
        String number = "123456789";
        double tLimit = 1000.0;
        double balance = 500.0;
        dbDriver.createCheckingAccount(owner, number, tLimit, balance);
        ResultSet resultSet = dbDriver.getCheckingAccountData(owner);
        assertNotNull(resultSet);
    }

    @Test
    public void testCreateSavingAccount() throws SQLException {
        // Assuming test data for creating a saving account
        String owner = "owner";
        String number = "987654321";
        double wLimit = 500.0;
        double balance = 1000.0;
        // Create saving account
        dbDriver.createSavingAccount(owner, number, wLimit, balance);
        ResultSet resultSet = dbDriver.getSavingAccountData(owner);
        assertNotNull(resultSet);
    }

    @Test
    public void testGetAllClientData() throws SQLException {
        ResultSet resultSet = dbDriver.getAllClientData();
        assertNotNull(resultSet);
    }

    @Test
    public void testNewTransaction() throws SQLException {
        String sender = "@mwalid1";
        String receiver = "@bBaker1";
        double amount = 100.0;
        String message = "Test message";

        dbDriver.newTransaction(sender, receiver, amount, message);

    }

//    @Test
//    public void testUpdateBalanceSavingAdd() throws SQLException {
//        // Call the updateBalanceSaving method to add an amount to the saving account
//        dbDriver.updateBalanceSaving("@ttest36", 200.0, "ADD");
//
//        // Retrieve the current balance from the database
//        double balance = dbDriver.getSavingsAccountBalance("@ttest36");
//        double newBalance = balance + 200.0;
//        double bb= model.getClient().savingAccountProperty().get().setBalance(model.getDataBaseDriver().getSavingsAccountBalance("@ttest36"));
//        System.out.println(bb);
//
//        // Assert that the current balance matches the expected balance after addition
//        assertEquals(newBalance, balance); // Assuming the initial balance was 100.0
//    }
//
//    @Test
//    public void testUpdateBalanceSavingSubtract() throws SQLException {
//        // Call the updateBalanceSaving method to subtract an amount from the saving account
//        dbDriver.updateBalanceSaving("ttest36", 1000.0, "SUB");
//
//        // Retrieve the current balance from the database
//        double balance = dbDriver.getSavingsAccountBalance("ttest36");
//
//        // Assert that the current balance matches the expected balance after subtraction
//        assertEquals(10000.0, balance); // Assuming the initial balance was 11000.0
//    }
//    @Test
//    public void testUpdateBalanceCheckingAdd() throws SQLException {
//        // Call the updateBalanceChecking method to add an amount to the checking account
//        dbDriver.updateBalanceChecking("@ttest36", 200.0, "ADD");
//
//        // Retrieve the current balance from the database
//        double balance = dbDriver.getCheckingccountBalance("@ttest36");
//
//        // Assert that the current balance matches the expected balance after addition
//        assertEquals(300.0, balance); // Assuming the initial balance was 100.0
//    }
//
//    @Test
//    public void testUpdateBalanceCheckingSubtract() throws SQLException {
//        // Call the updateBalanceChecking method to subtract an amount from the checking account
//        dbDriver.updateBalanceChecking("ttest36", 1000.0, "SUB");
//
//        // Retrieve the current balance from the database
//        double balance = dbDriver.getCheckingccountBalance("ttest36");
//
//        // Assert that the current balance matches the expected balance after subtraction
//        assertEquals(10000.0, balance); // Assuming the initial balance was 11000.0
//    }


}
