package ru.urbanatech;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.block.MapColor;

public class UrbanaTechMod implements ModInitializer {
    public static final String MOD_ID = "urbana_tech";

    public static final Block TOILET = registerBlock("toilet", new UtilityBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.WHITE)));
    public static final Block ROUTER = registerBlock("router", new UtilityBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block TV = registerBlock("tv", new UtilityBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE).mapColor(MapColor.BLACK)));
    public static final Block COMPUTER = registerBlock("computer", new UtilityBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).mapColor(MapColor.GRAY)));
    public static final Block DATACENTER = registerBlock("datacenter", new UtilityBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).mapColor(MapColor.BLUE)));
    public static final Item RAM = registerItem("ram", new Item(new Item.Settings()));
    public static final Item REMOTE = registerItem("remote", new Item(new Item.Settings().maxCount(1)));

    private static Block registerBlock(String name, Block block) {
        Identifier id = Identifier.of(MOD_ID, name);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Registry.register(Registries.BLOCK, blockKey, block);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings().registryKey(itemKey)));
        return block;
    }

    private static Item registerItem(String name, Item item) {
        Identifier id = Identifier.of(MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(Registries.ITEM, key, item);
    }

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(TOILET); entries.add(ROUTER); entries.add(TV);
            entries.add(COMPUTER); entries.add(DATACENTER); entries.add(RAM); entries.add(REMOTE);
        });
    }
}
