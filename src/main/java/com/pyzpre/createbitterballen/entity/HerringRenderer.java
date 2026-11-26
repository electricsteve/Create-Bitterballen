package com.pyzpre.createbitterballen.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

public class HerringRenderer extends MobRenderer<HerringEntity, HerringModel<HerringEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("create_bic_bit", "textures/entity/herring.png");

    public HerringRenderer(EntityRendererProvider.Context context) {
        super(context, new HerringModel<>(context.bakeLayer(HerringModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull HerringEntity entity) {
        return TEXTURE;
    }
}
