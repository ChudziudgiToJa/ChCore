package pl.chudziudgi.core.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.exceptions.CommandNotFoundException;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.Arrays;
import java.util.List;

public abstract class PluginCommand {

    private final List<String> aliases;
    private final boolean player;
    private final String name, usage, permission;

    public PluginCommand() {

        final CommandInfo commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        if (commandInfo == null)
            throw new CommandNotFoundException("Nie mozna zaladowac komendy " + this.getClass().getSimpleName() + ", ponieważ nie posiada ona adnotacji @CommandInfo");

        this.name = commandInfo.name();
        this.player = commandInfo.player();
        this.usage = "/" + commandInfo.name() + " " + commandInfo.usage();
        this.permission = commandInfo.perm();
        this.aliases = Arrays.asList(commandInfo.aliases());


    }

    public abstract void execute(CommandSender sender, String[] args);

    public void sendUsage(final CommandSender commandSender) {
        ChatUtil.error(commandSender, "Poprawne uzycie: " + this.usage);
    }

    public void sendUsage(final Player player) {
        ChatUtil.error(player, "Poprawne uzycie: " + this.usage);
    }

    public String getName() {
        return name;
    }

    public boolean getPlayer() {
        return player;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getAliases() {
        return aliases;
    }

}
