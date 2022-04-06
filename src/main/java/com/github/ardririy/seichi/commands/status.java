package com.github.ardririy.seichi.commands;

import com.github.ardririy.seichi.ConnectDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class status implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        ConnectDB cdb = new ConnectDB();

        //実行したプレイヤーの採掘量を表示.
        sender.sendMessage( "#" + cdb.PlayerRank(sender.getName()) + " : " + sender.getName() + " : " + cdb.getDigAmount(sender.getName()) + "blocks");
        return false;
    }
}
