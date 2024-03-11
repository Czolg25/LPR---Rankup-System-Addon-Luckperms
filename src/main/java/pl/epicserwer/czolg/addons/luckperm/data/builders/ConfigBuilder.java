package pl.epicserwer.czolg.addons.luckperm.data.builders;

import org.bukkit.configuration.file.FileConfiguration;
import pl.epicserwer.czolg.addons.luckperm.data.ConfigData;
import pl.epicserwer.czolg.addons.luckperm.objects.Name;
import pl.epicserwer.czolg.addons.luckperm.objects.commands.CommandObject;
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
        final MessageBuilder messageBuilder = new MessageBuilder(this.fileConfiguration);

        for (String pathNames : fileConfiguration.getConfigurationSection("paths").getKeys(false)) {
            final List<String> groupList = fileConfiguration.getStringList("paths."+pathNames);
            final List<Name> groupNameObjectList = new ArrayList<>();

            for (final String group : groupList)
                groupNameObjectList.add(new Name(group));

            final TrackObject trackObject = new TrackObject(new Name(pathNames),groupNameObjectList);
            trackObjectList.add(trackObject);
        }

        final TracksObject tracksObject = new TracksObject(trackObjectList);

        return new ConfigData(tracksObject,messageBuilder.build(),this.getBasicCommand(fileConfiguration));
    }


    private CommandObject getBasicCommand(final FileConfiguration fileConfiguration) throws Exception{
        final Name basicCommandName = new Name(fileConfiguration.getString("general.command"));
        final List<Name> aliases = new ArrayList<>();
        final Name basicCommandPermission = new Name(fileConfiguration.getString("general.base-permission"));

        for (String alias : fileConfiguration.getStringList("general.aliases"))
            aliases.add(new Name(alias));

        return new CommandObject(basicCommandName,aliases,basicCommandPermission);
    }
}
