package core;

import core.commands.*;
import core.data.Armor;
import core.listeners.AsyncPlayerChatListener;
import core.listeners.PlayerJoinQuitListener;
import core.managers.ChatManager;
import core.managers.UserManager;
import core.settings.ArmorSettings;
import core.settings.MessageConfig;
import core.settings.WeaponSettings;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.justrpg.api.RPG;
import pl.justrpg.api.store.StoreMode;
import pl.justrpg.api.util.Logger;

public class Main extends JavaPlugin {

    @Getter
    private static Main plugin;

    @Getter
    private static ChatManager chatManager;

    @Override
    public void onEnable(){
        Logger.info("Enable RPGCORE...");
        final long start = System.currentTimeMillis();
        plugin = this;
        chatManager = new ChatManager();
        RPG.addMYSQLTable("CREATE TABLE IF NOT EXISTS `{P}users` (" + ((RPG.getStore().getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + " `uuid` varchar(255) NOT NULL, `lastName` varchar(16) NOT NULL, `firstIP` varchar(20) NOT NULL, `lastIP` varchar(20) NOT NULL, `exp` bigint(20) NOT NULL, `money` bigint(32) NOT NULL, `level` int(6) NOT NULL, `timePlay` int(32) NOT NULL, `kills` int(16) NOT NULL, `mobKills` int(16) NOT NULL, `openChest` int(16) NOT NULL, `deaths` int(16) NOT NULL, `gameMode` int(1) NOT NULL, `fly` int(1) NOT NULL, `god` int(1) NOT NULL, `lastJoin` bigint(20) NOT NULL, `lastLocation` text NOT NULL, `homeLocation` text NOT NULL NOT NULL);");
        loadCommands();
        RPG.registerConfigCreator(new WeaponSettings());
        new WeaponSettings();
        RPG.registerConfigCreator(new MessageConfig());
        new MessageConfig();
        RPG.registerConfigCreator(new ArmorSettings());
        new ArmorSettings();
        RPG.registerListener(getPlugin(), new PlayerJoinQuitListener());
        RPG.registerListener(getPlugin(), new AsyncPlayerChatListener());
        UserManager.setup();
    }

    public void loadCommands(){
        RPG.registerCommand(new FlyCommand());
        RPG.registerCommand(new GamemodeCommand());
        RPG.registerCommand(new GodmodeCommand());
        RPG.registerCommand(new TeleportCommand());
        RPG.registerCommand(new HealCommand());
        RPG.registerCommand(new AdminPanelCommand());
        RPG.registerCommand(new ChatCommand());
        RPG.registerCommand(new ClearCommand());
        RPG.registerCommand(new DayCommand());
        RPG.registerCommand(new GiveCommand());
        RPG.registerCommand(new InvseeCommand());
        RPG.registerCommand(new NightCommand());
        RPG.registerCommand(new RepairCommand());
        RPG.registerCommand(new BroadcastCommand());
        RPG.registerCommand(new ListCommand());
        RPG.registerCommand(new MsgCommand());
        RPG.registerCommand(new RenameCommand());
        RPG.registerCommand(new ReplayCommand());
        RPG.registerCommand(new VanishCommand());
    }
}
