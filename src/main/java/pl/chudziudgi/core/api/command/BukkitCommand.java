package pl.chudziudgi.core.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.util.ChatUtil;

public class BukkitCommand extends org.bukkit.command.Command {

    private final PluginCommand pluginCommand;

    public BukkitCommand(PluginCommand pluginCommand) {
        super(pluginCommand.getName(), "", "", pluginCommand.getAliases());
        this.pluginCommand = pluginCommand;
    }

    @Override
    public boolean execute(CommandSender sender, String string, String[] args) {
        if (this.pluginCommand.getPlayer() && !(sender instanceof Player)) {
            ChatUtil.error(sender, "Tej komendy nie mozna wywolac z poziomu konsoli!");
            return true;
        }
        final String permission = this.pluginCommand.getPermission();
        if (!permission.isEmpty() && !sender.hasPermission(permission)) {
            ChatUtil.error(sender, String.format("Nie masz uprawnien! &8(&7%s&8)", permission));
            return true;
        }

        this.pluginCommand.execute(sender, args);
        return true;
    }

}
