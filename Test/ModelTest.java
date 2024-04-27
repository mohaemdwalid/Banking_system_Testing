import com.example.national_bank_of_egypt.Models.Client;
import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Models.Transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private Model model;
    @BeforeEach
    void setUp() {
        // Initialize a new Model instance before each test
        model = Model.getInstance();
        // Reset database state or perform any necessary setup here
    }

    @AfterEach
    void tearDown() {
        // Clean up resources or reset any changes made during the test
        model = null;
    }

    @Test
        void getAdminLoginSuccessFlag() {
            assertFalse(model.getAdminLoginSuccessFlag());
        }

    @Nested
    class AdminTests {
        @Test
        void getViewFactory() {
            assertNotNull(model.getViewFactory());
        }
        @Test
        void getDataBaseDriver() {
            assertNotNull(model.getDataBaseDriver());
        }

        @Test
        void setAdminLoginSuccessFlag() {
            model.setAdminLoginSuccessFlag(true);
            assertTrue(model.getAdminLoginSuccessFlag());
        }
        @Test
        void evaluateAdminCred() {
            model.evaluateAdminCred("Admin", "123456");
            assertTrue(model.getAdminLoginSuccessFlag());
        }
    }
    @Test
    void getClientLoginSuccessFlag() {
        assertFalse(model.getClientLoginSuccessFlag());
    }

    @Nested
    class ClientTests {
        @Test
        void getViewFactory() {
            assertNotNull(model.getViewFactory());
        }
        @Test
        void getDataBaseDriver() {
            assertNotNull(model.getDataBaseDriver());
        }
        @Test
        void setClientLoginSuccessFlag() {
            model.setClientLoginSuccessFlag(true);
            assertTrue(model.getClientLoginSuccessFlag());
        }
        @Test
        void getClient() {
            assertNotNull(model.getClient());
        }

        @Test
        void evaluateClientCred() {
            model.evaluateClientCred("@cClark3", "123456");
            assertTrue(model.getClientLoginSuccessFlag());
        }

        @Test
        void setLatesTrans() {
            model.setLatesTrans();
            ObservableList<Transactions> latestTransactions = model.getLatesTrans();
            assertNotNull(latestTransactions);
            assertFalse(latestTransactions.isEmpty());
        }

        @Test
        void getAllTrans() {
            model.setAllTrans();
            ObservableList<Transactions> allTransactions = model.getAllTrans();
            assertNotNull(allTransactions);
            assertFalse(allTransactions.isEmpty());
        }

        @Test
        void getClients() {
            model.setClient();
            ObservableList<Client> clients = model.getClients();
            assertNotNull(clients);
            assertFalse(clients.isEmpty());
        }

        @Test
        void getCheckingAccount() {
            assertNotNull(model.getCheckingAccount("@mWageeh27"));
        }

        @Test
        void getSavingAccount() {
            assertNotNull(model.getSavingAccount("@mWageeh27"));
        }
    }

    @Test
    void searchClient() {
        String userName = "@mwalid1";
        model.searchClient(userName);
        ObservableList<Client> result = model.searchClient(userName);

        Client client = result.get(0);

        assertEquals("mo", client.getFirstName());
        assertEquals("walid", client.getLastName());
        assertEquals(userName, client.getUserName());
        assertEquals(userName, client.userNameProperty().get());
        assertEquals(LocalDate.parse("2024-04-24"), client.dataCreatedProperty().get());
//        assertEquals(1600, client.getSavingAccount().balanceProperty().get());
//        assertEquals(100, client.getCheckingAccount().balanceProperty().get());
    }

    @Test
    void prepareTransactions() {
        ObservableList<Transactions> transactions = FXCollections.observableArrayList();
        int limit = 10;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "username", "password")) {
            insertTestData(connection);

            Model model = new Model();
            model.prepareTransactions(transactions, limit);

            assertEquals(3, transactions.size());
            Transactions firstTransaction = transactions.get(0);
            assertEquals("sender1", firstTransaction.getSender());
            assertEquals("receiver1", firstTransaction.getReceiver());
            assertEquals(100.0, firstTransaction.getAmount());
            assertEquals(LocalDate.of(2024, 4, 27), firstTransaction.getDate());
            assertEquals("message1", firstTransaction.getMessage());
            // Verify other transactions as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Helper method to insert test data into the database
    private void insertTestData(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO transactions (Sender, Receiver, Amount, Date, Message) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, "sender1");
            statement.setString(2, "receiver1");
            statement.setDouble(3, 100.0);
            statement.setDate(4, java.sql.Date.valueOf(LocalDate.of(2024, 4, 27)));
            statement.setString(5, "message1");
            statement.addBatch();

            statement.setString(1, "sender2");
            statement.setString(2, "receiver2");
            statement.setDouble(3, 200.0);
            statement.setDate(4, java.sql.Date.valueOf(LocalDate.of(2024, 4, 26)));
            statement.setString(5, "message2");
            statement.addBatch();

            statement.setString(1, "sender3");
            statement.setString(2, "receiver3");
            statement.setDouble(3, 300.0);
            statement.setDate(4, java.sql.Date.valueOf(LocalDate.of(2024, 4, 25)));
            statement.setString(5, "message3");
            statement.addBatch();

            statement.executeBatch();
        }
    }


}
