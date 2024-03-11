package pl.epicserwer.czolg.addons.luckperm.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.epicserwer.czolg.addons.luckperm.Main;
import pl.epicserwer.czolg.addons.luckperm.data.ConfigData;
import pl.epicserwer.czolg.addons.luckperm.data.MessageType;
import pl.epicserwer.czolg.addons.luckperm.data.builders.ConfigBuilder;
import pl.epicserwer.czolg.addons.luckperm.objects.Name;
import pl.epicserwer.czolg.addons.luckperm.objects.tracks.TrackObject;
import pl.epicserwer.czolg.addons.luckperm.objects.tracks.TracksObject;
import pl.epicserwer.czolg.addons.luckperm.wrappers.LuckPermRepository;

public class BasicCommand implements CommandExecutor {
    private final Main plugin;
    private final LuckPermRepository luckPermWrapper;
    private final ConfigData configData;

    public BasicCommand(final Main plugin, final LuckPermRepository luckPermWrapper, final ConfigData configData) {
        this.plugin = plugin;
        this.luckPermWrapper = luckPermWrapper;
        this.configData = configData;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        final Name senderName = new Name(commandSender.getName());

        if(command.getName().equalsIgnoreCase(this.configData.getBasicCommand().getCommandName().toString())){
            if(args.length >= 2){
                final TracksObject tracksObject = this.configData.getTracksObject();

                final Name trackName = new Name(args[1]);
                final Name receiverName = new Name(args[0]);

                if(!this.hasPermission(commandSender,trackName)){
                    commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.NO_PERMISSION).
                            getMessage(receiverName,senderName,this.getSenderRankName(commandSender),new Name()));
                    return true;
                }

                final Name receiverGroupName = this.luckPermWrapper.getRank(receiverName);
                if(receiverGroupName == null) {
                    commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.PLAYER_NOT_EXISTS).getMessage(receiverName,
                            senderName,new Name(), new Name()));
                    return true;
                }

                final TrackObject trackObject = tracksObject.getTrackObject(trackName);
                if(trackObject == null) {
                    commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.FAILED_PATH).getMessage(new Name(),
                            new Name(),new Name(), new Name()));
                    return true;
                }

                final Name nextGroupName = trackObject.getNextGroup(receiverGroupName);
                if (nextGroupName == null) {
                    commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.NEXT_GROUP_NOT_EXISTS).getMessage(receiverName,
                            senderName,this.getSenderRankName(commandSender),new Name()));
                    return true;
                }

                this.luckPermWrapper.setRank(receiverName,nextGroupName);

                commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.SUCCESS_ADMIN).getMessage(receiverName,senderName,
                        this.getSenderRankName(commandSender),nextGroupName));

                final Player receiverPlayer = Bukkit.getPlayer(receiverName.toString());
                if(receiverPlayer != null && receiverPlayer.isOnline())
                    receiverPlayer.sendMessage(this.configData.getMessagesData().getMessage(MessageType.SUCCESS_PLAYER).getMessage(receiverName,senderName,
                            this.getSenderRankName(commandSender),nextGroupName));
                return true;
            }

            commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.CORRECT_USAGE).getMessage(new Name(),
                    senderName,this.getSenderRankName(commandSender), new Name()));
            return true;
        }
        if(command.getName().equalsIgnoreCase("luckpermsrankup")){
            try {
                this.plugin.reloadConfig();
                this.configData.reload(new ConfigBuilder(this.plugin.getConfig()).build());
                commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.RELOAD_SUCCESS).getMessage(new Name(),senderName,
                        this.getSenderRankName(commandSender),new Name()));
            } catch (Exception e) {
                commandSender.sendMessage(this.configData.getMessagesData().getMessage(MessageType.RELOAD_FAILED).getMessage(new Name(),senderName,
                        this.getSenderRankName(commandSender),new Name()));
                e.printStackTrace();
            }
        }

        return false;
    }

    private Name getSenderRankName(final CommandSender commandSender){
        if(commandSender instanceof Player){
            final Player player = (Player)commandSender;
            return this.luckPermWrapper.getRank(new Name(player.getName()));
        }

        return new Name("Console");
    }

    private boolean hasPermission(final CommandSender commandSender, final Name trackName){
        return commandSender.hasPermission(this.configData.getBasicCommand().getPermission(trackName).toString()) ||
                commandSender.hasPermission(this.configData.getBasicCommand().getPermission(new Name("*")).toString());
    }
}
