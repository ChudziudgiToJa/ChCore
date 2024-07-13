package pl.chudziudgi.core.feature.enderchest;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "enderchest", perm = "core.command.enderchest", aliases = "ec", player = true)
public class EnderchestCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            EnderChestGui.openkit(player , player);
            ChatUtil.success(player, "Otworzono &3enderchest");
            return;
        }

        if (!player.hasPermission("core.command.enderchest.admin")) {
            ChatUtil.error(player, "Nie masz uprawnien! &8(&7core.command.enderchest.other&8)");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null || !target.isOnline()) {
            ChatUtil.error(player, "Gracz jest offline");
            return;
        }

        EnderChestGui.openkit(player, target);
        ChatUtil.success(player, "Otworzono &3enderchest &7gracza &3" + target.getName());
    }
}