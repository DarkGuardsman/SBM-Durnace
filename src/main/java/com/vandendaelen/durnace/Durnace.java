package com.vandendaelen.durnace;

import com.vandendaelen.durnace.util.Reference;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID,name = Reference.MOD_NAME,version = Reference.VERSION.VERSION)
public class Durnace {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    @Mod.Instance(Reference.MODID)
    public static Durnace instance;
}
