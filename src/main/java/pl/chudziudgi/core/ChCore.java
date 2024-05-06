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
<<<<<<< HEAD
import pl.chudziudgi.core.feature.command.admin.*;
import pl.chudziudgi.core.feature.command.user.EnderchestCommand;
import pl.chudziudgi.core.feature.command.user.PomocCommand;
import pl.chudziudgi.core.feature.command.user.WorkbenchCommand;
import pl.chudziudgi.core.feature.chat.privatemessage.MsgCommand;
import pl.chudziudgi.core.feature.chat.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.chat.privatemessage.ReplyCommand;
=======
import pl.chudziudgi.core.feature.essential.command.admin.*;
import pl.chudziudgi.core.feature.essential.command.user.EnderchestCommand;
import pl.chudziudgi.core.feature.essential.command.user.PomocCommand;
import pl.chudziudgi.core.feature.essential.command.user.WorkbenchCommand;
import pl.chudziudgi.core.feature.essential.privatemessage.MsgCommand;
import pl.chudziudgi.core.feature.essential.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.feature.essential.privatemessage.ReplyCommand;
>>>>>>> origin/master
import pl.chudziudgi.core.feature.combat.CombatController;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.combat.CombatTask;
import pl.chudziudgi.core.feature.drop.DropCommand;
import pl.chudziudgi.core.feature.drop.DropController;
<<<<<<< HEAD
import pl.chudziudgi.core.feature.home.command.DelHomeCommand;
import pl.chudziudgi.core.feature.home.command.HomeCommand;
import pl.chudziudgi.core.feature.home.command.SetHomeCommand;
=======
>>>>>>> origin/master
import pl.chudziudgi.core.feature.nether.*;
import pl.chudziudgi.core.feature.ochrona.OchronaCommand;
import pl.chudziudgi.core.feature.ochrona.ProtectionController;
import pl.chudziudgi.core.feature.ochrona.ProtectionManager;
import pl.chudziudgi.core.feature.ochrona.ProtectionTask;
import pl.chudziudgi.core.feature.particle.ParticleCommand;
import pl.chudziudgi.core.feature.particle.ParticlePlayerLocationTask;
import pl.chudziudgi.core.feature.particle.ParticleTask;
import pl.chudziudgi.core.feature.randomtp.RandomTpController;
import pl.chudziudgi.core.feature.schowek.DepositCommand;
import pl.chudziudgi.core.hook.PlaceholderApiHook;

public final class ChCore extends JavaPlugin {

    private ConfigLoader config;

    @Override
    public void onLoad() {
        PlaceholderApiHook.isPlaceholderAPIInstalled(this);
        config = new ConfigLoader();
        config.load(this);
    }

    @Override
    public void onDisable() {
        Database.saveDatabase();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new InventoryBuilder.Listeners(), this);
        Database.load(this);

        ProtectionManager protectionManager = new ProtectionManager();
        CombatManager combatManager = new CombatManager();
        PrivateMessageManager privateMessageManager = new PrivateMessageManager();
        ChatManager chatManager = new ChatManager(config.getChatConfig());

        new ProtectionController(this, protectionManager);
        new CombatController(this, combatManager, config.getCombatConfig() , protectionManager);
        new RandomTpController(this, combatManager);
        new NetherController(this, combatManager, config.getNetherConfig());
        new DropController(this, combatManager);
        new AsyncPlayerChatController(this, chatManager, config.getChatConfig());


        new CombatTask(this, combatManager);
        new ProtectionTask(this, protectionManager);
        new AutoMessageTask(this, config.getChatConfig());
        new NetherStatusTask(this, config.getNetherConfig());
        new NetherTeleportTaskT(this, config.getNetherConfig());
        new NetherEffectTask(this);
        new ParticleTask(this);
        new ParticlePlayerLocationTask(this);
//        new DepositTask().runTaskTimerAsynchronously(this, 20L, 20L * 2L);

        new PlaceholderApiHook(protectionManager).register();

        final CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands(
                new MsgCommand(privateMessageManager),
                new ReplyCommand(privateMessageManager),
                new DropCommand(),
                new DepositCommand(),
                new OchronaCommand(protectionManager),
                new NetherCommand(config.getNetherConfig()),
                new WorkbenchCommand(),
                new EnderchestCommand(),
                new PomocCommand(),
                new ClearCommand(),
                new FlightCommand(),
                new GameModeCommand(),
                new SpeedCommand(),
                new HealCommand(),
                new ChatCommand(chatManager),
<<<<<<< HEAD
                new ParticleCommand(),
                new HomeCommand(),
                new SetHomeCommand(),
                new DelHomeCommand(),
                new InventorySeeCommand()
=======
                new ParticleCommand()
>>>>>>> origin/master
        );
    }
}