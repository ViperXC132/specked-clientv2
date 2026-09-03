package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.input.Input;

public class ToggleSneakFeature extends Feature {
    private boolean sneakToggled = false;

    public ToggleSneakFeature() {
        super("Toggle Sneak", "Hold sneak automatically", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        sneakToggled = false;
    }

    @Override
    public void onTick() {
        if (mc == null || mc.player == null) return;
        
        if (mc.options.sneakKey.wasPressed()) {
            sneakToggled = !sneakToggled;
        }
        
        if (sneakToggled) {
            mc.options.sneakKey.setPressed(true);
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
