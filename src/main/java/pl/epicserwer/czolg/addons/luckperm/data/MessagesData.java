package pl.epicserwer.czolg.addons.luckperm.data;

import pl.epicserwer.czolg.addons.luckperm.objects.messages.MessageObject;

public class MessagesData {
    private final MessageObject noPermissionMessage;
    private final MessageObject successAdminMessage;
    private final MessageObject successPlayerMessage;
    private final MessageObject correctUsageMessage;
    private final MessageObject successReloadMessage;
    private final MessageObject failedReloadMessage;
    private final MessageObject failedPathMessage;
    private final MessageObject notPlayerExistsMessage;
    private final MessageObject groupNotExistsMessage;

    public MessagesData(MessageObject noPermissionMessage, MessageObject successAdminMessage, MessageObject successPlayerMessage, MessageObject correctUsageMessage, MessageObject successReloadMessage, MessageObject failedReloadMessage, MessageObject failedPathMessage, MessageObject notPlayerExistsMessage, MessageObject groupNotExistsMessage) {
        this.noPermissionMessage = noPermissionMessage;
        this.successAdminMessage = successAdminMessage;
        this.successPlayerMessage = successPlayerMessage;
        this.correctUsageMessage = correctUsageMessage;
        this.successReloadMessage = successReloadMessage;
        this.failedReloadMessage = failedReloadMessage;
        this.failedPathMessage = failedPathMessage;
        this.notPlayerExistsMessage = notPlayerExistsMessage;
        this.groupNotExistsMessage = groupNotExistsMessage;
    }

    public MessageObject getNoPermissionMessage() {
        return noPermissionMessage;
    }

    public MessageObject getSuccessAdminMessage() {
        return successAdminMessage;
    }

    public MessageObject getSuccessPlayerMessage() {
        return successPlayerMessage;
    }

    public MessageObject getCorrectUsageMessage() {
        return correctUsageMessage;
    }

    public MessageObject getSuccessReloadMessage() {
        return successReloadMessage;
    }

    public MessageObject getFailedReloadMessage() {
        return failedReloadMessage;
    }

    public MessageObject getFailedPathMessage() {
        return failedPathMessage;
    }

    public MessageObject getNotPlayerExistsMessage() {
        return notPlayerExistsMessage;
    }

    public MessageObject getGroupNotExistsMessage() {
        return groupNotExistsMessage;
    }

    @Override
    public String toString() {
        return "MessagesData{" +
                "noPermissionMessage=" + noPermissionMessage +
                ", successAdminMessage=" + successAdminMessage +
                ", successPlayerMessage=" + successPlayerMessage +
                ", correctUsageMessage=" + correctUsageMessage +
                ", successReloadMessage=" + successReloadMessage +
                ", failedReloadMessage=" + failedReloadMessage +
                ", failedPathMessage=" + failedPathMessage +
                ", notPlayerExistsMessage=" + notPlayerExistsMessage +
                ", groupNotExistsMessage=" + groupNotExistsMessage +
                '}';
    }
}
