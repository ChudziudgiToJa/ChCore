package pl.chudziudgi.core.feature.home;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "dom", player = true)
public class HomeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            HomeGui.openHome(player);
            return;
        }
        if (args[0].equalsIgnoreCase("usuń") || args[0].equalsIgnoreCase("usun")) {
            HomeGui.openDeleteHome(player);
            return;
        }
        if (args[0].equalsIgnoreCase("ustaw")) {
            HomeGui.openSetHome(player);
            return;
        }
        if (args[0].equalsIgnoreCase("pomoc")) {
            ChatUtil.error(sender, "Poprawne użycie komendy &3&n/dom");
            ChatUtil.error(sender, "");
            ChatUtil.error(sender, "&9/dom ustaw &7otwiera menu ustawiania domów");
            ChatUtil.error(sender, "&9/dom usuń &7otwiera menu usuwania domów");
            ChatUtil.error(sender, "&9/dom pomoc &7informacje o poleceniu");
            ChatUtil.error(sender, "&9/dom &7otwiera menu listy domów");
            ChatUtil.error(sender, "");
        }
    }
}
