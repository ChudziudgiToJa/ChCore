package pl.chudziudgi.core.feature.home.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.home.HomeGui;
import pl.chudziudgi.core.feature.teleport.TeleportManager;

@CommandInfo(name = "home", player = true, aliases = {"dom"})

public class HomeCommand extends PluginCommand {
    private final TeleportManager teleportManager;
    private final ChCore core;

    public HomeCommand(final TeleportManager teleportManager, final ChCore core) {
        this.teleportManager = teleportManager;
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        HomeGui.openMain(player, teleportManager, core);
    }
}
