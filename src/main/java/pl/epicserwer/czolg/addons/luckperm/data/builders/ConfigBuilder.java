package pl.epicserwer.czolg.addons.luckperm.data.builders;

import org.bukkit.configuration.file.FileConfiguration;
import pl.epicserwer.czolg.addons.luckperm.data.ConfigData;
import pl.epicserwer.czolg.addons.luckperm.data.MessagesData;
import pl.epicserwer.czolg.addons.luckperm.objects.NameObject;
import pl.epicserwer.czolg.addons.luckperm.objects.commands.CommandObject;
import pl.epicserwer.czolg.addons.luckperm.objects.messages.MessageObject;
import pl.epicserwer.czolg.addons.luckperm.objects.tracks.TrackObject;
import pl.epicserwer.czolg.addons.luckperm.objects.tracks.TracksObject;

import java.util.ArrayList;
import java.util.List;

public class ConfigBuilder {
    private final FileConfiguration fileConfiguration;

    public ConfigBuilder(FileConfiguration fileConfiguration){

        this.fileConfiguration = fileConfiguration;
    }

    public ConfigData build() throws Exception{
        final List<TrackObject> trackObjectList = new ArrayList<>();

        for (String pathNames : fileConfiguration.getConfigurationSection("paths").getKeys(false)) {
            final List<String> groupList = fileConfiguration.getStringList("paths."+pathNames);
            final List<NameObject> groupNameObjectList = new ArrayList<>();

            for (final String group : groupList)
                groupNameObjectList.add(new NameObject(group));

            final TrackObject trackObject = new TrackObject(new NameObject(pathNames),groupNameObjectList);
            trackObjectList.add(trackObject);
        }

        final TracksObject tracksObject = new TracksObject(trackObjectList);

        return new ConfigData(tracksObject,this.getMessagesData(fileConfiguration),this.getBasicCommand(fileConfiguration));
    }

    private MessagesData getMessagesData(final FileConfiguration fileConfiguration) throws Exception{
        final MessageObject noPermissionMessage = new MessageObject(fileConfiguration.getString("messages.NO_PERMISSION"));
        final MessageObject successAdminMessage = new MessageObject(fileConfiguration.getString("messages.SUCCESS_ADMIN"));
        final MessageObject successPlayerMessage = new MessageObject(fileConfiguration.getString("messages.SUCCESS_PLAYER"));
        final MessageObject correctUsageMessage = new MessageObject(fileConfiguration.getString("messages.CORRECT_USAGE"));
        final MessageObject successReloadMessage = new MessageObject(fileConfiguration.getString("messages.RELOAD_SUCCESS"));
        final MessageObject failedReloadMessage = new MessageObject(fileConfiguration.getString("messages.RELOAD_FAILED"));
        final MessageObject failedPathMessage = new MessageObject(fileConfiguration.getString("messages.FAILED_PATH"));
        final MessageObject notPlayerExistsMessage = new MessageObject(fileConfiguration.getString("messages.PLAYER_NOT_EXISTS"));
        final MessageObject groupNotExistsMessage = new MessageObject(fileConfiguration.getString("messages.NEXT_GROUP_NOT_EXISTS"));

        return new MessagesData(noPermissionMessage,successAdminMessage,successPlayerMessage,correctUsageMessage,successReloadMessage,failedReloadMessage, failedPathMessage,
                notPlayerExistsMessage, groupNotExistsMessage);
    }

    private CommandObject getBasicCommand(final FileConfiguration fileConfiguration) throws Exception{
        final NameObject basicCommandName = new NameObject(fileConfiguration.getString("general.command"));
        final List<NameObject> aliases = new ArrayList<>();
        final NameObject basicCommandPermission = new NameObject(fileConfiguration.getString("general.base-permission"));

        for (String alias : fileConfiguration.getStringList("general.aliases"))
            aliases.add(new NameObject(alias));

        return new CommandObject(basicCommandName,aliases,basicCommandPermission);
    }
}
