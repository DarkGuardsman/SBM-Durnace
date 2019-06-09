package com.builtbroken.durnace.common.block;

import com.builtbroken.durnace.common.Registries;
import com.builtbroken.durnace.common.tileentity.TileDurnace;
import com.builtbroken.durnace.util.BlockNames;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDurnace extends BlockFurnace implements ITileEntityProvider
{
    public BlockDurnace(boolean burn)
    {
        super(burn);
        setHardness(1.5F);
        setSoundType(SoundType.GROUND);
        this.setTranslationKey(BlockNames.DURNACE);
        setCreativeTab(CreativeTabs.DECORATIONS);

        if (burn)
        {
            setLightLevel(0.875F);
            this.setRegistryName(new ResourceLocation(BlockNames.LIT_DURNACE));
        }
        else
        {
            this.setRegistryName(new ResourceLocation(BlockNames.DURNACE));
        }
    }

    static Item durnace;

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return durnace == null ? durnace = Item.getItemFromBlock(Registries.durnace) : durnace;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState oldState)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != Registries.durnace && state.getBlock() != Registries.lit_durnace)
        {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof TileDurnace)
            {
                InventoryHelper.dropInventoryItems(world, pos, (TileDurnace) tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }
            world.removeTileEntity(pos);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileDurnace();
    }
}
