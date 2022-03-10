package io.github.ultimateboomer.lowfire.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.ultimateboomer.lowfire.config.LowFireConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;

@Mixin(ScreenEffectRenderer.class)
public class InGameOverlayRendererMixin {
	@Inject(method = "renderFire",
			at =@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"),
			cancellable = true)
	private static void renderFireOverlay(Minecraft client, PoseStack matrices, CallbackInfo ci) {
		matrices.translate(0.0, -LowFireConfig.client.fireOffset.get(), 0.0);
	}
}
