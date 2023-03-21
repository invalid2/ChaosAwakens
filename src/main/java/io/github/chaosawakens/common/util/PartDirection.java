package io.github.chaosawakens.common.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;

public enum PartDirection {
	NONE(new Vector3i(0, 0, 0)),
	NORTH(new Vector3i(0, 0, -1)),
	NORTH_EAST(new Vector3i(1, 0, -1)),
	EAST(new Vector3i(1, 0, 0)),
	SOUTH_EAST(new Vector3i(1, 0, 1)),
	SOUTH(new Vector3i(0, 0, 1)),
	SOUTH_WEST(new Vector3i(-1, 0, 1)),
	WEST(new Vector3i(-1, 0, 0)),
	NORTH_WEST(new Vector3i(-1, 0, -1));
	
	private final Vector3i vector;
	
	private static final PartDirection[] VALUES = values();
	private static final Long2ObjectMap<PartDirection> BY_VECTOR = Arrays.stream(VALUES).collect(
			Collectors.toMap((direction) -> (new BlockPos(direction.getVector())).asLong(), (direction) -> direction,
					(direction1, direction2) -> { throw new IllegalArgumentException("Duplicate keys");
	}, Long2ObjectOpenHashMap::new));
	
	PartDirection(Vector3i vector) {
		this.vector = vector;
	}

	public Vector3i getVector() {
		return vector;
	}
	
	public static PartDirection byVector(int x, int y, int z) {
		x = x == 0 ? x : x/Math.abs(x);
		y = y == 0 ? y : y/Math.abs(y);
		z = z == 0 ? z : z/Math.abs(z);
		return BY_VECTOR.get(BlockPos.asLong(x, y, z));
	}
}
