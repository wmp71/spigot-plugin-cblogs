package com.pb.cbindex;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.pb.cbindex.CBIndex;

public class CommandCBLogs implements CommandExecutor {
	CBIndex plugin;
	
	public CommandCBLogs(CBIndex plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		String ret = "40 Recent commands executed by command blocks.\n";
		for(String item : plugin.commands) {
			ret += item + "\n";
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ret));
		return true;
	}

}
