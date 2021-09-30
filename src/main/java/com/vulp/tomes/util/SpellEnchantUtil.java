package com.vulp.tomes.util;

import com.vulp.tomes.init.EnchantmentInit;
import com.vulp.tomes.items.TomeItem;
import com.vulp.tomes.spells.Spell;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SpellEnchantUtil {

    public static List<Enchantment> getEnchantList(PlayerEntity player) {
        List<Enchantment> map = new java.util.ArrayList<>(Collections.emptyList());
        for (ItemStack itemStack : player.getHeldEquipment()) {
            if (itemStack.getItem() instanceof TomeItem) {
                Map<Enchantment, Integer> thing = EnchantmentHelper.getEnchantments(itemStack);
                thing.forEach((i, j) -> map.add(i));
            }
        }
        return map;
    }

    public static boolean hasEnchant(PlayerEntity playerEntity, Enchantment enchantment) {
        return getEnchantList(playerEntity).contains(enchantment);
    }


}