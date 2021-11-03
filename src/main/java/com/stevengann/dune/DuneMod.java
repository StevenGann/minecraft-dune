package com.stevengann.dune;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DuneMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("dune-sg");

	public static final ItemGroup DUNE_GROUP = FabricItemGroupBuilder.create(
		new Identifier("dune-sg", "dune"))
		.icon(() -> new ItemStack(Items.WATER_BUCKET))
		.build();

    public static final SandCompactor SANDCOMPACTOR = new SandCompactor(SandCompactorToolMaterial.INSTANCE, 7, -3.0F,new FabricItemSettings().group(DuneMod.DUNE_GROUP));
	public static final Spice SPICE_ITEM = new Spice(new FabricItemSettings()
		.rarity(Rarity.RARE)
		.food(new FoodComponent.Builder().alwaysEdible().hunger(0).snack().build())
		.group(DuneMod.DUNE_GROUP));
 
	
	public static final ArmorMaterial STILLSUIT_ARMOR_MATERIAL = new StillsuitMaterial();
    //public static final Item STILLSUIT_MATERIAL = new CustomMaterialItem(new Item.Settings().group(DuneMod.DUNE_GROUP));
    // If you made a new material, this is where you would note it.
    public static final Item STILLSUIT_HELMET = new ArmorItem(STILLSUIT_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(DuneMod.DUNE_GROUP));
    public static final Item STILLSUIT_CHESTPLATE = new ArmorItem(STILLSUIT_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(DuneMod.DUNE_GROUP));
    public static final Item STILLSUIT_LEGGINGS = new ArmorItem(STILLSUIT_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(DuneMod.DUNE_GROUP));
    public static final Item STILLSUIT_BOOTS = new ArmorItem(STILLSUIT_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(DuneMod.DUNE_GROUP));

	//public static final Block SPICE_SAND_BLOCK = new SandBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.5f));
	public static final Block SPICE_SAND_BLOCK = new SandBlock(13532672, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.TERRACOTTA_ORANGE).strength(0.5F).sounds(BlockSoundGroup.SAND));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing Dune");		

		Registry.register(Registry.ITEM, new Identifier("dune-sg", "sand_compactor"), SANDCOMPACTOR);

		Registry.register(Registry.ITEM, new Identifier("dune-sg", "spice_item"), SPICE_ITEM);

		Registry.register(Registry.ITEM, new Identifier("dune-sg", "stillsuit_helmet"), STILLSUIT_HELMET);
		Registry.register(Registry.ITEM, new Identifier("dune-sg", "stillsuit_chestplate"), STILLSUIT_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("dune-sg", "stillsuit_leggings"), STILLSUIT_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("dune-sg", "stillsuit_boots"), STILLSUIT_BOOTS);

		Registry.register(Registry.BLOCK, new Identifier("dune-sg", "spice_sand_block"), SPICE_SAND_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("dune-sg", "spice_sand_block"), new BlockItem(SPICE_SAND_BLOCK, new FabricItemSettings().group(DuneMod.DUNE_GROUP)));
	}
}
