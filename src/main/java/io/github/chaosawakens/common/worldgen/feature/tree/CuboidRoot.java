package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.chaosawakens.common.util.PartDirection;
import io.github.chaosawakens.common.util.PartGenerationStage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class CuboidRoot implements ITreePart {
	public static final Codec<CuboidRoot> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("height").forGetter((part) -> part.baseHeight),
			Codec.intRange(1, 4).fieldOf("width").forGetter((part) -> part.width)
		).apply(instance, CuboidRoot::new));
	
	private final FeatureSpread baseHeight;
	private final int width;
	private int height;
	
	public CuboidRoot(FeatureSpread baseHeight, int width) {
		this.baseHeight = baseHeight;
		this.width = width;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.CUBOID_ROOT.get();
	}
	
	public int getHeight(Random rand) {
		if(this.height == 0)this.height = this.baseHeight.sample(rand);
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public MutableBoundingBox calculateBoundingBox(Random rand, Start currentStart) {
		int offsetStart = this.width > 2 ? (this.width - 1) / 2 : 0;
		return new MutableBoundingBox(currentStart.getStartPos(),
				currentStart.getStartPos().offset(this.width,  this.getHeight(rand), this.width))
				.moved(-offsetStart, 0, -offsetStart);
	}

	@Override
	public List<ITreePart.Start> place(ISeedReader reader, Random rand, BlockPos.Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		int offsetStart = this.width > 2 ? (this.width - 1) / 2 : 0, height = this.getHeight(rand);
		for(int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				for (int k = 0; k < width; k++)
					ITreePart.setLog(reader, rand, startPos.offset(j - offsetStart, i, k - offsetStart), config);
		return Lists.newArrayList(new ITreePart.Start(startPos.above(height), PartDirection.NONE, null));
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
