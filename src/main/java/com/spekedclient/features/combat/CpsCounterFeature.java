package com.spekedclient.features.combat;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class CpsCounterFeature extends Feature {
    private int[] clicksPerSecond = new int[1];
    private long lastSecond = System.currentTimeMillis();
    private int currentSecondClicks = 0;
    private boolean lastAttackPressed = false;

    public CpsCounterFeature() {
        super("CPS Counter", "Display clicks per second", FeatureCategory.COMBAT);
    }

    @Override
    public void onEnable() {
        lastSecond = System.currentTimeMillis();
        currentSecondClicks = 0;
    }

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.player == null) return;

        long current = System.currentTimeMillis();
        if (current - lastSecond >= 1000) {
            clicksPerSecond[0] = currentSecondClicks;
            currentSecondClicks = 0;
            lastSecond = current;
        }

        boolean attacking = mc.options.attackKey.isPressed();
        if (attacking && !lastAttackPressed) {
            currentSecondClicks++;
        }
        lastAttackPressed = attacking;
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled) return;
        int x = 10;
        int y = 60;
        String text = "CPS: " + clicksPerSecond[0];
        context.drawText(mc.textRenderer, text, x, y, 0xFFFFFF, true);
    }
}
