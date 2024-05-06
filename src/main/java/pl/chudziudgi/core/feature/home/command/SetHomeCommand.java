package pl.chudziudgi.core.feature.home.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.home.HomeGui;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "sethome", player = true)
public class SetHomeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            HomeGui.openSetHome(player);
            return;
        }

        if (!player.hasPermission("core.command.sethome.other")) return;

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            ChatUtil.error(sender, "Gracz jest offline!");
            return;
        }
        HomeGui.openSetHome(target);
    }
}