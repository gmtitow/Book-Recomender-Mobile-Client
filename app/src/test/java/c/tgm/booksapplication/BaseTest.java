package c.tgm.booksapplication;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class BaseTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
