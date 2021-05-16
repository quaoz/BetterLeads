package com.github.quaoz;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LeadsConfig {
    public static final Path CONFIG_FILE_PATH = Paths.get("config/leads.toml");
    private static final boolean DEFAULT_VILLAGER_ENABLED = true;
    private static final boolean DEFAULT_HOSTILES_ENABLED = false;
    private final Leads mod;
    protected FileConfig config;

    // Reads in the config file
    public LeadsConfig(@NotNull Leads mod) {
        this.mod = mod;
        this.config = FileConfig.builder(CONFIG_FILE_PATH)
                .defaultResource("src/main/resources/leads.toml")
                .concurrent()
                .defaultResource("/leads.toml")
                .autoreload()
                .autosave()
                .build();
    }

    // Loads the config
    public void load() {
        this.config.load();
    }

    // Resets the config
    public void reset() {
        this.setLeashableVillagers(DEFAULT_VILLAGER_ENABLED);
    }

    // Returns whether villagers can be leashed
    public boolean getLeashableVillagers() {
        return this.config.getOrElse("leashable_villagers", DEFAULT_VILLAGER_ENABLED);
    }

    // Sets whether villagers can be leashed
    public void setLeashableVillagers(boolean enabled) {
        this.config.set("leashable_villagers", enabled);
    }

    // Returns whether hostiles can be leashed
    public boolean getLeashableHostileMobs() {
        return this.config.getOrElse("leashable_hostiles", DEFAULT_HOSTILES_ENABLED);
    }

    // Sets whether hostiles can be leashed
    public void setLeashableHostileMobs(boolean enabled) {
        this.config.set("leashable_hostiles", enabled);
    }
}
