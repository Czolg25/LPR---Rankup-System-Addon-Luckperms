package pl.epicserwer.czolg.addons.luckperm.data.builders;

import org.bukkit.configuration.file.FileConfiguration;
import pl.epicserwer.czolg.addons.luckperm.data.MessageType;
import pl.epicserwer.czolg.addons.luckperm.data.MessagesData;
import pl.epicserwer.czolg.addons.luckperm.objects.messages.Message;

import java.util.HashMap;

public class MessageBuilder {
    private final FileConfiguration fileConfiguration;

    public MessageBuilder(final FileConfiguration fileConfiguration){
        this.fileConfiguration = fileConfiguration;
    }

    public MessagesData build(){
        final HashMap<MessageType, Message> messageHashMap = new HashMap<>();

        for (MessageType value : MessageType.values())
            messageHashMap.put(value, new Message(this.fileConfiguration.getString(value.getConfigPath())));

        return new MessagesData(messageHashMap,new Message());
    }
}
