package pl.chudziudgi.core.feature.backup;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "backup",
        usage = "<gracz>",
        perm = "core.command.backup"
)


public class BackupCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        User user= UserManager.get(player);

        if (args.length != 1) {
            if (user.backupAnswerList.isEmpty()) {
                ChatUtil.error(player, "Nie posiadasz żadnych backupów do odebrania.");
                return;
            }
            BackupGui.openBackup(player);
            return;
        }

        if (!player.hasPermission("core.command.backup.admin")) {
            ChatUtil.error(player, String.format("Nie masz uprawnien! &8(&7%s&8)", "core.command.backup.admin"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline() || !UserManager.isExists(player)) {
            ChatUtil.error(player, "Gracz nie jest online.");
            return;
        }
        BackupAdminGui.openBackup(player, target);
    }
}
