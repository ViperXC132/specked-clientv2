package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ArmorItem;

public class ArmorHudFeature extends Feature {
    public ArmorHudFeature() {
        super("Armor HUD", "Show armor durability", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc == null || mc.player == null) return;
        int y = 10;
        for (int i = 3; i >= 0; i--) {
            var armor = mc.player.getInventory().getArmorStack(i);
            if (!armor.isEmpty()) {
                String text = armor.getName().getString() + " " + armor.getDamage() + "/" + armor.getMaxDamage();
                context.drawText(mc.textRenderer, text, this.mc.getWindow().getWidth() - 150, y, 0xFFFFFF, true);
                y += 12;
            }
        }
    }
}
