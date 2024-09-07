package core.commands;

import core.inventories.AdminPanelInventory;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.PlayerCommand;

public class AdminPanelCommand extends PlayerCommand {
    public AdminPanelCommand() {
        super("panel", "Otwiera panel administracyjny", "/panel", "core.cmd.panel", "adminpanel");
    }

    @Override
    public boolean onCommand(Player player, String[] strings) {
        AdminPanelInventory.openMenu(player);
        return false;
    }
}
