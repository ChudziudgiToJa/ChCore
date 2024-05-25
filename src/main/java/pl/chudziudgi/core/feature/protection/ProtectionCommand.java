package pl.chudziudgi.core.feature.protection;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "ochrona",
        player = true
)
public class ProtectionCommand extends PluginCommand {

    private final ProtectionManager protectionManager;

    public ProtectionCommand(ProtectionManager protectionManager) {
        this.protectionManager = protectionManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        final Player player = (Player)sender;
        if (!this.protectionManager.hasProtection(player)){
            ChatUtil.error(player, "&4Blad: &cNie masz ochrony!");
            return;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("wylacz")){
            this.protectionManager.endProtection(player);
            ChatUtil.info(player, "&6Wylaczono ochrone!");
            return;
        }
        ChatUtil.warning(player, "/ochrona wylacz &3jezeli chcesz wylaczyc ochrone!");
    }
    }
