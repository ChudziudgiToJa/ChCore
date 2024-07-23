package pl.chudziudgi.core.feature.kit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CommandInfo(
        name = "kit",
        player = false,
        usage = "<clear/toggle/give> <player> <kit name>"
)

public class KitCommand extends PluginCommand {

    private final KitConfig kitConfig;
    private final KitManager kitManager;

    public KitCommand(KitConfig kitConfig, KitManager kitManager) {
        this.kitConfig = kitConfig;
        this.kitManager = kitManager;
    }
    private List<String> getKitNames() {
        List<String> names = new ArrayList<>();
        for (Kit kit : kitConfig.getKits()) {
            names.add(kit.getName());
        }
        return names;
    }

    private Kit getKitObject(String kitName) {
        for (Kit kit : kitConfig.getKits()) {
            if (Objects.equals(kit.getName(), kitName)) {
                return kit;
            }
        }
        return null;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                ChatUtil.error(sender, "&8[&4!&8] &7Tej komendy nie można wywołać z poziomu konsoli!");
                return;
            }
            if (!kitConfig.isKitStatus() && !player.hasPermission("core.command.kit.admin")) {
                ChatUtil.error(sender, "Zestawy są aktualnie wyłączone.");
                return;
            }
            KitGui.kits(player);
            return;
        }

        List<String> kitNameList = getKitNames();
        String command = args[0].toLowerCase();

        switch (command) {
            case "toggle":
                handleToggleCommand(sender);
                break;

            case "give":
                handleGiveCommand(sender, args, kitNameList);
                break;

            case "clear":
                handleClearCommand(sender, args, kitNameList);
                break;

            default:
                ChatUtil.error(sender, "Nieznana komenda. Użyj: toggle, give, clear.");
                break;
        }
    }

    private void handleToggleCommand(CommandSender sender) {
        kitConfig.setKitStatus(!kitConfig.isKitStatus());
        ChatUtil.success(sender, "Zmieniono status otwierania kitów na: " + (kitConfig.isKitStatus() ? "włączone" : "wyłączone"));
    }

    private void handleGiveCommand(CommandSender sender, String[] args, List<String> kitNameList) {
        if (args.length < 3) {
            ChatUtil.error(sender, "Użyj: /kit give <gracz> <zestaw>");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);
        String targetKit = args[2];

        if (target == null || !target.isOnline()) {
            ChatUtil.error(sender, "Gracz nie jest online");
            return;
        }

        if (!kitNameList.contains(targetKit)) {
            ChatUtil.error(sender, "Wybierz jeden z kitów: " + kitNameList);
            return;
        }

        Kit kit = getKitObject(targetKit);
        if (kit == null) {
            ChatUtil.error(sender, "Nie znaleziono zestawu: " + targetKit);
            return;
        }

        kitManager.giveKit(target, kit);
        ChatUtil.success(sender, "Nadano zestaw: " + targetKit + " dla " + target.getName());
    }

    private void handleClearCommand(CommandSender sender, String[] args, List<String> kitNameList) {
        if (args.length < 3) {
            ChatUtil.error(sender, "Użyj: /kit clear <gracz> <zestaw>");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);
        String targetKit = args[2];

        if (target == null || !target.isOnline()) {
            ChatUtil.error(sender, "Gracz nie jest online");
            return;
        }

        if (!kitNameList.contains(targetKit)) {
            ChatUtil.error(sender, "Wybierz jeden z kitów: " + kitNameList);
            return;
        }

        Kit kit = getKitObject(targetKit);
        if (kit == null) {
            ChatUtil.error(sender, "Nie znaleziono zestawu: " + targetKit);
            return;
        }
        User user = UserManager.get(target);
        user.kitList.remove(kit.getName());
        ChatUtil.success(sender, "Wyczyszczono czas oczekiwania na zestaw: " + targetKit + " dla " + target.getName());
    }
}