package pl.chudziudgi.core;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.command.managers.CommandManager;
import pl.chudziudgi.core.config.ConfigurationLoader;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.config.PluginConfigurationTask;
import pl.chudziudgi.core.database.Database;
import pl.chudziudgi.core.database.DatabaseTask;
import pl.chudziudgi.core.database.user.UserController;
import pl.chudziudgi.core.feature.abyss.AbyssCommand;
import pl.chudziudgi.core.feature.abyss.AbyssTask;
import pl.chudziudgi.core.feature.access.AccessCommand;
import pl.chudziudgi.core.feature.access.AccessController;
import pl.chudziudgi.core.feature.backup.BackupCommand;
import pl.chudziudgi.core.feature.backup.BackupController;
import pl.chudziudgi.core.feature.backup.BackupManager;
import pl.chudziudgi.core.feature.blocker.AnvilController;
import pl.chudziudgi.core.feature.blocker.CraftingController;
import pl.chudziudgi.core.feature.blocker.EnchantController;
import pl.chudziudgi.core.feature.chat.AutoMessageTask;
import pl.chudziudgi.core.feature.chat.ChatCommand;
import pl.chudziudgi.core.feature.chat.ChatController;
import pl.chudziudgi.core.feature.chat.ChatManager;
import pl.chudziudgi.core.feature.combat.CombatCommand;
import pl.chudziudgi.core.feature.combat.CombatController;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.combat.CombatTask;
import pl.chudziudgi.core.feature.command.*;
import pl.chudziudgi.core.feature.crafting.CraftingCommand;
import pl.chudziudgi.core.feature.crafting.CraftingRecipe;
import pl.chudziudgi.core.feature.customitem.CustomItemCommand;
import pl.chudziudgi.core.feature.customitem.magiccandle.MagicCandleController;
import pl.chudziudgi.core.feature.customitem.magiccandle.MagicCandleManager;
import pl.chudziudgi.core.feature.customitem.obsydiangenerator.ObsydianGeneratorController;
import pl.chudziudgi.core.feature.customitem.stonegenerator.StoneGeneratorController;
import pl.chudziudgi.core.feature.customitem.waterbucket.WaterBucketListener;
import pl.chudziudgi.core.feature.deposit.DepositCommand;
import pl.chudziudgi.core.feature.deposit.DepositController;
import pl.chudziudgi.core.feature.deposit.DepositTask;
import pl.chudziudgi.core.feature.drop.DropCommand;
import pl.chudziudgi.core.feature.drop.DropController;
import pl.chudziudgi.core.feature.enderchest.EnderChestController;
import pl.chudziudgi.core.feature.enderchest.EnderChestGuiListener;
import pl.chudziudgi.core.feature.enderchest.EnderchestCommand;
import pl.chudziudgi.core.feature.guild.permission.PermissionCommand;
import pl.chudziudgi.core.feature.guild.permission.PermissionController;
import pl.chudziudgi.core.feature.helpop.HelpOpCommand;
import pl.chudziudgi.core.feature.helpop.HelpOpManager;
import pl.chudziudgi.core.feature.home.command.DelHomeCommand;
import pl.chudziudgi.core.feature.home.command.HomeCommand;
import pl.chudziudgi.core.feature.home.command.SetHomeCommand;
import pl.chudziudgi.core.feature.itemshop.ItemShopCommand;
import pl.chudziudgi.core.feature.kit.KitCommand;
import pl.chudziudgi.core.feature.kit.KitManager;
import pl.chudziudgi.core.feature.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.privatemessage.command.IgnoreCommand;
import pl.chudziudgi.core.feature.privatemessage.command.MsgCommand;
import pl.chudziudgi.core.feature.privatemessage.command.ReplyCommand;
import pl.chudziudgi.core.feature.privatemessage.command.SocialSpyCommand;
import pl.chudziudgi.core.feature.protection.ProtectionCommand;
import pl.chudziudgi.core.feature.protection.ProtectionController;
import pl.chudziudgi.core.feature.protection.ProtectionManager;
import pl.chudziudgi.core.feature.protection.ProtectionTask;
import pl.chudziudgi.core.feature.question.QuestionController;
import pl.chudziudgi.core.feature.question.QuestionManager;
import pl.chudziudgi.core.feature.question.QuestionTask;
import pl.chudziudgi.core.feature.randomtp.RandomTpCommand;
import pl.chudziudgi.core.feature.randomtp.RandomTpController;
import pl.chudziudgi.core.feature.settings.SettingCommand;
import pl.chudziudgi.core.feature.shop.ShopCommand;
import pl.chudziudgi.core.feature.shop.time.TimeShopTask;
import pl.chudziudgi.core.feature.teleport.TeleportController;
import pl.chudziudgi.core.feature.teleport.TeleportManager;
import pl.chudziudgi.core.feature.tpa.TpaManager;
import pl.chudziudgi.core.feature.tpa.command.TpaAcceptCommand;
import pl.chudziudgi.core.feature.tpa.command.TpaRequestCommand;
import pl.chudziudgi.core.feature.vanish.VanishCommand;
import pl.chudziudgi.core.feature.vanish.VanishController;
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.feature.world.AntiRedStoneController;
import pl.chudziudgi.core.feature.world.WorldBorderController;
import pl.chudziudgi.core.feature.world.WorldController;
import pl.chudziudgi.core.hook.PlaceholderApiHook;

