package constants;

public enum Appenders {
    Pet("petLog", "petLogRQ"),
    User("userLog", "userLogRQ"),
    Store("storeLog", "storeLogRQ"),
    Default("defaultLog", "defaultLogRQ");

    String defaultName;
    String changedName;

    Appenders(String defaultName, String changedName) {
        this.defaultName = defaultName;
        this.changedName = changedName;
    }

    public static String changeAppender(String name) {
        for (Appenders appender : Appenders.values()) {
            if (appender.defaultName.equalsIgnoreCase(name)) {
                return appender.changedName;
            }
        }
        return "";
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getChangedName() {
        return changedName;
    }
}
