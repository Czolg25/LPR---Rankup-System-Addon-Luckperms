package pl.epicserwer.czolg.addons.luckperm;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.epicserwer.czolg.addons.luckperm.commands.BasicCommand;
import pl.epicserwer.czolg.addons.luckperm.commands.tab.BasicCommandTabComplete;
import pl.epicserwer.czolg.addons.luckperm.data.ConfigData;
import pl.epicserwer.czolg.addons.luckperm.data.builders.ConfigBuilder;
import pl.epicserwer.czolg.addons.luckperm.wrappers.LuckPermRepository;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private Logger logger;
    private ConfigData configData;
    private ConfigBuilder configBuilder;
    private LuckPermRepository luckPermWrapper;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.logger = Bukkit.getLogger();

        logger.info(ChatColor.RED+"Loading LuckPermsRankup plugin by Czolg1");

        this.configBuilder = new ConfigBuilder(this.getConfig());

        try {
            this.configData = this.configBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.luckPermWrapper = new LuckPermRepository(this.getLuckPerms());

        this.setupCommands();
    }

    private void setupCommands() {
        final BasicCommand basicCommand = new BasicCommand(this,this.luckPermWrapper,this.configData);
        final BasicCommandTabComplete basicCommandTabComplete = new BasicCommandTabComplete(this.configData);

        Bukkit.getPluginCommand("rankup").setExecutor(basicCommand);
        Bukkit.getPluginCommand("rankup").setTabCompleter(basicCommandTabComplete);
        Bukkit.getPluginCommand("luckpermsrankup").setExecutor(basicCommand);
        Bukkit.getPluginCommand("luckpermsrankup").setTabCompleter(basicCommandTabComplete);
    }

    private LuckPerms getLuckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) return null;
        return provider.getProvider();
    }
}
