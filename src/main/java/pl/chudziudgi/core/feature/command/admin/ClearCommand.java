package pl.chudziudgi.core.feature.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "clear", player = true, perm = "core.command.clear", usage = "<gracz>")
public class ClearCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length == 0) {
            final Player player = (Player) sender;
            clear(player);
            ChatUtil.success(player, "Wyczyszczono ekwipunek!");
            return;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        final Player admin = (Player) sender;
        if (player == null) {
            ChatUtil.error(sender, "Gracz jest offline!");
            return;
        }
        clear(player);
        ChatUtil.success(sender, "Wyczyszczono ekwipunek gracza " + player.getName() );
    }

    private void clear(final Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getInventory().clear();

    }
}
