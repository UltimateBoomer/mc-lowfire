package io.github.ultimateboomer.lowfire;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.minecraft.util.Util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static LowFireConfig readConfig() {
        String configPath = System.getProperty("user.dir") + "/config/" + LowFire.MOD_ID + ".json";

        // Read config
        LowFireConfig config;
        try (FileReader reader = new FileReader(configPath)) {
            config = GSON.fromJson(reader, LowFireConfig.class);
            if (config == null) {
                throw new NullPointerException();
            }
            config.validate();
            try (FileWriter writer = new FileWriter(configPath)) {
                GSON.toJson(config, writer);
            }

            LowFire.LOGGER.debug("Config: " + config);
        } catch (NullPointerException | JsonParseException | IOException e) {
            // Create new config
            config = new LowFireConfig();
            try (FileWriter writer = new FileWriter(configPath)) {
                GSON.toJson(config, writer);
                LowFire.LOGGER.debug("New config file created");
            } catch (IOException e2) {
                throw new IllegalStateException(e2);
            }
        }
        return config;
    }

    public static void openConfigFile() {
        String configPath = System.getProperty("user.dir") + "/config/" + LowFire.MOD_ID + ".json";

        Util.getOperatingSystem().open(new File(configPath));
    }
}
