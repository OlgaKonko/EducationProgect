package constants;

public enum Appenders {
    Pet("petLog"),
    User("userLog"),
    Store("storeLog"),
    Default("defaultLog");

    String defaultName;

    Appenders(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getDefaultName() {
        return defaultName;
    }
}
