package pl.chudziudgi.core.feature.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.teleport.TeleportManager;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "rtp", player = true, perm = "core.command.rtp.admin")

public class RandomTpCommand extends PluginCommand {
    private final TeleportManager teleportManager;
    private final ChCore plugin;

    public RandomTpCommand(TeleportManager teleportManager, ChCore plugin) {
        this.teleportManager = teleportManager;
        this.plugin = plugin;
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
            teleportManager.startTeleportation(player, RandomUtil.getRandomCord(player), plugin);
            return;
        }

        if (!sender.hasPermission("core.command.rtp.admin")) {
            ChatUtil.error(sender, "Nie masz uprawnien! &8(&7core.command.rtp.admin&8)");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            ChatUtil.error(sender, "Gracz nie jest online.");
            return;
        }
        target.teleport(RandomUtil.getRandomCord(player));
        ChatUtil.success(sender, "Teleportowano " + target.getName() + " w losowe kordynaty.");
    }
}

