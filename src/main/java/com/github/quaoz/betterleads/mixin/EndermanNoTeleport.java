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
public class EndermanNoTeleport extends HostileEntity {
	public EndermanNoTeleport(EntityType<? extends EndermanEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "teleportRandomly", at = @At("HEAD"), cancellable = true)
	private void teleportRandomly(CallbackInfoReturnable<Boolean> cir) {
		if (this.isLeashed()) {
			cir.setReturnValue(false);
		}
	}
}
