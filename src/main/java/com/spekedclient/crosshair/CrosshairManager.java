package com.spekedclient.crosshair;

import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

public class CrosshairManager {
    private final List<CrosshairPreset> presets = new ArrayList<>();
    private CrosshairPreset activeCrosshair;
    private boolean enabled = false;

    public void load() {
        // Load crosshair presets from config
        createDefaultPreset();
    }

    private void createDefaultPreset() {
        CrosshairPreset preset = new CrosshairPreset("Default");
        preset.setSize(15);
        preset.setGap(3);
        preset.setThickness(1);
        preset.setColor(0xFFFFFFFF);
        presets.add(preset);
        activeCrosshair = preset;
    }

    public void render(DrawContext context) {
        if (!enabled || activeCrosshair == null) return;
        activeCrosshair.render(context);
    }

    public void addPreset(CrosshairPreset preset) {
        presets.add(preset);
    }

    public CrosshairPreset getActivePreset() {
        return activeCrosshair;
    }

    public void setActivePreset(CrosshairPreset preset) {
        this.activeCrosshair = preset;
    }

    public List<CrosshairPreset> getPresets() {
        return new ArrayList<>(presets);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static class CrosshairPreset {
        private final String name;
        private int size = 15;
        private int gap = 3;
        private int thickness = 1;
        private int color = 0xFFFFFFFF;
        private int opacity = 255;
        private boolean outline = false;
        private int outlineColor = 0xFF000000;

        public CrosshairPreset(String name) {
            this.name = name;
        }

        public void render(DrawContext context) {
            // Render crosshair at screen center
            int screenWidth = context.getScaledWindowWidth();
            int screenHeight = context.getScaledWindowHeight();
            int centerX = screenWidth / 2;
            int centerY = screenHeight / 2;

            // Draw horizontal line
            context.fill(centerX - size, centerY - thickness/2, centerX - gap, centerY + thickness/2, color);
            context.fill(centerX + gap, centerY - thickness/2, centerX + size, centerY + thickness/2, color);

            // Draw vertical line
            context.fill(centerX - thickness/2, centerY - size, centerX + thickness/2, centerY - gap, color);
            context.fill(centerX - thickness/2, centerY + gap, centerX + thickness/2, centerY + size, color);
        }

        public String getName() { return name; }
        public int getSize() { return size; }
        public void setSize(int size) { this.size = size; }
        public int getGap() { return gap; }
        public void setGap(int gap) { this.gap = gap; }
        public int getThickness() { return thickness; }
        public void setThickness(int thickness) { this.thickness = thickness; }
        public int getColor() { return color; }
        public void setColor(int color) { this.color = color; }
        public int getOpacity() { return opacity; }
        public void setOpacity(int opacity) { this.opacity = opacity; }
        public boolean hasOutline() { return outline; }
        public void setOutline(boolean outline) { this.outline = outline; }
        public int getOutlineColor() { return outlineColor; }
        public void setOutlineColor(int color) { this.outlineColor = color; }
    }
}
