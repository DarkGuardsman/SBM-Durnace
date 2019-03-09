package com.vandendaelen.durnace.common.block;

import com.vandendaelen.durnace.common.Registries;
import com.vandendaelen.durnace.common.tileentity.TileDurnace;
import com.vandendaelen.durnace.util.BlockNames;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDurnace extends BlockFurnace implements ITileEntityProvider {
    public static final PropertyBool BURNING = PropertyBool.create("burning");

    public BlockDurnace() {
        super(false);
        this.setSoundType(SoundType.GROUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING,false));
        this.setRegistryName(new ResourceLocation(BlockNames.DURNACE));
        this.setTranslationKey(BlockNames.DURNACE);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setResistance(2.0F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Registries.durnace);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileDurnace){
                playerIn.displayGUIChest((TileDurnace)tileentity);
            }

        }
        return true;
    }

    public static void setState(boolean active, World world, BlockPos pos){
        IBlockState state = world.getBlockState(pos);
        TileEntity tileEntity = world.getTileEntity(pos);

        if (active)
            world.setBlockState(pos,Registries.durnace.getDefaultState().withProperty(FACING,state.getValue(FACING)).withProperty(BURNING,true),3);
        else
            world.setBlockState(pos,Registries.durnace.getDefaultState().withProperty(FACING,state.getValue(FACING)).withProperty(BURNING,false),3);

        if (tileEntity != null){
            tileEntity.validate();
            world.setTileEntity(pos,tileEntity);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileDurnace();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos,this.getDefaultState().withProperty(FACING,placer.getHorizontalFacing().getOpposite()),2);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileDurnace tileDurnace = (TileDurnace)worldIn.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(worldIn,pos,tileDurnace);
        super.breakBlock(worldIn,pos,state);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING,BURNING);
    }
}
