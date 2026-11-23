package com.pyzpre.createbitterballen.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.simibubi.create.foundation.item.TooltipHelper;

import net.createmod.catnip.lang.FontHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.DataMapHooks;

import java.util.List;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CheeseBlock extends Block implements ChangeOverTimeBlock<CheeseBlock.AgingState> {
    public static final MapCodec<CheeseBlock> CODEC = RecordCodecBuilder.mapCodec(
        codecBuilder -> codecBuilder.group(propertiesCodec(),
                AgingState.CODEC.fieldOf("age").forGetter(CheeseBlock::getAge),
                Codec.BOOL.fieldOf("waxed").forGetter(CheeseBlock::isWaxed)
            )
            .apply(codecBuilder, CheeseBlock::new)
    );
    private static final VoxelShape SHAPE = Shapes.box(1 / 16D, 0 / 16D, 1 / 16D, 15 / 16D, 8 / 16D, 15 / 16D);
    @SuppressWarnings("DataFlowIssue")
    public static final TagKey<Item> TOOLS_KNIVES = ItemTags.create(ResourceLocation.tryParse("c:tools/knife"));

    private final AgingState age;
    private final boolean waxed;
    private ResourceKey<LootTable> lootKey;

    public boolean isWaxed() {
        return waxed;
    }

    public CheeseBlock(Properties properties, AgingState age, boolean waxed) {
        super(properties);
        this.age = age;
        this.waxed = waxed;
    }

    public static ResourceKey<LootTable> getLootKey(Block block) {
        ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(block);
        return ResourceKey.create(Registries.LOOT_TABLE, resourcelocation.withPrefix("cheese/"));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            if (stack.canPerformAction(net.neoforged.neoforge.common.ItemAbilities.SHEARS_HARVEST) || stack.is(TOOLS_KNIVES)) {
                player.swing(hand, true);
                dropCheeseProducts((ServerLevel) level, pos);
                level.removeBlock(pos, false);
                level.playSound(player, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(player, GameEvent.SHEAR, pos);
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
                return ItemInteractionResult.CONSUME;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private void dropCheeseProducts(ServerLevel level, BlockPos pos) {
        MinecraftServer server = level.getServer();
        if (this.lootKey == null) {
            this.lootKey = getLootKey(this);
        }
        LootTable lootTable = server.reloadableRegistries().getLootTable(lootKey);
        LootParams.Builder builder = new LootParams.Builder(level);
        // Use whatever context parameters and values you need. Vanilla parameters can be found in LootContextParams.
        builder.withParameter(LootContextParams.ORIGIN, pos.getCenter());
        LootParams params = builder.create(LootContextParamSets.EMPTY);
        List<ItemStack> drops = lootTable.getRandomItems(params);
        RandomSource random = level.random;

        for (ItemStack drop : drops) {
            double d0 = random.nextFloat() * 0.7F + 0.15F;
            double d1 = random.nextFloat() * 0.7F + 0.060000002F + 0.6D;
            double d2 = random.nextFloat() * 0.7F + 0.15F;

            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, drop);
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
        }
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(blockState, level, pos, random);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        FontHelper.Palette palette = FontHelper.Palette.STANDARD_CREATE;

        // Add a "Hold Shift" message for extended tooltip using the new translation method
        tooltip.add(TooltipHelper.holdShift(FontHelper.Palette.STANDARD_CREATE, true));

        // Check if the player is holding Shift
        if (Screen.hasShiftDown()) {
            MutableComponent part1 = Component.translatable("item.create_bic_bit.cheese.tooltip.part1")
                .withStyle(palette.primary());
            MutableComponent part2 = Component.translatable("item.create_bic_bit.cheese.tooltip.part2")
                .withStyle(palette.highlight());
            MutableComponent part3 = Component.translatable("item.create_bic_bit.cheese.tooltip.part3")
                .withStyle(palette.primary());

            tooltip.add(part1.append(part2).append(part3));
        }
    }

    // TODO: cheese advancement through normal trigger

    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable(DataMapHooks.getNextOxidizedStage(block));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Override
    public Optional<BlockState> getNext(BlockState blockState) {
        return getNext(blockState.getBlock()).map((p_154896_) -> p_154896_.withPropertiesOf(blockState));
    }

    @Override
    public float getChanceModifier() {
        return 1.0f;
    }

    @Override
    public AgingState getAge() {
        return age;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState blockState) {
        return waxed && getNext(blockState).isPresent();
    }

    public enum AgingState implements StringRepresentable {
        UNRIPE("unripe"),
        YOUNG("young"),
        AGED("aged");

        public static final Codec<AgingState> CODEC = StringRepresentable.fromEnum(AgingState::values);
        private final String name;

        AgingState(String name) {
            this.name = name;
        }

        public String getSerializedName() {
            return this.name;
        }
    }
}
