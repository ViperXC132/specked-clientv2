package com.spekedclient.settings;

import java.util.HashMap;
import java.util.Map;

public class SettingsManager {
    private final Map<String, Setting<?>> settings = new HashMap<>();

    public void load() {
        // Settings will be loaded per-module
    }

    public void save() {
        // Settings will be saved per-module
    }

    public <T> Setting<T> register(String key, T defaultValue) {
        Setting<T> setting = new Setting<>(key, defaultValue);
        settings.put(key, setting);
        return setting;
    }

    public Setting<?> get(String key) {
        return settings.get(key);
    }

    public static class Setting<T> {
        private final String key;
        private T value;
        private final T defaultValue;

        public Setting(String key, T defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.value = defaultValue;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void reset() {
            this.value = defaultValue;
        }

        public String getKey() {
            return key;
        }
    }
}
