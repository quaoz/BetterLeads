package com.github.quaoz.betterleads;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BetterLeadsConfig {
	public static final Path CONFIG_FILE_PATH = Paths.get("config/betterleads.toml");
	private static final boolean DEFAULT_VILLAGER_ENABLED = true;
	private static final boolean DEFAULT_HOSTILES_ENABLED = false;
	private static final boolean DEFAULT_WATER_CREATURES_ENABLED = false;
	private final BetterLeads mod;
	protected FileConfig config;

	// Reads in the config file
	public BetterLeadsConfig(@NotNull BetterLeads mod) {
		this.mod = mod;
		this.config = FileConfig.builder(CONFIG_FILE_PATH)
				.defaultResource("/betterleads.toml")
				.concurrent()
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
		this.setLeashableHostileMobs(DEFAULT_HOSTILES_ENABLED);
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

	// Returns whether water creatures (fish) can be leashed
	public boolean getLeashableWaterCreatures() {
		return this.config.getOrElse("leashable_watercreatures", DEFAULT_WATER_CREATURES_ENABLED);
	}

	// Sets whether water creatures (fish) can be leashed
	public void setLeashableWaterCreatures(boolean enabled) {
		this.config.set("leashable_watercreatures", enabled);
	}
}
