package pl.chudziudgi.core.feature.settings;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(
        name = "ustawienia",
        player = true
)

public class SettingCommand extends PluginCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        SettingsGui.open((Player)sender);
    }
}
