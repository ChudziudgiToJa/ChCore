package pl.chudziudgi.core.feature.chat.privatemessage;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "ignore",
        player = true,
        usage = "<gracz>"
)

public class IgnoreCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        User user = UserManager.get((Player)sender);
        if (args.length < 1) {
            sendUsage(sender);
            return;
        }

        Player player = (Player) sender;
        Player other = Bukkit.getPlayer(args[0]);

        if (other == player) {
            ChatUtil.error(player, "Nie możesz ignorować samego siebie.");
            return;
        }

        if (other == null) {
            ChatUtil.error(player, "Gracz jest offline.");
            return;
        }

        if (!user.ignoredList.contains(other.getUniqueId())) {
            user.ignoredList.add(other.getUniqueId());
            ChatUtil.success(player, "Wyciszono gracza " + other.getName());
            return;
        }
        user.ignoredList.remove(other.getUniqueId());
        ChatUtil.success(player, "Odciszono gracza " + other.getName());
    }
}
