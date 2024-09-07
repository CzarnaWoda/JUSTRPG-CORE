package core.settings;

import core.Main;
import core.data.Armor;
import core.data.Weapon;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import pl.justrpg.api.configs.ConfigCreator;

import java.util.HashMap;
import java.util.List;

public class ArmorSettings extends ConfigCreator {

    @Getter public static HashMap<String, Armor> armorHashMap;

    public ArmorSettings() {
        super("armor.yml", "Armors Configuration", Main.getPlugin());
        armorHashMap = new HashMap<>();

        FileConfiguration config = getConfig();

        for(String key : config.getConfigurationSection("armors").getKeys(false)){
            ConfigurationSection configurationSection = getConfigurationSection("armors." + key);

            final Material armorPiece = Material.matchMaterial(configurationSection.getString("armorPiece"));
            final String displayName = configurationSection.getString("displayName");
            final List<String> lore = configurationSection.getStringList("lore");
            final double armors = configurationSection.getDouble("armor");
            final double armorToughness = configurationSection.getDouble("armorToughness");
            final double movementSpeed = configurationSection.getDouble("movementSpeed");
            final double health = configurationSection.getDouble("health");

            final Armor armor = new Armor(key,armorPiece,displayName,lore,armors,armorToughness,movementSpeed,health);
            armorHashMap.put(key,armor);

        }
    }
}
