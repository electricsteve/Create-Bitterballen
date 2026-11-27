package com.pyzpre.createbitterballen.index;

import com.pyzpre.createbitterballen.CreateBitterballen;
import com.pyzpre.createbitterballen.block.mechanicalfryer.DeepFryingRecipe;
import com.pyzpre.createbitterballen.block.mechanicalfryer.DeepFryingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class RecipeRegistry {
    public static final CBRecipe<DeepFryingRecipeSerializer, DeepFryingRecipe> DEEP_FRYING =
        new CBRecipe<>(() -> DeepFryingRecipeSerializer.INSTANCE, "deep_frying");

    public static void register(IEventBus modEventBus) {
        Registers.SERIALIZER_REGISTER.register(modEventBus);
        Registers.TYPE_REGISTER.register(modEventBus);
    }

    private static class Registers {
        static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "create_bic_bit");
        static final DeferredRegister<RecipeType<?>> TYPE_REGISTER =
            DeferredRegister.create(Registries.RECIPE_TYPE, "create_bic_bit");
    }

    @SuppressWarnings("unchecked") // intellij is stupid or smt bro
    public static class CBRecipe<T extends RecipeSerializer<? extends R>, R extends Recipe<?>> implements IRecipeTypeInfo, StringRepresentable {
        public final ResourceLocation id;
        private final DeferredHolder<RecipeSerializer<?>, T> serializerObject;
        private final Supplier<RecipeType<R>> type;

        public CBRecipe(Supplier<T> serializerSupplier, String name) {
            id = CreateBitterballen.asResource(name);
            serializerObject = Registers.SERIALIZER_REGISTER.register(name, serializerSupplier);
            type = Registers.TYPE_REGISTER.register(name, () -> RecipeType.simple(id));
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public T getSerializer() {
            return serializerObject.get();
        }

        @Override
        public RecipeType<R> getType() {
            return type.get();
        }

        @Override
        public @NotNull String getSerializedName() {
            return id.toString();
        }
    }
}
