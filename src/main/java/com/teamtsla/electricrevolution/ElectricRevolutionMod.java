package com.teamtsla.electricrevolution;


import com.teamtsla.electricrevolution.init.ModInit;
import com.teamtsla.electricrevolution.worldgen.OreGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod(modid = ElectricRevolutionMod.MODID, name = ElectricRevolutionMod.NAME, version = ElectricRevolutionMod.VERSION)
public class ElectricRevolutionMod
{
    public static final String MODID = "electricrevolution";
    public static final String NAME = "Electric Revolution Mod";
    public static final String VERSION = "1.0";


    public static final int GUI_SOLAR_CELL = 2;
    public static final int GUI_GLOWSTONE_CHARGER = 6;
    public static final int GUI_STEAM_GENERATOR = 5;
    public static final int GUI_LIGHT_GENERATOR = 4;

    @Mod.Instance
    public static ElectricRevolutionMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModInit.init();

    }


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        //GameRegistry.registerWorldGenerator(new OreGen(), 0);


    }


}
