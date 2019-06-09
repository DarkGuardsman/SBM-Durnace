package com.builtbroken.durnace.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemDurnace extends ItemBlock
{
    public ItemDurnace(Block block)
    {
        super(block);
        this.setRegistryName(block.getRegistryName());
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
    }
}
