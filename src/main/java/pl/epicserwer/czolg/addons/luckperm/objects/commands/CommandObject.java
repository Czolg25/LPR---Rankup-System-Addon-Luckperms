package pl.epicserwer.czolg.addons.luckperm.objects.commands;

import pl.epicserwer.czolg.addons.luckperm.objects.NameObject;

import java.util.List;

public class CommandObject {
    private final NameObject commandName;
    private final List<NameObject> aliases;
    private final NameObject permission;

    public CommandObject(NameObject commandName, List<NameObject> aliases, NameObject permission) {
        if(commandName.toString().startsWith("/")) this.commandName = new NameObject(commandName.toString().replaceFirst("/", ""));
        else this.commandName = commandName;

        this.aliases = aliases;
        this.permission = permission;
    }

    public NameObject getCommandName() {
        return commandName;
    }

    public List<NameObject> getAliases() {
        return aliases;
    }

    public NameObject getPermission(final NameObject trackName) {
        return new NameObject(permission+"."+trackName);
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
