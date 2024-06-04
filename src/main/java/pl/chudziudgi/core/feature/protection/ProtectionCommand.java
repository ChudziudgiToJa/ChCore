package pl.chudziudgi.core.feature.protection;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

@CommandInfo(name = "ochrona",
        player = true,
        usage = " <wylacz> aby wyłączyć ochrone"
)
public class ProtectionCommand extends PluginCommand {

    private final ProtectionManager protectionManager;

    public ProtectionCommand(ProtectionManager protectionManager) {
        this.protectionManager = protectionManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equalsIgnoreCase("wylacz")) {
            if (this.protectionManager.hasProtection(player)) {
                this.protectionManager.endProtection(player);
                ChatUtil.info(player, "Wylaczono ochrone!");
            } else {
                ChatUtil.error(player, "Nie masz ochrony!");
            }
            return;
        }

        if (!player.hasPermission("core.command.ochrona.admin")) {
            ChatUtil.error(player, "Nie masz uprawnien! &8(&7core.command.ochrona.admin&8)");
            return;
        }

        if (args.length < 2) {
            ChatUtil.error(player, "Podaj nazwę gracza!");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null || !target.isOnline()) {
            ChatUtil.error(player, "Gracz nie jest aktywny!");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "add":
                if (args.length < 4) {
                    ChatUtil.error(player, "Podaj czas ochrony!");
                    return;
                }

                try {
                    int time = Integer.parseInt(args[3]);
                    protectionManager.giveProtection(target, TimeEnum.SECOND, time);
                    ChatUtil.success(player, "Nadano ochrone dla " + target.getName() + " na czas " + time + "s.");
                } catch (NumberFormatException e) {
                    ChatUtil.error(player, "Błędny format liczby.");
                }
                break;

            case "clear":
                protectionManager.endProtection(target);
                ChatUtil.success(player, "Usunięto ochrone dla " + target.getName());
                break;

            default:
                sendUsage(sender);
        }
    }
}
