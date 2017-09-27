
/**
 * Copyright (c) 2017, HeroWise. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * The name of the author may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.topobon.nrtntdm.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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