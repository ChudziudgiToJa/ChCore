package pl.chudziudgi.core.feature.ochrona;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.drop.OverWorldDropGui;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "ochrona",
        player = true
)
public class OchronaCommand extends PluginCommand {

    private final ProtectionManager protectionManager;

    public OchronaCommand(ProtectionManager protectionManager) {
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
        ChatUtil.warning(player, "&6Pozostaly czas ochrony: " + (this.protectionManager.getProtectionCache().get(player.getUniqueId())));
        ChatUtil.warning(player, "&e/ochrona wylacz &6- jezeli chcesz wylaczyc ochrone!");
    }
    }
