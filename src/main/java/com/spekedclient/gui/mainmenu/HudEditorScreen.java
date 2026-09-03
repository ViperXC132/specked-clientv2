package com.spekedclient.gui.mainmenu;

import com.spekedclient.SpekedClient;
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

    public HudEditorScreen(Screen parent) {
        super(Text.literal("HUD Editor"));
        this.parent = parent;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(context);
        drawCenteredTextShadow(context, this.textRenderer, "HUD Editor", this.width / 2, 20, 0xFFFFFF);
        drawCenteredTextShadow(context, this.textRenderer, "Drag elements to reposition", this.width / 2, 40, 0xFF8892A8);

        // Draw FPS element
        drawElement(context, fps_x, fps_y, "FPS: 240", mouseX, mouseY, 0);

        // Draw Ping element
        drawElement(context, ping_x, ping_y, "Ping: 45ms", mouseX, mouseY, 1);

        // Draw Coords element
        drawElement(context, coords_x, coords_y, "Coords", mouseX, mouseY, 2);

        // Draw Armor element
        drawElement(context, armor_x, armor_y, "Armor", mouseX, mouseY, 3);

        drawString(context, this.textRenderer, "Right-click to close and save", 20, this.height - 20, 0xFF8892A8);
    }

    private void drawElement(DrawContext context, int x, int y, String text, int mouseX, int mouseY, int id) {
        int width = 100;
        int height = 30;
        
        boolean hover = mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
        int color = hover ? 0x603B5BDB : 0x30101828;
        
        context.fill(x, y, x + width, y + height, color);
        context.drawBorder(x, y, width, height, hover ? 0xFF6D7CFF : 0xFF1E2540);
        drawString(context, this.textRenderer, text, x + 8, y + 11, 0xFF8892A8);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            if (isInBounds(fps_x, fps_y, mouseX, mouseY)) dragging = 0;
            else if (isInBounds(ping_x, ping_y, mouseX, mouseY)) dragging = 1;
            else if (isInBounds(coords_x, coords_y, mouseX, mouseY)) dragging = 2;
            else if (isInBounds(armor_x, armor_y, mouseX, mouseY)) dragging = 3;
            
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        } else if (button == 1) {
            this.client.setScreen(this.parent);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (dragging >= 0) {
            int dx = (int)(mouseX - lastMouseX);
            int dy = (int)(mouseY - lastMouseY);
            
            if (dragging == 0) { fps_x += dx; fps_y += dy; }
            else if (dragging == 1) { ping_x += dx; ping_y += dy; }
            else if (dragging == 2) { coords_x += dx; coords_y += dy; }
            else if (dragging == 3) { armor_x += dx; armor_y += dy; }
            
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    private boolean isInBounds(int x, int y, double mouseX, double mouseY) {
        return mouseX >= x && mouseX < x + 100 && mouseY >= y && mouseY < y + 30;
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
