package com.github.quaoz.betterleads;

import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.option.SpruceBooleanOption;
import me.lambdaurora.spruceui.option.SpruceOption;
import me.lambdaurora.spruceui.option.SpruceSimpleActionOption;
import me.lambdaurora.spruceui.screen.SpruceScreen;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import me.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SettingsScreen extends SpruceScreen {

	private final BetterLeadsConfig config;
	private final Screen parent;
	private final SpruceOption villagerOption;
	private final SpruceOption hostileOption;
	private final SpruceOption waterCreatureOption;
	private final SpruceOption turtleOption;
	private final SpruceOption resetOption;
	private SpruceOptionListWidget list;

	public SettingsScreen(@Nullable Screen parent) {
		super(new TranslatableText("betterleads.menu.title"));
		this.parent = parent;
		this.config = BetterLeads.get().config;

		this.villagerOption = new SpruceBooleanOption("lead.merchants.option",
				this.config::getLeashableVillagers,
				this.config::setLeashableVillagers,
				new TranslatableText("lead.merchants.option"), true);

		this.hostileOption = new SpruceBooleanOption("lead.hostiles.option",
				this.config::getLeashableHostileMobs,
				this.config::setLeashableHostileMobs,
				new TranslatableText("lead.hostiles.option"), true);

		this.waterCreatureOption = new SpruceBooleanOption("lead.watercreatures.option",
				this.config::getLeashableWaterCreatures,
				this.config::setLeashableWaterCreatures,
				new TranslatableText("lead.watercreatures.option"), true);

		this.turtleOption = new SpruceBooleanOption("lead.turtles.option",
				this.config::getLeashableTurtles,
				this.config::setLeashableTurtles,
				new TranslatableText("lead.turtles.option"), true);

		this.resetOption = SpruceSimpleActionOption.reset(btn -> {
			this.config.reset();
			MinecraftClient client = MinecraftClient.getInstance();
			this.init(client, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight());
		});
	}

	private int getTextHeight() {
		return (5 + this.textRenderer.fontHeight) * 3 + 5;
	}

	@Override
	public void renderBackground(MatrixStack matrices) {
		this.renderBackgroundTexture(0);
	}

	@Override
	protected void init() {
		super.init();

		this.list = new SpruceOptionListWidget(Position.of(this, 0, 43), this.width, this.height - 43 - 29 - this.getTextHeight());
		this.list.addSingleOptionEntry(this.villagerOption);
		this.list.addSingleOptionEntry(this.hostileOption);
		this.list.addSingleOptionEntry(this.waterCreatureOption);
		this.list.addSingleOptionEntry(this.turtleOption);
		this.addChild(list);

		this.addChild(this.resetOption.createWidget(Position.of(this, this.width / 2 - 155, this.height - 29), 150));
		this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 155 + 160, this.height - 29), 150, 20, new TranslatableText("gui.done"),
				(btn) -> {
					assert this.client != null;
					this.client.openScreen(this.parent);
				}));
	}
}
