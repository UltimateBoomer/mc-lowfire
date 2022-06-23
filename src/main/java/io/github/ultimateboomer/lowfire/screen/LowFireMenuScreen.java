package io.github.ultimateboomer.lowfire.screen;

import io.github.ultimateboomer.lowfire.LowFire;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.text.NumberFormat;

public class LowFireMenuScreen extends Screen {
    private final Screen parent;

    private SliderWidget offsetSlider;

    private final int sliderHeight = 20;
    private final int sliderWidth = 100;

    private final NumberFormat numberFormat;

    public LowFireMenuScreen(Screen parent) {
        super(Text.translatable("lowfire.menu"));
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

        this.addDrawableChild(offsetSlider);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || LowFire.INSTANCE.getLowFireMenuKey().matchesKey(keyCode, scanCode)) {
            this.client.setScreen(parent);
            LowFire.configHandler.writeConfig(LowFire.config);
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFF);

        super.render(matrices, mouseX, mouseY, delta);
    }

    class FireOffsetSliderWidget extends SliderWidget {
        private static final double SLIDER_MAX = 0.5;

        public FireOffsetSliderWidget(int x, int y, int width, int height) {
            super(x, y, width, height, Text.empty(), LowFire.config.fireOffset / SLIDER_MAX);
            updateMessage();
        }

        @Override
        protected void updateMessage() {
            this.setMessage(Text.translatable("lowfire.menu.fireOffset")
                    .append(": " + numberFormat.format(value * SLIDER_MAX)));
        }

        @Override
        protected void applyValue() {
            LowFire.LOGGER.debug("Value applied");
            LowFire.config.fireOffset = Math.round(value * SLIDER_MAX * 10.0) / 10.0;
        }
    }
}
