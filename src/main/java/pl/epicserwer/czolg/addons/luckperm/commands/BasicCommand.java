package pl.epicserwer.czolg.addons.luckperm.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.epicserwer.czolg.addons.luckperm.Main;
import pl.epicserwer.czolg.addons.luckperm.data.ConfigData;
import pl.epicserwer.czolg.addons.luckperm.data.builders.ConfigBuilder;
import pl.epicserwer.czolg.addons.luckperm.objects.NameObject;
import pl.epicserwer.czolg.addons.luckperm.objects.tracks.TrackObject;
import pl.epicserwer.czolg.addons.luckperm.objects.tracks.TracksObject;
import pl.epicserwer.czolg.addons.luckperm.wrappers.LuckPermWrapper;

public class BasicCommand implements CommandExecutor {
    private final Main plugin;
    private final LuckPermWrapper luckPermWrapper;
    private final ConfigData configData;

    public BasicCommand(final Main plugin,final LuckPermWrapper luckPermWrapper,final ConfigData configData) {
        this.plugin = plugin;
        this.luckPermWrapper = luckPermWrapper;
        this.configData = configData;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        final NameObject senderName = new NameObject(commandSender.getName());

        if(command.getName().equalsIgnoreCase(this.configData.getBasicCommand().getCommandName().toString())){
            if(args.length >= 2){
                final TracksObject tracksObject = this.configData.getTracksObject();

                final NameObject trackName = new NameObject(args[1]);
                final NameObject receiverName = new NameObject(args[0]);

                if(!this.hasPermission(commandSender,trackName)){
                    commandSender.sendMessage(this.configData.getMessagesData().getNoPermissionMessage().
                            getMessage(receiverName,senderName,this.getSenderRankName(commandSender),new NameObject()));
                    return true;
                }

                final NameObject receiverGroupName = this.luckPermWrapper.getRank(receiverName);
                if(receiverGroupName == null) {
                    commandSender.sendMessage(this.configData.getMessagesData().getNotPlayerExistsMessage().getMessage(receiverName,
                            senderName,new NameObject(), new NameObject()));
                    return true;
                }

                final TrackObject trackObject = tracksObject.getTrackObject(trackName);
                if(trackObject == null) {
                    commandSender.sendMessage(this.configData.getMessagesData().getFailedPathMessage().getMessage(new NameObject(),
                            new NameObject(),new NameObject(), new NameObject()));
                    return true;
                }

                final NameObject nextGroupName = trackObject.getNextGroup(receiverGroupName);
                if (nextGroupName == null) {
                    commandSender.sendMessage(this.configData.getMessagesData().getGroupNotExistsMessage().getMessage(receiverName,
                            senderName,this.getSenderRankName(commandSender),new NameObject()));
                    return true;
                }

                this.luckPermWrapper.setRank(receiverName,nextGroupName);

                commandSender.sendMessage(this.configData.getMessagesData().getSuccessAdminMessage().getMessage(receiverName,senderName,
                        this.getSenderRankName(commandSender),nextGroupName));

                final Player receiverPlayer = Bukkit.getPlayer(receiverName.toString());
                if(receiverPlayer != null && receiverPlayer.isOnline())
                    receiverPlayer.sendMessage(this.configData.getMessagesData().getSuccessPlayerMessage().getMessage(receiverName,senderName,
                            this.getSenderRankName(commandSender),nextGroupName));
                return true;
            }

            commandSender.sendMessage(this.configData.getMessagesData().getCorrectUsageMessage().getMessage(new NameObject(),
                    senderName,this.getSenderRankName(commandSender), new NameObject()));
            return true;
        }
        if(command.getName().equalsIgnoreCase("luckpermsrankup")){
            try {
                this.plugin.reloadConfig();
                this.configData.reload(new ConfigBuilder(this.plugin.getConfig()).build());
                commandSender.sendMessage(this.configData.getMessagesData().getSuccessReloadMessage().getMessage(new NameObject(),senderName,
                        this.getSenderRankName(commandSender),new NameObject()));
            } catch (Exception e) {
                commandSender.sendMessage(this.configData.getMessagesData().getFailedReloadMessage().getMessage(new NameObject(),senderName,
                        this.getSenderRankName(commandSender),new NameObject()));
                e.printStackTrace();
            }
        }

        return false;
    }

    private NameObject getSenderRankName(final CommandSender commandSender){
        if(commandSender instanceof Player){
            final Player player = (Player)commandSender;
            return this.luckPermWrapper.getRank(new NameObject(player.getName()));
        }

        return new NameObject("Console");
    }

    private boolean hasPermission(final CommandSender commandSender, final NameObject trackName){
        return commandSender.hasPermission(this.configData.getBasicCommand().getPermission(trackName).toString()) ||
                commandSender.hasPermission(this.configData.getBasicCommand().getPermission(new NameObject("*")).toString());
    }
}
