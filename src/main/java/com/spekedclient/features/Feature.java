package com.spekedclient.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public abstract class Feature {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    protected boolean enabled = false;
    protected final String name;
    protected final String description;
    protected final FeatureCategory category;

    public Feature(String name, String description, FeatureCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void onTick();
    public abstract void onRender(DrawContext context, float tickDelta);

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public FeatureCategory getCategory() {
        return category;
    }

    public enum FeatureCategory {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        VISUAL("Visual"),
        HUD("HUD"),
        PERFORMANCE("Performance"),
        UTILITY("Utility"),
        SYSTEM("System");

        private final String displayName;

        FeatureCategory(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
