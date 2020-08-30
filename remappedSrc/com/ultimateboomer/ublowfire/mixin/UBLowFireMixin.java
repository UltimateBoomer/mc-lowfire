package com.ultimateboomer.ublowfire.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.render.item.HeldItemRenderer;

@Mixin(HeldItemRenderer.class)
public class UBLowFireMixin {
	@Inject(at = @At("HEAD"), method = "renderFireOverlay()V")
	private void init(CallbackInfo info) {
		GlStateManager.translated(0.0, -0.2, 0.0);
		
		//System.out.println("Mixin Test");
	}
}
