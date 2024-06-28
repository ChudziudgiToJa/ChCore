package pl.chudziudgi.core.feature.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "fly", perm = "core.command.fly", usage = "[gracz]", player = true)
public class FlightCommand extends PluginCommand {


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            final Player player = (Player) sender;
            player.setAllowFlight(!player.getAllowFlight());
            ChatUtil.success(player,"Fly został: " + (player.getAllowFlight() ? "&awłączony" : "&cWyłączony"));
            return;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null || !player.isOnline()) {
            ChatUtil.error(sender, "Gracz jest offline!");
            return;
        }
        player.setAllowFlight(!player.getAllowFlight());
        ChatUtil.success(player, "Fly dla: &f" + player.getName() + " &7zostało: " + (player.getAllowFlight() ? "&awłączone" : "&cwyłączone"));
    }
}