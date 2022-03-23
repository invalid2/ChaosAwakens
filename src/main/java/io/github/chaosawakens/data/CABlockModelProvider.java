package io.github.chaosawakens.data;

import io.github.chaosawakens.ChaosAwakens;
import net.minecraft.block.Block;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class CABlockModelProvider extends BlockModelProvider {
	public CABlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}

	@Override
	public String getName() {
		return ChaosAwakens.MODNAME + ": Block Models";
	}

	private static ResourceLocation getResourceLocation(String path) {
		return new ResourceLocation(ChaosAwakens.MODID, path);
	}

	private static ResourceLocation getBlockResourceLocation(String name) {
		return getResourceLocation("block/" + name);
	}

	private static ResourceLocation getBlockResourceLocation(String name, @Nonnull String removeSuffix, String addSuffix) {
		return getBlockResourceLocation(name.substring(0, name.length() - removeSuffix.length()) + addSuffix);
	}

	@Override
	protected void registerModels() {
		// TODO Automate the data generators
		for (Block block : ForgeRegistries.BLOCKS) {
			if (!ChaosAwakens.MODID.equals(block.getRegistryName().getNamespace())) continue;

			String name = block.getRegistryName().getPath();
			ChaosAwakens.LOGGER.debug(block.getRegistryName());

			if (block instanceof StandingSignBlock || block instanceof WallSignBlock) {
				if (block instanceof StandingSignBlock) {
					getBuilder(name).texture("particle", getBlockResourceLocation(name, "_sign", "_planks"));
				}
			}
		}

		this.orientableWithBottom("defossilizer", mcRL("iron_block"), chaosRL("defossilizer_front"), mcRL("iron_block"), chaosRL("defossilizer_top"));

		this.cubeBottomTop("dense_grass_block", chaosRL("dense_grass_block_side"), chaosRL("dense_dirt"), chaosRL("dense_grass_block_top"));
		this.cubeAll("dense_dirt", chaosRL("dense_dirt"));
		this.cubeBottomTop("dense_red_ant_nest", chaosRL("dense_grass_block_side"), chaosRL("dense_dirt"), chaosRL("dense_red_ant_nest"));
		this.plant("dense_grass", "dense_grass");
		this.doublePlant("tall_dense_grass", "tall_dense_grass");
		this.doublePlant("thorny_sun", "thorny_sun");
		this.cross("blue_bulb", chaosRL("blue_bulb"));
		this.cross("pink_bulb", chaosRL("pink_bulb"));
		this.cross("purple_bulb", chaosRL("purple_bulb"));

		this.cubeBottomTop("fossilised_husk_sandstone", chaosRL("fossilised_husk"), mcRL("sandstone_bottom"), mcRL("sandstone_top"));

		this.cubeAll("aluminum_block", chaosRL("aluminum_block"));
		this.cubeAll("aluminum_ore", chaosRL("aluminum_ore"));
		this.cubeAll("amethyst_block", chaosRL("amethyst_block"));
		this.cubeAll("amethyst_ore", chaosRL("amethyst_ore"));
		this.cubeAll("bloodstone_block", chaosRL("bloodstone_block"));
		this.cubeAll("bloodstone_ore", chaosRL("bloodstone_ore"));
		this.cubeAll("copper_block", chaosRL("copper_block"));
		this.cubeAll("copper_ore", chaosRL("copper_ore"));
		this.cubeAll("platinum_block", chaosRL("platinum_block"));
		this.cubeAll("platinum_ore", chaosRL("platinum_ore"));
		this.cubeAll("ruby_block", chaosRL("ruby_block"));
		this.cubeAll("ruby_ore", chaosRL("ruby_ore"));
		this.cubeAll("netherrack_ruby_ore", chaosRL("netherrack_ruby_ore"));
		this.cubeAll("blackstone_ruby_ore", chaosRL("blackstone_ruby_ore"));
		this.cubeAll("silver_block", chaosRL("silver_block"));
		this.cubeAll("silver_ore", chaosRL("silver_ore"));
		this.cubeAll("sunstone_block", chaosRL("sunstone_block"));
		this.cubeAll("sunstone_ore", chaosRL("sunstone_ore"));
		this.cubeAll("tigers_eye_block", chaosRL("tigers_eye_block"));
		this.cubeAll("tigers_eye_ore", chaosRL("tigers_eye_ore"));
		this.cubeAll("tin_block", chaosRL("tin_block"));
		this.cubeAll("tin_ore", chaosRL("tin_ore"));
		this.cubeAll("titanium_block", chaosRL("titanium_block"));
		this.cubeAll("titanium_ore", chaosRL("titanium_ore"));
		this.cubeAll("uranium_block", chaosRL("uranium_block"));
		this.cubeAll("uranium_ore", chaosRL("uranium_ore"));

		this.cubeAll("salt_ore", chaosRL("salt_ore"));

		this.cubeAll("budding_cats_eye", chaosRL("budding_cats_eye"));
		this.cubeAll("cats_eye_block", chaosRL("cats_eye_block"));
		this.cubeAll("budding_pink_tourmaline", chaosRL("budding_pink_tourmaline"));
		this.cubeAll("pink_tourmaline_block", chaosRL("pink_tourmaline_block"));

		this.cubeAll("fossilised_acacia_ent", chaosRL("fossilised_acacia_ent"));
		this.cubeAll("fossilised_birch_ent", chaosRL("fossilised_birch_ent"));
		this.cubeAll("fossilised_dark_oak_ent", chaosRL("fossilised_dark_oak_ent"));
		this.cubeAll("fossilised_jungle_ent", chaosRL("fossilised_jungle_ent"));
		this.cubeAll("fossilised_oak_ent", chaosRL("fossilised_oak_ent"));
		this.cubeAll("fossilised_spruce_ent", chaosRL("fossilised_spruce_ent"));
		this.cubeAll("fossilised_hercules_beetle", chaosRL("fossilised_hercules_beetle"));
		this.cubeAll("fossilised_ruby_bug", chaosRL("fossilised_ruby_bug"));
		this.cubeAll("fossilised_emerald_gator", chaosRL("fossilised_emerald_gator"));
		this.cubeAll("fossilised_green_fish", chaosRL("fossilised_green_fish"));
		this.cubeAll("fossilised_rock_fish", chaosRL("fossilised_rock_fish"));
		this.cubeAll("fossilised_spark_fish", chaosRL("fossilised_spark_fish"));
		this.cubeAll("fossilised_wood_fish", chaosRL("fossilised_wood_fish"));
		this.cubeAll("fossilised_whale", chaosRL("fossilised_whale"));
		this.cubeAll("fossilised_wtf", chaosRL("fossilised_wtf"));
		this.cubeAll("fossilised_scorpion", chaosRL("fossilised_scorpion"));
		this.cubeAll("fossilised_wasp", chaosRL("fossilised_wasp"));
		this.cubeAll("fossilised_piraporu", chaosRL("fossilised_piraporu"));
		this.cubeAll("fossilised_apple_cow", chaosRL("fossilised_apple_cow"));
		this.cubeAll("fossilised_golden_apple_cow", chaosRL("fossilised_golden_apple_cow"));
		this.cubeAll("fossilised_carrot_pig", chaosRL("fossilised_carrot_pig"));
		this.cubeAll("fossilised_golden_carrot_pig", chaosRL("fossilised_golden_carrot_pig"));
		this.cubeAll("fossilised_bird", chaosRL("fossilised_bird"));
		this.cubeAll("fossilised_dimetrodon", chaosRL("fossilised_dimetrodon"));
		this.cubeAll("fossilised_frog", chaosRL("fossilised_frog"));

		this.cubeAll("fossilised_bat", chaosRL("fossilised_bat"));
		this.cubeAll("fossilised_bee", chaosRL("fossilised_bee"));
		this.cubeAll("fossilised_cave_spider", chaosRL("fossilised_cave_spider"));
		this.cubeAll("fossilised_chicken", chaosRL("fossilised_chicken"));
		this.cubeAll("fossilised_cod", chaosRL("fossilised_cod"));
		this.cubeAll("fossilised_cow", chaosRL("fossilised_cow"));
		this.cubeAll("fossilised_creeper", chaosRL("fossilised_creeper"));
		this.cubeAll("fossilised_dolphin", chaosRL("fossilised_dolphin"));
		this.cubeAll("fossilised_donkey", chaosRL("fossilised_donkey"));
		this.cubeAll("fossilised_drowned", chaosRL("fossilised_drowned"));
		this.cubeAll("fossilised_enderman", chaosRL("fossilised_enderman"));
		this.cubeAll("fossilised_evoker", chaosRL("fossilised_evoker"));
		this.cubeAll("fossilised_fox", chaosRL("fossilised_fox"));
		this.cubeAll("fossilised_giant", chaosRL("fossilised_giant"));
		this.cubeAll("fossilised_guardian", chaosRL("fossilised_guardian"));
		this.cubeAll("fossilised_horse", chaosRL("fossilised_horse"));
		this.cubeAll("fossilised_husk", chaosRL("fossilised_husk"));
		this.cubeAll("fossilised_husk", chaosRL("fossilised_husk"));
		this.cubeAll("fossilised_illusioner", chaosRL("fossilised_illusioner"));
		this.cubeAll("fossilised_iron_golem", chaosRL("fossilised_iron_golem"));
		this.cubeAll("fossilised_llama", chaosRL("fossilised_llama"));
		this.cubeAll("fossilised_mooshroom", chaosRL("fossilised_mooshroom"));
		this.cubeAll("fossilised_ocelot", chaosRL("fossilised_ocelot"));
		this.cubeAll("fossilised_panda", chaosRL("fossilised_panda"));
		this.cubeAll("fossilised_pig", chaosRL("fossilised_pig"));
		this.cubeAll("fossilised_phantom", chaosRL("fossilised_phantom"));
		this.cubeAll("fossilised_pillager", chaosRL("fossilised_pillager"));
		this.cubeAll("fossilised_polar_bear", chaosRL("fossilised_polar_bear"));
		this.cubeAll("fossilised_pufferfish", chaosRL("fossilised_pufferfish"));
		this.cubeAll("fossilised_rabbit", chaosRL("fossilised_rabbit"));
		this.cubeAll("fossilised_ravager", chaosRL("fossilised_ravager"));
		this.cubeAll("fossilised_salmon", chaosRL("fossilised_salmon"));
		this.cubeAll("fossilised_sheep", chaosRL("fossilised_sheep"));
		this.cubeAll("fossilised_skeleton", chaosRL("fossilised_skeleton"));
		this.cubeAll("fossilised_skeleton_horse", chaosRL("fossilised_skeleton_horse"));
		this.cubeAll("fossilised_slime", chaosRL("fossilised_slime"));
		this.cubeAll("fossilised_snow_golem", chaosRL("fossilised_snow_golem"));
		this.cubeAll("fossilised_spider", chaosRL("fossilised_spider"));
		this.cubeAll("fossilised_squid", chaosRL("fossilised_squid"));
		this.cubeAll("fossilised_stray", chaosRL("fossilised_stray"));
		this.cubeAll("fossilised_tropical_fish", chaosRL("fossilised_tropical_fish"));
		this.cubeAll("fossilised_turtle", chaosRL("fossilised_turtle"));
		this.cubeAll("fossilised_villager", chaosRL("fossilised_villager"));
		this.cubeAll("fossilised_vindicator", chaosRL("fossilised_vindicator"));
		this.cubeAll("fossilised_wandering_trader", chaosRL("fossilised_wandering_trader"));
		this.cubeAll("fossilised_witch", chaosRL("fossilised_witch"));
		this.cubeAll("fossilised_wolf", chaosRL("fossilised_wolf"));
		this.cubeAll("fossilised_zombie", chaosRL("fossilised_zombie"));
		this.cubeAll("fossilised_zombie_horse", chaosRL("fossilised_zombie_horse"));

		this.cubeAll("fossilised_crimson_ent", chaosRL("fossilised_crimson_ent"));
		this.cubeAll("fossilised_warped_ent", chaosRL("fossilised_warped_ent"));
		this.cubeAll("fossilised_lava_eel", chaosRL("fossilised_lava_eel"));

		this.cubeAll("fossilised_blaze", chaosRL("fossilised_blaze"));
		this.cubeAll("fossilised_ghast", chaosRL("fossilised_ghast"));
		this.cubeAll("fossilised_hoglin", chaosRL("fossilised_hoglin"));
		this.cubeAll("fossilised_enderman_netherrack", chaosRL("fossilised_enderman_netherrack"));
		this.cubeAll("fossilised_magma_cube_netherrack", chaosRL("fossilised_magma_cube_netherrack"));
		this.cubeAll("fossilised_magma_cube_blackstone", chaosRL("fossilised_magma_cube_blackstone"));
		this.cubeAll("fossilised_piglin", chaosRL("fossilised_piglin"));
		this.cubeAll("fossilised_skeleton_soul_soil", chaosRL("fossilised_skeleton_soul_soil"));
		this.cubeAll("fossilised_strider", chaosRL("fossilised_strider"));
		this.cubeAll("fossilised_wither_skeleton", chaosRL("fossilised_wither_skeleton"));
		this.cubeAll("fossilised_zombified_piglin", chaosRL("fossilised_zombified_piglin"));

		this.cubeAll("fossilised_enderman_end_stone", chaosRL("fossilised_enderman_end_stone"));
		this.cubeAll("fossilised_endermite", chaosRL("fossilised_endermite"));
		this.cubeAll("fossilised_shulker", chaosRL("fossilised_shulker"));

		this.cubeAll("crystalised_crystal_apple_cow", chaosRL("crystalised_crystal_apple_cow"));

		this.cubeColumn("apple_log", chaosRL("apple_log"), chaosRL("apple_log_top"));
		this.cubeColumnHorizontal("apple_log", chaosRL("apple_log"), chaosRL("apple_log_top"));
		this.cubeColumn("apple_wood", chaosRL("apple_log"), chaosRL("apple_log"));
		this.cubeColumnHorizontal("apple_wood", chaosRL("apple_log"), chaosRL("apple_log"));
		this.cubeColumn("stripped_apple_log", chaosRL("stripped_apple_log"), chaosRL("stripped_apple_log_top"));
		this.cubeColumnHorizontal("stripped_apple_log", chaosRL("stripped_apple_log"), chaosRL("stripped_apple_log_top"));
		this.cubeColumn("stripped_apple_wood", chaosRL("stripped_apple_log"), chaosRL("stripped_apple_log"));
		this.cubeColumnHorizontal("stripped_apple_wood", chaosRL("stripped_apple_log"), chaosRL("stripped_apple_log"));
		this.cubeColumn("cherry_log", chaosRL("cherry_log"), chaosRL("cherry_log_top"));
		this.cubeColumnHorizontal("cherry_log", chaosRL("cherry_log"), chaosRL("cherry_log_top"));
		this.cubeColumn("cherry_wood", chaosRL("cherry_log"), chaosRL("cherry_log"));
		this.cubeColumnHorizontal("cherry_wood", chaosRL("cherry_log"), chaosRL("cherry_log"));
		this.cubeColumn("stripped_cherry_log", chaosRL("stripped_cherry_log"), chaosRL("stripped_cherry_log_top"));
		this.cubeColumnHorizontal("stripped_cherry_log", chaosRL("stripped_cherry_log"), chaosRL("stripped_cherry_log_top"));
		this.cubeColumn("stripped_cherry_wood", chaosRL("stripped_cherry_log"), chaosRL("stripped_cherry_log"));
		this.cubeColumnHorizontal("stripped_cherry_wood", chaosRL("stripped_cherry_log"), chaosRL("stripped_cherry_log"));
		this.cubeColumn("ginkgo_log", chaosRL("ginkgo_log"), chaosRL("ginkgo_log_top"));
		this.cubeColumnHorizontal("ginkgo_log", chaosRL("ginkgo_log"), chaosRL("ginkgo_log_top"));
		this.cubeColumn("ginkgo_wood", chaosRL("ginkgo_log"), chaosRL("ginkgo_log"));
		this.cubeColumnHorizontal("ginkgo_wood", chaosRL("ginkgo_log"), chaosRL("ginkgo_log"));
		this.cubeColumn("stripped_ginkgo_log", chaosRL("stripped_ginkgo_log"), chaosRL("stripped_ginkgo_log_top"));
		this.cubeColumnHorizontal("stripped_ginkgo_log", chaosRL("stripped_ginkgo_log"), chaosRL("stripped_ginkgo_log_top"));
		this.cubeColumn("stripped_ginkgo_wood", chaosRL("stripped_ginkgo_log"), chaosRL("stripped_ginkgo_log"));
		this.cubeColumnHorizontal("stripped_ginkgo_wood", chaosRL("stripped_ginkgo_log"), chaosRL("stripped_ginkgo_log"));
		this.cubeColumn("peach_log", chaosRL("peach_log"), chaosRL("peach_log_top"));
		this.cubeColumnHorizontal("peach_log", chaosRL("peach_log"), chaosRL("peach_log_top"));
		this.cubeColumn("peach_wood", chaosRL("peach_log"), chaosRL("peach_log"));
		this.cubeColumnHorizontal("peach_wood", chaosRL("peach_log"), chaosRL("peach_log"));
		this.cubeColumn("stripped_peach_log", chaosRL("stripped_peach_log"), chaosRL("stripped_peach_log_top"));
		this.cubeColumnHorizontal("stripped_peach_log", chaosRL("stripped_peach_log"), chaosRL("stripped_peach_log_top"));
		this.cubeColumn("stripped_peach_wood", chaosRL("stripped_peach_log"), chaosRL("stripped_peach_log"));
		this.cubeColumnHorizontal("stripped_peach_wood", chaosRL("stripped_peach_log"), chaosRL("stripped_peach_log"));
		this.cubeColumn("duplication_log", chaosRL("duplication_log"), chaosRL("duplication_log_top"));
		this.cubeColumnHorizontal("duplication_log", chaosRL("duplication_log"), chaosRL("duplication_log_top"));
		this.cubeColumn("duplication_wood", chaosRL("duplication_log"), chaosRL("duplication_log"));
		this.cubeColumnHorizontal("duplication_wood", chaosRL("duplication_log"), chaosRL("duplication_log"));
		this.cubeColumn("stripped_duplication_log", chaosRL("stripped_duplication_log"), chaosRL("stripped_duplication_log_top"));
		this.cubeColumnHorizontal("stripped_duplication_log", chaosRL("stripped_duplication_log"), chaosRL("stripped_duplication_log_top"));
		this.cubeColumn("stripped_duplication_wood", chaosRL("stripped_duplication_log"), chaosRL("stripped_duplication_log"));
		this.cubeColumnHorizontal("stripped_duplication_wood", chaosRL("stripped_duplication_log"), chaosRL("stripped_duplication_log"));
		this.cubeColumn("dead_duplication_log", chaosRL("dead_duplication_log"), chaosRL("dead_duplication_log_top"));
		this.cubeColumnHorizontal("dead_duplication_log", chaosRL("dead_duplication_log"), chaosRL("dead_duplication_log_top"));
		this.cubeColumn("dead_duplication_wood", chaosRL("dead_duplication_log"), chaosRL("dead_duplication_log"));
		this.cubeColumnHorizontal("dead_duplication_wood", chaosRL("dead_duplication_log"), chaosRL("dead_duplication_log"));
		this.cubeColumn("skywood_log", chaosRL("skywood_log"), chaosRL("skywood_log_top"));
		this.cubeColumnHorizontal("skywood_log", chaosRL("skywood_log"), chaosRL("skywood_log_top"));
		this.cubeColumn("skywood_wood", chaosRL("skywood_log"), chaosRL("skywood_log"));
		this.cubeColumnHorizontal("skywood_wood", chaosRL("skywood_log"), chaosRL("skywood_log"));
		this.cubeColumn("stripped_skywood_log", chaosRL("stripped_skywood_log"), chaosRL("stripped_skywood_log_top"));
		this.cubeColumnHorizontal("stripped_skywood_log", chaosRL("stripped_skywood_log"), chaosRL("stripped_skywood_log_top"));
		this.cubeColumn("stripped_skywood_wood", chaosRL("stripped_skywood_log"), chaosRL("stripped_skywood_log"));
		this.cubeColumnHorizontal("stripped_skywood_wood", chaosRL("stripped_skywood_log"), chaosRL("stripped_skywood_log"));
		this.cubeColumn("crystal_log", chaosRL("crystal_log"), chaosRL("crystal_log_top"));
		this.cubeColumnHorizontal("crystal_log", chaosRL("crystal_log"), chaosRL("crystal_log_top"));
		this.cubeColumn("crystal_wood", chaosRL("crystal_log"), chaosRL("crystal_log"));
		this.cubeColumnHorizontal("crystal_wood", chaosRL("crystal_log"), chaosRL("crystal_log"));
		this.cubeAll("apple_planks", chaosRL("apple_planks"));
		this.cubeAll("apple_leaves", chaosRL("apple_leaves"));
		this.leafCarpet("apple_leaf_carpet", chaosRL("apple_leaves"));
		this.cubeAll("apple_leaves_ripe", chaosRL("apple_leaves_ripe"));
		this.cross("apple_sapling", chaosRL("apple_sapling"));
		this.cubeAll("cherry_planks", chaosRL("cherry_planks"));
		this.cubeAll("cherry_leaves", chaosRL("cherry_leaves"));
		this.leafCarpet("cherry_leaf_carpet", chaosRL("cherry_leaves"));
		this.cubeAll("cherry_leaves_ripe", chaosRL("cherry_leaves_ripe"));
		this.cross("cherry_sapling", chaosRL("cherry_sapling"));
		this.cubeAll("ginkgo_planks", chaosRL("ginkgo_planks"));
		this.cubeAll("ginkgo_leaves", chaosRL("ginkgo_leaves"));
		this.leafCarpet("ginkgo_leaf_carpet", chaosRL("ginkgo_leaves"));
		this.cross("ginkgo_sapling", chaosRL("ginkgo_sapling"));
		this.cubeAll("peach_planks", chaosRL("peach_planks"));
		this.cubeAll("peach_leaves", chaosRL("peach_leaves"));
		this.leafCarpet("peach_leaf_carpet", chaosRL("peach_leaves"));
		this.cubeAll("peach_leaves_ripe", chaosRL("peach_leaves_ripe"));
		this.cross("peach_sapling", chaosRL("peach_sapling"));
		this.cubeAll("duplication_planks", chaosRL("duplication_planks"));
		this.cubeAll("duplication_leaves", chaosRL("duplication_leaves"));
		this.leafCarpet("duplication_leaf_carpet", chaosRL("duplication_leaves"));
		this.cubeAll("skywood_planks", chaosRL("skywood_planks"));
		this.cubeAll("skywood_leaves", chaosRL("skywood_leaves"));
		this.leafCarpet("skywood_leaf_carpet", chaosRL("skywood_leaves"));
		this.cubeAll("crystal_planks", chaosRL("crystal_planks"));
		this.cross("red_crystal_sapling", chaosRL("red_crystal_sapling"));
		this.cross("green_crystal_sapling", chaosRL("green_crystal_sapling"));
		this.cross("yellow_crystal_sapling", chaosRL("yellow_crystal_sapling"));

		this.leafCarpet("oak_leaf_carpet", mcRL("oak_leaves"));
		this.leafCarpet("spruce_leaf_carpet", mcRL("spruce_leaves"));
		this.leafCarpet("birch_leaf_carpet", mcRL("birch_leaves"));
		this.leafCarpet("jungle_leaf_carpet", mcRL("jungle_leaves"));
		this.leafCarpet("acacia_leaf_carpet", mcRL("acacia_leaves"));
		this.leafCarpet("dark_oak_leaf_carpet", mcRL("dark_oak_leaves"));

		this.stairs("apple_stairs", chaosRL("apple_planks"), chaosRL("apple_planks"), chaosRL("apple_planks"));
		this.stairsInner("apple_stairs", chaosRL("apple_planks"), chaosRL("apple_planks"), chaosRL("apple_planks"));
		this.stairsOuter("apple_stairs", chaosRL("apple_planks"), chaosRL("apple_planks"), chaosRL("apple_planks"));
		this.stairs("cherry_stairs", chaosRL("cherry_planks"), chaosRL("cherry_planks"), chaosRL("cherry_planks"));
		this.stairsInner("cherry_stairs", chaosRL("cherry_planks"), chaosRL("cherry_planks"), chaosRL("cherry_planks"));
		this.stairsOuter("cherry_stairs", chaosRL("cherry_planks"), chaosRL("cherry_planks"), chaosRL("cherry_planks"));
		this.stairs("ginkgo_stairs", chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"));
		this.stairsInner("ginkgo_stairs", chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"));
		this.stairsOuter("ginkgo_stairs", chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"));
		this.stairs("peach_stairs", chaosRL("peach_planks"), chaosRL("peach_planks"), chaosRL("peach_planks"));
		this.stairsInner("peach_stairs", chaosRL("peach_planks"), chaosRL("peach_planks"), chaosRL("peach_planks"));
		this.stairsOuter("peach_stairs", chaosRL("peach_planks"), chaosRL("peach_planks"), chaosRL("peach_planks"));
		this.stairs("duplication_stairs", chaosRL("duplication_planks"), chaosRL("duplication_planks"), chaosRL("duplication_planks"));
		this.stairsInner("duplication_stairs", chaosRL("duplication_planks"), chaosRL("duplication_planks"), chaosRL("duplication_planks"));
		this.stairsOuter("duplication_stairs", chaosRL("duplication_planks"), chaosRL("duplication_planks"), chaosRL("duplication_planks"));
		this.stairs("skywood_stairs", chaosRL("skywood_planks"), chaosRL("skywood_planks"), chaosRL("skywood_planks"));
		this.stairsInner("skywood_stairs", chaosRL("skywood_planks"), chaosRL("skywood_planks"), chaosRL("skywood_planks"));
		this.stairsOuter("skywood_stairs", chaosRL("skywood_planks"), chaosRL("skywood_planks"), chaosRL("skywood_planks"));
		this.stairs("crystal_stairs", chaosRL("crystal_planks"), chaosRL("crystal_planks"), chaosRL("crystal_planks"));
		this.stairsInner("crystal_stairs", chaosRL("crystal_planks"), chaosRL("crystal_planks"), chaosRL("crystal_planks"));
		this.stairsOuter("crystal_stairs", chaosRL("crystal_planks"), chaosRL("crystal_planks"), chaosRL("crystal_planks"));

		this.slab("apple_slab", chaosRL("apple_planks"), chaosRL("apple_planks"), chaosRL("apple_planks"));
		this.slabTop("apple_slab", chaosRL("apple_planks"), chaosRL("apple_planks"), chaosRL("apple_planks"));
		this.slab("cherry_slab", chaosRL("cherry_planks"), chaosRL("cherry_planks"), chaosRL("cherry_planks"));
		this.slabTop("cherry_slab", chaosRL("cherry_planks"), chaosRL("cherry_planks"), chaosRL("cherry_planks"));
		this.slab("ginkgo_slab", chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"));
		this.slabTop("ginkgo_slab", chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"), chaosRL("ginkgo_planks"));
		this.slab("peach_slab", chaosRL("peach_planks"), chaosRL("peach_planks"), chaosRL("peach_planks"));
		this.slabTop("peach_slab", chaosRL("peach_planks"), chaosRL("peach_planks"), chaosRL("peach_planks"));
		this.slab("duplication_slab", chaosRL("duplication_planks"), chaosRL("duplication_planks"), chaosRL("duplication_planks"));
		this.slabTop("duplication_slab", chaosRL("duplication_planks"), chaosRL("duplication_planks"), chaosRL("duplication_planks"));
		this.slab("skywood_slab", chaosRL("skywood_planks"), chaosRL("skywood_planks"), chaosRL("skywood_planks"));
		this.slabTop("skywood_slab", chaosRL("skywood_planks"), chaosRL("skywood_planks"), chaosRL("skywood_planks"));
		this.slab("crystal_slab", chaosRL("crystal_planks"), chaosRL("crystal_planks"), chaosRL("crystal_planks"));
		this.slabTop("crystal_slab", chaosRL("crystal_planks"), chaosRL("crystal_planks"), chaosRL("crystal_planks"));

		this.fenceGate("apple_fence_gate", chaosRL("apple_planks"));
		this.fenceGateOpen("apple_fence_gate", chaosRL("apple_planks"));
		this.fenceGateWall("apple_fence_gate", chaosRL("apple_planks"));
		this.fenceGateWallOpen("apple_fence_gate", chaosRL("apple_planks"));
		this.fencePost("apple_fence", chaosRL("apple_planks"));
		this.fenceInventory("apple_fence", chaosRL("apple_planks"));
		this.fenceSide("apple_fence", chaosRL("apple_planks"));
		this.fenceGate("cherry_fence_gate", chaosRL("cherry_planks"));
		this.fenceGateOpen("cherry_fence_gate", chaosRL("cherry_planks"));
		this.fenceGateWall("cherry_fence_gate", chaosRL("cherry_planks"));
		this.fenceGateWallOpen("cherry_fence_gate", chaosRL("cherry_planks"));
		this.fencePost("cherry_fence", chaosRL("cherry_planks"));
		this.fenceInventory("cherry_fence", chaosRL("cherry_planks"));
		this.fenceSide("cherry_fence", chaosRL("cherry_planks"));
		this.fenceGate("ginkgo_fence_gate", chaosRL("ginkgo_planks"));
		this.fenceGateOpen("ginkgo_fence_gate", chaosRL("ginkgo_planks"));
		this.fenceGateWall("ginkgo_fence_gate", chaosRL("ginkgo_planks"));
		this.fenceGateWallOpen("ginkgo_fence_gate", chaosRL("ginkgo_planks"));
		this.fencePost("ginkgo_fence", chaosRL("ginkgo_planks"));
		this.fenceInventory("ginkgo_fence", chaosRL("ginkgo_planks"));
		this.fenceSide("ginkgo_fence", chaosRL("ginkgo_planks"));
		this.fenceGate("peach_fence_gate", chaosRL("peach_planks"));
		this.fenceGateOpen("peach_fence_gate", chaosRL("peach_planks"));
		this.fenceGateWall("peach_fence_gate", chaosRL("peach_planks"));
		this.fenceGateWallOpen("peach_fence_gate", chaosRL("peach_planks"));
		this.fencePost("peach_fence", chaosRL("peach_planks"));
		this.fenceInventory("peach_fence", chaosRL("peach_planks"));
		this.fenceSide("peach_fence", chaosRL("peach_planks"));
		this.fenceGate("duplication_fence_gate", chaosRL("duplication_planks"));
		this.fenceGateOpen("duplication_fence_gate", chaosRL("duplication_planks"));
		this.fenceGateWall("duplication_fence_gate", chaosRL("duplication_planks"));
		this.fenceGateWallOpen("duplication_fence_gate", chaosRL("duplication_planks"));
		this.fencePost("duplication_fence", chaosRL("duplication_planks"));
		this.fenceInventory("duplication_fence", chaosRL("duplication_planks"));
		this.fenceSide("duplication_fence", chaosRL("duplication_planks"));
		this.fenceGate("skywood_fence_gate", chaosRL("skywood_planks"));
		this.fenceGateOpen("skywood_fence_gate", chaosRL("skywood_planks"));
		this.fenceGateWall("skywood_fence_gate", chaosRL("skywood_planks"));
		this.fenceGateWallOpen("skywood_fence_gate", chaosRL("skywood_planks"));
		this.fencePost("skywood_fence", chaosRL("skywood_planks"));
		this.fenceInventory("skywood_fence", chaosRL("skywood_planks"));
		this.fenceSide("skywood_fence", chaosRL("skywood_planks"));
		this.fenceGate("crystal_fence_gate", chaosRL("crystal_planks"));
		this.fenceGateOpen("crystal_fence_gate", chaosRL("crystal_planks"));
		this.fenceGateWall("crystal_fence_gate", chaosRL("crystal_planks"));
		this.fenceGateWallOpen("crystal_fence_gate", chaosRL("crystal_planks"));
		this.fencePost("crystal_fence", chaosRL("crystal_planks"));
		this.fenceInventory("crystal_fence", chaosRL("crystal_planks"));
		this.fenceSide("crystal_fence", chaosRL("crystal_planks"));

		this.pressurePlateUp("apple_pressure_plate", chaosRL("apple_planks"));
		this.pressurePlateDown("apple_pressure_plate", chaosRL("apple_planks"));
		this.pressurePlateUp("cherry_pressure_plate", chaosRL("cherry_planks"));
		this.pressurePlateDown("cherry_pressure_plate", chaosRL("cherry_planks"));
		this.pressurePlateUp("ginkgo_pressure_plate", chaosRL("ginkgo_planks"));
		this.pressurePlateDown("ginkgo_pressure_plate", chaosRL("ginkgo_planks"));
		this.pressurePlateUp("duplication_pressure_plate", chaosRL("duplication_planks"));
		this.pressurePlateDown("duplication_pressure_plate", chaosRL("duplication_planks"));
		this.pressurePlateUp("peach_pressure_plate", chaosRL("peach_planks"));
		this.pressurePlateDown("peach_pressure_plate", chaosRL("peach_planks"));
		this.pressurePlateUp("skywood_pressure_plate", chaosRL("skywood_planks"));
		this.pressurePlateDown("skywood_pressure_plate", chaosRL("skywood_planks"));
		this.pressurePlateUp("crystal_pressure_plate", chaosRL("crystal_planks"));
		this.pressurePlateDown("crystal_pressure_plate", chaosRL("crystal_planks"));

		this.cubeAll("moldy_planks", chaosRL("moldy_planks"));
		this.slab("moldy_slab", chaosRL("moldy_planks"), chaosRL("moldy_planks"), chaosRL("moldy_planks"));
		this.slabTop("moldy_slab", chaosRL("moldy_planks"), chaosRL("moldy_planks"), chaosRL("moldy_planks"));
		this.fencePost("moldy_fence", chaosRL("moldy_planks"));
		this.fenceInventory("moldy_fence", chaosRL("moldy_planks"));
		this.fenceSide("moldy_fence", chaosRL("moldy_planks"));
		this.cubeAll("mining_lamp", chaosRL("mining_lamp"));

		this.cross("cyan_rose", chaosRL("cyan_rose"));
		this.cross("red_rose", chaosRL("red_rose"));
		this.cross("paeonia", chaosRL("paeonia"));
		this.cross("blue_crystal_growth", chaosRL("blue_crystal_growth"));
		this.cross("green_crystal_growth", chaosRL("green_crystal_growth"));
		this.cross("red_crystal_growth", chaosRL("red_crystal_growth"));
		this.cross("yellow_crystal_growth", chaosRL("yellow_crystal_growth"));
		this.cross("orange_crystal_growth", chaosRL("orange_crystal_growth"));
		this.cross("pink_crystal_growth", chaosRL("pink_crystal_growth"));
		this.cross("blue_crystal_flower", chaosRL("blue_crystal_flower"));
		this.cross("green_crystal_flower", chaosRL("green_crystal_flower"));
		this.cross("red_crystal_flower", chaosRL("red_crystal_flower"));
		this.cross("yellow_crystal_flower", chaosRL("yellow_crystal_flower"));

		this.gateBlock("apple_gate_block", chaosRL("apple_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("cherry_gate_block", chaosRL("cherry_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("duplication_gate_block", chaosRL("duplication_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("ginkgo_gate_block", chaosRL("ginkgo_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("peach_gate_block", chaosRL("peach_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("skywood_gate_block", chaosRL("skywood_gate_block"), chaosRL("gate_block_top"));

		this.gateBlock("acacia_gate_block", chaosRL("acacia_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("birch_gate_block", chaosRL("birch_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("crimson_gate_block", chaosRL("crimson_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("dark_oak_gate_block", chaosRL("dark_oak_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("jungle_gate_block", chaosRL("jungle_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("oak_gate_block", chaosRL("oak_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("spruce_gate_block", chaosRL("spruce_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("warped_gate_block", chaosRL("warped_gate_block"), chaosRL("gate_block_top"));
		this.gateBlock("mushroom_gate_block", chaosRL("mushroom_gate_block"), chaosRL("gate_block_top"));

		this.trapDoor("apple_trapdoor", chaosRL("apple_trapdoor"));
		this.trapDoor("cherry_trapdoor", chaosRL("cherry_trapdoor"));
		this.trapDoor("duplication_trapdoor", chaosRL("duplication_trapdoor"));
		this.trapDoor("ginkgo_trapdoor", chaosRL("ginkgo_trapdoor"));
		this.trapDoor("peach_trapdoor", chaosRL("peach_trapdoor"));
		this.trapDoor("skywood_trapdoor", chaosRL("skywood_trapdoor"));
	}

	private ResourceLocation chaosRL(String texture) {
		return new ResourceLocation(ChaosAwakens.MODID, BLOCK_FOLDER + "/" + texture);
	}

	private ResourceLocation mcRL(String texture) {
		return new ResourceLocation("minecraft", BLOCK_FOLDER + "/" + texture);
	}

	public void pressurePlateUp(String name, ResourceLocation all) {
		singleTexture(name, mcRL("pressure_plate_up"), all);
	}

	public void pressurePlateDown(String name, ResourceLocation all) {
		singleTexture(name, mcRL("pressure_plate_down"), all);
	}

	public void trapDoor(String name, ResourceLocation texture) {
		singleTexture(name + "_bottom", mcRL("template_orientable_trapdoor_bottom"), texture);
		singleTexture(name + "_open", mcRL("template_orientable_trapdoor_open"), texture);
		singleTexture(name + "_top", mcRL("template_orientable_trapdoor_top"), texture);
	}

	public void leafCarpet(String name, ResourceLocation texture) {
		singleTexture(name, chaosRL("leaf_carpet"), "texture", texture);
	}

	public void gateBlock(String name, ResourceLocation side, ResourceLocation top) {
		withExistingParent(name, BLOCK_FOLDER).texture("side", side).texture("top", top).texture("bottom", top);
	}

	public void plant(String name, String texture) {
		cross(name, chaosRL(texture));
	}

	public void doublePlant(String name, String texture) {
		cross(name + "_top", chaosRL(texture + "_top"));
		cross(name + "_bottom", chaosRL(texture + "_bottom"));
	}

	@Override
	public BlockModelBuilder cubeColumn(String name, ResourceLocation side, ResourceLocation end) {
		return withExistingParent(name, BLOCK_FOLDER).texture("side", side).texture("end", end);
	}

	@Override
	public BlockModelBuilder cubeColumnHorizontal(String name, ResourceLocation side, ResourceLocation end) {
		return withExistingParent(name, BLOCK_FOLDER).texture("side", side).texture("end", end);
	}
}