package com.github.ardririy.seichi.commands;

import com.github.ardririy.seichi.ConnectDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ranking implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        //整地数ランキング上位5名を表示
        ConnectDB cdb = new ConnectDB();
        sender.sendMessage(cdb.dig_ranking());
        sender.sendMessage("------------------------");
        sender.sendMessage("#" + cdb.PlayerRank(sender.getName()) + " : " + sender.getName() + "(" + cdb.getDigAmount(sender.getName()) + "blocks" + ")");
        return false;
    }
}
