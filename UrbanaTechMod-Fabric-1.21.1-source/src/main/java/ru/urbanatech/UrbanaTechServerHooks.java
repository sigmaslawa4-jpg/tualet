package ru.urbanatech;

import net.minecraft.entity.player.PlayerEntity;

public final class UrbanaTechServerHooks {
    private UrbanaTechServerHooks() {}
    public static void resetNeeds(PlayerEntity player) {
        // Needs are currently a client-side HUD prototype; persistence/synchronisation is planned.
    }
}
