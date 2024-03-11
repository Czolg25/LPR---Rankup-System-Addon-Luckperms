package pl.epicserwer.czolg.addons.luckperm.wrappers;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import pl.epicserwer.czolg.addons.luckperm.objects.Name;

public class LuckPermRepository {
    private final LuckPerms luckPermsApi;

    public LuckPermRepository(final LuckPerms luckPermsApi) {
        this.luckPermsApi = luckPermsApi;
    }

    public Name getRank(final Name player){
        final User user = this.getUser(player.toString());
        if(user == null) return null;

        return new Name(user.getPrimaryGroup());
    }

    public boolean setRank(final Name player, final Name groupName) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user "+player+" parent set "+groupName);
        return true;
        /* todo
        final User user = this.getUser(player.toString());
        if(user == null) return false;


        final DataMutateResult dataMutateResult = user.setPrimaryGroup(groupName.toString());
        System.out.println(dataMutateResult);

        return dataMutateResult.wasSuccessful();
         */
    }

    private User getUser(final String player){
        return this.luckPermsApi.getUserManager().getUser(player);
    }
}