import java.util.stream.Stream;

public final class ChCore extends JavaPlugin {
    private final PluginConfiguration config = new PluginConfiguration();
    private final ConfigurationLoader configurationLoader = new ConfigurationLoader();
    private FunnyGuilds funnyGuilds;

    public void onLoad() {
        PlaceholderApiHook.isPlaceholderAPIInstalled(this);
        this.configurationLoader.load(this);
    }

    public void onDisable() {
        Database.saveDatabase();
        config.save();
    }

    public void onEnable() {
        funnyGuilds = FunnyGuilds.getInstance();
        Bukkit.getPluginManager().registerEvents(new InventoryBuilder.Listeners(), this);
        Database.load(this);

        ProtectionManager protectionManager = new ProtectionManager(this.config);
        CombatManager combatManager = new CombatManager();
        PrivateMessageManager privateMessageManager = new PrivateMessageManager();
        ChatManager chatManager = new ChatManager(this.config);
        VanishManager vanishManager = new VanishManager();
        MagicCandleManager magicCandleManager = new MagicCandleManager();
        TeleportManager teleportManager = new TeleportManager();
        HelpOpManager helpOpManager = new HelpOpManager();
        TpaManager tpaManager = new TpaManager();
        BackupManager backupManager = new BackupManager();
        QuestionManager questionManager = new QuestionManager();
        CraftingRecipe craftingRecipe = new CraftingRecipe();
        KitManager kitManager = new KitManager();

        craftingRecipe.loadCrafting(this);


        Stream.of(
                new UserController(this),
                new ProtectionController(protectionManager),
                new CombatController(combatManager, this.config, protectionManager, funnyGuilds),
                new RandomTpController(this),
                new DropController(combatManager),
                new ChatController(chatManager, this.config),
                new VanishController(this, vanishManager),
                new DepositController(this),
                new MagicCandleController(this, combatManager, magicCandleManager, this.config),
                new WorldBorderController(this.config),
                new CraftingController(this.config),
                new ObsydianGeneratorController(this, combatManager),
                new WaterBucketListener(this),
                new PermissionController(this),
                new TeleportController(teleportManager),
                new AntiRedStoneController(),
                new EnchantController(),
                new AnvilController(),
                new StoneGeneratorController(combatManager, this),
                new WorldController(),
                new QuestionController(questionManager),
                new BackupController(backupManager),
                new AccessController(this, this.config),

                new EnderChestGuiListener(this),
                new EnderChestController(this)
        ).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));


        new DatabaseTask(this);
        new CombatTask(this, combatManager, this.config);
        new ProtectionTask(this, protectionManager, this.config);
        new AutoMessageTask(this, this.config);
        new DepositTask(this);
        new AbyssTask(this);
        new TimeShopTask(this, combatManager, protectionManager);
        new PluginConfigurationTask(this, this.configurationLoader);
        new QuestionTask(questionManager, this.config, this);

        new PlaceholderApiHook(protectionManager).register();

        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands(
                new MsgCommand(privateMessageManager, chatManager, vanishManager),
                new ReplyCommand(privateMessageManager, chatManager),
                new DropCommand(),
                new DepositCommand(),
                new ProtectionCommand(protectionManager),
                new WorkbenchCommand(),
                new EnderchestCommand(),
                new PomocCommand(),
                new ClearCommand(),
                new FlightCommand(),
                new GameModeCommand(),
                new SpeedCommand(),
                new HealCommand(),
                new ChatCommand(chatManager),
                new HomeCommand(teleportManager, this),
                new SetHomeCommand(),
                new DelHomeCommand(),
                new CraftingCommand(),
                new SettingCommand(),
                new IgnoreCommand(),
                new AbyssCommand(),
                new AccessCommand(this.config),
                new KitCommand(this.config, kitManager),
                new VanishCommand(vanishManager),
                new BrodcastCommand(),
                new CombatCommand(combatManager),
                new RandomTpCommand(teleportManager, this),
                new ShopCommand(),
                new AnvilCommand(),
                new OnlineCommand(),
                new PermissionCommand(this),
                new TpaAcceptCommand(tpaManager, this),
                new TpaRequestCommand(tpaManager, vanishManager, this),
                new SocialSpyCommand(),
                new HelpOpCommand(helpOpManager),
                new BackupCommand(),
                new InventorySeeCommand(),
                new ItemShopCommand(),
                new CustomItemCommand());
    }

    public FunnyGuilds getFunnyGuilds() {
        return funnyGuilds;
    }
}
