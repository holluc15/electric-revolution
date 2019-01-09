package com.teamtsla.electricrevolution.init;


import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import com.teamtsla.electricrevolution.blocks.*;

import com.teamtsla.electricrevolution.glowstonecharger.BlockGlowstoneCharger;
import com.teamtsla.electricrevolution.glowstonecharger.TileEntityGlowstoneCharger;


import com.teamtsla.electricrevolution.gui.GuiHandler;
import com.teamtsla.electricrevolution.items.*;
import com.teamtsla.electricrevolution.lightgenerator.BlockLightGenerator;
import com.teamtsla.electricrevolution.lightgenerator.TileEntityLightGenerator;
import com.teamtsla.electricrevolution.solarcell.SolarCell;
import com.teamtsla.electricrevolution.solarcell.TileEntitySolarCell;
import com.teamtsla.electricrevolution.steamgenerator.BlockSteamGenerator;
import com.teamtsla.electricrevolution.steamgenerator.TileEntitySteamGenerator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ElectricRevolutionMod.MODID)
public class ModInit {

    //Items
    public static Item COPPER_INGOT;
    public static Item LITHIUM_INGOT;
    public static Item MAGNET;
    public static Item ALUMINIUM_INGOT;
    public static Item SILICON_INGOT;
    public static Item COPPER_WIRE;

    public static Item BATTERY;
    //Blocks
    public static Block COPPER_ORE;
    public static Block LITHIUM_ORE;
    public static Block LODESTONE_ORE;
    public static Block ALUMINIUM_ORE;
    public static Block SILICON_ORE;


    public static Block SOLAR_CELL;
    public static Block GLOWSTONE_CHARGER;
    public static Block STEAM_GENERATOR;


    public static Block FURNACE_GENERATOR;
    public static Block GLOWSTONE_GENERATOR;
    public static Block LIGHT_GENERATOR;

