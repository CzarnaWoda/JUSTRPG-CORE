package core.utils;

import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class MaterialUtil {

    @Getter private static List<Material> chestplates = Arrays.asList(Material.CHAINMAIL_CHESTPLATE,Material.DIAMOND_CHESTPLATE,Material.GOLDEN_CHESTPLATE,Material.IRON_CHESTPLATE,Material.LEATHER_CHESTPLATE);
    @Getter private static List<Material> helmets = Arrays.asList(Material.CHAINMAIL_HELMET,Material.DIAMOND_HELMET,Material.GOLDEN_HELMET,Material.IRON_HELMET,Material.LEATHER_HELMET);
    @Getter private static List<Material> leggings = Arrays.asList(Material.CHAINMAIL_LEGGINGS,Material.DIAMOND_LEGGINGS,Material.GOLDEN_LEGGINGS,Material.IRON_LEGGINGS,Material.LEATHER_LEGGINGS);
    @Getter private static List<Material> boots = Arrays.asList(Material.CHAINMAIL_BOOTS,Material.DIAMOND_BOOTS,Material.GOLDEN_BOOTS,Material.IRON_BOOTS,Material.LEATHER_BOOTS);
}
