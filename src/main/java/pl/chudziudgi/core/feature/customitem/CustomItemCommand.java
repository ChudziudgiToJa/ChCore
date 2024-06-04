package pl.chudziudgi.core.feature.customitem;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "customitemm", player = true, perm = "core.command.customitem", usage = "give <swieca/obsydian> <player>")


public class CustomItemCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 3) {
            sendUsage(player);
            return;
        }

        String subCommand = args[0];
        String itemType = args[1];
        String amountStr = args[2];
        Player target = args.length > 3 ? Bukkit.getPlayer(args[3]) : player;

        if (!subCommand.equalsIgnoreCase("give")) {
            sendUsage(player);
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
            ChatUtil.error(player, "Błędny format liczby itemu.");
            return;
        }

        ItemStack itemStack;
        switch (itemType.toLowerCase()) {
            case "obsydian":
                itemStack = CustomItemStack.obsydianGenerator();
                break;
            case "swieca":
                itemStack = CustomItemStack.magicCandle();
                break;
            default:
                sendUsage(player);
                return;
        }

        itemStack.setAmount(amount);
        target.getInventory().addItem(itemStack);
        ChatUtil.success(player, "&7Nadano " + amount + " " + itemType + " dla " + target.getName());
    }
}
