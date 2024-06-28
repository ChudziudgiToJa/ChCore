package pl.chudziudgi.core.feature.privatemessage.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "spocialspy", perm = "core.command.socialspy", player = true, usage = "<gracz>")

public class SocialSpyCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            User user = UserManager.get(player);
            user.socialSpyStatus = !user.socialSpyStatus;
            ChatUtil.success(player, "Zmieniono twój status &3socialspy&7 na: " + ChatUtil.booleanString(user.socialSpyStatus));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            ChatUtil.error(player, "Gracz nie jest online");
            return;
        }
        User user = UserManager.get(target);
        user.socialSpyStatus = !user.socialSpyStatus;
        ChatUtil.success(player, "Zmieniono status &3socialspy&7 na: " + ChatUtil.booleanString(user.socialSpyStatus) + " dla &3" + target.getName());
    }
}
