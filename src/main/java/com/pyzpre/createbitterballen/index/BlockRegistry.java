package com.pyzpre.createbitterballen.index;

import static com.pyzpre.createbitterballen.CreateBitterballen.REGISTRATE;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

import com.pyzpre.createbitterballen.block.CheeseBlock;
import com.pyzpre.createbitterballen.block.mechanicalfryer.MechanicalFryer;
import com.pyzpre.createbitterballen.block.CBSunflowerBlock;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class BlockRegistry {
    public static final BlockEntry<MechanicalFryer> MECHANICAL_FRYER =
        REGISTRATE.block("mechanical_fryer", MechanicalFryer::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion().strength(2.0f).mapColor(MapColor.COLOR_ORANGE))
            .transform(pickaxeOnly())
            .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 4.0))
            .blockstate((c, p) ->
                p.horizontalBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p), 90))
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();
    public static final BlockEntry<CBSunflowerBlock> SUNFLOWERSTEM =
        REGISTRATE.block("sunflower_seeds", CBSunflowerBlock::new)
            .properties(p -> p.noOcclusion().strength(1.0f))
            .properties(p -> p.sound(SoundType.GRASS))
            .properties(p -> p.offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY).noCollission().instabreak().mapColor(MapColor.PLANT))
            .item()
            .model((i1, i2) -> {})
            .build()
            .lang("Sunflower Seeds")
            .loot((lootTables, block) -> lootTables.add(block,
                LootTable.lootTable().withPool(lootTables.applyExplosionCondition(block,
                    LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                            StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                        )))
                )
            ))
            .blockstate((i1, i2) -> {})
            .register();
    public static final BlockEntry<CheeseBlock> UNRIPE_CHEESE =
        REGISTRATE.block("unripe_cheese", props -> new CheeseBlock(props, CheeseBlock.AgingState.UNRIPE, false))
            .initialProperties(() -> Blocks.CAKE)
            .properties(p -> p.sound(SoundType.WOOD))
            .transform(pickaxeOnly())
            .lang("Unripe Cheese")
            .item(AssemblyOperatorBlockItem::new)
//            .transform(customItemModel())
            .model((i1, i2) -> {})
            .build()
            .blockstate((i1, i2) -> {})
            .register();
    public static final BlockEntry<CheeseBlock> WAXED_UNRIPE_CHEESE =
        REGISTRATE.block("waxed_unripe_cheese", props -> new CheeseBlock(props, CheeseBlock.AgingState.UNRIPE, true))
            .initialProperties(() -> Blocks.CAKE)
            .properties(p -> p.sound(SoundType.WOOD))
            .transform(pickaxeOnly())
            .lang("Waxed Unripe Cheese")
            .item(AssemblyOperatorBlockItem::new)
//            .transform(customItemModel())
            .model((i1, i2) -> {})
            .build()
            .blockstate((i1, i2) -> {})
            .register();
    public static final BlockEntry<CheeseBlock> YOUNG_CHEESE =
        REGISTRATE.block("young_cheese", props -> new CheeseBlock(props, CheeseBlock.AgingState.YOUNG, false))
            .initialProperties(() -> Blocks.CAKE)
            .properties(p -> p.sound(SoundType.WOOD))
            .transform(pickaxeOnly())
            .lang("Young Cheese")
            .item(AssemblyOperatorBlockItem::new)
//            .transform(customItemModel())
            .model((i1, i2) -> {})
            .build()
            .blockstate((i1, i2) -> {})
            .register();
    public static final BlockEntry<CheeseBlock> WAXED_YOUNG_CHEESE =
        REGISTRATE.block("waxed_young_cheese", props -> new CheeseBlock(props, CheeseBlock.AgingState.YOUNG, true))
            .initialProperties(() -> Blocks.CAKE)
            .properties(p -> p.sound(SoundType.WOOD))
            .transform(pickaxeOnly())
            .lang("Waxed Young Cheese")
            .item(AssemblyOperatorBlockItem::new)
//            .transform(customItemModel())
            .model((i1, i2) -> {})
            .build()
            .blockstate((i1, i2) -> {})
            .register();
    public static final BlockEntry<CheeseBlock> AGED_CHEESE =
        REGISTRATE.block("aged_cheese", props -> new CheeseBlock(props, CheeseBlock.AgingState.AGED, false))
            .initialProperties(() -> Blocks.CAKE)
            .properties(p -> p.sound(SoundType.WOOD))
            .transform(pickaxeOnly())
            .lang("Aged Cheese")
            .item(AssemblyOperatorBlockItem::new)
//            .transform(customItemModel())
            .model((i1, i2) -> {})
            .build()
            .blockstate((i1, i2) -> {})
            .register();
    public static final BlockEntry<CheeseBlock> WAXED_AGED_CHEESE =
        REGISTRATE.block("waxed_aged_cheese", props -> new CheeseBlock(props, CheeseBlock.AgingState.AGED, true))
            .initialProperties(() -> Blocks.CAKE)
            .properties(p -> p.sound(SoundType.WOOD))
            .transform(pickaxeOnly())
            .lang("Waxed Aged Cheese")
            .item(AssemblyOperatorBlockItem::new)
//            .transform(customItemModel())
            .model((i1, i2) -> {})
            .build()
            .blockstate((i1, i2) -> {})
            .register();


    public static final BlockEntry<TransparentBlock> CRYSTALLISED_OIL =
        REGISTRATE.block("crystallised_oil", TransparentBlock::new)
            .properties(p -> p.lightLevel(s -> 10))
            .properties(p -> p.instrument(NoteBlockInstrument.HAT).strength(1F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, world, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false))
            .transform(pickaxeOnly())
            .item()
            .model((i1, i2) -> {})
            .build()
            .lang("Crystallised Oil")
            .register();

    public static void register() {}
}
