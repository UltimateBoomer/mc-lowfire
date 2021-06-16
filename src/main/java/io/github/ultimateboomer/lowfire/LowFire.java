package io.github.ultimateboomer.lowfire;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;

public class LowFire implements ClientModInitializer {
	public static final String MOD_ID = "lowfire";
	public static final String MOD_NAME = "Low Fire";

	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static LowFire INSTANCE;

	public static LowFireConfigHandler configHandler;
	public static LowFireConfig config;

	private static final DecimalFormat df = new DecimalFormat("0.0");

	private KeyBinding lowFireMenuKey;
	private KeyBinding nextFireOffsetKey;

	@Override
	public void onInitializeClient() {
		INSTANCE = this;
		configHandler = new LowFireConfigHandler(System.getProperty("user.dir")
				+ "/config/" + LowFire.MOD_ID + ".json");
		config = configHandler.readConfig();

		lowFireMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.lowfire.openMenu",
				InputUtil.Type.KEYSYM,
				-1,
				"key.categories.lowfire"
		));

		nextFireOffsetKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.lowfire.nextFireOffset",
				InputUtil.Type.KEYSYM,
				-1,
				"key.categories.lowfire"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (lowFireMenuKey.wasPressed()) {
				client.openScreen(new LowFireMenuScreen(client.currentScreen));
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (nextFireOffsetKey.wasPressed()) {
				if (config.fireOffset >= 0.5) {
					config.fireOffset = 0.0;
				} else {
					config.fireOffset = Math.floor(config.fireOffset * 10.0) / 10.0 + 0.1;
				}

				configHandler.writeConfig(config);

				//noinspection ConstantConditions
				client.player.sendMessage(
						new TranslatableText("lowfire.nextFireOffset", df.format(config.fireOffset)),
						true
				);
			}
		});
	}

	public KeyBinding getLowFireMenuKey() {
		return lowFireMenuKey;
	}

	public KeyBinding getNextFireOffsetKey() {
		return nextFireOffsetKey;
	}
}
