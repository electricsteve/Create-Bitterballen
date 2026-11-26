package com.pyzpre.createbitterballen;

import static net.neoforged.neoforge.common.NeoForge.EVENT_BUS;

import com.pyzpre.createbitterballen.block.mechanicalfryer.MechanicalFryerEntity;
import com.pyzpre.createbitterballen.datagen.CBLootTableProvider;
import com.pyzpre.createbitterballen.events.EntityEffectHandler;
import com.pyzpre.createbitterballen.events.LootTables;
import com.pyzpre.createbitterballen.events.SunflowerInteractionHandler;
import com.pyzpre.createbitterballen.index.BlockEntityRegistry;
import com.pyzpre.createbitterballen.index.BlockRegistry;
import com.pyzpre.createbitterballen.index.EffectRegistry;
import com.pyzpre.createbitterballen.index.EntityRegistry;
import com.pyzpre.createbitterballen.index.FluidRegistry;
import com.pyzpre.createbitterballen.index.ItemRegistry;
import com.pyzpre.createbitterballen.index.PartialsRegistry;
import com.pyzpre.createbitterballen.index.RecipeRegistry;
import com.pyzpre.createbitterballen.index.SoundsRegistry;
import com.pyzpre.createbitterballen.ponder.BitterOrbPonderPlugin;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;

import net.createmod.catnip.lang.FontHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(CreateBitterballen.MOD_ID)
public class CreateBitterballen {

    public static final String MOD_ID = "create_bic_bit";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateBitterballen.MOD_ID)
        .defaultCreativeTab((ResourceKey<CreativeModeTab>) null)
        .setTooltipModifierFactory(item ->
            new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
        );

    public static final Random RANDOM = new Random();

    public CreateBitterballen(IEventBus eventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(eventBus);
        FluidRegistry.register();
        EntityRegistry.register(eventBus);
        SoundsRegistry.prepare();
        PartialsRegistry.init();
        BlockRegistry.register();
        BlockEntityRegistry.register();
        EVENT_BUS.register(new EntityEffectHandler());
        ItemRegistry.register();
        LootTables.register();
        EffectRegistry.register(eventBus);
        CreateBitterballenTabs.register(eventBus);
        RecipeRegistry.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::setupClient);
        eventBus.addListener(this::dataGen);
        eventBus.addListener(SoundsRegistry::register);
        EVENT_BUS.register(SunflowerInteractionHandler.class);
    }

    @EventBusSubscriber(modid = CreateBitterballen.MOD_ID)
    public static class ModCapabilityRegistrar {
        @SubscribeEvent
        public static void registerCapabilities(RegisterCapabilitiesEvent event) {
            MechanicalFryerEntity.registerCapabilities(event);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(FluidRegistry::registerFluidInteractions);
    }


    @SuppressWarnings("deprecation")
    private void setupClient(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(FluidRegistry.FRYING_OIL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(FluidRegistry.FRYING_OIL.getSource(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CRYSTALLISED_OIL.get(), RenderType.translucent());
        PonderIndex.addPlugin(new BitterOrbPonderPlugin());
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(
            event.includeServer(),
            new CBLootTableProvider(output, lookupProvider)
        );
    }

    public static void grantAdvancementCriterion(ServerPlayer player, String advancementID, String criterionKey) {
        PlayerAdvancements playerAdvancements = player.getAdvancements();
        ResourceLocation id = ResourceLocation.parse(advancementID);

        Optional<AdvancementHolder> optionalHolder = Optional.ofNullable(player.server.getAdvancements().get(id));

        optionalHolder.ifPresent(holder -> {
            if (holder.value().criteria().containsKey(criterionKey)) {
                AdvancementProgress progress = playerAdvancements.getOrStartProgress(holder);
                if (!progress.isDone()) {
                    playerAdvancements.award(holder, criterionKey);
                }
            }
        });
    }
}
