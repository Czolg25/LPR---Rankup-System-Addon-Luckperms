package pl.epicserwer.czolg.addons.luckperm.wrappers;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import pl.epicserwer.czolg.addons.luckperm.objects.NameObject;

public class LuckPermWrapper {
    private final LuckPerms luckPermsApi;

    public LuckPermWrapper(final LuckPerms luckPermsApi) {
        this.luckPermsApi = luckPermsApi;
    }

    public NameObject getRank(final NameObject player){
        final User user = this.getUser(player.toString());
        if(user == null) return null;

        return new NameObject(user.getPrimaryGroup());
    }

    public boolean setRank(final NameObject player, final NameObject groupName) {
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
