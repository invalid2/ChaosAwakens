package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.chaosawakens.common.util.PartDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class StraightBranch implements ITreePart {
	public static final Codec<StraightBranch> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("length").forGetter((part) -> part.length)).apply(instance, StraightBranch::new));
	
	private final FeatureSpread length;
	public StraightBranch(FeatureSpread length) {
		this.length = length;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.STRAIGHT_BRANCH.get();
	}

	@Override
	public List<ITreePart.Start> place(ISeedReader reader, Random rand, BlockPos.Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		int length = this.length.sample(rand);
		Vector3i dir = direction.getVector();
		if(direction != PartDirection.NONE)
			for(int i = 0; i < length; i++)
				ITreePart.setLog(reader, rand, startPos.offset(dir.getX()*i, 0, dir.getZ()*i), config);
		return Lists.newArrayList(new ITreePart.Start(startPos.offset(dir.getX(), 0, dir.getZ()), PartDirection.NONE));
	}

}
