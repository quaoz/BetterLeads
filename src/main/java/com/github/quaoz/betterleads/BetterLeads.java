package com.github.quaoz.betterleads;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterLeads implements ClientModInitializer {
	public static final String MODID = "betterleads";
	private static BetterLeads INSTANCE;
	private static final MinecraftClient client = MinecraftClient.getInstance();
	public final Logger logger = LogManager.getLogger(MODID);
	public final BetterLeadsConfig config = new BetterLeadsConfig(this);

	// Returns false when connected to a server
	public boolean isEnabled() {
		this.log("The Integrated Server isn't running, disabling BetterLeads");
		return (client.isIntegratedServerRunning());
	}

	public static BetterLeads get() {
		return INSTANCE;
	}

	// Loads the configs when the client starts
	@Override
	public void onInitializeClient() {
		INSTANCE = this;
		this.log("its alive");

		this.config.load();
	}

	public void log(String info) {
		this.logger.info("[BetterLeads] " + info);
	}

	public void warn(String info) {
		this.logger.warn("[BetterLeads] " + info);
	}

}
