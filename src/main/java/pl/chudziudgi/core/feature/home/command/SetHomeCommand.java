package pl.chudziudgi.core.feature.home.command;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.home.HomeGui;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "sethome", player = true, usage = "<player>")
public class SetHomeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            ChatUtil.error(player, "Nie możesz ustawić domu w piekle!");
            return;
        }
        HomeGui.openSetHome(player);
    }
}