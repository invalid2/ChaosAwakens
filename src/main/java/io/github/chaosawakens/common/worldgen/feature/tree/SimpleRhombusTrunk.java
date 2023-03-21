package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.chaosawakens.common.util.PartDirection;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class SimpleRhombusTrunk implements ITreePart {
	public static final Codec<SimpleRhombusTrunk> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("height").forGetter((part) -> part.baseHeight),
			Codec.intRange(1, 4).fieldOf("width").forGetter((part) -> part.width)
		).apply(instance, SimpleRhombusTrunk::new));
	
	private final FeatureSpread baseHeight;
	private final int width;
	
	public SimpleRhombusTrunk(FeatureSpread baseHeight, int width) {
		this.baseHeight = baseHeight;
		this.width = width;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.SIMPLE_RHOMBUS_TRUNK.get();
	}
	
	public int setBaseHeight(Random rand) {
		return this.baseHeight.sample(rand);
	}
	
	@Override
	public List<Start> place(ISeedReader reader, Random rand, Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		List<ITreePart.Start> list = Lists.newArrayList();
		int offsetStart = this.width > 2 ? (this.width - 1) / 2 : 0, height = this.setBaseHeight(rand);
		float offsetEven = width % 2 == 0 ? 0.5f : 0;
		for(int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				for (int k = 0; k < width; k++) {
					int jOffset = j - offsetStart, kOffset = k - offsetStart, widthOffset = width - offsetStart;
					double distance = Math.abs(jOffset - offsetEven) + Math.abs(kOffset - offsetEven);
					if(distance < widthOffset) {
						ITreePart.setLog(reader, rand, startPos.offset(jOffset, i, kOffset), config);
						if(i == height - 1) {
							list.add( new ITreePart.Start(startPos.offset(0, height, 0), PartDirection.NONE));
						}
					}
				}
		return list;
	}

}
