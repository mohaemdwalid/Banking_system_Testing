import com.example.national_bank_of_egypt.Models.Transactions;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionsTest {
    @Test
    public void testTransactionsConstructorAndGetters() {
        String sender = "John";
        String receiver = "Doe";
        double amount = 100.0;
        LocalDate date = LocalDate.of(2022, 4, 26);
        String message = "Transaction message";

        Transactions transaction = new Transactions(sender, receiver, amount, date, message);

        assertEquals(sender, transaction.senderProperty().get());
        assertEquals(receiver, transaction.receiverProperty().get());
        assertEquals(amount, transaction.amountProperty().get(), 0.01); // Adding a delta for double comparison
        assertEquals(date, transaction.dateProperty().get());
        assertEquals(message, transaction.messageProperty().get());
    }
}