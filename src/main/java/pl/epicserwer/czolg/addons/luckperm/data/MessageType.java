package pl.epicserwer.czolg.addons.luckperm.data;

public enum MessageType {
    NO_PERMISSION("NO_PERMISSION"),
    SUCCESS_ADMIN("SUCCESS_ADMIN"),
    SUCCESS_PLAYER("SUCCESS_PLAYER"),
    CORRECT_USAGE("CORRECT_USAGE"),
    RELOAD_SUCCESS("RELOAD_SUCCESS"),
    RELOAD_FAILED("RELOAD_FAILED"),
    FAILED_PATH("FAILED_PATH"),
    PLAYER_NOT_EXISTS("PLAYER_NOT_EXISTS"),
    NEXT_GROUP_NOT_EXISTS("NEXT_GROUP_NOT_EXISTS");

    private static final String MESSAGE_PATH = "messages";
    private final String configPath;

    MessageType(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return MESSAGE_PATH+"."+configPath;
    }
}
