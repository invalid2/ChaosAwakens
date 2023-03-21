package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.chaosawakens.ChaosAwakens;
import io.github.chaosawakens.common.util.PartDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.FeatureSpread;

public class ConeFoliage implements ITreePart {
	public static final Codec<ConeFoliage> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			FeatureSpread.CODEC.fieldOf("length").forGetter((part) -> part.length),
			FeatureSpread.CODEC.fieldOf("height").forGetter((part) -> part.height),
			FeatureSpread.CODEC.fieldOf("radius").forGetter((part) -> part.radius)
			).apply(instance, ConeFoliage::new));
	
	private final FeatureSpread length;
	private final FeatureSpread height;
	private final FeatureSpread radius;
	
	public ConeFoliage(FeatureSpread length, FeatureSpread height, FeatureSpread radius) {
		this.length = length;
		this.height = height;
		this.radius = radius;
	}

	@Override
	public TreePartType<? extends ITreePart> type() {
		return CATreeParts.CONE_FOLIAGE.get();
	}
	
	public int getLength(Random rand) {
		return length.sample(rand);
	}
	
	public int getHeight(Random rand) {
		return height.sample(rand);
	}
	
	public int getRadius(Random rand) {
		return radius.sample(rand);
	}
	
	@Override
	public List<ITreePart.Start> place(ISeedReader reader, Random rand, BlockPos.Mutable startPos, PartDirection direction,
			MultiPartTreeFeatureConfig config) {
		Vector3i dir = direction.getVector();
		int radius = this.getRadius(rand);
		if(direction != PartDirection.NONE) {
			int length = this.getLength(rand), halfr = (radius-2) / 2, half = (length) / 2;
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < radius - 2; j++) {
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
		} else {
			int height = this.getHeight(rand), half = radius/2;
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
	}

}
