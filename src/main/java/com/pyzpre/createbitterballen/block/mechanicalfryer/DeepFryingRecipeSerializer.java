package com.pyzpre.createbitterballen.block.mechanicalfryer;

import com.mojang.serialization.MapCodec;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DeepFryingRecipeSerializer implements RecipeSerializer<DeepFryingRecipe> {

    public static final DeepFryingRecipeSerializer INSTANCE = new DeepFryingRecipeSerializer();

    private DeepFryingRecipeSerializer() {
    }

    @Override
    public MapCodec<DeepFryingRecipe> codec() {
        return DeepFryingRecipe.CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DeepFryingRecipe> streamCodec() {
        return DeepFryingRecipe.STREAM_CODEC;
    }
}