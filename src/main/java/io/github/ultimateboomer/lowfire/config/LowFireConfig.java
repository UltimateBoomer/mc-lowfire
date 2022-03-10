package io.github.ultimateboomer.lowfire.config;

import java.util.List;

import io.github.ultimateboomer.lowfire.LowFire;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = LowFire.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class LowFireConfig {

	public static class Client {
		public DoubleValue fireOffset;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.push("Client");

			fireOffset = builder.comment("Changes the offset of the fire effect. Positive values move it down, negative move up.")
								.translation("lowfire.")
								.defineInRange(List.of("FireOffset"), 0.3, 0.0, 0.5);
		}
	}

	public static final ForgeConfigSpec spec;
	public static final Client client;
	static {
		var pair = new ForgeConfigSpec.Builder().configure(Client::new);
		spec = pair.getRight();
		client = pair.getLeft();
	}

	public static void reload(final ModConfigEvent.Reloading event) {
		if (event.getConfig().getSpec() == spec) {
			client.fireOffset.set(event.getConfig().getConfigData().getOrElse("FireOffset", 0.3));
			System.out.println("RELOADING");
		}
	}
}
