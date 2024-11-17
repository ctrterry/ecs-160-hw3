package hwvisitor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    public void shouldDetectMatchingStateMethods() {
        // Test that StatePatternChecker detects matching methods correctly
        assertTrue(
            StatePatternChecker.checkContextHasMatchingMethodNames(
                ParserUtil.parseJavaText(
                    """
                    class LibraryBook {
                        public void issue() {}
                        public void extend() {}
                        public void returnIt() {}
                        public void extraMethod() {}
                    }
                    """
                ),
                ParserUtil.parseJavaText(
                    """
                    interface LBState {
                        public void issue() {}
                        public void extend() {}
                        public void returnIt() {}
                    }
                    """
                )
            )
        );
    }

    @Test
    public void shouldDetectMissingStateMethods() {
        // Test that StatePatternChecker detects missing methods in the context
        assertFalse(
            StatePatternChecker.checkContextHasMatchingMethodNames(
                ParserUtil.parseJavaText(
                    """
                    class LibraryBook {
                        public void issue() {}
                        public void returnIt() {}
                    }
                    """
                ),
                ParserUtil.parseJavaText(
                    """
                    interface LBState {
                        public void issue() {}
                        public void extend() {}
                        public void returnIt() {}
                    }
                    """
                )
            )
        );
    }
}
