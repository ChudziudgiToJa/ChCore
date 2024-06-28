package pl.chudziudgi.core.feature.home.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.home.HomeGui;

@CommandInfo(name = "delhome", player = true, aliases = {"deldom"})

public class DelHomeCommand extends PluginCommand {


    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        HomeGui.openDeleteHome(player);
    }
}
