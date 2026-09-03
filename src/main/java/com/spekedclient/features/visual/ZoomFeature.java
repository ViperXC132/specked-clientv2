package com.spekedclient.features.visual;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class ZoomFeature extends Feature {
    private float zoomLevel = 3.0f;
    private float originalFov = 0;

    public ZoomFeature() {
        super("Zoom", "Zoom in with keybind", FeatureCategory.VISUAL);
    }

    @Override
    public void onEnable() {
        if (mc != null) {
            originalFov = mc.options.getFov().getValue().floatValue();
        }
    }

    @Override
    public void onDisable() {
        if (mc != null) {
            mc.options.getFov().setValue((double) originalFov);
        }
    }

    @Override
    public void onTick() {
        if (mc == null) return;
        if (mc.options.sprintKey.isPressed()) {
            mc.options.getFov().setValue((double) (originalFov / zoomLevel));
        } else {
            mc.options.getFov().setValue((double) originalFov);
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
