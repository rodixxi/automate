import com.harriague.automate.web.steps.AllControlsCheckGestar;
import com.harriague.automate.web.steps.AllControlsCheckValuesGestar;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllControlsCheckGestar.class,
        AllControlsCheckValuesGestar.class
        })

public class TestSuite {
}
