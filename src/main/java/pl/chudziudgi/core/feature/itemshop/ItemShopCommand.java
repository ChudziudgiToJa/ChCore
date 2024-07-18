package pl.chudziudgi.core.feature.itemshop;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "itemshop",
        player = false,
        perm = "core.command.itemshop",
        usage = "<iron/gold/candle> <gracz> <ilość>"
)
public class ItemShopCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 3) {
            sendUsage(sender);
            return;
        }

        String subCommand = args[0].toLowerCase();
        Player target = Bukkit.getPlayer(args[1].toLowerCase());

        int amount;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            ChatUtil.error(sender, "Amount must be a number.");
            return;
        }

        switch (subCommand) {
            case "gold":
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[1].toLowerCase() + " group add gold");
                Bukkit.getOnlinePlayers().forEach(all -> {
                    ChatUtil.sendTitle(all, "&bⓈⓀⓁⒺⓅ", args[1].toLowerCase() + " &7zakupił range: &e&lGOLD", 50, 50, 50);
                    all.playSound(all, Sound.ENTITY_ENDER_DRAGON_DEATH, 5, 5);
                });
                break;
            case "iron":
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[1].toLowerCase() + " group add iron");
                Bukkit.getOnlinePlayers().forEach(all -> {
                    ChatUtil.sendTitle(all, "&bⓈⓀⓁⒺⓅ", args[1].toLowerCase() + " &7zakupił range: &f&lIRON", 50, 50, 50);
                    all.playSound(all, Sound.ENTITY_ENDER_DRAGON_DEATH, 5, 5);
                });
                break;
            case "candle":
                if (target == null) {
                    ChatUtil.error(sender, "Gracz nie jest online");
                    return;
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "customitem give candle " + amount + " " + target.getName());
                Bukkit.getOnlinePlayers().forEach(all -> {
                    ChatUtil.sendTitle(all, "&bⓈⓀⓁⒺⓅ", target.getName() + " &7zakupił &fMagiczną świece &8x" + amount, 50, 50, 50);
                    all.playSound(all, Sound.ENTITY_ENDER_DRAGON_DEATH, 5, 5);
                });
                break;
            default:
                sendUsage(sender);
                break;
        }
    }
}
