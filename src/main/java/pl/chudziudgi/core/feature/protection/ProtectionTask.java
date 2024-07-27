package pl.chudziudgi.core.feature.protection;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;

public class ProtectionTask extends BukkitRunnable {

    private final ProtectionManager protectionManager;
    private final PluginConfiguration config;

    public ProtectionTask(final ChCore plugin, ProtectionManager protectionManager, PluginConfiguration config){
        this.protectionManager = protectionManager;
        this.config = config;
        this.runTaskTimerAsynchronously(plugin, 20L, 20L);
    }

    @Override
    public void run() {
        for (final Player player : Bukkit.getOnlinePlayers()){
            if (protectionManager.getProtectionCache().get(player.getUniqueId()) != null){
                if (protectionManager.getProtectionCache().get(player.getUniqueId()) > System.currentTimeMillis()){
                    String message = new MessageBuilder().setText(this.config.protectionSettings.PROTECTION_MESSAGE)
                            .addField("{TIME}", DataUtils.durationToString(protectionManager.getProtectionCache().get(player.getUniqueId())))
                                    .build();
                    ChatUtil.sendActionbar(player, message);
                }else {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 10 ,10);
                    this.protectionManager.endProtection(player);
                }
            }
        }
    }
}