package com.spekedclient.event;

import com.spekedclient.SpekedClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class InputHandler {
    private static KeyBinding clickguiKey;
    private static KeyBinding huiEditorKey;
    private static KeyBinding zoomKey;

    public static void init() {
        clickguiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.spekedclient.clickgui",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "Speked Client"
        ));

        huiEditorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.spekedclient.huieditor",
            GLFW.GLFW_KEY_H,
            "Speked Client"
        ));

        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.spekedclient.zoom",
            GLFW.GLFW_KEY_C,
            "Speked Client"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (clickguiKey.wasPressed()) {
                handleClickGUI();
            }
            if (huiEditorKey.wasPressed()) {
                handleHUDEditor();
            }
            if (zoomKey.isPressed()) {
                handleZoom();
            }
        });
    }

    private static void handleClickGUI() {
        // TODO: Open ClickGUI screen
    }

    private static void handleHUDEditor() {
        // TODO: Open HUD editor screen
    }

    private static void handleZoom() {
        // TODO: Handle zoom feature
    }
}
