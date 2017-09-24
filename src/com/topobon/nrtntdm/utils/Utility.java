package com.topobon.nrtntdm.utils;

import org.bukkit.ChatColor;

import com.topobon.nrtntdm.TeamDeathMatchNRTN;

public class Utility {
	public static String messageToPlayer(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', TeamDeathMatchNRTN.getInitials() + " " + message));
    }
	
}