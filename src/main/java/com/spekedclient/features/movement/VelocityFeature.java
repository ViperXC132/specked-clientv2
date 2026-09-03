package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class VelocityFeature extends Feature {
    public VelocityFeature() {
        super("Velocity", "Display movement velocity", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc.player == null) return;
        double velocity = MathHelper.sqrt((float)(mc.player.getVelocity().x * mc.player.getVelocity().x + 
                                    mc.player.getVelocity().z * mc.player.getVelocity().z));
        String text = String.format("Vel: %.2f", velocity);
        context.drawText(mc.textRenderer, text, 10, 145, 0xFFFFFF, true);
    }
}
