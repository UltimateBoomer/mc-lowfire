package io.github.ultimateboomer.lowfire.config;

import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class LowFireConfig {

	public static class Client {
		public DoubleValue fireOffset;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.push("Client");

			fireOffset = builder.comment("Changes the offset of the fire effect")
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
}
