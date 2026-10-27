package ru.urbanatech;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UtilityBlock extends Block {
    public UtilityBlock(Settings settings) { super(settings); }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                 net.minecraft.util.hit.BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        String key = state.getBlock().toString();
        if (state.isOf(UrbanaTechMod.TOILET)) {
            player.sendMessage(Text.literal("§b[Нужды] §fТы воспользовался унитазом. Полосы нужд сброшены."), true);
            UrbanaTechServerHooks.resetNeeds(player);
        } else if (state.isOf(UrbanaTechMod.ROUTER)) {
            player.sendMessage(Text.literal("§b[Интернет] §fРоутер включён. Интернет работает в радиусе 16 блоков."), false);
        } else if (state.isOf(UrbanaTechMod.DATACENTER)) {
            player.sendMessage(Text.literal("§b[ИИ] §fДата-центр обнаружен. Установи RAM в будущих версиях для расширения мощности."), false);
        } else if (state.isOf(UrbanaTechMod.TV)) {
            player.sendMessage(Text.literal("§b[Смарт-ТВ] §fПроверь роутер рядом. В этой сборке внешнее видео ещё не встроено в игровой экран."), false);
        } else if (state.isOf(UrbanaTechMod.COMPUTER)) {
            player.sendMessage(Text.literal("§b[Компьютер] §fИнтернет и браузер будут доступны через клиентское окно в следующем этапе."), false);
        }
        return ActionResult.SUCCESS;
    }
}
