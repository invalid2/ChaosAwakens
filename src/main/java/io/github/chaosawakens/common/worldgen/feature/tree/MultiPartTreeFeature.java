package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;

import io.github.chaosawakens.common.util.PartDirection;
import io.github.chaosawakens.common.util.PartGenerationStage;
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
		if(!reader.getBlockState(startPos.below()).is(config.targetSurface)) {
			return false;
		} else {
			Queue<ITreePart.Start> treePartStartQueue = new PriorityQueue<>((a, b) -> a.getStage().compareTo(b.getStage()));
			treePartStartQueue.add(new ITreePart.Start(startPos, PartDirection.NONE, PartGenerationStage.ROOT));
			
			PartGenerationStage currentStage = PartGenerationStage.ROOT;
			List<ITreePart> currentStageParts = config.parts.stream()
					.filter(part -> !part.partGenerationStage().equals(currentStage)).collect(Collectors.toList());
			while(!treePartStartQueue.isEmpty()) {
				//if(currentStage.equals(currentStageParts))
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