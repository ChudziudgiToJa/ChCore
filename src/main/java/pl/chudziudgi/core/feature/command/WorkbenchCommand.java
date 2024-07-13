package pl.chudziudgi.core.feature.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(name = "crafting", perm = "core.workbench", player = true)
public class WorkbenchCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        final Player player = (Player) sender;
        final Inventory inv = Bukkit.createInventory(player, InventoryType.WORKBENCH);
        player.openInventory(inv);
    }
}
