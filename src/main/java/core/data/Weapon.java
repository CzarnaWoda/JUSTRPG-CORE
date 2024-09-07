package core.data;

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
public class Weapon {

    @NonNull private String id;
    @NonNull private Material sword;
    @NonNull private String displayName;
    @NonNull private List<String> lore;
    @NonNull private double damage;
    @NonNull private double lootChance;
    @NonNull private double attackSpeed;
    public ItemStack getWeapon(){
        ItemStack itemStack = new ItemStack(getSword());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Util.getReplacement(getDisplayName()));
        final List<String> formatedLore = new ArrayList<>();
        for(String s : getLore()){
            formatedLore.add(Util.getReplacement(s));
        }
        itemMeta.setLore(formatedLore);
        final AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), "damageAttribute", getDamage(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attributeModifier);
        final AttributeModifier attributeModifier1 = new AttributeModifier(UUID.randomUUID(), "luckAttribute", getLootChance(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_LUCK, attributeModifier1);
        final AttributeModifier attributeModifier2 = new AttributeModifier(UUID.randomUUID(), "attackSpeedAttribute", getAttackSpeed(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attributeModifier2);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}

