package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.List;
import java.util.Random;

import com.mojang.serialization.Codec;

import io.github.chaosawakens.common.util.PartDirection;
import io.github.chaosawakens.common.util.PartGenerationStage;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;

public interface ITreePart {
	public static final Codec<ITreePart> CODEC = TreePartType.CODEC.dispatch(ITreePart::type, TreePartType::codec);

	abstract TreePartType<? extends ITreePart> type();
	
	default MutableBoundingBox calculateBoundingBox(Random rand, Start partStart) {
		return null;
	}
	
	abstract boolean canPlace(ITreePart.Start start);
	
	abstract List<ITreePart.Start> place(ISeedReader reader, Random rand, BlockPos.Mutable startPos,
			PartDirection direction, MultiPartTreeFeatureConfig config);
	
	abstract PartGenerationStage partGenerationStage();
	
	static void setLog(ISeedReader reader, Random rand, BlockPos pos, MultiPartTreeFeatureConfig config) {
		if(isFreeForLogs(reader, pos))
			reader.setBlock(pos, config.logState, 2);
	}
	
	static void setLeaves(ISeedReader reader, Random rand, BlockPos pos, MultiPartTreeFeatureConfig config) {
		if(isFreeForLeaves(reader, pos))
			reader.setBlock(pos, config.leafState.setValue(LeavesBlock.DISTANCE, 1), 2);
	}
	
	static boolean isFreeForLogs(ISeedReader reader, BlockPos pos) {
		BlockState state = reader.getBlockState(pos);
		return !state.is(BlockTags.LOGS) || state.getMaterial().isReplaceable();
	}
	
	@SuppressWarnings("deprecation")
	static boolean isFreeForLeaves(ISeedReader reader, BlockPos pos) {
		BlockState state = reader.getBlockState(pos);
		return state.isAir(reader, pos) || !state.is(BlockTags.LOGS) || state.getMaterial().isReplaceable();
	}
	
	public static final class Start {
		final BlockPos startPos;
		final PartDirection direction;
		final PartGenerationStage stage;
		
		public Start(BlockPos startPos, PartDirection direction, PartGenerationStage stage) {
			this.startPos = startPos;
			this.direction = direction;
			this.stage = stage;
		}
		
		public BlockPos getStartPos() {
			return startPos;
		}
		
		public PartDirection getDirection() {
			return direction;
		}
		
		public PartGenerationStage getStage() {
			return stage;
		}
	}
}