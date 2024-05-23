package pl.chudziudgi.core.feature.command.admin;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "ogloszenie",
        player = true,
        perm = "core.command.brodcast"
)

public class BrodcastCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        String message = StringUtils.join(args, " ");
        ChatUtil.broadcast(" ");
        ChatUtil.broadcast("&8[&d&l!&8] &cⓤⓌⓐⒼⓐ&7: " + message);
        ChatUtil.broadcast(" ");
        Bukkit.getOnlinePlayers().forEach(player -> ChatUtil.sendTitle(player, "&cⓤⓌⓐⒼⓐ", "Spójrz na chat!", 10,50,10));
    }
}
