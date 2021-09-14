package com.github.quaoz.betterleads.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(EndermanEntity.class)
abstract class EndermanTeleportMixin extends HostileEntity {
	public EndermanTeleportMixin(EntityType<? extends EndermanEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "teleportRandomly", at = @At("RETURN"), cancellable = true)
	private void teleportRandomly(CallbackInfoReturnable<Boolean> cir) {
		if (this.isLeashed()) {
			cir.setReturnValue(false);
		}
	}
}
