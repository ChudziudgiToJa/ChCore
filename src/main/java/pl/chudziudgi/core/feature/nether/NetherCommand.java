package pl.chudziudgi.core.feature.nether;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.randomtp.RandomUtil;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "nether",
        perm = "core.nether.admin",
        usage = "toggle/tp",
        player = false
)
public class NetherCommand extends PluginCommand {

    private final NetherConfig config;

    public NetherCommand(NetherConfig config) {
        this.config = config;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("toggle")) {
            config.setNetherStatus(!config.isNetherStatus());
            ChatUtil.success(sender, "Aktualny status netheru " + (config.isNetherStatus() ? "wlaczony" : "wylaczony"));
            return;
        }
        if (args[0].equalsIgnoreCase("tp")) {

            if (args.length == 1) {
                player.teleport(RandomUtil.getRandomCords(1));
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                ChatUtil.error(sender, "Taki gracz nie istnieje.");
                return;
            }

            if (!target.isOnline()) {
                ChatUtil.error(sender, "Gracz nie jest online");
                return;
            }

            if (target == player) {
                player.teleport(RandomUtil.getRandomCords(1));
                return;
            }

            target.teleport(RandomUtil.getRandomCords(1));
            ChatUtil.success(sender, new MessageBuilder().setText("Nether teleportowano {TARGET} na losowe kordy").addField("{TARGET}", target.getName()).build());
        } else {
            sendUsage(sender);
        }
    }
}
