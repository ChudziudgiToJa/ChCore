package pl.chudziudgi.core.feature.command.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(name = "workbench", perm = "mhCore.workbench", player = true, aliases = "wb")
public class WorkbenchCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        final Player player = (Player) sender;
        player.openWorkbench(player.getLocation(), true);
    }
}
