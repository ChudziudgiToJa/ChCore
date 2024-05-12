package pl.chudziudgi.core.feature.kit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.Base64;

@CommandInfo(
        name = "kit"
)

public class KitCommand extends PluginCommand {

    private final KitConfig kitConfig;

    public KitCommand(KitConfig kitConfig) {
        this.kitConfig = kitConfig;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        Inventory inventory = player.getInventory();

        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                kitConfig.startItems.add(Base64.getEncoder());
            }
        }
        ChatUtil.success(sender, "Zapisano przedmioty");
    }
}
