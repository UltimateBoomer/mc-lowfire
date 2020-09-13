package com.ultimateboomer.ublowfire;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class UBLowFireMod implements ModInitializer, ClientModInitializer {
	public static final String MODID = "ublowfire";
	
	public static UBLowFireConfig config;
	
	@Override
	public void onInitialize() {
		config = AutoConfig.register(UBLowFireConfig.class, GsonConfigSerializer::new).getConfig();
	}
	
	@Override
	public void onInitializeClient() {
		AutoConfig.getGuiRegistry(UBLowFireConfig.class);
	}
}
