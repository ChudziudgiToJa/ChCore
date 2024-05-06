package pl.chudziudgi.core.feature.home.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.home.HomeGui;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "home", player = true, usage = "<player>")
public class HomeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        HomeGui.openHome(player);
    }
}