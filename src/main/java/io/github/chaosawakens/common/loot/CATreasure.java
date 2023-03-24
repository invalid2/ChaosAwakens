package io.github.chaosawakens.common.loot;

import java.util.Set;

import com.google.common.collect.Sets;

import io.github.chaosawakens.ChaosAwakens;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;

public class CATreasure {
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/acacia_loot"}} 1
	public static final CATreasure ent_tree_acacia_loot = new CATreasure("ent_tree/acacia_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/birch_loot"}} 1
	public static final CATreasure ent_tree_birch_loot = new CATreasure("ent_tree/birch_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/crimson_loot"}} 1
	public static final CATreasure ent_tree_crimson_loot = new CATreasure("ent_tree/crimson_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/dark_oak_loot"}} 1
	public static final CATreasure ent_tree_dark_oak_loot = new CATreasure("ent_tree/dark_oak_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/jungle_loot"}} 1
	public static final CATreasure ent_tree_jungle_loot = new CATreasure("ent_tree/jungle_loot");
	// /give @p dispenser{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/jungle_trap"}} 1
	public static final CATreasure ent_tree_jungle_trap = new CATreasure("ent_tree/jungle_trap");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/oak_loot"}} 1
	public static final CATreasure ent_tree_oak_loot = new CATreasure("ent_tree/oak_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/spruce_loot"}} 1
	public static final CATreasure ent_tree_spruce_loot = new CATreasure("ent_tree/spruce_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/warped_loot"}} 1
	public static final CATreasure ent_tree_warped_loot = new CATreasure("ent_tree/warped_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/brown_mushroom_loot"}} 1
	public static final CATreasure ent_tree_brown_mushroom_loot = new CATreasure("ent_tree/brown_mushroom_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/red_mushroom_loot"}} 1
	public static final CATreasure ent_tree_red_mushroom_loot = new CATreasure("ent_tree/red_mushroom_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/ent_tree/ginkgo_loot"}} 1
	public static final CATreasure ent_tree_ginkgo_loot = new CATreasure("ent_tree/ginkgo_loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/wasp_dungeon/loot"}} 1
	public static final CATreasure wasp_dungeon_loot = new CATreasure("wasp_dungeon/loot");
	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/mining_wasp_dungeon/loot"}} 1
	public static final CATreasure mining_wasp_dungeon_loot = new CATreasure("mining_wasp_dungeon/loot");
//	// /give @p chest{BlockEntityTag:{LootTable:"chaosawakens:chests/village/cherry_house"}} 1
//	public static final CATreasure village_cherry_house = new CATreasure("village/cherry_house");

	private static final Set<ResourceLocation> CA_LOOT_TABLES = Sets.newHashSet();
	
	public static LootFunctionType ENCHANT;
	public static final LootFunctionType DRAGON_FIGHT_FACTOR = registerFunction("dragon_fight_factor", new LootFunctionType(new AfterFirstDragonFightFactor.Serializer()));

	public final ResourceLocation lootTable;

	private CATreasure(String path) {
		lootTable = ChaosAwakens.prefix(String.format("chests/%s", path));
	}

	public static void init() {
		//ENCHANT = registerFunction("enchant", new LootFunctionType(new LootFunctionEnchant.Serializer()));
		//DRAGON_FIGHT_FACTOR = registerFunction("dragon_fight_factor", new LootFunctionType(new DragonFightFactorFunction.Serializer()));
	}

	private static LootFunctionType registerFunction(String name, LootFunctionType function) {
		return Registry.register(Registry.LOOT_FUNCTION_TYPE, ChaosAwakens.prefix(name), function); // ILootFunction registry
	}

	@SuppressWarnings("unused")
	private static LootConditionType registerCondition(String name, LootConditionType condition) {
		return Registry.register(Registry.LOOT_CONDITION_TYPE, ChaosAwakens.prefix(name), condition); // ILootCondition registry
	}

	@SuppressWarnings("unused")
	private static ResourceLocation register(String id) {
		return register(ChaosAwakens.prefix(id));
	}

	private static ResourceLocation register(ResourceLocation id) {
		if (CA_LOOT_TABLES.add(id)) return id;
		else throw new IllegalArgumentException(id + " is already a registered built-in loot table");
	}

	public void generateChest(IWorld world, BlockPos pos, Direction dir, boolean trapped) {
		world.setBlock(pos, (trapped ? Blocks.TRAPPED_CHEST : Blocks.CHEST).defaultBlockState().setValue(ChestBlock.FACING, dir), 2);
		TileEntity te = world.getBlockEntity(pos);
		if (te instanceof ChestTileEntity) ((ChestTileEntity) te).setLootTable(lootTable, ((ISeedReader) world).getSeed() * pos.getX() + pos.getY() ^ pos.getZ());
	}

	public void generateChestContents(ISeedReader world, BlockPos pos) {
		TileEntity te = world.getBlockEntity(pos);
		if (te instanceof ChestTileEntity) ((ChestTileEntity) te).setLootTable(lootTable, world.getSeed() * pos.getX() + pos.getY() ^ pos.getZ());
	}
}
