package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ArmorHudFeature extends Feature {
    public ArmorHudFeature() { super("Armor HUD", "Show armor durability", FeatureCategory.HUD); }
    @Override public void onEnable() {}
    @Override public void onDisable() {}
    @Override public void onTick() {}
    @Override public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc == null || mc.player == null) return;
        int y = 10;
        EquipmentSlot[] slots = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        for (EquipmentSlot slot : slots) {
            ItemStack armor = mc.player.getEquippedStack(slot);
            if (!armor.isEmpty()) {
                String text = armor.getName().getString() + " " + armor.getDamage() + "/" + armor.getMaxDamage();
                context.drawText(mc.textRenderer, text, mc.getWindow().getWidth() - 150, y, 0xFFFFFF, true);
                y += 12;
            }
        }
    }
}
