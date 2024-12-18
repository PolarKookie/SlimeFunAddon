package me.stella.slimefunaddon;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class Slimefun extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {

        Material[] myArray = {Material.COBBLESTONE, Material.ANDESITE, Material.DIORITE, Material.GRANITE, Material.TUFF, Material.DIRT};
        // Read something from your config.yml
        Config cfg = new Config(this);

        cfg.getBoolean("options.auto-update");// You could start an Auto-Updater for example

        /*
         * 1. Creating a new Category
         * This Category will use the following ItemStack
         */
        ItemStack itemGroupItem = new CustomItemStack(Material.DIAMOND, "&aX&eP Gainin&ag", "", "&a> Click to open");

        // Give your Category a unique id.
        NamespacedKey itemGroupId = new NamespacedKey(this, "addon_category");
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);

        /*
         * 2. Create a new SlimefunItemStack
         * This class has many constructors, it is very important
         * that you give each item a unique id.
         */
        SlimefunItemStack xpToken = new SlimefunItemStack("XP_TOKEN", Material.LIME_DYE, "&aXP Token &7| &fTier: 1");
        SlimefunItemStack xpToken2 = new SlimefunItemStack("XP_TOKEN2", Material.SLIME_BALL, "&aXP Token &7| &fTier: 2");
        SlimefunItemStack xpToken3 = new SlimefunItemStack("XP_TOKEN3", Material.SLIME_BLOCK, "&aXP Token &7| &fTier: 3");

        /*
         * 3. Creating a Recipe
         * The Recipe is an ItemStack Array with a length of 9.
         * It represents a Shaped Recipe in a 3x3 crafting grid.
         * The machine in which this recipe is crafted in is specified
         * further down as the RecipeType.
         */
        ItemStack[] xpTokenRecipe = {
                new ItemStack(Material.COBBLESTONE), null, null,
                null, null, null,
                null, null, null,
        };
        ItemStack[] xpTokenRecipe2 = {
                new ItemStack(Material.IRON_INGOT), null, null,
                null, null, null,
                null, null, null,
        };
        ItemStack[] xpTokenRecipe3 = {
                new ItemStack(Material.DIAMOND), null, null,
                null, null, null,
                null, null, null,
        };


        /*
         * 4. Registering the Item
         * Now you just have to register the item.
         * RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in
         * which this item is crafted in.
         * Recipe Types from Slimefun itself will automatically add the recipe to that machine.
         */
        XPToken xptoken = new XPToken(itemGroup, xpToken, RecipeType.MAGIC_WORKBENCH, xpTokenRecipe);
        XPToken2 xptoken2 = new XPToken2(itemGroup, xpToken2, RecipeType.MAGIC_WORKBENCH, xpTokenRecipe2);
        XPToken3 xptoken3 = new XPToken3(itemGroup, xpToken3, RecipeType.MAGIC_WORKBENCH, xpTokenRecipe3);
        xptoken.register(this);
        xptoken2.register(this);
        xptoken3.register(this);

        NamespacedKey xpTokenKey = new NamespacedKey(this, "xp_token");
        Research xpTokenResearch = new Research(xpTokenKey, 508091138, "Consumable Experience", 15);

        xpTokenResearch.register();
    }

    public static class XPToken extends SlimefunItem {

        public XPToken(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
            super(itemGroup, item, recipeType, recipe);
        }

        @Override
        public void preRegister() {
            ItemUseHandler itemUseHandler = this::onItemRightClick;
            addItemHandler(itemUseHandler);
        }

        private void onItemRightClick(PlayerRightClickEvent event) {
            Player p = event.getPlayer();
            event.getPlayer().giveExp(1);
            event.getItem().setAmount(event.getItem().getAmount() - 1);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

        }

    }

    public static class XPToken2 extends SlimefunItem {

        public XPToken2(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
            super(itemGroup, item, recipeType, recipe);
        }

        @Override
        public void preRegister() {
            ItemUseHandler itemUseHandler = this::onItemRightClick;
            addItemHandler(itemUseHandler);
        }

        private void onItemRightClick(PlayerRightClickEvent event) {
            Player p = event.getPlayer();
            event.getPlayer().giveExp(5);
            event.getItem().setAmount(event.getItem().getAmount() - 1);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

        }

    }

    public static class XPToken3 extends SlimefunItem {

        public XPToken3(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
            super(itemGroup, item, recipeType, recipe);
        }

        @Override
        public void preRegister() {
            ItemUseHandler itemUseHandler = this::onItemRightClick;
            addItemHandler(itemUseHandler);
        }

        private void onItemRightClick(PlayerRightClickEvent event) {
            event.cancel();
            Player p = event.getPlayer();
            event.getPlayer().giveExp(20);
            event.getItem().setAmount(event.getItem().getAmount() - 1);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

        }

    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
