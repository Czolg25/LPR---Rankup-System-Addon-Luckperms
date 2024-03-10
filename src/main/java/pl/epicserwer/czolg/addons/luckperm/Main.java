package pl.epicserwer.czolg.addons.luckperm;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.epicserwer.czolg.addons.luckperm.commands.BasicCommand;
import pl.epicserwer.czolg.addons.luckperm.commands.tab.BasicCommandTabComplete;
import pl.epicserwer.czolg.addons.luckperm.data.ConfigData;
import pl.epicserwer.czolg.addons.luckperm.data.builders.ConfigBuilder;
import pl.epicserwer.czolg.addons.luckperm.wrappers.LuckPermWrapper;

public class Main extends JavaPlugin {
    private ConfigData configData;
    private ConfigBuilder configBuilder;
    private LuckPermWrapper luckPermWrapper;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.configBuilder = new ConfigBuilder(this.getConfig());

        try {
            this.configData = this.configBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.luckPermWrapper = new LuckPermWrapper(this.getLuckPerms());

        new ConfigBuilder(this.getConfig());

        this.setupCommands();
        this.setupListeners();
    }

    private void setupListeners() {

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
        if (provider != null) return provider.getProvider();
        return null;
    }
}
