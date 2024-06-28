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
        usage = " <wylacz> / <add/liczba>/<clear>"
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
            sendUsage(sender);
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null || !target.isOnline()) {
            ChatUtil.error(sender, "Gracz " + args[1] + " nie jest online.");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "add":
                if (args.length < 3) {
                    ChatUtil.error(sender, "Podaj czas ochrony!");
                    return;
                }

                try {
                    int time = Integer.parseInt(args[2]);
                    protectionManager.giveProtection(target, TimeEnum.SECOND, time);
                    ChatUtil.success(sender, "Nadano ochrone dla " + target.getName() + " na czas " + time + "s.");
                } catch (NumberFormatException e) {
                    ChatUtil.error(sender, "Błędny format liczby.");
                }
                break;

            case "clear":
                protectionManager.endProtection(target);
                ChatUtil.success(sender, "Usunięto ochrone dla " + target.getName());
                break;

            default:
                sendUsage(sender);
                break;
        }
    }
}