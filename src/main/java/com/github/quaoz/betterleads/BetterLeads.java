package com.github.quaoz.betterleads;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterLeads implements ClientModInitializer {
	public static final String MODID = "betterleads";
	public final Logger logger = LogManager.getLogger(MODID);
	private static BetterLeads INSTANCE;
	public final BetterLeadsConfig config = new BetterLeadsConfig(this);

	public static BetterLeads get() {
		return INSTANCE;
	}

	// Loads the configs when the client starts
	@Override
	public void onInitializeClient() {
		INSTANCE = this;
		this.log("uwu");

		this.config.load();

	}

	public void log(String info) {
		this.logger.info("[BetterLeads] " + info);
	}

	public void warn(String info) {
		this.logger.warn("[BetterLeads] " + info);
	}

}
