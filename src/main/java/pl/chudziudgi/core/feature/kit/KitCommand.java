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
        usage = "toggle|czysc <gracz> <zestaw>|daj <gracz> <zestaw>|zmień <iron/diamond/netherite>"
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
            if (!kitConfig.isKitStarts()) {
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

        switch (args[0].toLowerCase()) {
            case "zmien":
                if (args.length < 2) {
                    ChatUtil.error(player, "Aktualny status kitów: " + kitConfig.getKitType() + " | <iron/diamond/netherite>");
                    return;
                }
                try {
                    KitStandard newStatus = KitStandard.valueOf(args[1].toUpperCase());
                    kitConfig.setKitType(newStatus);
                    ChatUtil.success(sender, "Zmieniono status otwierania kitów na: " + newStatus);
                } catch (IllegalArgumentException e) {
                    ChatUtil.error(sender, "Aktualny: "+ kitConfig.getKitType() +"  Nieprawidłowy status: IRON, DIAMOND, NETHERITE");
                }
                break;

            case "toggle":
                kitConfig.setKitStarts(!kitConfig.isKitStarts());
                ChatUtil.success(sender, "Zmieniono status otwierania kitów na: " + (kitConfig.isKitStarts() ? "włączone" : "wyłączone"));
                break;

            case "czysc":
                if (args.length < 3) {
                    ChatUtil.error(sender, "Musisz podać nazwę gracza oraz nazwę zestawu (gold, iron, start)");
                    return;
                }

                Player targetClear = Bukkit.getPlayer(args[1]);

                if (targetClear == null || !targetClear.isOnline()) {
                    ChatUtil.error(sender, "Gracz nie jest online");
                    return;
                }
                User user = UserManager.get(targetClear);

                String kitNameClear = args[2].toLowerCase();

                switch (kitNameClear) {
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
                ChatUtil.success(sender, "Czas zestawu " + kitNameClear + " został wyczyszczony graczowi " + targetClear.getName());
                break;

            case "daj":
                if (args.length < 3) {
                    ChatUtil.error(sender, "Musisz podać nazwę gracza oraz nazwę zestawu (gold, iron, start)");
                    return;
                }

                Player targetGive = Bukkit.getPlayer(args[1]);

                if (targetGive == null || !targetGive.isOnline()) {
                    ChatUtil.error(sender, "Gracz nie jest online");
                    return;
                }

                String kitNameGive = args[2].toLowerCase();

                switch (kitNameGive) {
                    case "gold":
                        KitManager.giveKitFree(targetGive, KitType.GOLD, kitConfig.getKitType());
                        break;
                    case "iron":
                        KitManager.giveKitFree(targetGive, KitType.IRON, kitConfig.getKitType());
                        break;
                    case "start":
                        KitManager.giveKitFree(targetGive, KitType.START, kitConfig.getKitType());
                        break;
                    default:
                        ChatUtil.error(sender, "Nieznany zestaw. Dostępne zestawy: gold, iron, start");
                        return;
                }
                ChatUtil.success(sender, "Zestaw " + kitNameGive + " został przekazany graczowi " + targetGive.getName());
                ChatUtil.info(targetGive, "Otrzymałeś zestaw od administratora");
                break;

            default:
                sendUsage(player);
                break;
        }
    }
}

