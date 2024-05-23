package pl.chudziudgi.core.feature.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "invsee",
        player = true,
        perm = "core.command.invsee",
        usage = "<gracz>"
)

public class InventorySeeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        final Player player = (Player)sender;

        if (args.length == 0) {
            sendUsage(sender);
            return;
        }

        final Player target = Bukkit.getPlayer(args[1]);

        if (target != null && !target.isOnline()){
            ChatUtil.error(player, "gracz nie jest aktywny");
            return;
        }
        final InventoryBuilder inv = new InventoryBuilder("&9Ekwipunek: " + target.getName(), player, 36);

        for (int i = 0; i < target.getInventory().getSize(); i++) {
            inv.setItem(i, target.getInventory().getItem(i));
        }
    }
}
