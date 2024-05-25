package pl.chudziudgi.core.feature.home;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "dom", player = true, aliases = {"home"})
public class HomeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            ChatUtil.info(sender, "");
            ChatUtil.info(sender, "Poprawne użycie komendy &3&n/dom");
            ChatUtil.info(sender, "");
            ChatUtil.info(sender, "&9/dom ustaw &7otwiera menu ustawiania domów");
            ChatUtil.info(sender, "&9/dom usuń &7otwiera menu usuwania domów");
            ChatUtil.info(sender, "&9/dom otwórz &7otwiera menu listy domów");
            ChatUtil.info(sender, "");
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
        if (args[0].equalsIgnoreCase("otwórz") || args[0].equalsIgnoreCase("otworz")) {
            HomeGui.openHome(player);
        }
    }
}
