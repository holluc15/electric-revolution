package com.teamtsla.electricrevolution.init;


import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import com.teamtsla.electricrevolution.blocks.*;
import com.teamtsla.electricrevolution.items.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.openal.AL;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ElectricRevolutionMod.MODID)
public class ModInit {

    //Items
    public static Item COPPER_INGOT;
    public static Item LITHIUM_INGOT;
    public static Item LODESTONE_INGOT;
    public static Item ALUMINIUM_INGOT;
    public static Item SILICON_INGOT;

    public static Item BATTERY;
    //Blocks
    public static Block COPPER_ORE;
    public static Block LITHIUM_ORE;
    public static Block LODESTONE_ORE;
    public static Block ALUMINIUM_ORE;
    public static Block SILICON_ORE;
    public static Block COPPER_BLOCK;


    public static void init() {
        //Items
        COPPER_INGOT = new CopperIngot("copperingot");
        LITHIUM_INGOT =  new LithiumIngot("lithiumingot");
        LODESTONE_INGOT = new LodestoneIngot("lodestoneingot");
        ALUMINIUM_INGOT = new AluminiumIngot("aluminiumingot");

        BATTERY = new Battery("battery");

        //Blocks
        COPPER_ORE = new CopperOre("copperore");
        LITHIUM_ORE = new LithiumOre("lithiumore");
        LODESTONE_ORE = new LodestoneOre("lodestoneore");
        ALUMINIUM_ORE = new AluminiumOre("aluminiumore");
        COPPER_BLOCK = new CopperBlock("copperblock");
        SILICON_ORE = new CopperBlock("siliconore");
        SILICON_INGOT = new SiliconIngot("siliconingot");

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //Items
        event.getRegistry().registerAll(COPPER_INGOT);
        event.getRegistry().registerAll(LITHIUM_INGOT);
        event.getRegistry().registerAll(LODESTONE_INGOT);
        event.getRegistry().registerAll(ALUMINIUM_INGOT);
        event.getRegistry().registerAll(SILICON_INGOT);
        event.getRegistry().registerAll(BATTERY);
        //Blocks
        event.getRegistry().register(new ItemBlock(COPPER_ORE).setRegistryName(Objects.requireNonNull(COPPER_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(LITHIUM_ORE).setRegistryName(Objects.requireNonNull(LITHIUM_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(COPPER_BLOCK).setRegistryName(Objects.requireNonNull(COPPER_BLOCK.getRegistryName())));
        event.getRegistry().register(new ItemBlock(LODESTONE_ORE).setRegistryName(Objects.requireNonNull(LODESTONE_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(ALUMINIUM_ORE).setRegistryName(Objects.requireNonNull(ALUMINIUM_ORE.getRegistryName())));
        event.getRegistry().register(new ItemBlock(SILICON_ORE).setRegistryName(Objects.requireNonNull(SILICON_ORE.getRegistryName())));
    }



    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(COPPER_ORE);
        event.getRegistry().registerAll(LITHIUM_ORE);
        event.getRegistry().registerAll(LODESTONE_ORE);
        event.getRegistry().registerAll(ALUMINIUM_ORE);
        event.getRegistry().registerAll(COPPER_BLOCK);
        event.getRegistry().registerAll(SILICON_ORE);


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
        registerRenderer(Item.getItemFromBlock(COPPER_BLOCK));
    }

    private static void loadItems() {
        registerRenderer(COPPER_INGOT);
        registerRenderer(LITHIUM_INGOT);
        registerRenderer(LODESTONE_INGOT);
        registerRenderer(ALUMINIUM_INGOT);
        registerRenderer(SILICON_INGOT);
        registerRenderer(BATTERY);

    }

    private static void loadCraftingRecepies() {
        GameRegistry.addShapedRecipe(new ResourceLocation("CopperBlockCrafting"),
                null, new ItemStack(COPPER_BLOCK),
                "XXX",
                "XXX",
                "XXX",
                'X', COPPER_INGOT);
    }

    private static void loadSmeltingRecepies() {
        GameRegistry.addSmelting(new ItemStack(ModInit.COPPER_ORE),new ItemStack(COPPER_INGOT),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.LITHIUM_ORE),new ItemStack(LITHIUM_INGOT),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.LODESTONE_ORE),new ItemStack(LODESTONE_INGOT),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.ALUMINIUM_ORE),new ItemStack(ALUMINIUM_INGOT),1f);
        GameRegistry.addSmelting(new ItemStack(ModInit.SILICON_ORE),new ItemStack(SILICON_INGOT),1f);

    }

}