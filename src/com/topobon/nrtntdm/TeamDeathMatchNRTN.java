
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
package com.topobon.nrtntdm;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <b>Team Death Match Extreme - NRTN </b>
 * <p>
 * This plugin was created by @HeroWise and allows you to create a TDM
 * simulation
 * <p>
 * You are welcome to use it or redistribute it under the following conditions:
 * <ul>
 * <li>Don't claim these classes or the whole as your own
 * <li>Tell the author before distributing
 * <li>Ask permission from server owner of NRTN
 * <li>Don't remove this disclaimer
 * </ul>
 * <i>It would be nice if you provide credit to me if you use this class or
 * plugin in a published / a already created project</i>
 * 
 * @author HeroWise
 * @version 1.10
 * 
 */
public class TeamDeathMatchNRTN extends JavaPlugin {
	private static Logo logo; // Logo object
	
	private static final Logo TEAHM_DEATH_MATCH_INITIALS = logo;

	/**
	 * Method: Calls when class runs {@link #getPluginLoader()}
	 * <p>
	 * It's work is to:
	 * <p>
	 * <ul>
	 * <li>Set Logo/Chevron
	 * <li>Register Classes
	 * <li>Register Events
	 * <li>Register Events
	 * <li>Register Commands
	 */
	public void onEnable() {
		
		setLogo(ChatColor.translateAlternateColorCodes('&', "&7&l[&6&lNaruto &c&lRTN&7&l] &r"));
		/**
		 * Talks about the TDM plugin and its state
		 * 
		 */
		System.out.println(logo.getLogo() + "TEAM DEATH MATCH Plugin has been deployed");
		System.out.println(logo.getLogo() + "Initiating Server Sequence....");
		System.out.println(logo.getLogo() + "TEAM DEATH MATCH Plugin is ready to be used!");

	}
	
	
	/**
	 * 
	 * @return Logo Object to #getLogo()  
	 */
	public static String getInitials(){
		return TEAHM_DEATH_MATCH_INITIALS.getLogo();
	}
	
	/**
	 * 
	 * @param string - to set Logo
	 */
	public void setLogo(String string){
		logo = new Logo(string);
	}
	
}
