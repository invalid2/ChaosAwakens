package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;

import io.github.chaosawakens.common.util.PartDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

public class MultiPartTreeFeature extends Feature<MultiPartTreeFeatureConfig> {

	public MultiPartTreeFeature(Codec<MultiPartTreeFeatureConfig> pCodec) {
		super(pCodec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator gen, Random rand, BlockPos pos, MultiPartTreeFeatureConfig config) {
		BlockPos startPos = new BlockPos(pos.getX(), reader.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).getY(), pos.getZ());
		if(!config.targetSurface.test(reader.getBlockState(startPos.below()), rand)) {
			return false;
		} else {
			//TODO Change how this is done to be more efficient
			List<ITreePart.Start> starts = Lists.newArrayList(new ITreePart.Start(startPos, PartDirection.NONE));
			for (ITreePart part : config.parts) {
				starts = placePart(starts, part, reader, gen, rand, config);
			}
			return true;
		}
	}
	
	private List<ITreePart.Start> placePart(List<ITreePart.Start> starts, ITreePart part,
			ISeedReader reader, ChunkGenerator gen, Random rand, MultiPartTreeFeatureConfig config) {
		List<ITreePart.Start> list = Lists.newArrayList();
		for(ITreePart.Start start : starts) {
			list.addAll(part.place(reader, rand, start.startPos.mutable(), start.direction, config));
		}
		return list;
	}
}