package com.pyzpre.createbitterballen.index;


import com.pyzpre.createbitterballen.CreateBitterballen;

import net.minecraft.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PartialsRegistry {
    public static final PartialModel MECHANICAL_FRYER_HEAD = block("mechanicalfryer/head");

    private static PartialModel block(@SuppressWarnings("SameParameterValue") String path) {
        return PartialModel.of(CreateBitterballen.asResource("block/" + path));
    }

    public static void init() {
    }
}
