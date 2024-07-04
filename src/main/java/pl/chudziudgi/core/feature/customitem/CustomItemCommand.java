package pl.chudziudgi.core.feature.customitem;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "customitem", player = false, perm = "core.command.customitem", usage = "give <candle/obsydian/stone> <player>")


public class CustomItemCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 3) {
            if (sender instanceof Player) {
                sendUsage((Player) sender);
            } else {
                ChatUtil.error(sender, "Musisz podać prawidłową komendę. Użyj: /customitem give <itemType> <amount> [target]");
            }
            return;
        }

        String subCommand = args[0];
        String itemType = args[1];
        String amountStr = args[2];
        Player target = args.length > 3 ? Bukkit.getPlayer(args[3]) : (sender instanceof Player ? (Player) sender : null);

        if (!subCommand.equalsIgnoreCase("give")) {
            if (sender instanceof Player) {
                sendUsage((Player) sender);
            } else {
                ChatUtil.error(sender, "Musisz podać prawidłową komendę. Użyj: /customitem give <itemType> <amount> [target]");
            }
            return;
        }

        if (target == null || !target.isOnline()) {
            ChatUtil.error(sender, "Gracz nie jest online");
            return;
        }

        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            ChatUtil.error(sender, "Błędny format liczby itemu.");
            return;
        }

        ItemStack itemStack;
        switch (itemType.toLowerCase()) {
            case "obsydian":
                itemStack = CustomItemStack.obsydianGenerator();
                break;
            case "candle":
                itemStack = CustomItemStack.magicCandle();
                break;
            case "stone":
                itemStack = CustomItemStack.stoneGenerator();
                break;
            default:
                if (sender instanceof Player) {
                    sendUsage((Player) sender);
                } else {
                    ChatUtil.error(sender, "Nieznany typ przedmiotu. Dostępne typy: obsydian, candle, stone");
                }
                return;
        }

        itemStack.setAmount(amount);
        target.getInventory().addItem(itemStack);
        ChatUtil.success(sender, "&7Nadano " + amount + " " + itemType + " dla " + target.getName());
    }
}
