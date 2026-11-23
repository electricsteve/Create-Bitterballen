package com.pyzpre.createbitterballen.datagen;

import com.pyzpre.createbitterballen.block.cheese.CheeseBlock;
import com.pyzpre.createbitterballen.index.BlockRegistry;
import com.pyzpre.createbitterballen.index.ItemRegistry;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class CBLootTableProvider extends LootTableProvider {
    private static final HashMap<Holder<Block>, Holder<Item>> CHEESE_MAP = new HashMap<>();

    static {
        CHEESE_MAP.put(BlockRegistry.UNRIPE_CHEESE, ItemRegistry.UNRIPE_CHEESE_WEDGE);
        CHEESE_MAP.put(BlockRegistry.WAXED_UNRIPE_CHEESE, ItemRegistry.UNRIPE_CHEESE_WEDGE);
        CHEESE_MAP.put(BlockRegistry.YOUNG_CHEESE, ItemRegistry.YOUNG_CHEESE_WEDGE);
        CHEESE_MAP.put(BlockRegistry.WAXED_YOUNG_CHEESE, ItemRegistry.YOUNG_CHEESE_WEDGE);
        CHEESE_MAP.put(BlockRegistry.AGED_CHEESE, ItemRegistry.AGED_CHEESE_WEDGE);
        CHEESE_MAP.put(BlockRegistry.WAXED_AGED_CHEESE, ItemRegistry.AGED_CHEESE_WEDGE);
    }

    public CBLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(
                Cheese::new, LootContextParamSets.EMPTY
            )
        ), registries);
    }

    public static class Cheese implements LootTableSubProvider {
        public Cheese(HolderLookup.Provider lookupProvider) {
//            super(lookupProvider);
        }

        @Override
        public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            for (Map.Entry<Holder<Block>, Holder<Item>> entry : CHEESE_MAP.entrySet()) {
                ResourceKey<LootTable> lootKey = CheeseBlock.getLootKey(entry.getKey().value());
                output.accept(
                    lootKey, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(4))
                            .add(LootItem.lootTableItem(entry.getValue().value()))
                        )
                );
            }
        }
    }
}
