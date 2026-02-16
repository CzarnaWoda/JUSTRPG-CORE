# ⚔️ JustRPG Core

> RPG core plugin with custom weapons, armor, and player progression system

[![Minecraft](https://img.shields.io/badge/Minecraft-1.8+-brightgreen.svg)](https://www.spigotmc.org/)
[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Dependency](https://img.shields.io/badge/Requires-JustRPG--API-blue.svg)](https://github.com/justrpg/api)

## 📖 Overview

JustRPG Core is a lightweight RPG plugin built on top of JustRPG API. It provides essential RPG features including custom weapons and armor with attribute modifiers, player progression system, economy, and admin tools.

## ✨ Features

### ⚔️ Custom Weapon System

Create custom weapons with attribute modifiers:

```yaml
# weapons.yml
weapons:
  legendary_sword:
    id: "legendary_sword"
    sword: DIAMOND_SWORD
    displayName: "&6&lLegendary Sword"
    lore:
      - "&7Damage: &c+10"
      - "&7Attack Speed: &a+2.5"
      - "&7Luck: &e+3"
    damage: 10.0
    attackSpeed: 2.5
    lootChance: 3.0
```

**Attributes:**
- **Damage** - Attack damage bonus
- **Attack Speed** - Swing speed modifier
- **Luck** - Loot chance multiplier

### 🛡️ Custom Armor System

Create custom armor pieces with stat bonuses:

```yaml
# armor.yml
armors:
  legendary_chestplate:
    id: "legendary_chestplate"
    armorPiece: DIAMOND_CHESTPLATE
    displayName: "&6&lLegendary Chestplate"
    lore:
      - "&7Armor: &9+8"
      - "&7Armor Toughness: &9+3"
      - "&7Health: &c+4"
      - "&7Movement Speed: &a+0.02"
    armors: 8.0
    armorToughness: 3.0
    movementSpeed: 0.02
    health: 4.0
```

**Attributes:**
- **Armor** - Armor points
- **Armor Toughness** - Armor toughness
- **Movement Speed** - Speed multiplier
- **Health** - Max health bonus

### 👤 Player Progression

```java
User user = UserManager.getUser(player);

// Experience & Leveling
user.addExp(100.0);
user.addLevel(1);

// Economy
user.addMoney(500.0);
double balance = user.getMoney();

// Statistics
int kills = user.getKills();
int mobKills = user.getMobKills();
int deaths = user.getDeaths();
int chestsOpened = user.getOpenChest();

// Playtime tracking
int playtime = user.getTimePlay(); // seconds
```

**Player Data:**
- UUID, name, IP tracking
- Experience & level system
- Money/economy
- Kill & death statistics
- Mob kills tracking
- Chest opening counter
- Playtime tracking
- GameMode, fly, god mode
- Last location & home location

### 💬 Chat System

```java
ChatManager chatManager = Main.getChatManager();

// Toggle global chat
chatManager.setChatEnabled(!chatManager.isChatEnabled());

// Private messaging
User user = UserManager.getUser(player);
user.setLastMsg(targetPlayer);

// Commands
/msg <player> <message>
/reply <message>
```

### 🛠️ Admin Commands

| Command | Permission | Description |
|---------|------------|-------------|
| `/fly [player]` | `core.fly` | Toggle fly mode |
| `/god [player]` | `core.god` | Toggle god mode |
| `/gamemode <mode> [player]` | `core.gamemode` | Change game mode |
| `/heal [player]` | `core.heal` | Heal player |
| `/tp <player>` | `core.tp` | Teleport to player |
| `/vanish` | `core.vanish` | Toggle vanish mode |
| `/adminpanel` | `core.adminpanel` | Open admin GUI |
| `/give <player> <item> [amount]` | `core.give` | Give items |
| `/clear [player]` | `core.clear` | Clear inventory |
| `/invsee <player>` | `core.invsee` | View inventory |
| `/repair` | `core.repair` | Repair held item |
| `/rename <name>` | `core.rename` | Rename held item |
| `/day` | `core.day` | Set time to day |
| `/night` | `core.night` | Set time to night |
| `/broadcast <message>` | `core.broadcast` | Broadcast message |
| `/chat` | `core.chat` | Toggle global chat |
| `/list` | `core.list` | Player list |
| `/helpop <message>` | `core.helpop` | Request admin help |

## 🚀 Installation

### Requirements

- Minecraft 1.8+ server
- JustRPG API plugin
- MySQL or SQLite database

### Setup

1. **Install JustRPG API:**
   - Download and place `RPGAPI.jar` in plugins folder

2. **Install JustRPG Core:**
   - Place `RPGCore.jar` in plugins folder

3. **Configure database:**
   - Edit `plugins/RPGAPI/config.yml`
   - Set database mode and credentials

4. **Start server:**
   - Plugin will auto-create database tables
   - Configure weapons and armor in respective files

## ⚙️ Configuration

### Database

The plugin uses JustRPG API's database configuration:

```yaml
# plugins/RPGAPI/config.yml
config:
  database:
    mode: "mysql"  # or "sqlite"
    tableprefix: "rpg_"
    mysql:
      host: "localhost"
      port: 3306
      user: "root"
      pass: "password"
      name: "minecraft"
```

### Weapons Configuration

Create/edit `plugins/RPGCore/weapons.yml`:

```yaml
weapons:
  starter_sword:
    id: "starter_sword"
    sword: IRON_SWORD
    displayName: "&7Starter Sword"
    lore:
      - "&7A basic sword for beginners"
      - ""
      - "&7Damage: &c+3"
    damage: 3.0
    attackSpeed: 1.5
    lootChance: 0.5
    
  epic_axe:
    id: "epic_axe"
    sword: DIAMOND_AXE
    displayName: "&5&lEpic Battle Axe"
    lore:
      - "&7Crush your enemies!"
      - ""
      - "&7Damage: &c+12"
      - "&7Attack Speed: &a+1.2"
    damage: 12.0
    attackSpeed: 1.2
    lootChance: 2.0
```

### Armor Configuration

Create/edit `plugins/RPGCore/armor.yml`:

```yaml
armors:
  starter_helmet:
    id: "starter_helmet"
    armorPiece: IRON_HELMET
    displayName: "&7Starter Helmet"
    lore:
      - "&7Basic protection"
      - ""
      - "&7Armor: &9+2"
    armors: 2.0
    armorToughness: 0.0
    movementSpeed: 0.0
    health: 0.0
    
  legendary_boots:
    id: "legendary_boots"
    armorPiece: DIAMOND_BOOTS
    displayName: "&6&lLegendary Boots"
    lore:
      - "&7Swift as the wind"
      - ""
      - "&7Armor: &9+3"
      - "&7Movement Speed: &a+0.05"
    armors: 3.0
    armorToughness: 2.0
    movementSpeed: 0.05
    health: 2.0
```

### Messages Configuration

Customize plugin messages in `messages.yml`:

```yaml
messages:
  prefix: "&8[&6RPG&8]&7"
  no_permission: "{PREFIX} &cYou don't have permission!"
  player_not_found: "{PREFIX} &cPlayer not found!"
  # ... more messages
```

## 🗃️ Database Schema

```sql
CREATE TABLE `rpg_users` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uuid` VARCHAR(255) NOT NULL,
    `lastName` VARCHAR(16) NOT NULL,
    `firstIP` VARCHAR(20) NOT NULL,
    `lastIP` VARCHAR(20) NOT NULL,
    `exp` BIGINT(20) NOT NULL,
    `money` BIGINT(32) NOT NULL,
    `level` INT(6) NOT NULL,
    `timePlay` INT(32) NOT NULL,
    `kills` INT(16) NOT NULL,
    `mobKills` INT(16) NOT NULL,
    `openChest` INT(16) NOT NULL,
    `deaths` INT(16) NOT NULL,
    `gameMode` INT(1) NOT NULL,
    `fly` INT(1) NOT NULL,
    `god` INT(1) NOT NULL,
    `vanish` INT(1) NOT NULL,
    `lastJoin` BIGINT(20) NOT NULL,
    `lastLocation` TEXT NOT NULL,
    `homeLocation` TEXT NOT NULL
);
```

## 💻 API Usage

### Get User Data

```java
import core.managers.UserManager;
import core.data.User;

// Get user
User user = UserManager.getUser(player);
User user = UserManager.getUser(uuid);

// Check if exists
if (user == null) {
    // Create new user (auto on join)
}
```

### Economy Operations

```java
// Add money
user.addMoney(100.0);

// Remove money
user.addMoney(-50.0);

// Get balance
double balance = user.getMoney();

// Save changes
user.update(false); // async
user.update(true);  // sync
```

### Experience & Leveling

```java
// Add experience
user.addExp(250.0);

// Add levels
user.addLevel(1);

// Get stats
double exp = user.getExp();
int level = user.getLevel();
```

### Create Custom Weapon

```java
import core.data.Weapon;
import org.bukkit.Material;

Weapon weapon = new Weapon(
    "custom_sword",              // ID
    Material.DIAMOND_SWORD,      // Material
    "&c&lFlaming Sword",         // Display name
    Arrays.asList(               // Lore
        "&7A sword engulfed in flames",
        "",
        "&7Damage: &c+15"
    ),
    15.0,                        // Damage
    5.0,                         // Luck
    2.0                          // Attack speed
);

ItemStack item = weapon.getWeapon();
player.getInventory().addItem(item);
```

### Create Custom Armor

```java
import core.data.Armor;

Armor armor = new Armor(
    "tank_chestplate",           // ID
    Material.DIAMOND_CHESTPLATE, // Material
    "&9&lTank Chestplate",       // Display name
    Arrays.asList(               // Lore
        "&7Built like a fortress",
        "",
        "&7Armor: &9+10",
        "&7Health: &c+6"
    ),
    10.0,                        // Armor
    4.0,                         // Armor Toughness
    -0.01,                       // Movement Speed
    6.0                          // Health
);

ItemStack item = armor.getArmor();
player.getInventory().setChestplate(item);
```

## 📂 Project Structure

```
core/
├── Main.java                   # Plugin main class
│
├── data/                       # Data models
│   ├── User.java               # Player data
│   ├── Weapon.java             # Weapon system
│   └── Armor.java              # Armor system
│
├── commands/                   # Command implementations
│   ├── FlyCommand.java
│   ├── GodmodeCommand.java
│   ├── GamemodeCommand.java
│   ├── HealCommand.java
│   ├── TeleportCommand.java
│   ├── VanishCommand.java
│   ├── AdminPanelCommand.java
│   ├── GiveCommand.java
│   ├── ClearCommand.java
│   ├── InvseeCommand.java
│   ├── RepairCommand.java
│   ├── RenameCommand.java
│   ├── MsgCommand.java
│   ├── ReplayCommand.java
│   ├── ChatCommand.java
│   ├── BroadcastCommand.java
│   ├── ListCommand.java
│   ├── HelpopCommand.java
│   ├── DayCommand.java
│   └── NightCommand.java
│
├── managers/                   # System managers
│   ├── UserManager.java        # User management
│   └── ChatManager.java        # Chat control
│
├── listeners/                  # Event listeners
│   ├── PlayerJoinQuitListener.java
│   └── AsyncPlayerChatListener.java
│
├── inventories/                # GUI inventories
│   └── AdminPanelInventory.java
│
├── settings/                   # Configuration
│   ├── WeaponSettings.java
│   ├── ArmorSettings.java
│   └── MessageConfig.java
│
├── utils/                      # Utilities
│   ├── MaterialUtil.java
│   └── LocationUtil.java
│
└── interfaces/                 # Interfaces
    ├── Colors.java
    └── SpecialCharacters.java
```

## 🎮 Gameplay Features

### Attribute System

**Weapons:**
- ⚔️ **Damage** - Increases attack damage
- ⚡ **Attack Speed** - Faster weapon swing
- 🍀 **Luck** - Better loot drops

**Armor:**
- 🛡️ **Armor** - Damage reduction
- 💎 **Armor Toughness** - Additional protection
- 👟 **Movement Speed** - Walk/run speed
- ❤️ **Health** - Maximum health increase

### Admin Panel

Access via `/adminpanel` - GUI with quick admin actions:
- Player management
- Server settings
- Quick commands
- Inventory management

## 🔧 Developer API

### Events

Listen for user-related events:

```java
@EventHandler
public void onJoin(PlayerJoinEvent event) {
    User user = UserManager.getUser(event.getPlayer());
    // Access user data
}
```

### Custom Integrations

```java
// Hook into the system
public class MyPlugin extends JavaPlugin {
    
    @Override
    public void onEnable() {
        // Ensure RPGCore is loaded
        if (!Bukkit.getPluginManager().isPluginEnabled("RPGCore")) {
            getLogger().severe("RPGCore not found!");
            setEnabled(false);
            return;
        }
        
        // Use the API
        User user = UserManager.getUser(player);
        user.addMoney(1000.0);
    }
}
```

## 📊 Stat Tracking

The plugin automatically tracks:
- ✅ Player kills
- ✅ Mob kills  
- ✅ Deaths
- ✅ Chests opened
- ✅ Playtime (seconds)
- ✅ Join/quit timestamps
- ✅ Last location

## 🤝 Contributing

Contributions welcome! Please submit pull requests.

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**JustRPG Development**
- Package: `core`
- Requires: JustRPG API

---

**Simple. Lightweight. Powerful.**
