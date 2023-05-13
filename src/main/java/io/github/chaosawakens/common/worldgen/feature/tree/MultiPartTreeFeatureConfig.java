package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.ITag;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class MultiPartTreeFeatureConfig implements IFeatureConfig {
	public static final Codec<MultiPartTreeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			BlockState.CODEC.fieldOf("base_state").forGetter((config) -> config.baseState),
			BlockState.CODEC.fieldOf("log_state").forGetter((config) -> config.logState),
			BlockState.CODEC.fieldOf("leaves_state").forGetter((config) -> config.leafState),
			ITag.codec(() -> TagCollectionManager.getInstance().getBlocks()).fieldOf("target_surface")
				.forGetter((config) -> config.targetSurface),
			ITreePart.CODEC.listOf().fieldOf("parts").forGetter((config) -> config.parts)
			).apply(instance, MultiPartTreeFeatureConfig::new));

	public final BlockState baseState;
	public final BlockState logState;
	public final BlockState leafState;
	public final ITag<Block> targetSurface;
	public final List<ITreePart> parts;
	
    public MultiPartTreeFeatureConfig(BlockState baseState, BlockState logState, BlockState leafState, ITag<Block> target,
    		List<ITreePart> parts) {
		this.baseState = baseState;
    	this.logState = logState;
		this.leafState = leafState;
		this.targetSurface = target;
		this.parts = parts;
    }
    
    
}