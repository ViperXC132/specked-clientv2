package com.spekedclient.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class InputHandler {
    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(Identifier.of("spekedclient", "keybinds"));
    private static KeyBinding clickguiKey;
    private static KeyBinding huiEditorKey;
    private static KeyBinding zoomKey;

    public static void init() {
        clickguiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.spekedclient.clickgui", GLFW.GLFW_KEY_RIGHT_SHIFT, CATEGORY));
        huiEditorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.spekedclient.huieditor", GLFW.GLFW_KEY_H, CATEGORY));
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.spekedclient.zoom", GLFW.GLFW_KEY_C, CATEGORY));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (clickguiKey.wasPressed()) handleClickGUI();
            if (huiEditorKey.wasPressed()) handleHUDEditor();
            if (zoomKey.isPressed()) handleZoom();
        });
    }
    private static void handleClickGUI() {}
    private static void handleHUDEditor() {}
    private static void handleZoom() {}
}
