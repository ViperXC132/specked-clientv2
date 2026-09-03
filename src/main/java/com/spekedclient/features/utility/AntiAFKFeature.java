package com.spekedclient.features.utility;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class AntiAFKFeature extends Feature {
    private long lastMove = System.currentTimeMillis();

    public AntiAFKFeature() {
        super("Anti AFK", "Prevent AFK kick", FeatureCategory.UTILITY);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.player == null) return;
        
        long now = System.currentTimeMillis();
        if (now - lastMove > 5000) { // Move every 5 seconds
            mc.player.move(net.minecraft.entity.MovementType.SELF, mc.player.getPos());
            lastMove = now;
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
