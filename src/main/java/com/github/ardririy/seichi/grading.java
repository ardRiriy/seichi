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
        Guis guis = new Guis();
        Player player = (Player) sender;

        if(subCmmand.equalsIgnoreCase("ranking")) {
            //整地数ランキング上位5名を表示
            //Todo:実行者のランキングも表示できるようにする.
            sender.sendMessage(cdb.dig_ranking());

        }else if(subCmmand.equalsIgnoreCase("trash")) {
            //インベントリを閉じた際にアイテムが消滅するゴミ箱を表示
            guis.TrashBox((HumanEntity) sender);

        }else if(subCmmand.equalsIgnoreCase("status")) {
            //実行したプレイヤーの採掘量を表示.
            //Todo:実行者のRankingも同時に表示できるようにする.
            sender.sendMessage(sender.getName() + " : " + cdb.getDigAmount(String.valueOf(player.getUniqueId())) + "blocks");

        }else if(subCmmand.equalsIgnoreCase("tool")){
            //コマンド実行 -> Tool 配布
            guis.Toolbox(player);

        }else{
            sender.sendMessage("/grading ranking - Show ranking");
            sender.sendMessage("/grading trash - Open trash box");
            sender.sendMessage("/grading status - Show your status");
            sender.sendMessage("/grading tool - Get new tools.");
        }
        return false;
    }
}
