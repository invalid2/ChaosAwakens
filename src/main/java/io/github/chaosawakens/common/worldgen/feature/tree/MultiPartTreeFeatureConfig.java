package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class MultiPartTreeFeatureConfig implements IFeatureConfig {
	public static final Codec<MultiPartTreeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			BlockState.CODEC.fieldOf("base").forGetter((config) -> config.baseState),
			BlockState.CODEC.fieldOf("log").forGetter((config) -> config.logState),
			BlockState.CODEC.fieldOf("leaves").forGetter((config) -> config.leafState),
			RuleTest.CODEC.fieldOf("target_surface").forGetter((config) -> config.targetSurface),
			ITreePart.CODEC.listOf().fieldOf("parts").forGetter((config) -> config.parts))
			.apply(instance, MultiPartTreeFeatureConfig::new));

	public final BlockState baseState;
	public final BlockState logState;
	public final BlockState leafState;
	public final RuleTest targetSurface;
	public final List<ITreePart> parts;
	
    public MultiPartTreeFeatureConfig(BlockState baseState, BlockState logState, BlockState leafState, RuleTest target, List<ITreePart> parts) {
		this.baseState = baseState;
    	this.logState = logState;
		this.leafState = leafState;
		this.targetSurface = target;
		this.parts = parts;
    }
    
    
}