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
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class ConiferBranchFoliage implements ITreePart {
	public static final Codec<ConiferBranchFoliage> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("length").forGetter((part) -> part.baseLength),
			Codec.intRange(0, 1).fieldOf("width").forGetter((part) -> part.width)
			).apply(instance, ConiferBranchFoliage::new));
	
	private final FeatureSpread baseLength;
	private final int width;
	public ConiferBranchFoliage(FeatureSpread baseLength, int width) {
		this.baseLength = baseLength;
		this.width = width;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.CONIFER_BRANCH_FOLIAGE.get();
	}
	
	public int getLength(Random rand) {
		return baseLength.sample(rand);
	}
	
	@Override
	public List<Start> place(ISeedReader reader, Random rand, Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		Vector3i dir = direction.getVector();
		int length = this.getLength(rand), halfr = (width-2) / 2, half = (length) / 2;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width - 2; j++) {
				int offx = i * dir.getX() + j * dir.getZ() - halfr * dir.getZ(),
						offz = i * dir.getZ() + j * dir.getX() - halfr * dir.getX();
				if(i < length - 1) {
					ITreePart.setLeaves(reader, rand, startPos.offset(offx, i < half ? 0 : -1, offz), config);
					if(j == halfr)
						ITreePart.setLeaves(reader, rand, startPos.offset(offx, i < half ? 1 : 0, offz), config);
				} else {
					if(j == halfr)ITreePart.setLeaves(reader, rand, startPos.offset(offx, -1, offz), config);
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
