package io.github.ultimateboomer.lowfire;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.minecraft.util.Util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LowFireConfigHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final String configPath;

    public LowFireConfigHandler(String configPath) {
        this.configPath = configPath;
    }

    public LowFireConfig readConfig() {
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

                LowFire.LOGGER.info("New config file created");
            } catch (IOException e2) {
                throw new IllegalStateException(e2);
            }
        }
        return config;
    }

    public void writeConfig(LowFireConfig config) {
        try (FileWriter writer = new FileWriter(configPath)) {
            GSON.toJson(config, writer);

            LowFire.LOGGER.debug("Written to config");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void openConfigFile() {
        Util.getOperatingSystem().open(new File(configPath));
    }
}
