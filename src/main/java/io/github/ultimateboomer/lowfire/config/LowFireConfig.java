package io.github.ultimateboomer.lowfire.config;

import io.github.ultimateboomer.lowfire.LowFire;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = LowFire.MOD_ID)
public class LowFireConfig implements ConfigData {
	public boolean enabled = true;
	public double fireOffset = 0.3;
}
