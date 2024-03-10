package pl.epicserwer.czolg.addons.luckperm.data;

import pl.epicserwer.czolg.addons.luckperm.objects.commands.CommandObject;
import pl.epicserwer.czolg.addons.luckperm.objects.tracks.TracksObject;

public class ConfigData {
    private TracksObject tracksObject;
    private MessagesData messagesData;
    private CommandObject basicCommand;

    public ConfigData(final TracksObject tracksObject, final MessagesData messagesData, final CommandObject basicCommand){
        this.reload(tracksObject, messagesData,basicCommand);
    }

    public void reload(final ConfigData configData){
        this.reload(configData.tracksObject, configData.messagesData,configData.basicCommand);
    }

    private void reload(final TracksObject tracksObject,final MessagesData messagesData, final CommandObject basicCommand){
        this.tracksObject = tracksObject;
        this.messagesData = messagesData;
        this.basicCommand = basicCommand;
    }

    public TracksObject getTracksObject() {
        return tracksObject;
    }

    public MessagesData getMessagesData() {
        return messagesData;
    }

    public CommandObject getBasicCommand() {
        return basicCommand;
    }

    @Override
    public String toString() {
        return "ConfigData{" +
                "tracksObject=" + tracksObject +
                ", messagesData=" + messagesData +
                ", basicCommand=" + basicCommand +
                '}';
    }
}
