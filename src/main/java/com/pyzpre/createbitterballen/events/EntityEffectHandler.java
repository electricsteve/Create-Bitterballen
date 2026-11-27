package com.pyzpre.createbitterballen.events;

import com.pyzpre.createbitterballen.index.BlockRegistry;
import com.pyzpre.createbitterballen.index.EffectRegistry;
import com.pyzpre.createbitterballen.index.FluidRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class EntityEffectHandler {
    @SubscribeEvent
    public void onLevelTick(LevelTickEvent.Post event) {
        Level level = event.getLevel();

        if (level.isClientSide()) return;
        for (Player player : level.players()) {
            BlockPos playerPos = player.blockPosition();
            AABB dynamicBounds = new AABB(
                playerPos.getX() - 16, playerPos.getY() - 16, playerPos.getZ() - 16,
                playerPos.getX() + 16, playerPos.getY() + 16, playerPos.getZ() + 16
            );

            for (Entity entity : level.getEntities(null, dynamicBounds))
                if (entity instanceof LivingEntity living && isInFryingOil(living, level)) applyOilEffect(living);
        }
    }

    private boolean isInFryingOil(LivingEntity entity, Level level) {
        BlockPos pos = entity.blockPosition();
        FluidState fluidState = level.getFluidState(pos);

        Fluid fluid = fluidState.getType();

        return fluid.isSame(FluidRegistry.FRYING_OIL.get());
    }

    private void applyOilEffect(LivingEntity entity) {
        if (!entity.hasEffect(EffectRegistry.OILED_UP))
            entity.addEffect(new MobEffectInstance(EffectRegistry.OILED_UP, 200, 1));
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getLevel().isClientSide()) return;
        Entity target = event.getTarget();
        Player player = event.getEntity();
        ItemStack heldItem = player.getItemInHand(event.getHand());

        if (!(target instanceof Chicken chicken)) return;
        if (heldItem.getItem() != BlockRegistry.SUNFLOWERSTEM.get().asItem()) return;
        if (!chicken.isAlive() || chicken.isBaby() || chicken.isInLove()) return;

        if (!player.getAbilities().instabuild) heldItem.shrink(1);
        chicken.setInLove(player);
        player.swing(event.getHand(), true);
        Level level = player.level();
        level.broadcastEntityEvent(chicken, (byte) 18);
        event.setCanceled(true);
    }
}
