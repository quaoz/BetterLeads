package com.github.quaoz.betterleads;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BetterLeadsConfig {
	public static final Path CONFIG_FILE_PATH = Paths.get("config/betterleads.toml");
	private static final boolean DEFAULT_VILLAGERS_ENABLED = true;
	private static final boolean DEFAULT_HOSTILES_ENABLED = false;
	private static final boolean DEFAULT_WATER_CREATURES_ENABLED = false;
	private static final boolean DEFAULT_TURTLES_ENABLED = true;
	private static final boolean DEFAULT_AMBIENTS_ENABLED = false;
	private static final boolean DEFAULT_PANDAS_ENABLED = false;
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
		this.mod.log("Config loaded");
	}

	// Resets the config
	public void reset() {
		this.setLeashableVillagers(DEFAULT_VILLAGERS_ENABLED);
		this.setLeashableHostileMobs(DEFAULT_HOSTILES_ENABLED);
		this.setLeashableWaterCreatures(DEFAULT_WATER_CREATURES_ENABLED);
		this.setLeashableTurtles(DEFAULT_TURTLES_ENABLED);
		this.setLeashableAmbientMobs(DEFAULT_AMBIENTS_ENABLED);
		this.setLeashablePandas(DEFAULT_PANDAS_ENABLED);
		this.mod.log("Config reset");
	}

	// Returns whether villagers can be leashed
	public boolean getLeashableVillagers() {
		return this.config.getOrElse("leashable_villagers", DEFAULT_VILLAGERS_ENABLED);
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

	// Returns whether turtles can be leashed
	public boolean getLeashableTurtles() {
		return this.config.getOrElse("leashable_turtles", DEFAULT_TURTLES_ENABLED);
	}

	// Sets whether turtles can be leashed
	public void setLeashableTurtles(boolean enabled) {
		this.config.set("leashable_turtles", enabled);
	}

	// Returns whether ambient entities (bats) can be leashed
	public boolean getLeashableAmbientMobs() {
		return this.config.getOrElse("leashable_ambients", DEFAULT_AMBIENTS_ENABLED);
	}

	// Sets whether ambient entities (bats) can be leashed
	public void setLeashableAmbientMobs(boolean enabled) {
		this.config.set("leashable_ambients", enabled);
	}

	// Returns whether pandas can be leashed
	public boolean getLeashablePandas() {
		return this.config.getOrElse("leashable_pandas", DEFAULT_PANDAS_ENABLED);
	}

	// Sets whether pandas can be leashed
	public void setLeashablePandas(boolean enabled) {
		this.config.set("leashable_pandas", enabled);
	}
}
