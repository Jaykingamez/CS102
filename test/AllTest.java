import EntityTest.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    SuitTest.class,
    DeckTest.class,
    RankTest.class
})
public class AllTest {

}