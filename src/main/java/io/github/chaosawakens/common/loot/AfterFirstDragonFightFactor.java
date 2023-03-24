package io.github.chaosawakens.common.loot;

import java.util.Objects;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import io.github.chaosawakens.ChaosAwakens;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.LootParameter;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.JSONUtils;

public class AfterFirstDragonFightFactor extends LootFunction {
	private final float factor;
	
	public AfterFirstDragonFightFactor(ILootCondition[] pConditions, float factor) {
		super(pConditions);
		this.factor = factor;
	}
	
	@Override
	public LootFunctionType getType() {
		return CATreasure.DRAGON_FIGHT_FACTOR;
	}
	
	public Set<LootParameter<?>> getReferencedContextParams() {
		return ImmutableSet.of(LootParameters.THIS_ENTITY);
	}
	
	public static AfterFirstDragonFightFactor.Builder fightFactor(float factor) {
		return new AfterFirstDragonFightFactor.Builder(factor);
	}
	
	@Override
	protected ItemStack run(ItemStack pStack, LootContext pContext) {
		if(pContext.getParamOrNull(LootParameters.THIS_ENTITY) instanceof EnderDragonEntity) {
			EnderDragonEntity dragon = (EnderDragonEntity) pContext.getParamOrNull(LootParameters.THIS_ENTITY);
			boolean hasFought = Objects.requireNonNull(dragon.getDragonFight()).hasPreviouslyKilledDragon();
			ChaosAwakens.LOGGER.debug(getFactor());
			if(hasFought)pStack.setCount(Math.round(pStack.getCount() * getFactor()));
		}
		return pStack;
	}
	
	public float getFactor() {
		return factor;
	}
	
	public static class Builder extends LootFunction.Builder<AfterFirstDragonFightFactor.Builder> {

		private final float factor;

		public Builder(float factor) {
			this.factor = factor;
		}

		@Override
		public ILootFunction build() {
			return new AfterFirstDragonFightFactor(this.getConditions(), factor);
		}

		@Override
		protected Builder getThis() {
			return this;
		}
		
	}
	
	public static class Serializer extends LootFunction.Serializer<AfterFirstDragonFightFactor> {
		
		@Override
		public void serialize(JsonObject pJson, AfterFirstDragonFightFactor pValue,
				JsonSerializationContext pSerializationContext) {
			super.serialize(pJson, pValue, pSerializationContext);
			pJson.add("factor", pSerializationContext.serialize(pValue.getFactor()));
		}
		
		@Override
		public AfterFirstDragonFightFactor deserialize(JsonObject pObject,
				JsonDeserializationContext pDeserializationContext, ILootCondition[] pConditions) {
			float factor = JSONUtils.getAsFloat(pObject, "factor");
			return new AfterFirstDragonFightFactor(pConditions, factor);
		}
		
	}
}