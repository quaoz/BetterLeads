package com.github.quaoz.betterleads;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterLeads implements ModInitializer {
	public static final String MODID = "betterleads";
	private static BetterLeads INSTANCE;
	public final Logger logger = LogManager.getLogger(MODID);
	public final BetterLeadsConfig config = new BetterLeadsConfig(this);

	public static BetterLeads get() {
		return INSTANCE;
	}

	@Override
	public void onInitialize() {
		INSTANCE = this;
		this.log("its alive");

		this.config.load();
	}

	public void log(String info) {
		this.logger.info("[BetterLeads/INFO] " + info);
	}

	public void warn(String info) {
		this.logger.warn("[BetterLeads/WARN] " + info);
	}
}
