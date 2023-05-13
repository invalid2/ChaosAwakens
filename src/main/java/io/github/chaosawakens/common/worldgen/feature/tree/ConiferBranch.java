package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.chaosawakens.common.util.PartDirection;
import io.github.chaosawakens.common.util.PartGenerationStage;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class ConiferBranch implements ITreePart {
	public static final Codec<ConiferBranch> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("length").forGetter((part) -> part.length),
			Codec.intRange(1,6).fieldOf("width").forGetter((part) -> part.width)
			).apply(instance, ConiferBranch::new));
	
	private final FeatureSpread length;
	private final int width;
	
	public ConiferBranch(FeatureSpread length, int width) {
		this.length = length;
		this.width = width;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.CONIFER_BRANCH.get();
	}
	
	private int getLength(Random rand) {
		return this.length.sample(rand);
	}
	
	@Override
	public List<Start> place(ISeedReader reader, Random rand, Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		Vector3i dir = direction.getVector();
		if(direction != PartDirection.NONE) {
			int length = this.getLength(rand), half = width / 2, offy = rand.nextInt(4) - 3;
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {
					int offx = i * dir.getX() + j * dir.getZ() - half * dir.getZ(),
							offz = i * dir.getZ() + j * dir.getX() - half * dir.getX();
					if(i < length / 2) {
						ITreePart.setLog(reader, rand, startPos.offset(offx, offy, offz), config);
					} else {
						if(j == half)ITreePart.setLog(reader, rand, startPos.offset(offx, offy, offz), config);
					}
				}
			}
			return Lists.newArrayList(new ITreePart.Start(startPos.offset(dir.getX(), offy + 1, dir.getZ()), direction, null));
		} else {
			return Lists.newArrayList(new ITreePart.Start(startPos.offset(dir.getX(), 0, dir.getZ()), direction, null));
		}
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