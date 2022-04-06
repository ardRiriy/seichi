package com.github.ardririy.seichi;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Guis implements Listener {

    private Inventory inv;

    public void TrashBox(final HumanEntity ent){
        inv = Bukkit.createInventory(null,54,"TrashBox");
        ent.openInventory(inv);
    }

    public void Toolbox(final HumanEntity ent) {
        inv = Bukkit.createInventory(null, 9, "Example");

        initializeItems();

        ent.openInventory(inv);

    }
    public void enchant(ItemStack item){
        Map<Enchantment,Integer> map = new HashMap<>();
        map.put(Enchantment.DIG_SPEED, 4);
        map.put(Enchantment.DURABILITY, 10);
        item.addUnsafeEnchantments(map);
    }

    public void initializeItems() {
        //ここもうちょっときれいにしたい
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack shovel = new ItemStack(Material.DIAMOND_SPADE);
        ItemStack  scissor = new ItemStack(Material.SHEARS);
        enchant(pickaxe);
        enchant(axe);
        enchant(shovel);
        enchant(scissor);

        inv.addItem(createGuiItem(pickaxe, "DIAMOND_PICKAXE"));
        inv.addItem(createGuiItem(axe,"DIAMOND_AXE"));
        inv.addItem(createGuiItem(shovel,"DIAMOND_SPADE"));
        inv.addItem(createGuiItem(scissor,"SHEARS"));

    }

    protected ItemStack createGuiItem(final ItemStack item, final String name, final String... lore) {
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

}



