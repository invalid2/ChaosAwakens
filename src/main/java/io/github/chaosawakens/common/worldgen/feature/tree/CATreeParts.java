package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.function.Supplier;

import io.github.chaosawakens.ChaosAwakens;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class CATreeParts {
	@SuppressWarnings("unchecked")
	public static final DeferredRegister<TreePartType<?>> TREE_PARTS = DeferredRegister.create(TreePartType.class, 
			ChaosAwakens.MODID);
	public static final Supplier<IForgeRegistry<TreePartType<?>>> SUPPLIER = TREE_PARTS.makeRegistry("tree_parts", 
			RegistryBuilder::new);
	
	public static final RegistryObject<TreePartType<CuboidRoot>> CUBOID_ROOT = TREE_PARTS.register("cuboid_root", () -> 
		new TreePartType<>(CuboidRoot.CODEC));
	
	public static final RegistryObject<TreePartType<SimpleCuboidTrunk>> SIMPLE_CUBOID_TRUNK = TREE_PARTS.register(
			"simple_cuboid_trunk", () -> new TreePartType<>(SimpleCuboidTrunk.CODEC));
	public static final RegistryObject<TreePartType<SimpleRhombusTrunk>> SIMPLE_RHOMBUS_TRUNK = TREE_PARTS.register(
			"simple_rhombus_trunk", () -> new TreePartType<>(SimpleRhombusTrunk.CODEC));
	public static final RegistryObject<TreePartType<BranchedRhombusTrunk>> BRANCHED_RHOMBUS_TRUNK = TREE_PARTS.register(
			"branched_rhombus_trunk", () -> new TreePartType<>(BranchedRhombusTrunk.CODEC));
	
	public static final RegistryObject<TreePartType<StraightBranch>> STRAIGHT_BRANCH = TREE_PARTS.register(
			"straight_branch", () -> new TreePartType<>(StraightBranch.CODEC));
	public static final RegistryObject<TreePartType<ConiferBranch>> CONIFER_BRANCH = TREE_PARTS.register(
			"conifer_branch", () -> new TreePartType<>(ConiferBranch.CODEC));
	
	public static final RegistryObject<TreePartType<ConeFoliage>> CONE_FOLIAGE = TREE_PARTS.register("cone_foliage", 
			() -> new TreePartType<>(ConeFoliage.CODEC));
}
