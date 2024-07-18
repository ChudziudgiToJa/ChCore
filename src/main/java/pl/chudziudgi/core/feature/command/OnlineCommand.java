package pl.chudziudgi.core.feature.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "online",
        perm = "core.command.online"
)

public class OnlineCommand extends PluginCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        ChatUtil.success(sender, new MessageBuilder().setText("Gracze online: {AMOUNT} List: {LIST}")
                .addField("{AMOUNT}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .addField("{LIST}", Bukkit.getOnlinePlayers().toString())
                .build());
    }
}
