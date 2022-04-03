package com.github.ardririy.seichi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class grading implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        //nullチェック
        String subCmmand = args.length == 0 ? "" : args[0];
        ConnectDB cdb = new ConnectDB();
        Player player = (Player) sender;
        if(subCmmand.equalsIgnoreCase("ranking")) {
            //整地数ランキング上位5名を表示
            sender.sendMessage(cdb.dig_ranking());

        }else if(subCmmand.equalsIgnoreCase("trash")) {
            //インベントリを閉じた際にアイテムが消滅するゴミ箱を表示
            Guis guis = new Guis();
            guis.openTrashBox((HumanEntity) sender);
        }else if(subCmmand.equalsIgnoreCase("status")){
            sender.sendMessage(sender.getName() + " : " + cdb.getDigAmount(String.valueOf(player.getUniqueId())) + "blocks");

        }else{
            sender.sendMessage("/grading ranking - Show ranking");
            sender.sendMessage("/grading trash - Open trash box");
            sender.sendMessage("/grading status - Show your status");
        }
        return false;
    }
}
