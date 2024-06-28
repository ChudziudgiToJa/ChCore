package pl.chudziudgi.core.feature.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(name = "crafting", perm = "core.workbench", player = true)
public class WorkbenchCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        final Player player = (Player) sender;
        final InventoryBuilder inv = new InventoryBuilder("&9przenośny crafting", InventoryType.CRAFTING);
        inv.open(player);
    }
}
