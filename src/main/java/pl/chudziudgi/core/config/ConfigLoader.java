package pl.chudziudgi.core.config;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.Bukkit;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.access.AccessConfig;
import pl.chudziudgi.core.feature.blocker.BlockerConfig;
import pl.chudziudgi.core.feature.chat.ChatConfig;
import pl.chudziudgi.core.feature.combat.CombatConfig;
import pl.chudziudgi.core.feature.customitem.magiccandle.MagicCandleConfig;
import pl.chudziudgi.core.feature.deposit.DepositConfig;
import pl.chudziudgi.core.feature.drop.DropConfig;
import pl.chudziudgi.core.feature.kit.KitConfig;
import pl.chudziudgi.core.feature.protection.ProtectionConfig;
import pl.chudziudgi.core.feature.question.QuestionConfig;
import pl.chudziudgi.core.feature.randomtp.RandomTpConfig;
import pl.chudziudgi.core.feature.shop.time.TimeShopConfig;

import java.io.File;
import java.util.logging.Level;

public class ConfigLoader {
    private DropConfig dropConfig;
    private ChatConfig chatConfig;
    private DepositConfig depositConfig;
    private RandomTpConfig randomTpConfig;
    private CombatConfig combatConfig;
    private KitConfig kitConfig;
    private ProtectionConfig protectionConfig;
    private BlockerConfig blockerConfig;
    private TimeShopConfig timeShopConfig;
    private QuestionConfig questionConfig;
    private AccessConfig accessConfig;
    private MagicCandleConfig magicCandleConfig;

    public MagicCandleConfig getMagicCandleConfig() {
        return magicCandleConfig;
    }

    public AccessConfig getAccessConfig() {
        return accessConfig;
    }

    public QuestionConfig getQuestionConfig() {
        return questionConfig;
    }

    public BlockerConfig getBlockerConfig() {
        return blockerConfig;
    }

    public TimeShopConfig getTimeShopConfig() {
        return timeShopConfig;
    }

    public BlockerConfig getCustomItemConfig() {
        return blockerConfig;
    }

    public ProtectionConfig getProtectionConfig() {
        return protectionConfig;
    }

    public KitConfig getKitConfig() {
        return kitConfig;
    }

    public DropConfig getDropConfig() {
        return dropConfig;
    }

    public ChatConfig getChatConfig() {
        return chatConfig;
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

    public void save(final ChCore plugin) {
        chatConfig.save();
        combatConfig.save();
        dropConfig.save();
        kitConfig.save();
        depositConfig.save();
        blockerConfig.save();
        randomTpConfig.save();
        protectionConfig.save();
        questionConfig.save();
        accessConfig.save();
        magicCandleConfig.save();
                plugin.getLogger().info("Zapisano configi");
    }


    public void load(final ChCore plugin) {
        // Drop
        try {
            this.dropConfig = ConfigManager.create(DropConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "drop.yml"));
                it.withRemoveOrphans(true);
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
                it.withBindFile(new File(plugin.getDataFolder(), "deposit.yml"));
                it.withRemoveOrphans(true);
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
                it.withBindFile(new File(plugin.getDataFolder(), "random.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading randommtp.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // Combat
        try {
            this.combatConfig = ConfigManager.create(CombatConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "combat.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading combat.yml", exception);
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
        // Kit
        try {
            this.kitConfig = ConfigManager.create(KitConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "kit.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading kit.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // protection
        try {
            this.protectionConfig = ConfigManager.create(ProtectionConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "ochrona.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading ochrona.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // customitem
        try {
            this.blockerConfig = ConfigManager.create(BlockerConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "customitem.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading customitem.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // timeShop
        try {
            this.timeShopConfig = ConfigManager.create(TimeShopConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "timeshop.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading timeshop.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // Question
        try {
            this.questionConfig = ConfigManager.create(QuestionConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "question.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading question.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // Access
        try {
            this.accessConfig = ConfigManager.create(AccessConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "access.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading access.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        // magic candle config
        try {
            this.magicCandleConfig = ConfigManager.create(MagicCandleConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "magicCandle.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading magicCandle.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
}
