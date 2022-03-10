package io.github.ultimateboomer.lowfire.screen;

import java.text.NumberFormat;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.ultimateboomer.lowfire.LowFire;
import io.github.ultimateboomer.lowfire.config.LowFireConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.SliderButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;

public class LowFireMenuScreen extends Screen {
    private final Screen parent;

    private SliderButton offsetSlider;

    private final int sliderHeight = 20;
    private final int sliderWidth = 100;

    private final NumberFormat numberFormat;

    public LowFireMenuScreen(Screen parent) {
        super(new TranslatableComponent("lowfire.menu"));
        this.parent = parent;

        this.numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(1);
        numberFormat.setMaximumFractionDigits(1);
    }

    @Override
    protected void init() {
        super.init();

        this.offsetSlider = new FireOffsetSliderWidget((this.width - sliderWidth) / 2, this.height / 2,
                sliderWidth, sliderHeight);

        this.addRenderableWidget(offsetSlider);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || LowFire.INSTANCE.getLowFireMenuKey().matches(keyCode, scanCode)) {
            this.minecraft.setScreen(parent);
            LowFireConfig.client.fireOffset.save();
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        drawCenteredString(matrices, minecraft.font, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFF);

        super.render(matrices, mouseX, mouseY, delta);
    }

    class FireOffsetSliderWidget extends SliderButton {
        private static final double SLIDER_MAX = 0.5;

        public FireOffsetSliderWidget(int x, int y, int width, int height) {
        	super(Minecraft.getInstance().options, x, y, width, height, 
        			new ProgressOption("", 0, SLIDER_MAX,
							(float)(LowFireConfig.client.fireOffset.get() / SLIDER_MAX),
							option -> LowFireConfig.client.fireOffset.get(),
							(option, doub) -> LowFireConfig.client.fireOffset.set(doub),
							(option, prog) -> TextComponent.EMPTY),
        			List.of(FormattedCharSequence.EMPTY));
            updateMessage();
        }

        @Override
        protected void updateMessage() {
            this.setMessage(new TranslatableComponent("lowfire.menu.fireOffset")
                    .append(": " + numberFormat.format(value * SLIDER_MAX)));
        }

        @Override
        protected void applyValue() {
            LowFire.LOGGER.debug("Value applied");
            LowFireConfig.client.fireOffset.set(Math.round(value * SLIDER_MAX * 10.0) / 10.0);
        }
    }
}
