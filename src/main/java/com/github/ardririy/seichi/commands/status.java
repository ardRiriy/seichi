package com.github.ardririy.seichi.commands;

import com.github.ardririy.seichi.ConnectDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class status implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        ConnectDB cdb = new ConnectDB();
        Player player = (Player) sender;

        //実行したプレイヤーの採掘量を表示.
        //Todo:実行者のRankingも同時に表示できるようにする.
        sender.sendMessage(sender.getName() + " : " + cdb.getDigAmount(String.valueOf(player.getUniqueId())) + "blocks");
        return false;
    }
}
