package simpleTests;

import logger.CPUListener;
import logger.LogListener;
import org.testng.annotations.Listeners;

@Listeners({LogListener.class, CPUListener.class})
class BaseTest {
}
