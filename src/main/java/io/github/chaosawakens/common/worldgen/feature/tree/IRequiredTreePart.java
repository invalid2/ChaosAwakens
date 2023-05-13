package io.github.chaosawakens.common.worldgen.feature.tree;

import java.util.Random;

import net.minecraft.util.math.MutableBoundingBox;

public interface IRequiredTreePart extends ITreePart {
	
	abstract TreePartType<? extends IRequiredTreePart> type();
	
	abstract MutableBoundingBox calculateBoundingBox(Random rand, Start partStart);
}