package pl.epicserwer.czolg.addons.luckperm.objects.messages;

import org.bukkit.ChatColor;
import pl.epicserwer.czolg.addons.luckperm.objects.NameObject;

public class MessageObject {
    private final String message;

    public MessageObject(String message) {
        if(message == null) throw new IllegalArgumentException("message must not be null");
        if(message.isEmpty()) throw new IllegalArgumentException("message must not be empty");

        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getMessage(final NameObject receiverName, final NameObject senderName,final NameObject senderRank,final NameObject rank) {
        return this.setPlaceHolders(this.message,receiverName,senderName,senderRank,rank);
    }

    private String setPlaceHolders(String message, final NameObject receiverName, final NameObject senderName,final NameObject senderRank,final NameObject rank) {
        message = message.replaceAll("%player%",receiverName.toString()).replaceAll("%admin_nickname%",senderName.toString())
                .replaceAll("%admin_rank%",senderRank.toString()).replaceAll("%rank%",rank.toString());

        return message;
    }


    @Override
    public String toString() {
        return "MessageObject{" +
                "message='" + message + '\'' +
                '}';
    }
}
