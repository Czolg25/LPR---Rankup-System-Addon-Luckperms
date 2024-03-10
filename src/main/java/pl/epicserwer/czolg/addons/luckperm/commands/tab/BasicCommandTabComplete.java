package pl.epicserwer.czolg.addons.luckperm.commands.tab;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pl.epicserwer.czolg.addons.luckperm.data.ConfigData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasicCommandTabComplete implements TabCompleter {
    private final ConfigData configData;

    public BasicCommandTabComplete(final ConfigData configData){
        this.configData = configData;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase(this.configData.getBasicCommand().getCommandName().toString())){
            if(args.length == 2) return this.configData.getTracksObject().getTrackNames();

            return this.getPlayers();
        }
        if(command.getName().equalsIgnoreCase("luckpermsrankup")){
            if(args.length == 1) return Collections.singletonList("reload");
            return this.getPlayers();
        }
        return this.getPlayers();
    }

    private List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
            players.add(onlinePlayer.getName());

        return players;
    }
}
