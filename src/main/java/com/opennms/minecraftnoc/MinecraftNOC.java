package com.opennms.minecraftnoc;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.map.MapRenderer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftNOC extends JavaPlugin {
    private Block currentBlock;
    private Entity currentEntity;
    private NOCMapRenderer mapRenderer;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        currentBlock = null;
        currentEntity = null;

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ClickListener(this), this);
        pm.registerEvents(new MapListener(this), this);
        getCommand("getpng").setExecutor(new CommandGetPNG(this));
        getCommand("getmetric").setExecutor(new CommandGetMetric(this));

        mapRenderer = new NOCMapRenderer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Block getCurrentBlock() { return currentBlock; }
    public void setCurrentBlock(Block block) { currentBlock = block; }
    public void clearCurrentBlock() { currentBlock = null; }

    public Entity getCurrentEntity() { return currentEntity; }
    public void setCurrentEntity(Entity ent) { currentEntity = ent; }
    public void clearCurrentEntity() { currentEntity = null; }

    public NOCMapRenderer getMapRenderer() { return mapRenderer; }
}
