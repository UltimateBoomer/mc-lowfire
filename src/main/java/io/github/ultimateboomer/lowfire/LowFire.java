package io.github.ultimateboomer.lowfire;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class LowFire implements ClientModInitializer {
	public static final String MODID = "lowfire";
	
	public static LowFireConfig config;
	
	@Override
	public void onInitializeClient() {
		config = AutoConfig.register(LowFireConfig.class, GsonConfigSerializer::new).getConfig();
	}
}
