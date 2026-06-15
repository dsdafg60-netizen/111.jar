# 111.jar - Horror Mod for Minecraft 1.20.1

A Minecraft Forge mod that adds terrifying horror elements to your game!

## Features

### 🐰 Bunnyman Mob
A terrifying hostile mob with the following abilities:
- **Fear Scream**: When you look directly at the Bunnyman, it lets out a horrifying scream
- **Damage**: Deals 5 hearts of damage when it attacks you
- **Fear Induction**: Triggers immediate fear and stress effects in players who see it

### 😨 Fear & Stress System

#### Fear Mechanic (0-100 scale):
- **Increases from**:
  - Looking at Bunnyman: +30 fear
  - Complete darkness: +1 fear every 2 seconds
  - Hallucination triggers: +15 fear
  
- **Decreases from**:
  - Bright light (light level 14+): -1 fear per tick
  - No fear reduction in darkness

#### Stress System:
- **Increases**: From fear events and Bunnyman encounters
- **Decreases**: Naturally over time (waiting) - 2 stress per second

#### Fear Effects:
- **Low Fear (0-50)**: Minimal effects
- **Medium Fear (50-75)**: Slight slowness effect
- **High Fear (75-100)**: Severe slowness

#### Hallucination Effects:
- **Blindness**: Temporary complete blindness (3 seconds)
- **Nausea**: Screen warping and disorientation (2 seconds)
- **Triggered by**: Bunnyman encounters

### 🌙 Darkness Mechanic
- The darker it gets, the more afraid you become
- Complete darkness adds fear every 2 seconds
- Light counteracts fear naturally

## Installation

1. Download the latest release
2. Place the JAR file in your `.minecraft/mods/` folder
3. Make sure you have Forge 1.20.1 (build 47 or later) installed
4. Launch Minecraft

## Building from Source

```bash
./gradlew build
```

The compiled mod will be in `build/libs/`

## Configuration

Config file: `.minecraft/config/111jar-config.toml`

(To be added in future updates)

## Credits

Created by: dsdafg60

## License

MIT License

## Changelog

### Version 1.0.0
- Initial release
- Added Bunnyman mob
- Added Fear/Stress system
- Added Hallucination effects
- Added darkness mechanic
