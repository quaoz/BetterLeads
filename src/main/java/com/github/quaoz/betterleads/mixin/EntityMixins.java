package com.github.quaoz.betterleads.mixin;

import com.github.quaoz.betterleads.BetterLeads;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Npc;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
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
	protected MerchantEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue((cir.getReturnValue() || !this.isLeashed()) && BetterLeads.get().config.getLeashableVillagers());
	}
}

// Allows water creatures (fish) to be leashed
@Mixin(WaterCreatureEntity.class)
abstract class WaterCreatureEntityMixin extends PathAwareEntity {
	protected WaterCreatureEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "canBeLeashedBy", at = @At("RETURN"), cancellable = true)
	private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue((cir.getReturnValue() || !this.isLeashed()) && BetterLeads.get().config.getLeashableWaterCreatures());
	}
}

// Allows hostile mobs to be leashed
// There is probably a better way to do this but this is the first mod i've ever made so yeah
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
