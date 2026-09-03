package com.spekedclient.input;

import java.util.HashMap;
import java.util.Map;

public class KeybindManager {
    private final Map<String, Integer> keybinds = new HashMap<>();

    public void init() {
        // Register default keybinds
        registerKeybind("gui.clickgui", 344); // Right Shift
        registerKeybind("gui.huieditor", 72); // H key
        registerKeybind("feature.zoom", 46); // C key
    }

    public void registerKeybind(String action, int keyCode) {
        keybinds.put(action, keyCode);
    }

    public int getKeybind(String action) {
        return keybinds.getOrDefault(action, -1);
    }

    public void setKeybind(String action, int keyCode) {
        keybinds.put(action, keyCode);
    }
}
