package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class EnchantHudFeature extends Feature {
    public EnchantHudFeature() {
        super("Enchant Display", "Show held item enchants", FeatureCategory.HUD);
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
        var item = mc.player.getMainHandStack();
        int y = 10;
        context.drawText(mc.textRenderer, "Item: " + item.getName().getString(), this.mc.getWindow().getWidth() - 150, y, 0xFFFFFF, true);
    }
}
