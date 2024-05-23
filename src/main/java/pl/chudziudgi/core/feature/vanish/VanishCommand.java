package pl.chudziudgi.core.feature.vanish;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "vanish",
        aliases = {"v"},
        usage = "<gracz>",
        player = true,
        perm = "core.command.vanish"
)

public class VanishCommand extends PluginCommand {

    private final VanishManager vanishManager;

    public VanishCommand(VanishManager vanishManager) {
        this.vanishManager = vanishManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            final Player player = (Player) sender;
            final User user = UserManager.get(player);
            vanishManager.toggleVanish(player);
            ChatUtil.success(player,"Vanish: " + (user.vanishStatus ? "&awłączono" : "&cwyłączono"));
            return;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            ChatUtil.error(sender, "Gracz jest offline!");
            return;
        }
        if (!player.hasPermission("core.vanish.see")) {
            ChatUtil.error(sender, "Gracz nie może posiadać vanisha ze względu na brak permisji");
            return;
        }
        final User user = UserManager.get(player);
        vanishManager.toggleVanish(player);
        ChatUtil.success(sender, "Vanish dla: &f" + player.getName() + " " + (user.vanishStatus ? "&awłączono" : "&cwyłączono"));
    }
}