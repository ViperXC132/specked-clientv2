package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class AirStrafingFeature extends Feature {
    public AirStrafingFeature() {
        super("Air Strafing", "Enhanced air movement", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.player == null) return;
        if (!mc.player.isOnGround()) {
            double moveSpeed = 0.02;
            if (mc.options.rightKey.isPressed()) {
                mc.player.addVelocity(moveSpeed, 0, 0);
            }
            if (mc.options.leftKey.isPressed()) {
                mc.player.addVelocity(-moveSpeed, 0, 0);
            }
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
