package pl.epicserwer.czolg.addons.luckperm.objects.commands;

import pl.epicserwer.czolg.addons.luckperm.objects.Name;

import java.util.List;

public class CommandObject {
    private final Name commandName;
    private final List<Name> aliases;
    private final Name permission;

    public CommandObject(Name commandName, List<Name> aliases, Name permission) {
        if(commandName.toString().startsWith("/")) this.commandName = new Name(commandName.toString().replaceFirst("/", ""));
        else this.commandName = commandName;

        this.aliases = aliases;
        this.permission = permission;
    }

    public Name getCommandName() {
        return commandName;
    }

    public List<Name> getAliases() {
        return aliases;
    }

    public Name getPermission(final Name trackName) {
        return new Name(permission+"."+trackName);
    }

    @Override
    public String toString() {
        return "CommandObject{" +
                "commandName=" + commandName +
                ", aliases=" + aliases +
                ", permission=" + permission +
                '}';
    }
}
