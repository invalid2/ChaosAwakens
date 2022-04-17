package io.github.chaosawakens.common.entity;

import javax.annotation.Nullable;

import io.github.chaosawakens.common.registry.CAEntityTypes;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GazelleEntity extends AnimatableAnimalEntity implements IAnimatable{
	private AnimationFactory factory = new AnimationFactory(this);
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT);
	public static final DataParameter<Integer> DATA_TYPE_ID = EntityDataManager.defineId(GazelleEntity.class, DataSerializers.INT);

	public GazelleEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.noCulling = true;
	}
	
	
	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		 return MobEntity.createLivingAttributes()
				 .add(Attributes.MAX_HEALTH, 12)
				 .add(Attributes.MOVEMENT_SPEED, 0.25D)
				 .add(Attributes.FOLLOW_RANGE, 14);
	}

	 private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		 if (event.isMoving()) { 
			 event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gazelle.walking", true));
			 return PlayState.CONTINUE;
		 }
		 
		 if (!event.isMoving()) {
			 event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gazelle.idle", true)); 
			 return PlayState.CONTINUE;  
		 }  
		 
		 if (this.isSprinting()) {
			 event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gazelle.running"));
		 }
		 
		 return PlayState.CONTINUE;
	 }

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "gazellecontroller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

/*	@Override
	public AgeableEntity getBreedOffspring(ServerWorld s, AgeableEntity e) {
		return CAEntityTypes.GAZELLE.get().create(s);
	}*/
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.0D));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 1.0F));
		this.goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0D, 1));
		this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, RoboEntity.class, 10, 0.0D, 2.0D));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.2D, false, FOOD_ITEMS));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
	}
	
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("GazelleType", this.getGazelleType());
    }

    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setGazelleType(nbt.getInt("GazelleType"));
    }
	
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }
    
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE_ID, 0);
    }

    public int getGazelleType() {
        return MathHelper.clamp(this.entityData.get(DATA_TYPE_ID), 0, 5);
    }

    public void setGazelleType(int type) {
        this.entityData.set(DATA_TYPE_ID, type);
    }
    
    @Override
    public GazelleEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
        GazelleEntity entity = CAEntityTypes.GAZELLE.get().create(world);

        assert entity != null;
        entity.setGazelleType(((GazelleEntity) mate).getGazelleType());

        return entity;
    }
    
    static class GazelleData extends AgeableEntity.AgeableData {
        public final int gazelletype;
        private GazelleData(int gazelletype) {
            super(true);
            this.gazelletype = gazelletype;
        }
    }
    
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        int i = this.getRandomGazelleType(world);
        if (entityData instanceof GazelleData) {
            i = ((GazelleData)entityData).gazelletype;
        } else {
            entityData = new GazelleData(i);
        }
        this.setGazelleType(i);
        return super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
    }
    
    private int getRandomGazelleType(IWorld world) {
        @SuppressWarnings("unused")
		Biome biome = world.getBiome(this.blockPosition());
        int i = this.random.nextInt(5);
        return i;
    }
    
    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, 0.6F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }
    
    @Override
    public float getStandingEyeHeight(Pose pose, EntitySize size) {
    	return this.isBaby() ? size.height * 0.75F : 1.1F;
    }
	
}