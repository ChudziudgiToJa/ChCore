package pl.chudziudgi.core.feature.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "speed", player = true, perm = "core.command.speed", usage = "<1-10>")
public class SpeedCommand extends PluginCommand {


    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 2) {
            sendUsage(sender);
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);

        if (targetPlayer == null || !targetPlayer.isOnline()) {
            ChatUtil.error(player, "Podany gracz nie jest online.");
            return;
        }

        float speed;
        try {
            speed = Float.parseFloat(args[1]);
        } catch (NumberFormatException e) {
            ChatUtil.error(player, "Podana wartość prędkości nie jest liczbą.");
            return;
        }

        if (speed > 10.0f || speed < 1.0f) {
            ChatUtil.error(player, "Wybierz tryb między 1 a 10.");
            return;
        }

        targetPlayer.setFlySpeed(speed / 10.0f);
        ChatUtil.success(player, "Ustawiono prędkość latania gracza " + targetPlayer.getName() + " na: " + args[1]);
    }
}