package pl.chudziudgi.core.feature.ochrona;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;

public class ProtectionTask extends BukkitRunnable {

    private final ProtectionManager protectionManager;

    public ProtectionTask(final ChCore plugin, ProtectionManager protectionManager){
        this.protectionManager = protectionManager;
        this.runTaskTimerAsynchronously(plugin, 20L, 20L);
    }

    @Override
    public void run() {
        for (final Player player : Bukkit.getOnlinePlayers()){
            if (protectionManager.getProtectionCache().get(player.getUniqueId()) != null){
                if (protectionManager.getProtectionCache().get(player.getUniqueId()) > System.currentTimeMillis()){
                    ChatUtil.sendActionbar(player, "&6⌚ Ochrona &7pozostalo &e"+ DataUtils.durationToString(protectionManager.getProtectionCache().get(player.getUniqueId())));
                }else {
                    this.protectionManager.endProtection(player);
                }
            }
        }
    }
}