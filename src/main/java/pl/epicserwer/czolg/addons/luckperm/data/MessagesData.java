package pl.epicserwer.czolg.addons.luckperm.data;

import pl.epicserwer.czolg.addons.luckperm.objects.messages.Message;

import java.util.HashMap;

public class MessagesData {
    private final HashMap<MessageType, Message> messageHashMap;
    private final Message defaultMessage;

    public MessagesData(HashMap<MessageType, Message> messageHashMap, Message defaultMessage) {
        this.messageHashMap = messageHashMap;
        this.defaultMessage = defaultMessage;
    }

    public Message getMessage(final MessageType messageType){
        if(messageType == null) return this.defaultMessage;

        return this.messageHashMap.getOrDefault(messageType,this.defaultMessage);
    }

    @Override
    public String toString() {
        return "MessagesData{" +
                "messageHashMap=" + messageHashMap +
                ", defaultMessage=" + defaultMessage +
                '}';
    }
}
