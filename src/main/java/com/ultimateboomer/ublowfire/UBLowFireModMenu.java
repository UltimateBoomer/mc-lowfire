package com.ultimateboomer.ublowfire;

import java.util.function.Function;

import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class UBLowFireModMenu implements ModMenuApi {
	@Override
	public String getModId() {
		return UBLowFireMod.MODID;
	}
	
	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(UBLowFireConfig.class, parent).get();
	}
}
