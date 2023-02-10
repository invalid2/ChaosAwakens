package io.github.chaosawakens.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThornySunBlock extends DoubleDensePlantBlock {

	public ThornySunBlock(Properties properties) {
		super(properties);
	}
	
	//TODO Change to custom source
	public void entityInside(BlockState pState, World pLevel, BlockPos pPos, Entity pEntity) {
		pEntity.hurt(DamageSource.CACTUS, 1.0f);
	}
}
