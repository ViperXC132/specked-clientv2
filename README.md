# Speked Client 2.0

A modern, polished Fabric utility client for Minecraft 1.21.11 with a focus on PvP, performance, and quality-of-life features.

## Features

✨ **Polished UI**
- Custom main menu with glassmorphism design
- Custom pause menu with server hosting integration
- Responsive UI that adapts to different resolutions

🎮 **Combat Modules**
- CPS Counter
- Hit Color
- Attack Indicator
- Combo Counter

⚡ **Movement Modules**
- Toggle Sprint
- Toggle Sneak
- Speed Display
- Direction Indicator

👁️ **Visual Modules**
- Fullbright
- Zoom
- FOV Customizer
- Custom Crosshair

🖥️ **HUD Modules**
- FPS Display
- Ping Display
- Coordinates
- Armor HUD
- Potion HUD
- Keystrokes Display

⚙️ **Performance Features**
- Dynamic FPS
- Entity Culling
- Reduce Particles

🔐 **Account Management**
- Multi-account support
- Microsoft authentication
- Ely.by support
- LittleSkin support
- Offline accounts

🌐 **Built-in Server Hosting**
- Create and manage local Minecraft servers
- Start, stop, restart servers from the client
- Console access
- Server configuration
- Port and RAM management

🎨 **Custom Crosshairs**
- Pixel-by-pixel crosshair editor
- Multiple preset support
- Real-time customization
- Various crosshair styles

## Requirements

- Minecraft **1.21.11**
- Fabric Loader **0.19.3+**
- Fabric API **0.141.6+1.21.11**
- Java **21**

## Installation

1. Download the latest Speked Client JAR from the [Releases page](https://github.com/ViperXC132/speked-client/releases)
2. Place it in your Minecraft `mods` folder
3. Launch Minecraft with Fabric

## Build from Source

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/speked-client-2.0.jar`

## Architecture

- **accounts/** - Account management (Microsoft, Ely.by, LittleSkin, Offline)
- **config/** - Configuration system
- **crosshair/** - Custom crosshair system
- **features/** - Module implementations (combat, movement, visual, HUD, performance)
- **gui/** - User interface screens
- **hosting/** - Built-in server hosting
- **hud/** - HUD element management
- **input/** - Keybind management
- **notification/** - In-game notifications
- **render/** - Rendering utilities
- **settings/** - Settings management
- **utils/** - Utility functions

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

**ViperXC132** - https://github.com/ViperXC132

## Disclaimer

This is a client-side mod intended for quality-of-life and performance improvements. All features are designed to keep normal gameplay fair. Use responsibly and follow server rules.
