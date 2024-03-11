package pl.epicserwer.czolg.addons.luckperm.objects.messages;

import org.bukkit.ChatColor;
import pl.epicserwer.czolg.addons.luckperm.objects.Name;

public class Message {
    private static final String DEFAULT_MESSAGE = "Message do not exist";
    private final String message;

    public Message(){
        this(DEFAULT_MESSAGE);
    }

    public Message(String message) {
        if(message == null) throw new IllegalArgumentException("message must not be null");
        if(message.isEmpty()) throw new IllegalArgumentException("message must not be empty");

        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getMessage(final Name receiverName, final Name senderName, final Name senderRank, final Name rank) {
        return this.setPlaceHolders(this.message,receiverName,senderName,senderRank,rank);
    }

    private String setPlaceHolders(String message, final Name receiverName, final Name senderName, final Name senderRank, final Name rank) {
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
