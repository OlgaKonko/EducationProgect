package constants;

import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;

public enum LoggerLayout {
    GeneralLayout(new PatternLayout("%d{ABSOLUTE} %5p %C{1}:%M:%L:%t - %m%n")),
    AssureLayout(new PatternLayout("%m%n"));
    private Layout layout;


    LoggerLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }
}
