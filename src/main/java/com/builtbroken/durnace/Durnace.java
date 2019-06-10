package com.builtbroken.durnace;

import com.builtbroken.durnace.content.BlockDurnace;
import com.builtbroken.durnace.content.TileDurnace;
import com.builtbroken.durnace.content.gui.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Durnace.MODID, name = Durnace.MOD_NAME, version = Durnace.VERSION)
@Mod.EventBusSubscriber(modid = Durnace.MODID)
public class Durnace
{
    public static final String MOD_NAME = "Durnace";
    public static final String MODID = "durnace";

    public static final String DURNACE = MODID + ":durnace";

    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String MC_VERSION = "@MC@";
    public static final String VERSION = MC_VERSION + "-" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    @Mod.Instance(MODID)
    public static Durnace INSTANCE;

    @GameRegistry.ObjectHolder(Durnace.DURNACE)
    public static BlockDurnace durnace;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new BlockDurnace().setRegistryName(DURNACE).setTranslationKey(DURNACE));
        GameRegistry.registerTileEntity(TileDurnace.class, new ResourceLocation(Durnace.MODID, "TileDurnace"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemBlock(durnace).setRegistryName(durnace.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(durnace), 0, new ModelResourceLocation(durnace.getRegistryName(), "normal"));
    }
}
