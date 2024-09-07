package core.settings;

import core.Main;
import core.data.Weapon;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import pl.justrpg.api.configs.ConfigCreator;

import java.util.HashMap;
import java.util.List;

public class WeaponSettings extends ConfigCreator {

    @Getter public static HashMap<String, Weapon> weaponHashMap;

    public WeaponSettings() {
        super("weapon.yml", "Weapon Configuration", Main.getPlugin());
        weaponHashMap = new HashMap<String, Weapon>();

        FileConfiguration config = getConfig();

        for(String key : config.getConfigurationSection("weapons").getKeys(false)){
            ConfigurationSection configurationSection = getConfigurationSection("weapons." + key);

            final Material sword = Material.matchMaterial(configurationSection.getString("sword"));
            final String displayName = configurationSection.getString("displayName");
            final List<String> lore = configurationSection.getStringList("lore");
            final double damage = configurationSection.getDouble("damage");
            final double lootChance = configurationSection.getDouble("lootChance");
            final double attacSpeed = configurationSection.getDouble("attackSpeed");

            final Weapon weapon = new Weapon(key,sword,displayName,lore,damage,lootChance,attacSpeed);
            weaponHashMap.put(key, weapon);

        }
    }
}
