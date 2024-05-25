package pl.chudziudgi.core.feature.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "rtp", player = true, perm = "core.command.rtp.admin")

public class RandomTpCommand extends PluginCommand {


    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
            Location randomLocation = RandomUtil.getRandomCords(0);
            player.teleport(randomLocation);
            ChatUtil.success(sender, "Teleportowano w losowe kordynaty.");
            return;
        }

        if (!sender.hasPermission("core.command.rtp.admin")) {
            ChatUtil.error(sender, "Nie masz uprawnien! &8(&7core.command.rtp.admin&8)");
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            ChatUtil.error(sender, "Gracz nie jest online.");
            return;
        }

        Location randomLocation = RandomUtil.getRandomCords(0);
        target.teleport(randomLocation);
        ChatUtil.success(sender, "Teleportowano " + target.getName() + " w losowe kordynaty.");
    }
}

