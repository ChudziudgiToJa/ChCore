package pl.chudziudgi.core.feature.crafting;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;


@CommandInfo(
        name = "craftingi",
        player = true
)

public class CraftingCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        CraftingGui.chose((Player) sender);
    }
}
