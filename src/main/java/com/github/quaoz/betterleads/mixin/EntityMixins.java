package com.github.quaoz.betterleads.mixin;

import com.github.quaoz.betterleads.BetterLeads;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Npc;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.village.Merchant;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Allows trader entities (villagers and wandering traders) to be leashed
@Mixin(MerchantEntity.class)
abstract class MerchantEntityMixin extends PassiveEntity implements Npc, Merchant {
	protected MerchantEntityMixin(EntityType<? extends MerchantEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
			cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed()) && BetterLeads.get().config.getLeashableVillagers()));
	}
}

// Allows water creatures (fish) to be leashed
@Mixin(WaterCreatureEntity.class)
abstract class WaterCreatureEntityMixin extends PathAwareEntity {
	protected WaterCreatureEntityMixin(EntityType<? extends WaterCreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
			cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed()) && BetterLeads.get().config.getLeashableWaterCreatures()));
	}
}

// Allows turtles to be leashed
@Mixin(TurtleEntity.class)
abstract class TurtleEntityMixin extends AnimalEntity {
	protected TurtleEntityMixin(EntityType<? extends TurtleEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed()) && BetterLeads.get().config.getLeashableTurtles()));
	}
}

// Allows ambient entities (bats) to be leashed
@Mixin(AmbientEntity.class)
abstract class AmbientEntityMixin extends MobEntity {
	protected AmbientEntityMixin(EntityType<? extends AmbientEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed()) && BetterLeads.get().config.getLeashableAmbientMobs()));
	}
}

// Allows pandas to be leashed
@Mixin(PandaEntity.class)
abstract class PandaEntityMixin extends AnimalEntity {
	protected PandaEntityMixin(EntityType<? extends PandaEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed()) && BetterLeads.get().config.getLeashablePandas()));
	}
}

// Allows hostile mobs to be leashed
@Mixin(MobEntity.class)
abstract class MobEntityMixin extends LivingEntity {
	protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Shadow
	public abstract boolean isLeashed();

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed()) && BetterLeads.get().config.getLeashableHostileMobs()));
	}
}
