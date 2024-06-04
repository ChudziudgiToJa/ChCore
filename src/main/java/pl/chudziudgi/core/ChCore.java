package pl.chudziudgi.core;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.command.managers.CommandManager;
import pl.chudziudgi.core.database.Database;
import pl.chudziudgi.core.database.user.UserController;
import pl.chudziudgi.core.feature.abyss.AbyssCommand;
import pl.chudziudgi.core.feature.abyss.AbyssTask;
import pl.chudziudgi.core.feature.chat.AutoMessageTask;
import pl.chudziudgi.core.feature.chat.ChatCommand;
import pl.chudziudgi.core.feature.chat.ChatController;
import pl.chudziudgi.core.feature.chat.ChatManager;
import pl.chudziudgi.core.feature.chat.privatemessage.IgnoreCommand;
import pl.chudziudgi.core.feature.chat.privatemessage.MsgCommand;
import pl.chudziudgi.core.feature.chat.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.chat.privatemessage.ReplyCommand;
import pl.chudziudgi.core.feature.combat.CombatCommand;
import pl.chudziudgi.core.feature.combat.CombatController;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.combat.CombatTask;
import pl.chudziudgi.core.feature.command.admin.*;
import pl.chudziudgi.core.feature.command.admin.invsee.InventorySeeCommand;
import pl.chudziudgi.core.feature.command.user.EnderchestCommand;
import pl.chudziudgi.core.feature.command.user.PomocCommand;
import pl.chudziudgi.core.feature.command.user.WorkbenchCommand;
import pl.chudziudgi.core.feature.customitem.BlockerController;
import pl.chudziudgi.core.feature.customitem.CustomItemCommand;
import pl.chudziudgi.core.feature.customitem.obsydiangenerator.ObsydianGeneratorController;
import pl.chudziudgi.core.feature.customitem.magiccandle.MagicCandleController;
import pl.chudziudgi.core.feature.customitem.magiccandle.MagicCandleManager;
import pl.chudziudgi.core.feature.deposit.DepositCommand;
import pl.chudziudgi.core.feature.deposit.DepositController;
import pl.chudziudgi.core.feature.deposit.DepositTask;
import pl.chudziudgi.core.feature.drop.DropCommand;
import pl.chudziudgi.core.feature.drop.DropController;
import pl.chudziudgi.core.feature.home.HomeCommand;
import pl.chudziudgi.core.feature.kit.KitCommand;
import pl.chudziudgi.core.feature.nether.*;
import pl.chudziudgi.core.feature.protection.ProtectionCommand;
import pl.chudziudgi.core.feature.protection.ProtectionController;
import pl.chudziudgi.core.feature.protection.ProtectionManager;
import pl.chudziudgi.core.feature.protection.ProtectionTask;
import pl.chudziudgi.core.feature.randomtp.RandomTpCommand;
import pl.chudziudgi.core.feature.randomtp.RandomTpController;
import pl.chudziudgi.core.feature.settings.SettingCommand;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoController;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;
import pl.chudziudgi.core.feature.shop.ShopCommand;
import pl.chudziudgi.core.feature.shop.time.TimeShopTask;
import pl.chudziudgi.core.feature.vanish.VanishCommand;
import pl.chudziudgi.core.feature.vanish.VanishController;
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.feature.world.WorldBorderController;
import pl.chudziudgi.core.hook.PlaceholderApiHook;

public final class ChCore extends JavaPlugin {
    private final ConfigLoader config = new ConfigLoader();
    public FunnyGuilds funnyGuilds;

    public void onLoad() {
        PlaceholderApiHook.isPlaceholderAPIInstalled(this);
        this.config.load(this);
    }

    public void onDisable() {
        Database.saveDatabase();
    }

    public void onEnable() {
        funnyGuilds = FunnyGuilds.getInstance();
        Bukkit.getPluginManager().registerEvents(new InventoryBuilder.Listeners(), this);
        Database.load(this);

        ProtectionManager protectionManager = new ProtectionManager();
        CombatManager combatManager = new CombatManager();
        PrivateMessageManager privateMessageManager = new PrivateMessageManager();
        ChatManager chatManager = new ChatManager(this.config.getChatConfig());
        IncognitoManager incognitoManager = new IncognitoManager();
        VanishManager vanishManager = new VanishManager();
        MagicCandleManager magicCandleManager = new MagicCandleManager();

        new UserController(this);
        new ProtectionController(this, protectionManager);
        new CombatController(this, combatManager, this.config.getCombatConfig(), protectionManager);
        new RandomTpController(this);
        new NetherController(this, combatManager, this.config.getNetherConfig());
        new DropController(this, combatManager);
        new ChatController(this, chatManager, this.config.getChatConfig());
        new VanishController(this, vanishManager);
        new IncognitoController(this, incognitoManager);
        new DepositController(this);
        new MagicCandleController(this, combatManager, magicCandleManager);
        new WorldBorderController(this, this.config.getRandomTpConfig());
        new BlockerController(this, this.config.getCustomItemConfig());
        new ObsydianGeneratorController(this, combatManager);

        new CombatTask(this, combatManager, this.config.getCombatConfig());
        new ProtectionTask(this, protectionManager, this.config.getProtectionConfig());
        new AutoMessageTask(this, this.config.getChatConfig());
        new NetherStatusTask(this, this.config.getNetherConfig());
        new NetherTeleportTask(this, this.config.getNetherConfig());
        new NetherEffectTask(this);
        new DepositTask(this);
        new AbyssTask(this);
        new TimeShopTask(this, combatManager, protectionManager);

        new PlaceholderApiHook(protectionManager).register();

        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands(new MsgCommand(privateMessageManager), new ReplyCommand(privateMessageManager), new DropCommand(), new DepositCommand(), new ProtectionCommand(protectionManager), new NetherCommand(this.config.getNetherConfig()), new WorkbenchCommand(), new EnderchestCommand(), new PomocCommand(), new ClearCommand(), new FlightCommand(), new GameModeCommand(), new SpeedCommand(), new HealCommand(), new ChatCommand(chatManager, config.getChatConfig()), new HomeCommand(), new InventorySeeCommand(), new SettingCommand(), new IgnoreCommand(), new AbyssCommand(), new KitCommand(this.config.getKitConfig()), new VanishCommand(vanishManager), new BrodcastCommand(), new CombatCommand(combatManager), new RandomTpCommand(), new ShopCommand(), new CustomItemCommand());
    }

    public FunnyGuilds getFunnyGuilds() {
        return funnyGuilds;
    }
}
