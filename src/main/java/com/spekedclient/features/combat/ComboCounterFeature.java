package com.spekedclient.features.combat;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class ComboCounterFeature extends Feature {
    private int comboCount = 0;
    private long lastHitTime = 0;

    public ComboCounterFeature() {
        super("Combo Counter", "Display hit combo", FeatureCategory.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.world == null) return;
        long now = System.currentTimeMillis();
        if (now - lastHitTime > 2000) {
            comboCount = 0;
        }
        
        if (mc.options.attackKey.wasPressed()) {
            comboCount++;
            lastHitTime = now;
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || comboCount == 0) return;
        String text = "Combo: x" + comboCount;
        context.drawText(mc.textRenderer, text, 10, 100, 0xFFFFFF00, true);
    }
}
