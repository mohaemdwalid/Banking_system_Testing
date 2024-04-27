import com.example.national_bank_of_egypt.Models.Transactions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsTest {
    @Test
    public void testTransactionsConstructorAndGetters() {
        String sender = "John";
        String receiver = "Doe";
        double amount = 100.0;
        LocalDate date = LocalDate.of(2022, 4, 26);
        String message = "For the ps5";
        Transactions transaction = new Transactions(sender, receiver, amount, date, message);
        assertEquals(sender, transaction.senderProperty().get());
        assertEquals(receiver, transaction.receiverProperty().get());
        assertEquals(amount, transaction.amountProperty().get(), 0.01);
        assertEquals(date, transaction.dateProperty().get());
        assertEquals(message, transaction.messageProperty().get());
        assertEquals(sender, transaction.getSender());
        assertEquals(receiver, transaction.getReceiver());
        assertEquals(amount, transaction.getAmount(), 0.01);
        assertEquals(date, transaction.getDate());
        assertEquals(message, transaction.getMessage());
    }
}