package com.github.quaoz;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LeadsConfig {
    public static final Path CONFIG_FILE_PATH = Paths.get("config/leads.toml");
    private static final boolean DEFAULT_VILLAGER_ENABLED = true;
    private final Leads mod;
    protected FileConfig config;

    public LeadsConfig(@NotNull Leads mod) {
        this.mod = mod;
        this.config = FileConfig.builder(CONFIG_FILE_PATH)
                .concurrent()
                .defaultResource("/leads.toml")
                .autoreload()
                .autosave()
                .build();
    }

    public void load() {
        this.config.load();
    }

    public void reset() {
        this.setLeashableVillagers(DEFAULT_VILLAGER_ENABLED);
    }

    public boolean getLeashableVillagers() {
        return this.config.getOrElse("leashable_villagers", DEFAULT_VILLAGER_ENABLED);
    }

    public void setLeashableVillagers(boolean enabled) {
        this.config.set("leashable_villagers", enabled);
    }
}
