package com.vulp.tomes.enchantments;

import com.vulp.tomes.spells.SpellIndex;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class TomeEnchantment extends Enchantment {

    private static final EquipmentSlotType[] SLOTS = new EquipmentSlotType[]{};
    private final SpellIndex spellIndex;
    private final boolean isRare;
    private final boolean isActive;

    public TomeEnchantment(SpellIndex spellIndex, EnchantmentType enchantmentType) {
        super(spellIndex.getSpell().getRarity(), enchantmentType, SLOTS);
        this.spellIndex = spellIndex;
        this.isRare = spellIndex.getSpell().isRare();
        this.isActive = spellIndex.getSpell().isActive();
    }

    public boolean isRare() {
        return this.isRare;
    }

    public SpellIndex getSpellIndex() {
        return this.spellIndex;
    }

    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        if (this.isActive) {
            return 30;
        } else return super.getMinEnchantability(enchantmentLevel);
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof TomeEnchantment) {
            if (this.isActive() && ((TomeEnchantment) ench).isActive()) {
                return false;
            }
        }
        return super.canApplyTogether(ench);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return !isRare() && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isTreasureEnchantment() {
        return isRare() && super.isTreasureEnchantment();
    }

}
