package com.spekedclient.gui.mainmenu;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class HudEditorScreen extends Screen {
    private final Screen parent;
    private int fps_x = 10, fps_y = 10;
    private int ping_x = 200, ping_y = 10;
    private int coords_x = 10, coords_y = 500;
    private int armor_x = 600, armor_y = 500;
    private int dragging = -1;
    private double lastMouseX, lastMouseY;

    public HudEditorScreen(Screen parent) { super(Text.literal("HUD Editor")); this.parent = parent; }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(context, mouseX, mouseY, partialTick);
        context.drawCenteredTextWithShadow(this.textRenderer, "HUD Editor", this.width / 2, 20, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "Drag elements to reposition", this.width / 2, 40, 0xFF8892A8);
        drawElement(context, fps_x, fps_y, "FPS: 240", mouseX, mouseY);
        drawElement(context, ping_x, ping_y, "Ping: 45ms", mouseX, mouseY);
        drawElement(context, coords_x, coords_y, "Coords", mouseX, mouseY);
        drawElement(context, armor_x, armor_y, "Armor", mouseX, mouseY);
        context.drawTextWithShadow(this.textRenderer, "Right-click to close and save", 20, this.height - 20, 0xFF8892A8);
    }

    private void drawElement(DrawContext context, int x, int y, String text, int mouseX, int mouseY) {
        boolean hover = mouseX >= x && mouseX < x + 100 && mouseY >= y && mouseY < y + 30;
        context.fill(x, y, x + 100, y + 30, hover ? 0x603B5BDB : 0x30101828);
        context.drawBorder(x, y, 100, 30, hover ? 0xFF6D7CFF : 0xFF1E2540);
        context.drawTextWithShadow(this.textRenderer, text, x + 8, y + 11, 0xFF8892A8);
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        double mouseX = click.x(), mouseY = click.y();
        int button = click.button();
        if (button == 0) {
            if (isInBounds(fps_x, fps_y, mouseX, mouseY)) dragging = 0;
            else if (isInBounds(ping_x, ping_y, mouseX, mouseY)) dragging = 1;
            else if (isInBounds(coords_x, coords_y, mouseX, mouseY)) dragging = 2;
            else if (isInBounds(armor_x, armor_y, mouseX, mouseY)) dragging = 3;
            lastMouseX = mouseX; lastMouseY = mouseY;
            return true;
        } else if (button == 1) {
            this.client.setScreen(this.parent);
            return true;
        }
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean mouseDragged(Click click, double offsetX, double offsetY) {
        if (dragging >= 0) {
            int dx = (int) offsetX, dy = (int) offsetY;
            if (dragging == 0) { fps_x += dx; fps_y += dy; }
            else if (dragging == 1) { ping_x += dx; ping_y += dy; }
            else if (dragging == 2) { coords_x += dx; coords_y += dy; }
            else { armor_x += dx; armor_y += dy; }
            lastMouseX = click.x(); lastMouseY = click.y();
            return true;
        }
        return super.mouseDragged(click, offsetX, offsetY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    private boolean isInBounds(int x, int y, double mouseX, double mouseY) { return mouseX >= x && mouseX < x + 100 && mouseY >= y && mouseY < y + 30; }
    @Override public void close() { this.client.setScreen(this.parent); }
    @Override public boolean shouldPause() { return false; }
}
