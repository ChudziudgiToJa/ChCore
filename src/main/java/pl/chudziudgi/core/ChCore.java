package pl.chudziudgi.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.command.managers.CommandManager;
import pl.chudziudgi.core.database.Database;
import pl.chudziudgi.core.feature.abyss.AbyssCommand;
import pl.chudziudgi.core.feature.abyss.AbyssTask;
import pl.chudziudgi.core.feature.chat.AsyncPlayerChatController;
import pl.chudziudgi.core.feature.chat.AutoMessageTask;
import pl.chudziudgi.core.feature.chat.ChatCommand;
import pl.chudziudgi.core.feature.chat.ChatManager;
import pl.chudziudgi.core.feature.chat.privatemessage.IgnoreCommand;
import pl.chudziudgi.core.feature.chat.privatemessage.MsgCommand;
import pl.chudziudgi.core.feature.chat.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.chat.privatemessage.ReplyCommand;
import pl.chudziudgi.core.feature.combat.CombatController;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.combat.CombatTask;
import pl.chudziudgi.core.feature.command.admin.*;
import pl.chudziudgi.core.feature.command.user.EnderchestCommand;
import pl.chudziudgi.core.feature.command.user.PomocCommand;
import pl.chudziudgi.core.feature.command.user.WorkbenchCommand;
import pl.chudziudgi.core.feature.drop.DropCommand;
import pl.chudziudgi.core.feature.drop.DropController;
import pl.chudziudgi.core.feature.home.command.DelHomeCommand;
import pl.chudziudgi.core.feature.home.command.HomeCommand;
import pl.chudziudgi.core.feature.home.command.SetHomeCommand;
import pl.chudziudgi.core.feature.kit.KitCommand;
import pl.chudziudgi.core.feature.listener.PlayerJoinQuitListener;
import pl.chudziudgi.core.feature.nether.*;
import pl.chudziudgi.core.feature.protection.ProtectionCommand;
import pl.chudziudgi.core.feature.protection.ProtectionController;
import pl.chudziudgi.core.feature.protection.ProtectionManager;
import pl.chudziudgi.core.feature.protection.ProtectionTask;
import pl.chudziudgi.core.feature.randomtp.RandomTpController;
import pl.chudziudgi.core.feature.deposit.DepositCommand;
import pl.chudziudgi.core.feature.deposit.DepositTask;
import pl.chudziudgi.core.feature.settings.SettingCommand;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;
import pl.chudziudgi.core.feature.vanish.VanishCommand;
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.hook.PlaceholderApiHook;

public final class ChCore extends JavaPlugin {
    private final ConfigLoader config = new ConfigLoader();

    public void onLoad() {
        PlaceholderApiHook.isPlaceholderAPIInstalled(this);
        this.config.load(this);
    }

    public void onDisable() {
        Database.saveDatabase();
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new InventoryBuilder.Listeners(), this);
        Database.load(this);

        ProtectionManager protectionManager = new ProtectionManager();
        CombatManager combatManager = new CombatManager();
        PrivateMessageManager privateMessageManager = new PrivateMessageManager();
        ChatManager chatManager = new ChatManager(this.config.getChatConfig());
        IncognitoManager incognitoManager = new IncognitoManager();
        VanishManager vanishManager = new VanishManager();

        new ProtectionController(this, protectionManager);
        new CombatController(this, combatManager, this.config.getCombatConfig(), protectionManager);
        new RandomTpController(this);
        new NetherController(this, combatManager, this.config.getNetherConfig());
        new DropController(this, combatManager);
        new AsyncPlayerChatController(this, chatManager, this.config.getChatConfig());
        new PlayerJoinQuitListener(this,incognitoManager, vanishManager);

        new CombatTask(this, combatManager);
        new ProtectionTask(this, protectionManager);
        new AutoMessageTask(this, this.config.getChatConfig());
        new NetherStatusTask(this, this.config.getNetherConfig());
        new NetherTeleportTaskT(this, this.config.getNetherConfig());
        new NetherEffectTask(this);
        new DepositTask(this);
        new AbyssTask(this);

        new PlaceholderApiHook(protectionManager).register();

        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands(
                new MsgCommand(privateMessageManager),
                new ReplyCommand(privateMessageManager),
                new DropCommand(),
                new DepositCommand(),
                new ProtectionCommand(protectionManager),
                new NetherCommand(this.config.getNetherConfig()),
                new WorkbenchCommand(),
                new EnderchestCommand(),
                new PomocCommand(),
                new ClearCommand(),
                new FlightCommand(),
                new GameModeCommand(),
                new SpeedCommand(),
                new HealCommand(),
                new ChatCommand(chatManager, config.getChatConfig()),
                new HomeCommand(),
                new SetHomeCommand(),
                new DelHomeCommand(),
                new InventorySeeCommand(),
                new SettingCommand(),
                new IgnoreCommand(),
                new AbyssCommand(),
                new KitCommand(this.config.getKitConfig()),
                new VanishCommand(vanishManager)
        );
    }
}
