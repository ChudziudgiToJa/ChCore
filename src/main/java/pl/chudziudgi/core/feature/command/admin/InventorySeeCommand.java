package pl.chudziudgi.core.feature.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "invsee",
        player = true,
        perm = "core.command.invsee",
        usage = "<ec/armor/inv>"
)

public class InventorySeeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        final Player player = (Player)sender;
        final Player target = Bukkit.getPlayer(args[0]);

        if (target != null && !target.isOnline()){
            ChatUtil.error(player, "gracz nie jest aktywny");
            return;
        }
        if (args[1].equalsIgnoreCase("inv")){
            player.openInventory(target.getInventory());
            return;
        }
        if (args[1].equalsIgnoreCase("ec")){
            player.openInventory(target.getEnderChest());
            return;
        }
        if (args[1].equalsIgnoreCase("armor")){
            final Inventory eq = Bukkit.createInventory(null, 9, "Zbroja gracza: "+ target.getName());
            eq.setContents(target.getInventory().getArmorContents());
            player.openInventory(eq);
        }
        else {
            sendUsage(sender);
        }
    }
}
