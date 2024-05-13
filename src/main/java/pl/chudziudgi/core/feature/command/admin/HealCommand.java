package pl.chudziudgi.core.feature.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(
        name = "heal",
        perm = "core.command.heal",
        usage = "<gracz>"
)
public class HealCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0){
            Player player = (Player)sender;
            heal(player);
            ChatUtil.sendTitle(player, "", "Uleczono", 20, 60, 20);
            return;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null){
            ChatUtil.error(sender, "Gracz jest offline!");
            return;
        }
        heal(player);
        ChatUtil.success(sender, "Uleczono: " + player.getName());
    }

    private void heal(final Player player){
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.setFoodLevel(20);
        player.setHealth(20.0);
        player.setFireTicks(0);
    }
}
