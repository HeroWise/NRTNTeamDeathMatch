package com.topobon.nrtntdm.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.topobon.nrtntdm.TeamDeathMatch;
import com.topobon.nrtntdm.TeamDeathMatchNRTN;
import com.topobon.nrtntdm.gameprocessing.QueueProcess;
import com.topobon.nrtntdm.utils.Utility;

public class TeamDeathMatchNRTNCommands implements CommandExecutor {
	TeamDeathMatchNRTN instance;
	public TeamDeathMatchNRTNCommands(TeamDeathMatchNRTN instance) {
		this.instance = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//TODO Check for permissions
		if (cmd.getName().equalsIgnoreCase("tdm")) {
			if (args.length == 0) {
				CommandSender player = sender;
				player.sendMessage(Utility.messageToPlayer(("&8&m========&b&lChuunin Exams&8&m========")));
				player.sendMessage(Utility.messageToPlayer("&7/&c&lchuunin &8- &astart"));
				player.sendMessage(Utility.messageToPlayer("&7/&c&lchuunin &8- &astop"));
				player.sendMessage(Utility.messageToPlayer("&7/&c&lchuunin &8- &afind &e(&a&lWIP!!!&e)"));
				player.sendMessage(Utility.messageToPlayer("&8&m=============================="));
			}

			if (args.length > 0) {

				if (args[0].equalsIgnoreCase("start") && (!TeamDeathMatch.isGameRunning())) {
					TeamDeathMatch.startGame();
					
				}
				if (args[0].equalsIgnoreCase("join") && (TeamDeathMatch.isGameRunning()) && sender instanceof Player) {
					Player pSender = (Player) sender;
					QueueProcess.setPlayerInQueue(pSender);
					
				}

			}

		}
		return false;
	}

}
