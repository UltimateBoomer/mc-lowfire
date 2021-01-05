package io.github.ultimateboomer.lowfire.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.ultimateboomer.lowfire.LowFire;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
	@Inject(at = @At("HEAD"), method = "renderFireOverlay", cancellable = true)
	private static void renderFireOverlay(CallbackInfo info) {		
		RenderSystem.translated(0.0, -LowFire.config.fireOffset, 0.0);
	}
}
