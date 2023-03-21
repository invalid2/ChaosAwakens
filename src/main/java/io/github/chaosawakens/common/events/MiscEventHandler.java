package io.github.chaosawakens.common.events;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.Map;
import java.util.Map.Entry;

import com.mojang.brigadier.CommandDispatcher;

import io.github.chaosawakens.ChaosAwakens;
import io.github.chaosawakens.api.IUtilityHelper;
import io.github.chaosawakens.common.blocks.StrawberryBushBlock;
import io.github.chaosawakens.common.config.CACommonConfig;
import io.github.chaosawakens.common.enchantments.HoplologyEnchantment;
import io.github.chaosawakens.common.entity.robo.RoboPounderEntity;
import io.github.chaosawakens.common.entity.robo.RoboSniperEntity;
import io.github.chaosawakens.common.entity.robo.RoboWarriorEntity;
import io.github.chaosawakens.common.registry.CABlocks;
import io.github.chaosawakens.common.registry.CACommand;
import io.github.chaosawakens.common.registry.CADimensions;
import io.github.chaosawakens.common.registry.CAEffects;
import io.github.chaosawakens.common.registry.CAEnchantments;
import io.github.chaosawakens.common.registry.CAItems;
import io.github.chaosawakens.common.registry.CATags;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.GiantEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickEmpty;
import net.minecraftforge.event.entity.player.PlayerXpEvent.LevelChange;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BlockToolInteractEvent;
import net.minecraftforge.event.world.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;

public class MiscEventHandler {
	
