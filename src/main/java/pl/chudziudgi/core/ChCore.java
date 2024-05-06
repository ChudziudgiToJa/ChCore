package pl.chudziudgi.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.command.managers.CommandManager;
import pl.chudziudgi.core.database.Database;
import pl.chudziudgi.core.feature.chat.AsyncPlayerChatController;
import pl.chudziudgi.core.feature.chat.AutoMessageTask;
import pl.chudziudgi.core.feature.chat.ChatCommand;
import pl.chudziudgi.core.feature.chat.ChatManager;
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
import pl.chudziudgi.core.feature.home.HomeManager;
import pl.chudziudgi.core.feature.home.command.DelHomeCommand;
import pl.chudziudgi.core.feature.home.command.HomeCommand;
import pl.chudziudgi.core.feature.home.command.SetHomeCommand;
import pl.chudziudgi.core.feature.nether.*;
import pl.chudziudgi.core.feature.ochrona.OchronaCommand;
import pl.chudziudgi.core.feature.ochrona.ProtectionController;
import pl.chudziudgi.core.feature.ochrona.ProtectionManager;
import pl.chudziudgi.core.feature.ochrona.ProtectionTask;
import pl.chudziudgi.core.feature.randomtp.RandomTpController;
import pl.chudziudgi.core.feature.schowek.DepositCommand;
import pl.chudziudgi.core.hook.PlaceholderApiHook;

public final class ChCore extends JavaPlugin {
    private ConfigLoader config;

    public void onLoad() {
        PlaceholderApiHook.isPlaceholderAPIInstalled(this);
        this.config = new ConfigLoader();
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

        new ProtectionController(this, protectionManager);
        new CombatController(this, combatManager, this.config.getCombatConfig(), protectionManager);
        new RandomTpController(this, combatManager);
        new NetherController(this, combatManager, this.config.getNetherConfig());
        new DropController(this, combatManager);
        new AsyncPlayerChatController(this, chatManager, this.config.getChatConfig());
        new CombatTask(this, combatManager);

        new ProtectionTask(this, protectionManager);
        new AutoMessageTask(this, this.config.getChatConfig());
        new NetherStatusTask(this, this.config.getNetherConfig());
        new NetherTeleportTaskT(this, this.config.getNetherConfig());
        new NetherEffectTask(this);

        new PlaceholderApiHook(protectionManager).register();

        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands(
                new MsgCommand(privateMessageManager),
                new ReplyCommand(privateMessageManager),
                new DropCommand(),
                new DepositCommand(),
                new OchronaCommand(protectionManager),
                new NetherCommand(this.config.getNetherConfig()),
                new WorkbenchCommand(),
                new EnderchestCommand(),
                new PomocCommand(),
                new ClearCommand(),
                new FlightCommand(),
                new GameModeCommand(),
                new SpeedCommand(),
                new HealCommand(),
                new ChatCommand(chatManager),
                new HomeCommand(),
                new SetHomeCommand(),
                new DelHomeCommand(),
                new InventorySeeCommand()
        );
    }
}
