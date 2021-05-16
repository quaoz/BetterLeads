package com.github.quaoz;

import net.fabricmc.api.ClientModInitializer;

public class Leads implements ClientModInitializer {
    private static Leads INSTANCE;
    public final LeadsConfig config = new LeadsConfig(this);

    public static Leads get() {
        return INSTANCE;
    }

    // Loads the configs when the client starts
    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        this.config.load();

    }
}
