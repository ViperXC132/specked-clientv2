package com.spekedclient.features.combat;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class AttackIndicatorFeature extends Feature {
    private long lastAttackTime = 0;
    private float attackCooldown = 0;

    public AttackIndicatorFeature() {
        super("Attack Indicator", "Show attack cooldown", FeatureCategory.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc != null && mc.player != null) {
            attackCooldown = mc.player.getAttackCooldownProgress(0);
            if (mc.options.attackKey.wasPressed()) {
                lastAttackTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled) return;
        int x = mc.getWindow().getWidth() / 2;
        int y = mc.getWindow().getHeight() / 2 + 20;
        int width = 100;
        int height = 5;
        context.fill(x - width/2, y, x + width/2, y + height, 0xFF333333);
        int filledWidth = (int)(width * attackCooldown);
        context.fill(x - width/2, y, x - width/2 + filledWidth, y + height, 0xFF00FF00);
    }
}
