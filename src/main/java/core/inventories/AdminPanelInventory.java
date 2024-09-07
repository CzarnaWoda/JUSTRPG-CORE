package core.inventories;

import core.Main;
import core.data.Armor;
import core.data.Weapon;
import core.interfaces.Colors;
import core.managers.ChatManager;
import core.settings.ArmorSettings;
import core.settings.MessageConfig;
import core.settings.WeaponSettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.justrpg.api.configs.ConfigCreator;
import pl.justrpg.api.configs.ConfigManager;
import pl.justrpg.api.inventory.IAction;
import pl.justrpg.api.inventory.InventoryGUI;
import pl.justrpg.api.util.ItemBuilder;
import pl.justrpg.api.util.Util;

import java.util.Collections;

public class AdminPanelInventory implements Colors {

    public static void openMenu(Player player){
        final InventoryGUI inventoryGUI = new InventoryGUI(Main.getPlugin(), Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Panel " + ImportantColor + "Administracyjny")),3);
        final ItemBuilder black = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setTitle(Util.fixColor("&6"));
        final ItemBuilder configs = new ItemBuilder(Material.PAPER).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadzanie " + ImportantColor + UnderLined + "konfiguracjami")));
        final ItemBuilder cyan = new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE).setTitle(Util.fixColor("&6"));
        final ItemBuilder weapons = new ItemBuilder(Material.DIAMOND_SWORD).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadzanie " + ImportantColor + UnderLined + "broniami")));
        final ItemBuilder purple = new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setTitle(Util.fixColor("&6"));
        final ItemBuilder armors = new ItemBuilder(Material.DIAMOND_CHESTPLATE).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadzanie " + ImportantColor + UnderLined + "zbrojami")));
        final ItemBuilder blue = new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setTitle(Util.fixColor("&6"));
        final ItemBuilder chat = new ItemBuilder(Material.DIAMOND_CHESTPLATE).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadzanie " + ImportantColor + UnderLined + "chatem")));
        inventoryGUI.setItem(0,black.build(),null);
        inventoryGUI.setItem(9, configs.build(), (p, inventory, i, itemStack) -> {
            p.closeInventory();
            openConfigsMenu(p);
        });
        inventoryGUI.setItem(18, black.build(), null);
        inventoryGUI.setItem(1,cyan.build(), null);
        inventoryGUI.setItem(10, weapons.build(), (p, inventory, i, itemStack) -> {
            p.closeInventory();
            openWeaponMenu(p);
        });
        inventoryGUI.setItem(19,cyan.build(),null);
        inventoryGUI.setItem(2,purple.build(),null);
        inventoryGUI.setItem(11, armors.build(), (p, inventory, i, itemStack) -> {
            p.closeInventory();
            openArmorMenu(p);
        });
        inventoryGUI.setItem(20,purple.build(),null);
        inventoryGUI.setItem(3,blue.build(),null);
        inventoryGUI.setItem(12, chat.build(), (p, inventory, i, itemStack) -> {
            p.closeInventory();
            openChatManager(p);
        });
        inventoryGUI.setItem(21,blue.build(),null);
        inventoryGUI.openInventory(player);
    }
    private static void openConfigsMenu(Player player){
        final InventoryGUI inventoryGUI = new InventoryGUI(Main.getPlugin(), Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadzanie " + ImportantColor + "konfiguracjami")),1);
        final ItemBuilder core = new ItemBuilder(Material.EMERALD_BLOCK).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Konfiguracje " + ImportantColor + BOLD + "JustRPGCore")));
        inventoryGUI.setItem(0, core.build(), (p, inventory, i, itemStack) -> {
            p.closeInventory();
            openCoreConfigs(p);
        });
        inventoryGUI.openInventory(player);
    }
    private static void openChatManager(Player player){
        final InventoryGUI inventoryGUI = new InventoryGUI(Main.getPlugin(), Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadzanie " + ImportantColor + "chatem")), 1);
        final ItemBuilder toggleChat = new ItemBuilder(Material.GOLD_BLOCK).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Wlacz/Wylacz " + ImportantColor + "chat")));
        final ItemBuilder clearChat = new ItemBuilder(Material.GLASS).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Wyczysc " + ImportantColor + "chat")));
        inventoryGUI.setItem(0, toggleChat.build(), (p, inventory, i, itemStack) -> {
            Main.getChatManager().toggleChat(player);
        });
        inventoryGUI.setItem(1, clearChat.build(), (p, inventory, i, itemStack) -> {
            Main.getChatManager().clearChat(player);
        });
        inventoryGUI.openInventory(player);
    }
    private static void openCoreConfigs(Player player){
        final InventoryGUI inventoryGUI = new InventoryGUI(Main.getPlugin(), Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Konfiguracje " + ImportantColor + "JustRPGCore")),3);
        final ItemBuilder core = new ItemBuilder(Material.EMERALD_BLOCK).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Konfiguracje " + ImportantColor + BOLD + "JustRPGCore")));
        inventoryGUI.setItem(4, core.build(), (p, inventory, i, itemStack) -> {
            p.closeInventory();
            openCoreConfigs(p);
        });
        final ItemBuilder weaponconfig = new ItemBuilder(Material.DIAMOND_SWORD).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "* " + MainColor + "Konfiguracja " + ImportantColor + UnderLined + Main.getPlugin().getDescription().getName() + "/weapon.yml")));
        inventoryGUI.setItem(18, weaponconfig.build(), (p, inventory, i, itemStack) -> {
            ConfigCreator configCreator = ConfigManager.getConfig("weapon.yml");
            configCreator.reloadConfig();
            new WeaponSettings();
            updateConfigItem(itemStack);
        });
        final ItemBuilder messageconfig = new ItemBuilder(Material.BOOK).setTitle(Util.fixColor(Util.getReplacement(SpecialSigns + "* " + MainColor + "Konfiguracja " + ImportantColor + UnderLined + Main.getPlugin().getDescription().getName() + "/message.yml")));
        inventoryGUI.setItem(19, messageconfig.build(), (p, inventory, i, itemStack) -> {
            ConfigCreator configCreator = ConfigManager.getConfig("message.yml");
            configCreator.reloadConfig();
            new MessageConfig();
            updateConfigItem(itemStack);
        });
        inventoryGUI.openInventory(player);

    }
    private static void updateConfigItem(ItemStack itemStack){
        final ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setLore(Collections.singletonList(Util.fixColor(Util.getReplacement(SpecialSigns + "  * " + MainColor + "Przeladowano konfiguracje: " + ImportantColor + Util.getDate(System.currentTimeMillis())))));
        itemStack.setItemMeta(itemMeta);
    }
    private static void openWeaponMenu(Player player){
        final InventoryGUI inventoryGUI = new InventoryGUI(Main.getPlugin(), Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadanie " + ImportantColor + "broniami")),6);
        int index = 0;
        for(Weapon weapon : WeaponSettings.getWeaponHashMap().values()){
            inventoryGUI.setItem(index, weapon.getWeapon(), (p, inventory, i, itemStack) -> {
                p.getInventory().addItem(weapon.getWeapon());
            });
            index++;
        }
        inventoryGUI.openInventory(player);
    }
    private static void openArmorMenu(Player player){
        final InventoryGUI inventoryGUI = new InventoryGUI(Main.getPlugin(), Util.fixColor(Util.getReplacement(SpecialSigns + "->> " + MainColor + "Zarzadanie " + ImportantColor + "zbrojami")),6);
        int index = 0;
        for(Armor armor : ArmorSettings.getArmorHashMap().values()){
            inventoryGUI.setItem(index, armor.getArmor(), (p, inventory, i, itemStack) -> {
                p.getInventory().addItem(armor.getArmor());
            });
            index++;
        }
        inventoryGUI.openInventory(player);
    }
}
