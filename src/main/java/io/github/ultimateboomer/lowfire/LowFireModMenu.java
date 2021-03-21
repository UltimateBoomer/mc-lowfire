package io.github.ultimateboomer.lowfire;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class LowFireModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return screen -> new Screen(Text.of("")) {
			@Override
			protected void init() {
				ConfigHandler.openConfigFile();
				this.client.openScreen(screen);
			}
		};
	}
}
