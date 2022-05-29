package com.pb.cbindex;
import org.bukkit.plugin.java.JavaPlugin;

import com.pb.cbindex.CommandCBLogs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CBIndex extends JavaPlugin implements Listener {
	public List<String> commands = new ArrayList<>();
	
	@Override
    public void onEnable() {
		this.getCommand("cblogs").setExecutor(new CommandCBLogs(this));
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onCommand(ServerCommandEvent e) {
		CommandSender _sender = e.getSender();
		if(_sender instanceof BlockCommandSender) {
			BlockCommandSender sender = (BlockCommandSender) _sender;
			int x = sender.getBlock().getX();
			int y = sender.getBlock().getY();
			int z = sender.getBlock().getZ();
			String cmd = e.getCommand();
			Date currentDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("kk:mm:ss");
			String time = formatter.format(currentDate);
			String ret = "[&a" + sender.getBlock().getWorld().getName() + "&f] [&b" + x + ", " + y + ", " + z + "&f] " + cmd;
			if(!commands.contains(ret)) {
				commands.add(0, ret);
				if(commands.size() > 30)
					commands.remove(commands.size() - 1);
			}
        }
	}

    @Override
    public void onDisable() {
		
    }
}
