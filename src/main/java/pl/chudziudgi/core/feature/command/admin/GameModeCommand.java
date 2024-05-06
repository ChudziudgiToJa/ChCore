package pl.chudziudgi.core.feature.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "gamemode", player = true, perm = "core.command.gamemode", usage = "<0/1/2/3> [gracz]", aliases = {"gm"})
public class GameModeCommand extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0 || args.length > 2) {
            sendUsage(sender);
            return;
        }

        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        Player target = null;
        if (args.length == 2) {
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                ChatUtil.error(sender, "&4Blad: &cGracz jest offline!");
                return;
            }
        } else if (!(sender instanceof Player)) {
            sendUsage(sender);
            return;
        }

        GameMode gameMode = null;
        switch (args[0]) {
            case "0", "survival", "s":
                gameMode = GameMode.SURVIVAL;
                break;
            case "1", "creative", "c":
                gameMode = GameMode.CREATIVE;
                break;
            case "2", "adventure", "a":
                gameMode = GameMode.ADVENTURE;
                break;
            case "3", "spectator", "sp":
                gameMode = GameMode.SPECTATOR;
                break;
            default:
                sendUsage(sender);
                return;
        }

        if (target == null) {
            player.setGameMode(gameMode);
            ChatUtil.success(player, "&aZmieniono tryb gry na: &f" + gameMode);
        } else {
            target.setGameMode(gameMode);
            if (player != null) {
                ChatUtil.success(player, "&aZmieniles tryb graczowi: &f" + target.getName() + " &ana: " + gameMode);
            }
        }
    }
}
