package com.ultimateboomer.ublowfire;

import net.fabricmc.api.ModInitializer;

public class UBLowFireMod implements ModInitializer {
	
	public static final String MODID = "ublowfire";
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println(MODID + " initialize method");
	}
}
