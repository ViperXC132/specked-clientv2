package com.spekedclient.gui.mainmenu;

import com.spekedclient.gui.pause.PauseMenuScreen;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.text.Text;

public class MainMenuScreen extends Screen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BG_COLOR = 0xFF0E1117;
    private static final int PANEL_COLOR = 0xD0101424;
    private static final int BORDER_COLOR = 0xFF1E2540;
    private static final int ACCENT_COLOR = 0xFF6D7CFF;
    private static final int TEXT_COLOR = 0xFFEAF0FF;
    private static final int BUTTON_HOVER_COLOR = 0x503B5BDB;
    private static final int BUTTON_DEFAULT_COLOR = 0x30101828;

    public MainMenuScreen() {
        super(Text.literal("Speked Client 2.0"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        context.fill(0, 0, this.width, this.height, BG_COLOR);
        for (int gx = 0; gx < width; gx += 40) context.fill(gx, 0, gx + 1, height, 0x05FFFFFF);
        for (int gy = 0; gy < height; gy += 40) context.fill(0, gy, width, gy + 1, 0x05FFFFFF);

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int panelWidth = 280;
        int panelHeight = 320;
        int panelX = centerX - panelWidth / 2;
        int panelY = centerY - panelHeight / 2;

        drawPanel(context, panelX, panelY, panelWidth, panelHeight);
        context.drawCenteredTextWithShadow(this.textRenderer, "SPEKED CLIENT", panelX + panelWidth / 2, panelY + 20, ACCENT_COLOR);
        context.drawCenteredTextWithShadow(this.textRenderer, "2.0", panelX + panelWidth / 2, panelY + 35, 0xFF748FFF);

        int buttonY = panelY + 60;
        int buttonX = panelX + 20;
        buttonY = drawButton(context, buttonX, buttonY, "Singleplayer", mouseX, mouseY) + 5;
        buttonY = drawButton(context, buttonX, buttonY, "Multiplayer", mouseX, mouseY) + 5;
        buttonY = drawButton(context, buttonX, buttonY, "Server Hosting", mouseX, mouseY) + 5;
        buttonY = drawButton(context, buttonX, buttonY, "Mods", mouseX, mouseY) + 5;
        buttonY = drawButton(context, buttonX, buttonY, "Settings", mouseX, mouseY) + 5;
        buttonY = drawButton(context, buttonX, buttonY, "Resource Packs", mouseX, mouseY) + 5;
        drawButton(context, buttonX, buttonY, "Quit", mouseX, mouseY);

        super.render(context, mouseX, mouseY, partialTick);
    }

    private void drawPanel(DrawContext context, int x, int y, int width, int height) {
        context.fill(x, y, x + width, y + height, PANEL_COLOR);
        context.fill(x, y, x + width, y + 1, BORDER_COLOR);
        context.fill(x, y + height - 1, x + width, y + height, BORDER_COLOR);
        context.fill(x, y, x + 1, y + height, BORDER_COLOR);
        context.fill(x + width - 1, y, x + width, y + height, BORDER_COLOR);
    }

    private int drawButton(DrawContext context, int x, int y, String label, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseX <= x + BUTTON_WIDTH && mouseY >= y && mouseY <= y + BUTTON_HEIGHT;
        int bgColor = hovered ? BUTTON_HOVER_COLOR : BUTTON_DEFAULT_COLOR;
        context.fill(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, bgColor);
        context.fill(x, y, x + BUTTON_WIDTH, y + 1, hovered ? ACCENT_COLOR : BORDER_COLOR);
        context.fill(x, y, x + 1, y + BUTTON_HEIGHT, hovered ? ACCENT_COLOR : BORDER_COLOR);
        context.drawCenteredTextWithShadow(this.textRenderer, label, x + BUTTON_WIDTH / 2, y + BUTTON_HEIGHT / 2 - 4, hovered ? TEXT_COLOR : 0xFF8892A8);
        return y + BUTTON_HEIGHT;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        double mouseX = click.x();
        double mouseY = click.y();
        int button = click.button();
        if (button != 0) return false;

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int panelWidth = 280;
        int panelHeight = 320;
        int panelX = centerX - panelWidth / 2;
        int panelY = centerY - panelHeight / 2;
        int buttonY = panelY + 60;
        int buttonX = panelX + (panelWidth - BUTTON_WIDTH) / 2;

        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) { this.client.setScreen(new SelectWorldScreen(this)); return true; }
        buttonY += BUTTON_HEIGHT + 5;
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) { this.client.setScreen(new MultiplayerScreen(this)); return true; }
        buttonY += BUTTON_HEIGHT + 5;
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) { this.client.setScreen(new ServerHostingScreen(this)); return true; }
        buttonY += BUTTON_HEIGHT + 5;
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) { this.client.setScreen(new ModsScreen(this)); return true; }
        buttonY += BUTTON_HEIGHT + 5;
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) { this.client.setScreen(new OptionsScreen(this, this.client.options)); return true; }
        buttonY += BUTTON_HEIGHT + 5;
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) { this.client.setScreen(new AccountsScreen(this)); return true; }
        buttonY += BUTTON_HEIGHT + 5;
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) { this.client.scheduleStop(); return true; }
        return super.mouseClicked(click, doubled);
    }

    private boolean isMouseOverButton(int mouseX, int mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + BUTTON_WIDTH && mouseY >= buttonY && mouseY <= buttonY + BUTTON_HEIGHT;
    }

    @Override public void close() {}
    @Override public boolean shouldPause() { return false; }
}
