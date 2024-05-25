package pl.chudziudgi.core.feature.kit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "zestaw",
        player = true,
        aliases = {"kit"},
        usage = "toggle|czysc <gracz> <zestaw>|daj <gracz> <zestaw>"
)

public class KitCommand extends PluginCommand {

    private final KitConfig kitConfig;

    public KitCommand(KitConfig kitConfig) {
        this.kitConfig = kitConfig;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!kitConfig.isKitStauts()) {
                if (player.hasPermission("core.kit.admin")) {
                    KitGui.openMain(player);
                }
                ChatUtil.error(sender, "Zestawy są aktualnie wyłączone.");
                return;
            }
            KitGui.openMain(player);
            return;
        }

        if (!player.hasPermission("core.command.kit.admin")) {
            ChatUtil.error(sender, "Nie masz uprawnien! &8(&7core.command.kit.admin&8)");
            return;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
            kitConfig.setKitStauts(!kitConfig.isKitStauts());
            ChatUtil.success(sender, "Zmnieniono status otwierania kitów na: " + kitConfig.kitStauts);
            return;
        }

        if (args[0].equalsIgnoreCase("czysc")) {
            if (args.length < 2) {
                ChatUtil.error(sender, "Musisz podać nazwę gracza oraz nazwę zestawu (gold, iron, start)");
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null || !target.isOnline()) {
                ChatUtil.error(sender, "Gracz nie jest online");
                return;
            }
            User user = UserManager.get(target);
            if (args.length < 3) {
                ChatUtil.error(sender, "Musisz podać nazwę zestawu (gold, iron, start)");
                return;
            }

            String kitName = args[2].toLowerCase();

            switch (kitName) {
                case "gold":
                    user.kitGold = null;
                    break;
                case "iron":
                    user.kitIron = null;
                    break;
                case "start":
                    user.kitStart = null;
                    break;
                default:
                    ChatUtil.error(sender, "Nieznany zestaw. Dostępne zestawy: gold, iron, start");
                    return;
            }
            ChatUtil.success(sender, "Czas zestawu " + kitName + " został wyczyszczony graczowi " + target.getName());
        }

        if (args[0].equalsIgnoreCase("daj")) {
            if (args.length < 2) {
                ChatUtil.error(sender, "Musisz podać nazwę gracza oraz nazwę zestawu (gold, iron, start)");
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null || !target.isOnline()) {
                ChatUtil.error(sender, "Gracz nie jest online");
                return;
            }

            if (args.length < 3) {
                ChatUtil.error(sender, "Musisz podać nazwę zestawu (gold, iron, start)");
                return;
            }

            String kitName = args[2].toLowerCase();

            switch (kitName) {
                case "gold":
                    KitManager.giveKitFree(target, KitType.GOLD);
                    break;
                case "iron":
                    KitManager.giveKitFree(target, KitType.IRON);
                    break;
                case "start":
                    KitManager.giveKitFree(target, KitType.START);
                    break;
                default:
                    ChatUtil.error(sender, "Nieznany zestaw. Dostępne zestawy: gold, iron, start");
                    return;
            }
            ChatUtil.success(sender, "Zestaw " + kitName + " został przekazany graczowi " + target.getName());
            ChatUtil.info(target, "Otrzymałeś zestaw od administratora");
        }
    }
}

