package ru.urbanatech.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class UrbanaTechClient implements ClientModInitializer {
    private static float pee = 8f;
    private static float poop = 4f;
    private static int tickCounter = 0;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;
            if (++tickCounter >= 120) {
                tickCounter = 0;
                pee = Math.min(100f, pee + 0.65f);
                poop = Math.min(100f, poop + 0.35f);
            }
        });
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> renderNeeds(drawContext));
    }

    private static void renderNeeds(DrawContext ctx) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;
        int x = 10, y = 10, width = 112, height = 8;
        ctx.fill(x - 3, y - 3, x + width + 3, y + 36, 0x99000000);
        ctx.drawTextWithShadow(client.textRenderer, "НУЖДЫ", x, y - 1, 0xFFFFFFFF);
        drawBar(ctx, x, y + 12, width, height, pee, "Писать", 0xFF4AA3FF);
        drawBar(ctx, x, y + 25, width, height, poop, "Какать", 0xFFB98554);
    }

    private static void drawBar(DrawContext ctx, int x, int y, int width, int height, float value, String label, int color) {
        ctx.fill(x, y, x + width, y + height, 0xFF333333);
        int fill = MathHelper.floor(width * MathHelper.clamp(value / 100f, 0f, 1f));
        ctx.fill(x, y, x + fill, y + height, color);
        ctx.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,
                label + " " + Math.round(value) + "%", x + width + 7, y - 1, 0xFFFFFFFF);
    }
}
