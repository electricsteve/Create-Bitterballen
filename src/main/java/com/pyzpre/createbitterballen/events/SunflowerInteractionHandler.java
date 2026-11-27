package com.pyzpre.createbitterballen.events;


import com.pyzpre.createbitterballen.CreateBitterballen;
import com.pyzpre.createbitterballen.block.CBSunflowerBlock;
import com.pyzpre.createbitterballen.index.BlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class SunflowerInteractionHandler {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);
        Player player = event.getEntity();
        Level world = event.getLevel();

        if (state.getBlock() != Blocks.SUNFLOWER) return;
        ItemStack sunflowerHead = new ItemStack(Items.SUNFLOWER);
        Block.popResource(world, pos, sunflowerHead);
        boolean isUpperPart = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER;
        BlockPos upperPartPos = isUpperPart ? pos : pos.above();
        world.setBlock(upperPartPos, Blocks.AIR.defaultBlockState(), 18);
        if (isUpperPart) pos = pos.below();
        world.setBlock(pos, BlockRegistry.SUNFLOWERSTEM.get().defaultBlockState().setValue(CBSunflowerBlock.AGE, 2), 3);
        world.setBlock(
            pos.above(),
            BlockRegistry.SUNFLOWERSTEM.get().defaultBlockState()
                .setValue(CBSunflowerBlock.HALF, DoubleBlockHalf.UPPER)
                .setValue(CBSunflowerBlock.AGE, 2),
            3
        );
        world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (player instanceof ServerPlayer serverPlayer)
            CreateBitterballen.grantAdvancementCriterion(serverPlayer, "create_bic_bit:lawn_defender", "harvested_sunflower");
        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }
}



