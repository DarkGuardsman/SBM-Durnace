package com.builtbroken.durnace.common;

import com.builtbroken.durnace.common.block.BlockDurnace;
import com.builtbroken.durnace.common.item.ItemDurnace;
import com.builtbroken.durnace.common.tileentity.TileDurnace;
import com.builtbroken.durnace.util.BlockNames;
import com.builtbroken.durnace.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid= Reference.MODID)
public class Registries {
    @GameRegistry.ObjectHolder(BlockNames.DURNACE)
    public static BlockDurnace durnace;
    @GameRegistry.ObjectHolder(BlockNames.LIT_DURNACE)
    public static BlockDurnace lit_durnace;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e){
        IForgeRegistry<Block> reg = e.getRegistry();

        //Durnace
        GameRegistry.registerTileEntity(TileDurnace.class,new ResourceLocation(Reference.MODID,"TileDurnace"));

        durnace = new BlockDurnace(false);
        lit_durnace = new BlockDurnace(true);

        reg.register(durnace);
        reg.register(lit_durnace);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e){
        IForgeRegistry<Item> reg = e.getRegistry();

        //Durnace
        reg.register(new ItemDurnace(durnace));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        //Durnace
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(durnace), 0, new ModelResourceLocation(durnace.getRegistryName(), "normal"));
    }
}
