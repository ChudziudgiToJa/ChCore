package pl.chudziudgi.core.feature.command.user;

import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "pomoc",
        player = true
)
public class PomocCommand extends PluginCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        ChatUtil.info(sender, "cos tu kiedyś będzie");
    }
}
