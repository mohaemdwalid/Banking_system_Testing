import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("System Test Suite")
@SelectClasses({
        AccountTest.class,
        CheckingAccountTest.class,
        ClientTest.class,
        DataBaseDriverTest.class,
        ModelTest.class,
        SavingAccountTest.class,
        TransactionsTest.class
})
public class TestAll {
}