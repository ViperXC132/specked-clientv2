package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class KeystrokesFeature extends Feature {
    public KeystrokesFeature() {
        super("Keystrokes", "Show key presses", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc == null || mc.options == null) return;
        int x = this.mc.getWindow().getWidth() - 100;
        int y = this.mc.getWindow().getHeight() - 70;
        
        String w = mc.options.forwardKey.isPressed() ? "[W]" : "W";
        String a = mc.options.leftKey.isPressed() ? "[A]" : "A";
        String s = mc.options.backKey.isPressed() ? "[S]" : "S";
        String d = mc.options.rightKey.isPressed() ? "[D]" : "D";
        
        context.drawText(mc.textRenderer, "  " + w, x, y, 0xFFFFFF, true);
        context.drawText(mc.textRenderer, a + " " + s + " " + d, x, y + 12, 0xFFFFFF, true);
    }
}
