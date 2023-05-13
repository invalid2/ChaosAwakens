package io.github.chaosawakens.common.worldgen.feature.tree.foliage;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.chaosawakens.common.util.PartDirection;
import io.github.chaosawakens.common.util.PartGenerationStage;
import io.github.chaosawakens.common.worldgen.feature.tree.CATreeParts;
import io.github.chaosawakens.common.worldgen.feature.tree.ITreePart;
import io.github.chaosawakens.common.worldgen.feature.tree.MultiPartTreeFeatureConfig;
import io.github.chaosawakens.common.worldgen.feature.tree.TreePartType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class ConeFoliage implements ITreePart {
	public static final Codec<ConeFoliage> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("height").forGetter((part) -> part.baseHeight),
			FeatureSpread.CODEC.fieldOf("radius").forGetter((part) -> part.baseRadius)
			).apply(instance, ConeFoliage::new));
	
	private final FeatureSpread baseHeight;
	private final FeatureSpread baseRadius;
	private int height;
	private int radius;
	
	public ConeFoliage(FeatureSpread height, FeatureSpread radius) {
		this.baseHeight = height;
		this.baseRadius = radius;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.CONE_FOLIAGE.get();
	}
	
	public int setHeight(Random rand) {
		this.height = this.baseHeight.sample(rand);
		return this.height;
	}
	
	public int setRadius(Random rand) {
		this.radius = this.baseRadius.sample(rand);
		return radius;
	}
	
	@Override
	public List<ITreePart.Start> place(ISeedReader reader, Random rand, BlockPos.Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		int radius = this.setRadius(rand), height = this.setHeight(rand), half = radius/2;
		for (int i = 0; i < radius; i++) {
			for (int j = 0; j < height; j++) {
				for (int k = 0; k < radius; k++) {
					double xoff = i - half, zoff = k - half, a = xoff*xoff, b = zoff*zoff;
					if(a + b + (height-j-1) < (radius - 1) * 2)
						ITreePart.setLeaves(reader, rand, startPos.offset(xoff, -j + 1, zoff), config);
				}
			}
		}
		return Lists.newArrayList();
	}

	@Override
	public boolean canPlace(Start start) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PartGenerationStage partGenerationStage() {
		// TODO Auto-generated method stub
		return null;
	}
}