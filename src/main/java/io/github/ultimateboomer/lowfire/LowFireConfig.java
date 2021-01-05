package io.github.ultimateboomer.lowfire;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = LowFire.MODID)
public class LowFireConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip
	public double fireOffset = 0.3;
}
