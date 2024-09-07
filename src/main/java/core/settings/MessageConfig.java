package core.settings;

import core.Main;
import org.bukkit.configuration.file.FileConfiguration;
import pl.justrpg.api.configs.ConfigCreator;
import pl.justrpg.api.util.Util;

import java.util.List;


public class MessageConfig extends ConfigCreator {
	    
	    public static String MESSAGE_ANTYMACRO,AUTOMESSAGE_PREFIX,MESSAGE_WHITELIST_KICKREASON,MESSAGE_COMMAND_UNKNOWNCOMMAND,ANTYLOGOUTMESSAGE_STARTFIGHT,ANTYLOGOUTMESSAGE_ENDFIGHT,ANTYLOGOUTACTIONBARMESSAGE_INFIGHT,ANTYLOGOUTACTIONBARRMESSAGE_ENDFIGHT,MESSAGE_ADMINCHAT,OSIAGNIECIA_TEXTCOMPONENT_TEXT,OSIAGNIECIA_TEXTCOMPONENT_COMMAND,OSIAGNIECIA_TEXTCOMPONENT_HIDETEXT,MESSAGE_COMMAND_UNKNOWNUSER,MESSAGE_COMMAND_UNKNOWNPLAYER,MESSAGE_COMMAND_GETUSAGE,SERVERNAME_TAG;
	    public static List<String> AUTOMESSAGE_MESSAGES,COMMAND_HELP,COMMAND_YOUTUBE, COMMAND_TWITCH,COMMAND_VIP,COMMAND_SVIP,COMMAND_MVIP,COMMAND_REGULAMIN,MESSAGE_JOIN;	    
	    public MessageConfig(){
	      super("message.yml" , "Message Config" , Main.getPlugin());
	    	FileConfiguration config = getConfig();
	    	ANTYLOGOUTMESSAGE_STARTFIGHT = Util.getReplacement(config.getString("antylogout.message.startfight"));
	    	ANTYLOGOUTMESSAGE_ENDFIGHT = Util.getReplacement(config.getString("antylogout.message.endfight"));
	    	ANTYLOGOUTACTIONBARMESSAGE_INFIGHT = Util.getReplacement(config.getString("antylogout.message.actionbar.infight"));
	    	ANTYLOGOUTACTIONBARRMESSAGE_ENDFIGHT = Util.getReplacement(config.getString("antylogout.message.actionbar.endfight"));
	    	MESSAGE_ADMINCHAT = Util.getReplacement(config.getString("adminchat.message"));
	    	COMMAND_HELP = config.getStringList("commands.help");
	    	COMMAND_YOUTUBE = config.getStringList("commands.youtube");
	    	COMMAND_TWITCH = config.getStringList("commands.twitch");
	    	COMMAND_VIP = config.getStringList("commands.vip");
	    	COMMAND_SVIP = config.getStringList("commands.svip");
	    	COMMAND_MVIP = config.getStringList("commands.mvip");
	    	COMMAND_REGULAMIN = config.getStringList("commands.regulamin");
	    	MESSAGE_JOIN = config.getStringList("join.message");
	    	MESSAGE_COMMAND_UNKNOWNUSER = Util.getReplacement(config.getString("message.command.unknown.user"));
	    	MESSAGE_COMMAND_UNKNOWNPLAYER = Util.getReplacement(config.getString("message.command.unknown.player"));
	    	MESSAGE_COMMAND_GETUSAGE = Util.fixColor(Util.getReplacement(config.getString("message.command.getusage")));
	    	SERVERNAME_TAG = Util.fixColor(Util.getReplacement(config.getString("server.nametag")));
	    	MESSAGE_COMMAND_UNKNOWNCOMMAND = Util.getReplacement(config.getString("message.command.unknown.command"));
	    	MESSAGE_WHITELIST_KICKREASON = Util.fixColor(Util.getReplacement(config.getString("message.whitelist.kickreason")));
	    	AUTOMESSAGE_MESSAGES = config.getStringList("automessage.messages");
	    	AUTOMESSAGE_PREFIX = Util.fixColor(Util.getReplacement(config.getString("automessage.prefix")));
	    	MESSAGE_ANTYMACRO = Util.fixColor(Util.getReplacement(config.getString("message.antymacro.antymacro")));
	    }
}
