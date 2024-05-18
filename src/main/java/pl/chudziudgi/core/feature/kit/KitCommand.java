package pl.chudziudgi.core.feature.kit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(
        name = "kit",
        player = true
)

public class KitCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        KitGui.openMain(player);
    }
}
