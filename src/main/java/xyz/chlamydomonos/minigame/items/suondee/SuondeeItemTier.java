package xyz.chlamydomonos.minigame.items.suondee;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import xyz.chlamydomonos.minigame.core.loaders.ItemLoader;

public class SuondeeItemTier implements IItemTier
{
    @Override
    public int getMaxUses()
    {
        return 1;
    }

    @Override
    public float getEfficiency()
    {
        return 10;
    }

    @Override
    public float getAttackDamage()
    {
        return Float.MAX_VALUE;
    }

    @Override
    public int getHarvestLevel()
    {
        return 3;
    }

    @Override
    public int getEnchantability()
    {
        return 30;
    }

    @Override
    public Ingredient getRepairMaterial()
    {
        return Ingredient.fromItems(ItemLoader.SUONDEE_METAL_INGOT);
    }
}
