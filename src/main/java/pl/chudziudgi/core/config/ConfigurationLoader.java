package pl.chudziudgi.core.config;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.Bukkit;
import pl.chudziudgi.core.ChCore;

import java.io.File;
import java.util.logging.Level;

public class ConfigurationLoader {
    private PluginConfiguration configurations;

    public void save() {
        configurations.save();
    }


    public void load(final ChCore plugin) {
        try {
            this.configurations = ConfigManager.create(PluginConfiguration.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(plugin.getDataFolder(), "config.yml"));
                it.withRemoveOrphans(true);
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, "Error loading config.yml", exception);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
}
