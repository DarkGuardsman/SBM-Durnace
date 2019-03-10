package com.vandendaelen.durnace;

import com.vandendaelen.durnace.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID,name = Reference.MOD_NAME,version = Reference.VERSION.VERSION)
public class Durnace {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    @Mod.Instance(Reference.MODID)
    public static Durnace instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
    }

}
