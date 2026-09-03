package com.spekedclient.notification;

import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
    private final List<Notification> notifications = new ArrayList<>();
    private static final int MAX_NOTIFICATIONS = 5;

    public void addNotification(String title, String message, NotificationType type) {
        addNotification(title, message, type, 3000);
    }

    public void addNotification(String title, String message, NotificationType type, int duration) {
        Notification notif = new Notification(title, message, type, duration);
        notifications.add(notif);
        
        if (notifications.size() > MAX_NOTIFICATIONS) {
            notifications.remove(0);
        }
    }

    public void tick() {
        notifications.removeIf(n -> n.tick());
    }

    public void render(DrawContext context) {
        int y = 10;
        for (Notification n : notifications) {
            n.render(context, 10, y);
            y += n.getHeight() + 5;
        }
    }

    public enum NotificationType {
        INFO,
        SUCCESS,
        WARNING,
        ERROR
    }

    public static class Notification {
        private final String title;
        private final String message;
        private final NotificationType type;
        private final int duration;
        private int age = 0;

        public Notification(String title, String message, NotificationType type, int duration) {
            this.title = title;
            this.message = message;
            this.type = type;
            this.duration = duration;
        }

        public boolean tick() {
            age++;
            return age > duration / 50;
        }

        public void render(DrawContext context, int x, int y) {
            // Render notification box
            int width = 200;
            int height = 40;
            
            int bgColor = switch (type) {
                case SUCCESS -> 0xFF00AA00;
                case WARNING -> 0xFFFFAA00;
                case ERROR -> 0xFFAA0000;
                default -> 0xFF0000AA;
            };
            
            context.fill(x, y, x + width, y + height, bgColor);
            // Render text would go here
        }

        public int getHeight() {
            return 40;
        }
    }
}
