package pl.chudziudgi.core;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.Bukkit;
import pl.chudziudgi.core.feature.chat.ChatConfig;
import pl.chudziudgi.core.feature.combat.CombatConfig;
import pl.chudziudgi.core.feature.drop.DropConfig;
import pl.chudziudgi.core.feature.nether.NetherConfig;
import pl.chudziudgi.core.feature.randomtp.RandomTpConfig;
import pl.chudziudgi.core.feature.schowek.DepositConfig;

import java.io.File;
import java.util.logging.Level;

public class ConfigLoader {
    private DropConfig dropConfig;
    private ChatConfig chatConfig;
    private NetherConfig netherConfig;
    private DepositConfig depositConfig;
    private RandomTpConfig randomTpConfig;
    private CombatConfig combatConfig;

    public DropConfig getDropConfig() {
        return dropConfig;
    }

    public ChatConfig getChatConfig() {
        return chatConfig;
    }

    public NetherConfig getNetherConfig() {
        return netherConfig;
    }

    public DepositConfig getDepositConfig() {
        return depositConfig;
    }

    public RandomTpConfig getRandomTpConfig() {
        return randomTpConfig;
    }

    public CombatConfig getCombatConfig() {
        return combatConfig;
    }

    public void load(final ChCore plugin) {
        // Drop
        try {
            this.dropConfig = ConfigManager.create(DropConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "drop.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading drop.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // Schowek
        try {
            this.depositConfig = ConfigManager.create(DepositConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "schowek.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading schowek.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // RandomTp
        try {
            this.randomTpConfig = ConfigManager.create(RandomTpConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "randomtp.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading randommtp.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // RandomTp
        try {
            this.combatConfig = ConfigManager.create(CombatConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "combat.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading combat.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // NetherConfig
        try {
            this.netherConfig = ConfigManager.create(NetherConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "nether.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading nether.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // Chat
        try {
            this.chatConfig = ConfigManager.create(ChatConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "chat.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading chat.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
}
