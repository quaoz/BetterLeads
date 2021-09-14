package com.github.quaoz.betterleads;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.option.SpruceBooleanOption;
import dev.lambdaurora.spruceui.option.SpruceOption;
import dev.lambdaurora.spruceui.option.SpruceSimpleActionOption;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
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
	private final SpruceOption merchantsOption;
	private final SpruceOption hostilesOption;
	private final SpruceOption waterCreaturesOption;
	private final SpruceOption turtlesOption;
	private final SpruceOption ambientsOption;
	private final SpruceOption pandasOption;
	private final SpruceOption resetOption;

	public SettingsScreen(@Nullable Screen parent) {
		super(new TranslatableText("betterleads.menu.title"));
		this.parent = parent;
		this.config = BetterLeads.get().config;

		this.merchantsOption = new SpruceBooleanOption("betterleads.merchants.option",
				this.config::getLeashableVillagers,
				this.config::setLeashableVillagers,
				new TranslatableText("betterleads.merchants.option"), true);

		this.hostilesOption = new SpruceBooleanOption("betterleads.hostiles.option",
				this.config::getLeashableHostileMobs,
				this.config::setLeashableHostileMobs,
				new TranslatableText("betterleads.hostiles.option"), true);

		this.waterCreaturesOption = new SpruceBooleanOption("betterleads.watercreatures.option",
				this.config::getLeashableWaterCreatures,
				this.config::setLeashableWaterCreatures,
				new TranslatableText("betterleads.watercreatures.option"), true);

		this.turtlesOption = new SpruceBooleanOption("betterleads.turtles.option",
				this.config::getLeashableTurtles,
				this.config::setLeashableTurtles,
				new TranslatableText("betterleads.turtles.option"), true);

		this.ambientsOption = new SpruceBooleanOption("betterleads.ambients.option",
				this.config::getLeashableAmbientMobs,
				this.config::setLeashableAmbientMobs,
				new TranslatableText("betterleads.ambients.option"), true);

		this.pandasOption = new SpruceBooleanOption("betterleads.pandas.option",
				this.config::getLeashablePandas,
				this.config::setLeashablePandas,
				new TranslatableText("betterleads.pandas.option"), true);

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

		SpruceOptionListWidget list = new SpruceOptionListWidget(Position.of(this, 0, 43), this.width, this.height - 43 - 29 - this.getTextHeight());
		list.addSingleOptionEntry(this.merchantsOption);
		list.addSingleOptionEntry(this.hostilesOption);
		list.addSingleOptionEntry(this.waterCreaturesOption);
		list.addSingleOptionEntry(this.turtlesOption);
		list.addSingleOptionEntry(this.ambientsOption);
		list.addSingleOptionEntry(this.pandasOption);
		this.addDrawableChild(list);

		this.addDrawableChild(this.resetOption.createWidget(Position.of(this, this.width / 2 - 155, this.height - 29), 150));
		this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 155 + 160, this.height - 29), 150, 20, new TranslatableText("gui.done"),
				(btn) -> {
					assert this.client != null;
					this.client.setScreen(this.parent);
				}));
	}
}
