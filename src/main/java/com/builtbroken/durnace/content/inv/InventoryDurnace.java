package com.builtbroken.durnace.content.inv;

import com.builtbroken.durnace.content.TileDurnace;
import net.minecraft.init.Items;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Created by Dark(DarkGuardsman, Robert) on 6/9/2019.
 */
public class InventoryDurnace extends ItemStackHandler
{
    private TileDurnace tileDurnace;

    public InventoryDurnace(TileDurnace tileDurnace)
    {
        super(3);
        this.tileDurnace = tileDurnace;
    }

    @Override
    public boolean isItemValid(int index, @Nonnull ItemStack stack)
    {
        if (index == 2)
        {
            return false;
        }
        else if (index == 0)
        {
            return true;
        }
        else if (index == 1)
        {
            ItemStack itemstack = getStackInSlot(1);
            return TileEntityFurnace.isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
        return false;
    }


}
