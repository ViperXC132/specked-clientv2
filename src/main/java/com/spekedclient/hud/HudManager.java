package com.spekedclient.hud;

import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private final List<HudElement> elements = new ArrayList<>();
    private boolean editing = false;

    public void load() {
        // Initialize default HUD elements from features
    }

    public void render(DrawContext context, float tickDelta) {
        for (HudElement element : elements) {
            if (element.isVisible()) {
                element.render(context, tickDelta);
            }
        }
    }

    public void addElement(HudElement element) {
        elements.add(element);
    }

    public List<HudElement> getElements() {
        return new ArrayList<>(elements);
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public abstract static class HudElement {
        protected int x, y, width, height;
        protected boolean visible = true;
        protected float scale = 1.0f;

        public abstract void render(DrawContext context, float tickDelta);

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isVisible() { return visible; }
        public void setVisible(boolean visible) { this.visible = visible; }
        public float getScale() { return scale; }
        public void setScale(float scale) { this.scale = scale; }
    }
}
