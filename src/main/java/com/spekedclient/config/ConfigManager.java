package com.spekedclient.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.spekedclient.SpekedClient;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Path configDir;
    private final Path mainConfig;

    public ConfigManager() {
        this.configDir = Paths.get(System.getProperty("user.home"), ".spekedclient");
        this.mainConfig = configDir.resolve("config.json");
        
        try {
            Files.createDirectories(configDir);
        } catch (Exception e) {
            SpekedClient.LOGGER.warn("Failed to create config directory", e);
        }
    }

    public void loadAll() {
        try {
            if (Files.exists(mainConfig)) {
                try (FileReader reader = new FileReader(mainConfig.toFile())) {
                    JsonObject json = GSON.fromJson(reader, JsonObject.class);
                    SpekedClient.LOGGER.info("Loaded main configuration");
                }
            }
        } catch (Exception e) {
            SpekedClient.LOGGER.warn("Failed to load config", e);
        }
    }

    public void saveAll() {
        try {
            JsonObject root = new JsonObject();
            Files.createDirectories(configDir);
            try (FileWriter writer = new FileWriter(mainConfig.toFile())) {
                GSON.toJson(root, writer);
            }
            SpekedClient.LOGGER.info("Saved configuration");
        } catch (Exception e) {
            SpekedClient.LOGGER.warn("Failed to save config", e);
        }
    }

    public Path getConfigDir() {
        return configDir;
    }

    public Path getFile(String filename) {
        return configDir.resolve(filename);
    }
}
