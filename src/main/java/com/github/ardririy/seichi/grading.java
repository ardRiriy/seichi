package com.github.ardririy.seichi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

public class grading implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        //nullチェック
        String subCmmand = args.length == 0 ? "" : args[0];

        if(subCmmand.equalsIgnoreCase("ranking")) {
            //整地数ランキング上位5名を表示
            ConnectDB cdb = new ConnectDB();
            sender.sendMessage(cdb.dig_ranking());

        }else if(subCmmand.equalsIgnoreCase("trash")){
            //インベントリを閉じた際にアイテムが消滅するゴミ箱を表示
            Guis guis = new Guis();
            guis.openTrashBox((HumanEntity) sender);

        }else{
            sender.sendMessage("/grading ranking - Show ranking");
            sender.sendMessage("/grading trash - Open trash box");
        }
        return false;
    }
}
