package com.spekedclient.hosting;

import com.spekedclient.SpekedClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerHostingManager {
    private final List<ManagedServer> servers = new CopyOnWriteArrayList<>();
    private final Path serversDir;

    public ServerHostingManager() {
        this.serversDir = Paths.get(System.getProperty("user.home"), ".spekedclient", "servers");
        try {
            Files.createDirectories(serversDir);
        } catch (Exception e) {
            SpekedClient.LOGGER.warn("Failed to create servers directory", e);
        }
    }

    public void load() {
        // Load server configs from disk
        try {
            if (Files.exists(serversDir)) {
                Files.list(serversDir)
                    .filter(p -> Files.isDirectory(p))
                    .forEach(this::loadServer);
            }
        } catch (Exception e) {
            SpekedClient.LOGGER.warn("Failed to load servers", e);
        }
    }

    private void loadServer(Path serverPath) {
        String serverName = serverPath.getFileName().toString();
        ManagedServer server = new ManagedServer(serverName, serverPath);
        servers.add(server);
    }

    public void tick() {
        for (ManagedServer server : servers) {
            server.tick();
        }
    }

    public ManagedServer createServer(String name, String version) {
        Path serverPath = serversDir.resolve(name);
        try {
            Files.createDirectories(serverPath);
            ManagedServer server = new ManagedServer(name, serverPath);
            servers.add(server);
            return server;
        } catch (Exception e) {
            SpekedClient.LOGGER.error("Failed to create server", e);
            return null;
        }
    }

    public void deleteServer(ManagedServer server) {
        server.stop();
        servers.remove(server);
    }

    public List<ManagedServer> getServers() {
        return new ArrayList<>(servers);
    }

    public static class ManagedServer {
        private final String name;
        private final Path serverPath;
        private ServerStatus status = ServerStatus.OFFLINE;
        private Process serverProcess;
        private BufferedReader processReader;
        private final List<String> logs = new CopyOnWriteArrayList<>();
        private int port = 25565;
        private int ram = 1024;
        private String javaPath = "java";

        public ManagedServer(String name, Path serverPath) {
            this.name = name;
            this.serverPath = serverPath;
        }

        public void start() {
            if (status != ServerStatus.OFFLINE) return;
            status = ServerStatus.STARTING;

            new Thread(() -> {
                try {
                    // Create server properties if they don't exist
                    Path propsFile = serverPath.resolve("server.properties");
                    if (!Files.exists(propsFile)) {
                        Files.write(propsFile, String.format(
                            "server-port=%d\n" +
                            "max-players=20\n" +
                            "motd=Speked Server\n",
                            port
                        ).getBytes());
                    }

                    // Start server process
                    ProcessBuilder pb = new ProcessBuilder(
                        javaPath,
                        "-Xmx" + ram + "M",
                        "-Xms" + (ram / 2) + "M",
                        "-jar",
                        serverPath.resolve("server.jar").toString(),
                        "nogui"
                    );
                    pb.directory(serverPath.toFile());
                    pb.redirectErrorStream(true);

                    serverProcess = pb.start();
                    processReader = new BufferedReader(new InputStreamReader(serverProcess.getInputStream()));
                    status = ServerStatus.ONLINE;

                    String line;
                    while ((line = processReader.readLine()) != null) {
                        logs.add(line);
                        if (logs.size() > 1000) {
                            logs.remove(0);
                        }
                    }

                    status = ServerStatus.CRASHED;
                } catch (Exception e) {
                    SpekedClient.LOGGER.error("Server startup failed", e);
                    status = ServerStatus.CRASHED;
                    logs.add("ERROR: " + e.getMessage());
                }
            }).start();
        }

        public void stop() {
            if (serverProcess != null && serverProcess.isAlive()) {
                status = ServerStatus.STOPPING;
                serverProcess.destroy();
                try {
                    if (!serverProcess.waitFor(10, java.util.concurrent.TimeUnit.SECONDS)) {
                        serverProcess.destroyForcibly();
                    }
                } catch (InterruptedException e) {
                    serverProcess.destroyForcibly();
                }
            }
            status = ServerStatus.OFFLINE;
        }

        public void restart() {
            stop();
            start();
        }

        public void sendCommand(String command) {
            if (serverProcess != null && serverProcess.isAlive()) {
                try {
                    serverProcess.getOutputStream().write((command + "\n").getBytes());
                    serverProcess.getOutputStream().flush();
                } catch (Exception e) {
                    SpekedClient.LOGGER.warn("Failed to send command", e);
                }
            }
        }

        public void tick() {
            if (serverProcess != null && !serverProcess.isAlive() && status == ServerStatus.ONLINE) {
                status = ServerStatus.CRASHED;
            }
        }

        // Getters and setters
        public String getName() { return name; }
        public Path getServerPath() { return serverPath; }
        public ServerStatus getStatus() { return status; }
        public List<String> getLogs() { return new ArrayList<>(logs); }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
        public int getRam() { return ram; }
        public void setRam(int ram) { this.ram = ram; }
        public String getJavaPath() { return javaPath; }
        public void setJavaPath(String path) { this.javaPath = path; }
    }

    public enum ServerStatus {
        OFFLINE("Offline"),
        STARTING("Starting"),
        ONLINE("Online"),
        STOPPING("Stopping"),
        CRASHED("Crashed");

        private final String displayName;

        ServerStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
