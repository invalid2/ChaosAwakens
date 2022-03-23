package io.github.chaosawakens.data;


import io.github.chaosawakens.ChaosAwakens;
import io.github.chaosawakens.common.loot.CATreasure;
import io.github.chaosawakens.common.registry.CABlocks;
import io.github.chaosawakens.common.registry.CAItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.SetNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CAChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    public String getName() {
        return ChaosAwakens.MODNAME + ": Chest Loot Tables";
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> register) {
        register.accept(CATreasure.ent_dungeon_acacia_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_LEAVES).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.GRASS).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.GRASS).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.ACACIA_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_birch_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LEAVES).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.BIRCH_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_crimson_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_FUNGUS).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_FUNGUS).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_ROOTS).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_ROOTS).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_NYLIUM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.CRIMSON_NYLIUM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.SHROOMLIGHT).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.SHROOMLIGHT).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_dark_oak_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LEAVES).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.VINE).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.VINE).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.DARK_OAK_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_jungle_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_LEAVES).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.VINE).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.VINE).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.COCOA_BEANS).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.COCOA_BEANS).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.BAMBOO).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.BAMBOO).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.JUNGLE_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_jungle_trap.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))

                                .add(ItemLootEntry.lootTableItem(Items.TIPPED_ARROW).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))).apply(SetNBT.setTag(Util.make(new CompoundNBT(), (nbt) -> nbt.putString("Potion", "minecraft:poison")))))
                                .add(ItemLootEntry.lootTableItem(Items.TIPPED_ARROW).apply(SetCount.setCount(RandomValueRange.between(2.0F, 6.0F))).apply(SetNBT.setTag(Util.make(new CompoundNBT(), (nbt) -> nbt.putString("Potion", "minecraft:long_poison")))))
                                .add(ItemLootEntry.lootTableItem(Items.TIPPED_ARROW).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))).apply(SetNBT.setTag(Util.make(new CompoundNBT(), (nbt) -> nbt.putString("Potion", "minecraft:strong_poison")))))));
        register.accept(CATreasure.ent_dungeon_oak_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_LEAVES).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.OAK_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_spruce_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_LOG).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_LEAVES).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_LEAVES).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.PODZOL).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.PODZOL).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.COARSE_DIRT).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.COARSE_DIRT).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.FERN).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.FERN).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.SPRUCE_SAPLING).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_warped_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_PLANKS).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_STEM).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_FUNGUS).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_FUNGUS).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_ROOTS).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_ROOTS).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_NYLIUM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.WARPED_NYLIUM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.SHROOMLIGHT).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.SHROOMLIGHT).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_brown_mushroom_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.ent_dungeon_red_mushroom_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(4))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.MUSHROOM_STEM).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM_BLOCK).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(100)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(25))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).apply(SetCount.setCount(RandomValueRange.between(3, 7))).setWeight(50)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.RUBY.get()).apply(SetCount.setCount(RandomValueRange.between(0, 2))).when(RandomChance.randomChance(0.65F)).setWeight(25))));
        register.accept(CATreasure.wasp_dungeon_loot.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(3))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.HONEYCOMB).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.HONEYCOMB).apply(SetCount.setCount(RandomValueRange.between(8, 14))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.HONEY_BOTTLE).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(30))
                                .add(ItemLootEntry.lootTableItem(Items.GLASS_BOTTLE).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(40))
                                .add(ItemLootEntry.lootTableItem(Items.GLASS_BOTTLE).apply(SetCount.setCount(RandomValueRange.between(3, 5))).setWeight(40)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.HONEYCOMB_BLOCK).apply(SetCount.setCount(RandomValueRange.between(2, 6))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.HONEYCOMB_BLOCK).apply(SetCount.setCount(RandomValueRange.between(1, 5))).setWeight(80))
                                .add(ItemLootEntry.lootTableItem(Items.HONEY_BLOCK).apply(SetCount.setCount(RandomValueRange.between(1, 4))).setWeight(20)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(2))
                                //common loot
                                .add(ItemLootEntry.lootTableItem(Items.GOLD_NUGGET).apply(SetCount.setCount(RandomValueRange.between(7, 16))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.GOLD_NUGGET).apply(SetCount.setCount(RandomValueRange.between(7, 16))).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(Items.GOLD_INGOT).apply(SetCount.setCount(RandomValueRange.between(1, 3))).setWeight(20)))
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(0, 1))
                                //rare loot
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND).apply(SetCount.setCount(RandomValueRange.between(0, 1))).when(RandomChance.randomChance(0.35F)).setWeight(110))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).apply(SetCount.setCount(RandomValueRange.between(0, 1))).when(RandomChance.randomChance(0.35F)).setWeight(70))
                                .add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get()).apply(SetCount.setCount(RandomValueRange.between(0, 1))).when(RandomChance.randomChance(0.35F)).setWeight(50))
                                .add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).apply(SetCount.setCount(RandomValueRange.between(0, 1))).when(RandomChance.randomChance(0.35F)).setWeight(50))));
        register.accept(CATreasure.village_cherry_house.lootTable,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(3.0F, 8.0F))
                                .add(ItemLootEntry.lootTableItem(CAItems.ALUMINUM_NUGGET.get()).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F))))
                                .add(ItemLootEntry.lootTableItem(CABlocks.CYAN_ROSE.get()).setWeight(2))
                                .add(ItemLootEntry.lootTableItem(CABlocks.RED_ROSE.get()).setWeight(2))
                                .add(ItemLootEntry.lootTableItem(CABlocks.PAEONIA.get()).setWeight(2))
                                .add(ItemLootEntry.lootTableItem(CAItems.LETTUCE.get()).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0F, 7.0F))))
                                .add(ItemLootEntry.lootTableItem(CAItems.STRAWBERRY.get()).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0F, 7.0F))))
                                .add(ItemLootEntry.lootTableItem(CAItems.RADISH_SEEDS.get()).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                                .add(ItemLootEntry.lootTableItem(CAItems.RADISH.get()).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F))))
                                .add(ItemLootEntry.lootTableItem(CAItems.RADISH_STEW.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                                .add(ItemLootEntry.lootTableItem(CABlocks.CHERRY_SAPLING.get()).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F))))
                                .add(ItemLootEntry.lootTableItem(CAItems.CHERRY_SIGN.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(CABlocks.CHERRY_LOG.get()).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F))))));

    }
}