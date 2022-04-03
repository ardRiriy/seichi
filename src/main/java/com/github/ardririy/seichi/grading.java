package com.github.ardririy.seichi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class grading implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        //nullチェック
        String subCmmand = args.length == 0 ? "" : args[0];

        if(subCmmand.equalsIgnoreCase("ranking")) {
            //整地数ランキング上位5名と自分の順位を表示
            ConnectDB cdb = new ConnectDB();
            sender.sendMessage(cdb.dig_ranking());
            sender.sendMessage("-----------------------------");
            sender.sendMessage("a");

        }else if(subCmmand.equalsIgnoreCase("trash")){
            //インベントリを閉じた際にアイテムが消滅するゴミ箱を表示
            sender.sendMessage("Here is trash box");
        }else{
            sender.sendMessage("/grading ranking - Show ranking");
            sender.sendMessage("/grading trash - Open trash box");
        }
        return false;
    }
}
