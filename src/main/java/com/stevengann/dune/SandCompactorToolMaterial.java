package com.stevengann.dune;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SandCompactorToolMaterial implements ToolMaterial 
{

    public static final SandCompactorToolMaterial INSTANCE = new SandCompactorToolMaterial();

    @Override
    public int getDurability() {
        return 100;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1.0F;
    }

    @Override
    public float getAttackDamage() {
        return 1.0F;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.IRON_INGOT);
    }
    
}
