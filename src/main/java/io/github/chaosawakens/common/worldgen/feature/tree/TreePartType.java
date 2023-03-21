package io.github.chaosawakens.common.worldgen.feature.tree;

import com.mojang.serialization.Codec;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class TreePartType<P extends ITreePart> extends ForgeRegistryEntry<TreePartType<P>> {
	
	//TODO Create a generalized ForgeRegistryEntry with Codec
	public static final Codec<TreePartType<?>> CODEC = ResourceLocation.CODEC.xmap(
			(key) -> CATreeParts.SUPPLIER.get().getValue(key), TreePartType::getRegistryName);
	private final Codec<P> codec;
	
	public TreePartType(Codec<P> codec) {
		this.codec = codec;
	}
	
	public Codec<P> codec() {
		return this.codec;
	}
}
