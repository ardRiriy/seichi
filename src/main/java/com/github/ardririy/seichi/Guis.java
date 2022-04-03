package com.github.ardririy.seichi;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class Guis implements Listener {

    private Inventory inv;

    public void openTrashBox(final HumanEntity ent) {
        inv = Bukkit.createInventory(null,54,"TrashBox");
        ent.openInventory(inv);
    }
}
