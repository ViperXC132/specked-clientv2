package com.spekedclient.gui.mainmenu;

import com.spekedclient.SpekedClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CrosshairEditorScreen extends Screen {
    private final Screen parent;
    private boolean[][] grid = new boolean[9][3];
    private static final int CELL_SIZE = 20;
    private static final int GRID_START_X = 100;
    private static final int GRID_START_Y = 100;

    public CrosshairEditorScreen(Screen parent) {
        super(Text.literal("Crosshair Editor"));
        this.parent = parent;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(context);
        drawCenteredTextShadow(context, this.textRenderer, "Crosshair Editor", this.width / 2, 30, 0xFFFFFF);

        // Draw grid
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 3; y++) {
                int px = GRID_START_X + x * (CELL_SIZE + 4);
                int py = GRID_START_Y + y * (CELL_SIZE + 4);
                
                if (grid[x][y]) {
                    context.fill(px, py, px + CELL_SIZE, py + CELL_SIZE, 0xFF6D7CFF);
                } else {
                    context.fill(px, py, px + CELL_SIZE, py + CELL_SIZE, 0xFF1E2540);
                }
                context.drawBorder(px, py, CELL_SIZE, CELL_SIZE, 0xFF3F4D6D);
            }
        }

        // Save button
        drawString(context, this.textRenderer, "Click cells to paint | Right-click to erase | Close to save", 20, this.height - 30, 0xFF8892A8);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 3; y++) {
                int px = GRID_START_X + x * (CELL_SIZE + 4);
                int py = GRID_START_Y + y * (CELL_SIZE + 4);
                
                if (mouseX >= px && mouseX < px + CELL_SIZE && mouseY >= py && mouseY < py + CELL_SIZE) {
                    if (button == 0) {
                        grid[x][y] = true;
                    } else if (button == 1) {
                        grid[x][y] = false;
                    }
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        SpekedClient.getInstance().getCrosshairManager().setEnabled(true);
        this.client.setScreen(this.parent);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
