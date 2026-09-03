package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class ToggleSprintFeature extends Feature {
    private boolean sprintToggled = false;

    public ToggleSprintFeature() {
        super("Toggle Sprint", "Hold sprint automatically", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        sprintToggled = false;
    }

    @Override
    public void onTick() {
        if (mc == null || mc.player == null) return;
        
        if (mc.options.sprintKey.wasPressed()) {
            sprintToggled = !sprintToggled;
        }
        
        if (sprintToggled && mc.player.isOnGround()) {
            mc.player.setSprinting(true);
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
