package io.github.ultimateboomer.lowfire;

import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.platform.InputConstants;

import io.github.ultimateboomer.lowfire.config.LowFireConfig;
import io.github.ultimateboomer.lowfire.screen.LowFireMenuScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LowFire.MOD_ID)
public class LowFire {
	public static final String MOD_ID = "lowfire";
	public static final String MOD_NAME = "Low Fire";

	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static LowFire INSTANCE;

	public LowFire() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInitializeClient);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LowFireConfig.spec);
		MinecraftForge.EVENT_BUS.register(this);
		INSTANCE = this;
	}

	private static final DecimalFormat df = new DecimalFormat("0.0");

	private KeyMapping lowFireMenuKey;
	private KeyMapping nextFireOffsetKey;

	private void onInitializeClient(final FMLClientSetupEvent event) {
		lowFireMenuKey = new KeyMapping("key.lowfire.openMenu", InputConstants.UNKNOWN.getValue(), "key.categories.lowfire");

		nextFireOffsetKey = new KeyMapping("key.lowfire.nextFireOffset", InputConstants.UNKNOWN.getValue(), "key.categories.lowfire");
		event.enqueueWork(() -> {
			ClientRegistry.registerKeyBinding(lowFireMenuKey);
			ClientRegistry.registerKeyBinding(nextFireOffsetKey);
		});
	}

	public KeyMapping getLowFireMenuKey() {
		return lowFireMenuKey;
	}

	public KeyMapping getNextFireOffsetKey() {
		return nextFireOffsetKey;
	}

	@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
	public class Events {
		@SubscribeEvent
		public static void clientTick(final ClientTickEvent event) {
			if (LowFire.INSTANCE.lowFireMenuKey.consumeClick()) {
				Minecraft.getInstance().setScreen(new LowFireMenuScreen(Minecraft.getInstance().screen));
			}
			if (LowFire.INSTANCE.nextFireOffsetKey.consumeClick()) {
				if (LowFireConfig.client.fireOffset.get() >= 0.5) {
					LowFireConfig.client.fireOffset.set(0d);
				} else {
					LowFireConfig.client.fireOffset.set(Math.floor(LowFireConfig.client.fireOffset.get() * 10.0) / 10.0 + 0.1);
				}

				Minecraft.getInstance().player.displayClientMessage(new TranslatableComponent("lowfire.nextFireOffset", df.format(LowFireConfig.client.fireOffset.get())), true);
			}
		}
	}
}