    public static CreativeTabs electricRevolutionTab = new CreativeTabs("electricRevolution"){
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(GLOWSTONE_CHARGER);
        }

    };

    public static void init() {

        //Items
        COPPER_INGOT = new CopperIngot("copperingot");
        LITHIUM_INGOT =  new LithiumIngot("lithiumingot");
        MAGNET = new Magnet("magnet");
        ALUMINIUM_INGOT = new AluminiumIngot("aluminiumingot");

        BATTERY = new Battery("battery");
        COPPER_WIRE = new CopperWire("copperwire");


        //Blocks
        COPPER_ORE = new CopperOre("copperore");
        LITHIUM_ORE = new LithiumOre("lithiumore");
        LODESTONE_ORE = new LodestoneOre("lodestoneore");
        ALUMINIUM_ORE = new AluminiumOre("aluminiumore");
        SILICON_ORE = new SiliconOre("siliconore");
        SILICON_INGOT = new SiliconIngot("siliconingot");

        SOLAR_CELL = new SolarCell("solarcell");

        GLOWSTONE_CHARGER = new BlockGlowstoneCharger("glowstone_charger");
        STEAM_GENERATOR = new BlockSteamGenerator("steam_generator");


        LIGHT_GENERATOR = new BlockLightGenerator("light_generator");


    }



    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //Items
        event.getRegistry().registerAll(COPPER_INGOT);
        event.getRegistry().registerAll(LITHIUM_INGOT);
        event.getRegistry().registerAll(MAGNET);
        event.getRegistry().registerAll(ALUMINIUM_INGOT);
        event.getRegistry().registerAll(SILICON_INGOT);
        event.getRegistry().registerAll(BATTERY);
        event.getRegistry().registerAll(COPPER_WIRE);
        //Blocks
        event.getRegistry().register(new ItemBlock(COPPER_ORE).setRegistryName(Objects.requireNonNull(COPPER_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(LITHIUM_ORE).setRegistryName(Objects.requireNonNull(LITHIUM_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(LODESTONE_ORE).setRegistryName(Objects.requireNonNull(LODESTONE_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(ALUMINIUM_ORE).setRegistryName(Objects.requireNonNull(ALUMINIUM_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(SILICON_ORE).setRegistryName(Objects.requireNonNull(SILICON_ORE.getRegistryName())));


        event.getRegistry().register(new ItemBlock(SOLAR_CELL).setRegistryName(Objects.requireNonNull(SOLAR_CELL.getRegistryName())));
        event.getRegistry().register(new ItemBlock(GLOWSTONE_CHARGER).setRegistryName(Objects.requireNonNull(GLOWSTONE_CHARGER.getRegistryName())));
        event.getRegistry().register(new ItemBlock(STEAM_GENERATOR).setRegistryName(Objects.requireNonNull(STEAM_GENERATOR.getRegistryName())));


        event.getRegistry().register(new ItemBlock(LIGHT_GENERATOR).setRegistryName(Objects.requireNonNull(LIGHT_GENERATOR.getRegistryName())));


    }



    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(COPPER_ORE);
        event.getRegistry().registerAll(LITHIUM_ORE);
        event.getRegistry().registerAll(LODESTONE_ORE);
        event.getRegistry().registerAll(ALUMINIUM_ORE);
        event.getRegistry().registerAll(SILICON_ORE);
        event.getRegistry().registerAll(SOLAR_CELL);

        NetworkRegistry.INSTANCE.registerGuiHandler(ElectricRevolutionMod.instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileEntityGlowstoneCharger.class,
                new ResourceLocation(ElectricRevolutionMod.MODID + ":glowstone_charger"));
        event.getRegistry().registerAll(GLOWSTONE_CHARGER);
        NetworkRegistry.INSTANCE.registerGuiHandler(ElectricRevolutionMod.instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileEntitySolarCell.class,
                new ResourceLocation(ElectricRevolutionMod.MODID + ":solarcell"));

        NetworkRegistry.INSTANCE.registerGuiHandler(ElectricRevolutionMod.instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileEntitySteamGenerator.class,
                new ResourceLocation(ElectricRevolutionMod.MODID + ":steam_generator"));
        event.getRegistry().registerAll(STEAM_GENERATOR);

        event.getRegistry().registerAll(LIGHT_GENERATOR);
        NetworkRegistry.INSTANCE.registerGuiHandler(ElectricRevolutionMod.instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileEntityLightGenerator.class,
                new ResourceLocation(ElectricRevolutionMod.MODID + ":light_generator"));
    }

    @SubscribeEvent
    public static void registerRenderers(ModelRegistryEvent event) {

        loadItems();
        loadBlocks();
        loadCraftingRecepies();
        loadSmeltingRecepies();

    }

    private static void registerRenderer(Item item) {
        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }

    private static void loadBlocks() {
        registerRenderer(Item.getItemFromBlock(COPPER_ORE));
        registerRenderer(Item.getItemFromBlock(LITHIUM_ORE));
        registerRenderer(Item.getItemFromBlock(LODESTONE_ORE));
        registerRenderer(Item.getItemFromBlock(ALUMINIUM_ORE));
        registerRenderer(Item.getItemFromBlock(SILICON_ORE));
        registerRenderer(Item.getItemFromBlock(SOLAR_CELL));


        registerRenderer(Item.getItemFromBlock(GLOWSTONE_CHARGER));
        registerRenderer(Item.getItemFromBlock(STEAM_GENERATOR));

        registerRenderer(Item.getItemFromBlock(LIGHT_GENERATOR));

    }

    private static void loadItems() {
        registerRenderer(COPPER_INGOT);
        registerRenderer(LITHIUM_INGOT);
        registerRenderer(MAGNET);
        registerRenderer(ALUMINIUM_INGOT);
        registerRenderer(SILICON_INGOT);
        registerRenderer(BATTERY);
        registerRenderer(COPPER_WIRE);

    }

    private static void loadCraftingRecepies() {
        GameRegistry.addShapedRecipe(new ResourceLocation("CopperWireCrafting"),
                null, new ItemStack(COPPER_WIRE,6),
                "   ",
                         "XXX",
                         "   ",
                'X', COPPER_INGOT);

        GameRegistry.addShapedRecipe(new ResourceLocation("CopperWireCrafting"),
                null, new ItemStack(BATTERY,1),
                "X X",
                         "YZY",
                         "YYY",
                'X', COPPER_WIRE,'Y', ALUMINIUM_INGOT,'Z', LITHIUM_INGOT);


    }

    private static void loadSmeltingRecepies() {
        GameRegistry.addSmelting(new ItemStack(ModInit.COPPER_ORE),new ItemStack(COPPER_INGOT),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.LITHIUM_ORE),new ItemStack(LITHIUM_INGOT),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.LODESTONE_ORE),new ItemStack(MAGNET),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.ALUMINIUM_ORE),new ItemStack(ALUMINIUM_INGOT),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.SILICON_ORE),new ItemStack(SILICON_INGOT),1f);
    }

}