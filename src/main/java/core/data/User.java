package core.data;

import core.utils.LocationUtil;
import lombok.Data;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.justrpg.api.RPG;
import pl.justrpg.api.store.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
@Data
public class User implements Entry {


    private UUID uuid;
    private String lastName,firstIP,lastIP;
    private double exp, money;
    private int level,timePlay,kills,mobKills,openChest,deaths;
    private GameMode gameMode;
    private boolean fly,god,vanish;
    private Player lastMsg;
    private long lastJoin;
    private Location lastLocation,homeLocation;

    public User(final Player player){
        uuid = player.getUniqueId();
        lastName = player.getName();
        firstIP = player.getAddress().getHostString();
        lastIP = player.getAddress().getHostString();
        exp = 0.0D;
        money = 0.0D;
        level = 0;
        timePlay = 0;
        kills = 0;
        mobKills = 0;
        openChest = 0;
        lastMsg = null;
        vanish = false;
        deaths = 0;
        gameMode = player.getGameMode();
        fly = player.getAllowFlight();
        god = (player.getGameMode() == GameMode.CREATIVE);
        lastJoin = System.currentTimeMillis();
        lastLocation = player.getLocation();
        homeLocation = player.getWorld().getSpawnLocation();
        insert();
    }
    public User(ResultSet rs) throws SQLException {
        uuid = UUID.fromString(rs.getString("uuid"));
        lastName = rs.getString("lastName");
        firstIP = rs.getString("firstIP");
        lastIP = rs.getString("lastIP");
        exp = rs.getDouble("exp");
        money = rs.getDouble("money");
        level = rs.getInt("level");
        timePlay = rs.getInt("timePlay");
        kills = rs.getInt("kills");
        mobKills = rs.getInt("mobKills");
        openChest = rs.getInt("openChest");
        deaths = rs.getInt("deaths");
        vanish = rs.getBoolean("vanish");
        gameMode = GameMode.getByValue(rs.getInt("gameMode"));
        god = rs.getBoolean("god");
        lastJoin = rs.getLong("lastJoin");
        lastLocation = LocationUtil.convertStringToLocation(rs.getString("lastLocation"));
        homeLocation = LocationUtil.convertStringToLocation(rs.getString("homeLocation"));
        lastMsg = null;
    }

    public void addMoney(double index){
        this.money += index;
    }

    public void addExp(double index){
        this.exp += index;
    }

    public void addLevel(int index){
        this.level += index;
    }
    public void addTimePlay(int timePlay){
        this.timePlay += timePlay;
    }


    public void insert(){
        final String sql = "INSERT INTO `{P}users` (`id`,`uuid`,`lastName`,`firstIP`,`lastIP`,`exp`,`money`,`level`,`timePlay`,`kills`,`mobKills`,`openChest`,`deaths`,`gameMode`,`fly`,`god`,`lastJoin`,`lastLocation`,`homeLocation`,`vanish`) VALUES (NULL,'" + getUuid() + "', '" + getLastName() + "', '" + getFirstIP() + "', '" + getLastIP() + "', '" + getExp() + "', '" + getMoney() + "', '" + getLevel() + "', '" + getTimePlay() + "', '" + getKills() + "', '" + getMobKills() + "', '" + getOpenChest() + "', '" + getDeaths() + "', '" + getGameMode().getValue() + "', '" + (isFly() ? 1 : 0) + "', '" + (isGod() ? 1 : 0) + "', '" + getLastJoin() + "', '" + LocationUtil.convertLocationToString(getLastLocation()) + "', '" + LocationUtil.convertLocationToString(getHomeLocation()) + "', '" + (isVanish() ? 1 : 0) + "')";
        RPG.getStore().update(false, sql);
    }

    public void update(boolean b) {
        final String sql = "UPDATE `{P}users` SET `lastName`='" + getLastName() + "',`lastIP`='" + getLastIP() + "',`exp`='" + getExp() + "',`money`='" + getMoney() + "',`level`='" + getLevel() + "',`timePlay`='" + getTimePlay() + "',`kills`='" + getKills() + "',`mobKills`='" + getMobKills() + "',`openChest`='" + getOpenChest() + "',`deaths`='" + getDeaths() + "',`gameMode`='" + getGameMode().getValue() + "',`fly`='" + (isFly() ? 1 : 0) + "',`god`='" + (isGod() ? 1 : 0) + "',`lastJoin`='" + getLastJoin() + "',`lastLocation`='" + LocationUtil.convertLocationToString(getLastLocation()) + "',`homeLocation`='" + LocationUtil.convertLocationToString(getHomeLocation()) + "',`vanish`='" + (isVanish() ? 1 : 0) + "' WHERE `uuid`='" + getUuid() + "'";
        RPG.getStore().update(false, sql);
    }

    public void delete() {
        final String sql = "DELETE FROM `{P}users` WHERE `uuid` = '" + getUuid() + "'";
        RPG.getStore().update(false, sql);
    }
}