	@SubscribeEvent
	public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
		CACommand.register(commandDispatcher);
	}

	@SubscribeEvent
	public static void livingDeathEvent(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		MinecraftServer server = entity.getServer();
		Random random = new Random();
		if (server == null) return;
		if (entity instanceof PlayerEntity) {
			// Make myself (Blackout03_) drop Ink Sacs any time I die. Even if I have none on me.
			if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("89cd9d1b-9d50-4502-8bd4-95b9e63ff589"))) { // UUID of Blackout03_
				((PlayerEntity) entity).drop(new ItemStack(Items.INK_SAC, random.nextInt(3)), true, false);
			}
			
			//Update Hoplology protection bonus on player death
			for(ItemStack armorStack : ((PlayerEntity) entity).getArmorSlots()) {
				Map<Enchantment, Integer> enchantMap = EnchantmentHelper.getEnchantments(armorStack);
				enchantMap.forEach((enchant, level) -> {
					if(enchant instanceof HoplologyEnchantment) {
						((HoplologyEnchantment) enchant).setProtection(0);
					}
				});
			}
		}
		if (CACommonConfig.COMMON.enableDragonEggRespawns.get()) {
			if (entity.getCommandSenderWorld().equals(server.getLevel(World.END))) {
				if (entity instanceof EnderDragonEntity) {
					EnderDragonEntity dragon = (EnderDragonEntity) entity;
					if (dragon.getDragonFight() != null && dragon.getDragonFight().hasPreviouslyKilledDragon()) {
						entity.getCommandSenderWorld().setBlockAndUpdate(entity.getCommandSenderWorld().getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.defaultBlockState());
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void onEnchant(EnchantmentLevelSetEvent event) {
		World world = event.getWorld();
		//I found that the best way to do this is to loop through each player
		for (PlayerEntity player : world.players()) {
			if (player == null) return;
			if (IUtilityHelper.isFullArmorSet(player, CAItems.LAPIS_HELMET.get(), CAItems.LAPIS_CHESTPLATE.get(), CAItems.LAPIS_LEGGINGS.get(), CAItems.LAPIS_BOOTS.get())) {
				ItemStack stack = event.getItem();
				int row = event.getEnchantRow();
				int power = event.getPower();
				int cost = EnchantmentHelper.getEnchantmentCost(world.random, row, power, stack);
				switch (row) {
				default:
					break;
				case 1:
					event.setLevel(event.getLevel() - (CACommonConfig.LAPISMODLIST.get(0) + 5));
					if (event.getLevel() <= 0) {
						event.setLevel(EnchantmentHelper.getEnchantmentCost(world.random, row, power, stack));
					}
					break;
				case 2:
					event.setLevel(event.getLevel() - (CACommonConfig.LAPISMODLIST.get(1) + 5));
					if (event.getLevel() <= 0) {
						event.setLevel(EnchantmentHelper.getEnchantmentCost(world.random, row, power, stack));
					}
					break;
				case 3:
					event.setLevel(event.getLevel() - (CACommonConfig.LAPISMODLIST.get(2) + 5));
					if (event.getLevel() <= 0) {
						event.setLevel(EnchantmentHelper.getEnchantmentCost(world.random, row, power, stack));
					}
					break;
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onMobXPDrop(LivingExperienceDropEvent event) {
		PlayerEntity player = event.getAttackingPlayer();
		if (player == null) return;
		int xpValue = event.getDroppedExperience();
		
		if (CACommonConfig.COMMON.enableExperienceArmorSetBonus.get()) {
			if (IUtilityHelper.isFullArmorSet(player, CAItems.EXPERIENCE_HELMET.get(), CAItems.EXPERIENCE_CHESTPLATE.get(), CAItems.EXPERIENCE_LEGGINGS.get(), CAItems.EXPERIENCE_BOOTS.get())) {
				event.setDroppedExperience(xpValue * CACommonConfig.COMMON.experienceArmorSetXPMultiplier.get());
			}
			
			if (IUtilityHelper.isFullArmorSet(player, CAItems.EXPERIENCE_HELMET.get(), CAItems.EXPERIENCE_CHESTPLATE.get(), CAItems.EXPERIENCE_LEGGINGS.get(), CAItems.EXPERIENCE_BOOTS.get()) && player.getMainHandItem().getItem().equals(CAItems.EXPERIENCE_SWORD.get())) {
				event.setDroppedExperience(xpValue * (CACommonConfig.COMMON.experienceArmorSetXPMultiplier.get() + CACommonConfig.COMMON.experienceSwordXPMultiplier.get()));
			}
		}
		
		if (CACommonConfig.COMMON.enableExperienceSwordBonus.get()) {
			if (player.getMainHandItem().getItem().equals(CAItems.EXPERIENCE_SWORD.get())) {
				event.setDroppedExperience(xpValue * CACommonConfig.COMMON.experienceSwordXPMultiplier.get());
			}
		}
	}
	
	@SubscribeEvent
	public static void onBlockBreakXP(BlockEvent.BreakEvent event) {
		PlayerEntity player = event.getPlayer();
		if (player == null) return;
		int xpValue = event.getExpToDrop();
		
		if (CACommonConfig.COMMON.enableExperienceArmorSetBonus.get()) {
			if (IUtilityHelper.isFullArmorSet(player, CAItems.EXPERIENCE_HELMET.get(), CAItems.EXPERIENCE_CHESTPLATE.get(), CAItems.EXPERIENCE_LEGGINGS.get(), CAItems.EXPERIENCE_BOOTS.get())) {
				event.setExpToDrop(xpValue * CACommonConfig.COMMON.experienceArmorSetXPMultiplier.get());
			}
		}
		
		if (player instanceof LivingEntity) {
			if (event.isCancelable() && ((LivingEntity) player).hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
		}
	}
	
	// Account for paralysis actually taking full effect
	@SubscribeEvent
	public static void onLivingJump(LivingJumpEvent event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onLivingAttack(AttackEntityEvent event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onLivingUse(LivingEntityUseItemEvent event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onLivingBlockPlace(EntityPlaceEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			if (event.isCancelable() && ((LivingEntity) event.getEntity()) instanceof PlayerEntity && ((LivingEntity) event.getEntity()).hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void onBucketFill(FillBucketEvent event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onPlayerItemPickup(ItemPickupEvent event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
		
		Map<Enchantment, Integer> enchantMap = EnchantmentHelper.getEnchantments(event.getStack());
		enchantMap.forEach((enchant, level) -> {
			if(enchant instanceof HoplologyEnchantment) {
				ChaosAwakens.LOGGER.debug(event.getPlayer().experienceLevel);
				((HoplologyEnchantment) enchant).setProtection(event.getPlayer().experienceLevel/10);
			}
		});
	}
	
	// Client side events to prevent the player/entity from swinging their hand or interacting while paralyzed
	@SubscribeEvent
	public static void onPlayerLeftClickInteractEmpty(LeftClickEmpty event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onPlayerRightClickInteractEmpty(RightClickEmpty event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onPlayerLeftClickInteractBlock(LeftClickBlock event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onPlayerRightClickInteractBlock(RightClickBlock event) {
		if (event.isCancelable() && event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().hasEffect(CAEffects.PARALYSIS_EFFECT.get())) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public static void onMobDrops(LivingDropsEvent event) {
		ItemStack stack;
		ItemEntity drop;

		// ENDER DRAGON
		if (event.getEntityLiving() instanceof EnderDragonEntity) {
			EnderDragonEntity dragon = (EnderDragonEntity) event.getEntityLiving();

			// Drop #1: Ender Dragon Scales
			int amount = 8 + (int) (Math.random() * 6) + (int) (Math.random() * event.getLootingLevel() * 4);
			if (Objects.requireNonNull(dragon.getDragonFight()).hasPreviouslyKilledDragon()) amount /= 2; // Amount is halved with repeat kills.
			stack = new ItemStack(CAItems.ENDER_DRAGON_SCALE.get(), amount);
			drop = new ItemEntity(event.getEntityLiving().level, 0, 90, 0, stack);
			event.getDrops().add(drop);

			// Drop #2: Ender Dragon Head
			double chance = 0.1D + event.getLootingLevel() * 0.1D;
			if (Math.random() < chance && CACommonConfig.COMMON.enderDragonHeadDrop.get()) {
				stack = new ItemStack(Items.DRAGON_HEAD, 1);
				drop = new ItemEntity(event.getEntityLiving().level, 0, 90, 0, stack);
				event.getDrops().add(drop);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getEntity();
		if (entity == null) return;
		if (CACommonConfig.COMMON.showUpdateMessage.get() && VersionChecker.getResult(ModList.get().getModContainerById(ChaosAwakens.MODID).get().getModInfo()).status == VersionChecker.Status.OUTDATED) {
			entity.sendMessage(new StringTextComponent("A new version of ").withStyle(TextFormatting.WHITE)
					.append(new StringTextComponent(ChaosAwakens.MODNAME).withStyle(TextFormatting.BOLD, TextFormatting.GOLD))
					.append(new StringTextComponent(" is now available from: ").withStyle(TextFormatting.WHITE))
					.append(new StringTextComponent("https://chaosawakens.github.io/downloads").withStyle((style) -> style.withColor(TextFormatting.GOLD).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://chaosawakens.github.io/downloads")))), Util.NIL_UUID);
		}
		if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("89cd9d1b-9d50-4502-8bd4-95b9e63ff589"))) { // UUID of Blackout03_
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Developer, ").withStyle(TextFormatting.GREEN)
					.append(new StringTextComponent("Blackout03_").withStyle(TextFormatting.BOLD, TextFormatting.DARK_GREEN))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.GREEN)), ChatType.SYSTEM, Util.NIL_UUID);
		} else if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("29aa413b-d714-46f1-a3f5-68b9c67a4923"))) { // UUID of Ninjaguy169
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Developer, ").withStyle(TextFormatting.BLUE)
					.append(new StringTextComponent("Ninjaguy169").withStyle(TextFormatting.BOLD, TextFormatting.DARK_BLUE))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.BLUE)), ChatType.SYSTEM, Util.NIL_UUID);
		} else if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("2668a475-2166-4539-9935-00f087818c4a"))) { // UUID of T40ne
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Owner, ").withStyle(TextFormatting.GOLD)
					.append(new StringTextComponent("T40ne").withStyle(TextFormatting.BOLD, TextFormatting.YELLOW))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.GOLD)), ChatType.SYSTEM, Util.NIL_UUID);
		} else if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("8c89a0d3-3271-459d-a8c1-a9d34d53365b"))) { // UUID of FunkyMonk127
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Owner, ").withStyle(TextFormatting.RED)
					.append(new StringTextComponent("FunkyMonk127").withStyle(TextFormatting.BOLD, TextFormatting.DARK_RED))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.RED)), ChatType.SYSTEM, Util.NIL_UUID);
		}
	}

	@SubscribeEvent
	public static void onEntityJoin(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		// Make villagers afraid of our entities
		if (entity instanceof VillagerEntity) {
			VillagerEntity villager = (VillagerEntity) entity;
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboPounderEntity.class, 24.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboSniperEntity.class, 24.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboWarriorEntity.class, 24.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboPounderEntity.class, 40.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, GiantEntity.class, 32.0F, 0.5D, 0.5D));
		}
		if (entity instanceof WanderingTraderEntity) {
			WanderingTraderEntity wanderingTrader = (WanderingTraderEntity) entity;
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboPounderEntity.class, 24.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboSniperEntity.class, 24.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboWarriorEntity.class, 24.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboPounderEntity.class, 40.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, GiantEntity.class, 32.0F, 0.5D, 0.5D));
		}
		if (entity instanceof GiantEntity) {
			GiantEntity giant = (GiantEntity) entity;
			giant.goalSelector.addGoal(8, new LookAtGoal(giant, PlayerEntity.class, 24.0F));
			giant.goalSelector.addGoal(8, new LookRandomlyGoal(giant));

			giant.goalSelector.addGoal(2, new MeleeAttackGoal(giant, 1.0F, false));
			giant.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(giant, 1.0F));
			giant.targetSelector.addGoal(2, new HurtByTargetGoal(giant));
			giant.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, PlayerEntity.class, true));
			giant.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, AbstractVillagerEntity.class, false));
			giant.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, IronGolemEntity.class, true));

			Objects.requireNonNull(giant.getAttribute(Attributes.FOLLOW_RANGE)).setBaseValue(100); // FOLLOW_RANGE
			Objects.requireNonNull(giant.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.15F); // MOVEMENT_SPEED
			Objects.requireNonNull(giant.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(20.0D); // ATTACK_DAMAGE
			Objects.requireNonNull(giant.getAttribute(Attributes.ARMOR)).setBaseValue(10.0D); // ARMOR
		}
	}

	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) {
		IWorld world = event.getWorld();
		if (world instanceof ServerWorld) {
			ServerWorld level = (ServerWorld) event.getWorld();
			if(level.dimension().location().getNamespace().equals("chaosawakens"))
				level.getServer().overworld().setDayTime(event.getNewTime());
		}
	}
	
	@SubscribeEvent
	public static void onUseHoeOnDense(BlockToolInteractEvent event) {
		if(event.getToolType() == ToolType.HOE)
			if(event.getState().is(CATags.Blocks.DENSE_DIRT))
				event.setFinalState(CABlocks.DENSE_FARMLAND.get().defaultBlockState());
			else if(event.getState().is(CATags.Blocks.TERRA_PRETA))
				event.setFinalState(CABlocks.TERRA_PRETA_FARMLAND.get().defaultBlockState());
	}
	
	@SubscribeEvent
	public static void onHoplologyArmorUpdate(LevelChange event) {
		PlayerEntity player = event.getPlayer();
		for(ItemStack armorStack : player.getArmorSlots()) {
			Map<Enchantment, Integer> enchantMap = EnchantmentHelper.getEnchantments(armorStack);
			enchantMap.forEach((enchant, level) -> {
				if(enchant instanceof HoplologyEnchantment) {
					((HoplologyEnchantment) enchant).setProtection((player.experienceLevel+1)/10);
				}
			});
		}
	}
}