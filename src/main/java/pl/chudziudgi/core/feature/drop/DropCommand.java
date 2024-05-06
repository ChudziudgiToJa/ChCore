package pl.chudziudgi.core.feature.drop;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(name = "drop",
        player = true
)
public class DropCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            NetherDropGui.open((Player) sender);
        } else {
            OverWorldDropGui.open((Player) sender);
        }
    }
}