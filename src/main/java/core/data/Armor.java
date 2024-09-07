package core.data;

import core.utils.MaterialUtil;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.justrpg.api.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Armor {
    @NonNull private String id;
    @NonNull private Material armorPiece;
    @NonNull private String displayName;
    @NonNull private List<String> lore;
    @NonNull private double armors;
    @NonNull private double armorToughness;
    @NonNull private double movementSpeed;
    @NonNull private double health;

    public ItemStack getArmor(){
        final ItemStack itemStack = new ItemStack(getArmorPiece());
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Util.getReplacement(getDisplayName()));
        final List<String> formatedLore = new ArrayList<>();
        for(String s : getLore()){
            formatedLore.add(Util.getReplacement(s));
        }
        itemMeta.setLore(formatedLore);
        final AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), "armorAttribute", getArmors(), AttributeModifier.Operation.ADD_NUMBER, (MaterialUtil.getChestplates().contains(getArmorPiece()) ? EquipmentSlot.CHEST : (MaterialUtil.getBoots().contains(getArmorPiece()) ? EquipmentSlot.FEET : (MaterialUtil.getHelmets().contains(getArmorPiece()) ? EquipmentSlot.HEAD : EquipmentSlot.LEGS))));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, attributeModifier);
        final AttributeModifier attributeModifier1 = new AttributeModifier(UUID.randomUUID(), "armorToughnessAttribute", getArmorToughness(), AttributeModifier.Operation.ADD_NUMBER,  (MaterialUtil.getChestplates().contains(getArmorPiece()) ? EquipmentSlot.CHEST : (MaterialUtil.getBoots().contains(getArmorPiece()) ? EquipmentSlot.FEET : (MaterialUtil.getHelmets().contains(getArmorPiece()) ? EquipmentSlot.HEAD : EquipmentSlot.LEGS))));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, attributeModifier1);
        final AttributeModifier attributeModifier2 = new AttributeModifier(UUID.randomUUID(), "movementSpeedAttribute" , getMovementSpeed() , AttributeModifier.Operation.ADD_NUMBER,  (MaterialUtil.getChestplates().contains(getArmorPiece()) ? EquipmentSlot.CHEST : (MaterialUtil.getBoots().contains(getArmorPiece()) ? EquipmentSlot.FEET : (MaterialUtil.getHelmets().contains(getArmorPiece()) ? EquipmentSlot.HEAD : EquipmentSlot.LEGS))));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, attributeModifier2);
        final AttributeModifier attributeModifier3 = new AttributeModifier(UUID.randomUUID(), "healthAttribute" , getHealth() , AttributeModifier.Operation.ADD_NUMBER,  (MaterialUtil.getChestplates().contains(getArmorPiece()) ? EquipmentSlot.CHEST : (MaterialUtil.getBoots().contains(getArmorPiece()) ? EquipmentSlot.FEET : (MaterialUtil.getHelmets().contains(getArmorPiece()) ? EquipmentSlot.HEAD : EquipmentSlot.LEGS))));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, attributeModifier3);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
