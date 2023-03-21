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

public class SimpleCuboidTrunk implements ITreePart {
	public static final Codec<SimpleCuboidTrunk> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("height").forGetter((part) -> part.baseHeight),
			Codec.intRange(1, 4).fieldOf("width").forGetter((part) -> part.width)
		).apply(instance, SimpleCuboidTrunk::new));
	
	private final FeatureSpread baseHeight;
	private final int width;
	
	public SimpleCuboidTrunk(FeatureSpread baseHeight, int width) {
		this.baseHeight = baseHeight;
		this.width = width;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.SIMPLE_CUBOID_TRUNK.get();
	}
	
	public int setBaseHeight(Random rand) {
		return this.baseHeight.sample(rand);
	}
	
	@Override
	public List<ITreePart.Start> place(ISeedReader reader, Random rand, BlockPos.Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		List<ITreePart.Start> list = Lists.newArrayList();
		int offsetStart = this.width > 2 ? (this.width - 1) / 2 : 0, offsetwidth = this.width - offsetStart,
				height = this.setBaseHeight(rand);
		for(int i = 0; i < height; i++)
			for (int j = -offsetStart; j < offsetwidth; j++)
				for (int k = -offsetStart; k < offsetwidth; k++) {
					ITreePart.setLog(reader, rand, startPos.offset(j, i, k), config);
					if(i == 0)list.add(new ITreePart.Start(startPos.offset(j, height, k), PartDirection.NONE));
				}
		return list;
	}

}
