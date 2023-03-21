package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.chaosawakens.common.util.PartDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class CuboidRoot implements ITreePart {
	public static final Codec<CuboidRoot> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("height").forGetter((part) -> part.baseHeight),
			Codec.intRange(1, 4).fieldOf("width").forGetter((part) -> part.width)
		).apply(instance, CuboidRoot::new));
	
	private final FeatureSpread baseHeight;
	private final int width;
	
	public CuboidRoot(FeatureSpread baseHeight, int width) {
		this.baseHeight = baseHeight;
		this.width = width;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.CUBOID_ROOT.get();
	}
	
	public int setBaseHeight(Random rand) {
		return this.baseHeight.sample(rand);
	}
	
	@Override
	public int getSize() {
		return this.width;
	}

	@Override
	public List<ITreePart.Start> place(ISeedReader reader, Random rand, BlockPos.Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		int offsetStart = this.width > 2 ? (this.width - 1) / 2 : 0, height = this.setBaseHeight(rand);
		for(int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				for (int k = 0; k < width; k++)
					ITreePart.setLog(reader, rand, startPos.offset(j - offsetStart, i, k - offsetStart), config);
		return Lists.newArrayList(new ITreePart.Start(startPos.above(height), PartDirection.NONE));
	}

}
