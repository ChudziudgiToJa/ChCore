package pl.chudziudgi.core.feature.deposit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(name = "schowek",
        player = true
)
public class DepositCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        DepositGui.open((Player)sender);
    }
}
