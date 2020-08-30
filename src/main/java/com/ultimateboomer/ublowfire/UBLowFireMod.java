package com.ultimateboomer.ublowfire;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigHolder;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class UBLowFireMod implements ModInitializer, ClientModInitializer {
	public static final String MODID = "ublowfire";
	
	public static ConfigHolder<UBLowFireConfig> configHolder;
	
	@Override
	public void onInitialize() {
		configHolder = AutoConfig.register(UBLowFireConfig.class, GsonConfigSerializer::new);
	}
	
	@Override
	public void onInitializeClient() {
		AutoConfig.getGuiRegistry(UBLowFireConfig.class);
	}
}
