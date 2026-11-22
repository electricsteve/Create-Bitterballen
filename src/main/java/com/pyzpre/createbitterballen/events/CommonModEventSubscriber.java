package com.pyzpre.createbitterballen.events;

import com.pyzpre.createbitterballen.CreateBitterballen;
import com.pyzpre.createbitterballen.entity.HerringEntity;
import com.pyzpre.createbitterballen.index.EntityRegistry;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = CreateBitterballen.MOD_ID)
public class CommonModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
            EntityRegistry.HERRING.get(),
            SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.WORLD_SURFACE,
            HerringEntity::canSpawnHere,
            RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
    }
}
