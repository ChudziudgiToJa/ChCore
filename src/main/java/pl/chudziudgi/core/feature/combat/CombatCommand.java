package pl.chudziudgi.core.feature.combat;

import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(
        name = "antylogaut",
        player = true,
        perm = "core.command.antylogaut",
        usage = "<clear/add>"
)

public class CombatCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
}
