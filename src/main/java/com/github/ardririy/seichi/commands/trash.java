package com.github.ardririy.seichi.commands;

import com.github.ardririy.seichi.Guis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

public class trash implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Guis guis = new Guis();
        guis.TrashBox((HumanEntity) sender);
        return false;
    }
}
