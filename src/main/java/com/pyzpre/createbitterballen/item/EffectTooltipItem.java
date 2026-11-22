package com.pyzpre.createbitterballen.item;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class EffectTooltipItem extends Item {
    public EffectTooltipItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(itemStack, context, tooltip, flag);
        appendHoverText(this, tooltip, context.level());
    }

    public static void appendHoverText(Item item, List<Component> tooltip, @Nullable Level level) {
        FoodProperties foodProperties = item.components().get(DataComponents.FOOD);
        if (foodProperties == null) return;
        for (FoodProperties.PossibleEffect effect : foodProperties.effects()) {
            MobEffectInstance effectInstance = effect.effect();
            MutableComponent effectText = Component.translatable(effectInstance.getDescriptionId());
            if (effectInstance.getDuration() > 20) {
                effectText = Component.translatable("potion.withDuration", effectText,
                    MobEffectUtil.formatDuration(effectInstance, 1, level == null ? 20 : level.tickRateManager().tickrate()));
            }
            tooltip.add(effectText.withStyle(effectInstance.getEffect().value().getCategory().getTooltipFormatting()));
        }
    }
}
