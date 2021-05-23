package com.github.quaoz.betterleads;

import net.fabricmc.api.ClientModInitializer;

public class BetterLeads implements ClientModInitializer {
	private static BetterLeads INSTANCE;
	public final BetterLeadsConfig config = new BetterLeadsConfig(this);

	public static BetterLeads get() {
		return INSTANCE;
	}

	// Loads the configs when the client starts
	@Override
	public void onInitializeClient() {
		INSTANCE = this;
		this.config.load();

	}
}
