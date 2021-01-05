package io.github.ultimateboomer.lowfire.mixin;

import io.github.ultimateboomer.lowfire.LowFire;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.hud.InGameOverlayRenderer;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
	@Inject(at = @At("HEAD"), method = "renderFireOverlay", cancellable = true)
	private static void renderFireOverlay(CallbackInfo info) {		
		RenderSystem.translated(0.0, -LowFire.config.fireOffset, 0.0);
	}
}
