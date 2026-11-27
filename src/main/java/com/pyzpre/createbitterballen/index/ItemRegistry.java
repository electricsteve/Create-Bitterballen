package com.pyzpre.createbitterballen.index;

import static com.pyzpre.createbitterballen.CreateBitterballen.REGISTRATE;

import com.pyzpre.createbitterballen.item.BottleItem;
import com.pyzpre.createbitterballen.item.EffectTooltipItem;
import com.pyzpre.createbitterballen.item.FryingOilBottleItem;
import com.pyzpre.createbitterballen.item.WrappedItem;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import javax.annotation.Nullable;

public class ItemRegistry {
    public static final ItemEntry<Item> SWEET_DOUGH = simpleItem("sweet_dough");
    public static final ItemEntry<Item> BASKET = simpleItem("andesite_basket");
    public static final ItemEntry<Item> DIRTY_PAPER = simpleItem("dirty_paper");
    public static final ItemEntry<Item> CRUSHED_NETHER_WART = simpleItem("crushed_nether_wart");
    public static final ItemEntry<Item> CRUSHED_SUNFLOWER_SEEDS = simpleItem("crushed_sunflower_seeds");
    public static final ItemEntry<Item> FRIES = simpleFood("fries", 6, 0.5f).register();
    public static final ItemEntry<Item> KROKET = simpleFood("kroket", 11, 0.4f).register();
    public static final ItemEntry<Item> CHURROS = simpleFood("churros", 6, 0.6f).register();
    public static final ItemEntry<Item> EGGBALL = simpleFood("eggball", 6, 0.2f).register();
    public static final ItemEntry<Item> RAW_FRIES = simpleFood("raw_fries", 1, 0.3f).register();
    public static final ItemEntry<Item> SPECULAAS = simpleFood("speculaas", 8, 0.2f).register();
    public static final ItemEntry<Item> FRIKANDEL = simpleFood("frikandel", 11, 0.4f).register();
    public static final ItemEntry<Item> KRUIDNOTEN = simpleFood("kruidnoten", 4, 0.4f).register();
    public static final ItemEntry<Item> OLIEBOLLEN = simpleFood("oliebollen", 8, 0.3f).register();
    public static final ItemEntry<Item> STROOPWAFEL = simpleFood("stroopwafel", 9, 0.5f).register();
    public static final ItemEntry<Item> RAW_CHURROS = simpleFood("raw_churros", 2, 0.3f).register();
    public static final ItemEntry<Item> RAW_HERRING = simpleFood("raw_herring", 3, 0.2f).register();
    public static final ItemEntry<Item> BITTERBALLEN = simpleFood("bitterballen", 10, 0.4f).register();
    public static final ItemEntry<Item> COOKED_HERRING = simpleFood("cooked_herring", 6, 0.4f).register();
    public static final ItemEntry<Item> KROKET_SANDWICH = simpleFood("kroket_sandwich", 13, 0.4f).register();
    public static final ItemEntry<Item> AGED_CHEESE_WEDGE = simpleFood("aged_cheese_wedge", 6, 0.8f).register();
    public static final ItemEntry<Item> YOUNG_CHEESE_WEDGE = simpleFood("young_cheese_wedge", 6, 0.5f).register();
    public static final ItemEntry<Item> FRIKANDEL_SANDWICH = simpleFood("frikandel_sandwich", 13, 0.4f).register();
    public static final ItemEntry<Item> UNRIPE_CHEESE_WEDGE = simpleFood("unripe_cheese_wedge", 3, 0.3f).register();
    public static final ItemEntry<Item> UNBAKED_STROOPWAFEL = simpleFood("unbaked_stroopwafel", 1, 0.3f).register();
    public static final ItemEntry<Item> CHEESE_SOUFFLE = simpleFood("cheese_souffle", 11, 0.5f, "Cheese Soufflé").register();
    public static final ItemEntry<Item> CHOCOLATE_GLAZED_STROOPWAFEL = simpleFood("chocolate_glazed_stroopwafel", 11, 0.7f).register();
    public static final ItemEntry<Item> COATED_CHURROS = simpleFood("coated_churros", 10, 0.8f, "Chocolate Glazed Churros").register();
    public static final ItemEntry<Item> RAW_CHEESE_SOUFFLE = simpleFood("raw_cheese_souffle", 3, 0.5f, "Raw Cheese Soufflé").register();
    public static final ItemEntry<Item> COATED_OLIEBOLLEN = simpleFood("coated_oliebollen", 9, 0.4f, "Sugar Coated Oliebollen").register();
    public static final ItemEntry<WrappedItem> WRAPPED_FRIES = simpleWrappedFood("wrapped_fries", 6, 0.5f).register();
    public static final ItemEntry<WrappedItem> WRAPPED_CHURROS = simpleWrappedFood("wrapped_churros", 6, 0.6f).register();
    public static final ItemEntry<WrappedItem> WRAPPED_STROOPWAFEL = simpleWrappedFood("wrapped_stroopwafel", 9, 0.5f).register();
    public static final ItemEntry<WrappedItem> WRAPPED_COATED_STROOPWAFEL = simpleWrappedFood("wrapped_chocolate_glazed_stroopwafel", 11, 0.7f).register();
    public static final ItemEntry<WrappedItem> WRAPPED_COATED_CHURROS = simpleWrappedFood("wrapped_coated_churros", 10, 0.8f)
        .lang("Wrapped Chocolate Glazed Churros").register();
    // TODO: simplify the rest too
    public static final ItemEntry<Item> RAW_KROKET = REGISTRATE.item("raw_kroket", Item::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .nutrition(4)
            .saturationModifier(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
            .build()))
        .register();
    public static final ItemEntry<EffectTooltipItem> KETCHUP_TOPPED_KROKET_SANDWICH = REGISTRATE.item("ketchup_topped_kroket_sandwich", EffectTooltipItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(13)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1F)
            .build()))
        .register();
    public static final ItemEntry<EffectTooltipItem> MAYONNAISE_TOPPED_KROKET_SANDWICH = REGISTRATE.item("mayonnaise_topped_kroket_sandwich", EffectTooltipItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(13)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1F)
            .build()))
        .register();
    public static final ItemEntry<EffectTooltipItem> MAYONNAISE_KETCHUP_TOPPED_KROKET_SANDWICH = REGISTRATE.item("mayonnaise_ketchup_topped_kroket_sandwich", EffectTooltipItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(14)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1F)
            .build()))
        .lang("Double Topped Kroket Sandwich")
        .register();
    public static final ItemEntry<Item> RAW_BITTERBALLEN = REGISTRATE.item("raw_bitterballen", Item::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .nutrition(3)
            .saturationModifier(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
            .build()))
        .register();
    public static final ItemEntry<Item> RAW_EGGBALL = REGISTRATE.item("raw_eggball", Item::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .nutrition(1)
            .saturationModifier(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
            .build()))
        .register();
    public static final ItemEntry<Item> RAW_FRIKANDEL = REGISTRATE.item("raw_frikandel", Item::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .nutrition(4)
            .saturationModifier(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
            .build()))
        .register();
    public static final ItemEntry<EffectTooltipItem> KETCHUP_TOPPED_FRIKANDEL_SANDWICH = REGISTRATE.item("ketchup_topped_frikandel_sandwich", EffectTooltipItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(13)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1F)
            .build()))
        .register();
    public static final ItemEntry<EffectTooltipItem> MAYONNAISE_TOPPED_FRIKANDEL_SANDWICH = REGISTRATE.item("mayonnaise_topped_frikandel_sandwich", EffectTooltipItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(13)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1F)
            .build()))
        .register();
    public static final ItemEntry<EffectTooltipItem> MAYONNAISE_KETCHUP_TOPPED_FRIKANDEL_SANDWICH = REGISTRATE.item("mayonnaise_ketchup_topped_frikandel_sandwich", EffectTooltipItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(14)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1F)
            .build()))
        .lang("Double Topped Frikandel Sandwich")
        .register();
    public static final ItemEntry<WrappedItem> WRAPPED_KETCHUP_TOPPED_FRIES = REGISTRATE.item("wrapped_ketchup_topped_fries", WrappedItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(6)
            .saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1F)
            .build()))
        .register();
    public static final ItemEntry<WrappedItem> WRAPPED_MAYONNAISE_TOPPED_FRIES = REGISTRATE.item("wrapped_mayonnaise_topped_fries", WrappedItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(6)
            .saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1F)
            .build()))
        .register();
    public static final ItemEntry<WrappedItem> WRAPPED_MAYONNAISE_KETCHUP_TOPPED_FRIES = REGISTRATE.item("wrapped_mayonnaise_ketchup_topped_fries", WrappedItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(7)
            .saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1F)
            .build()))
        .lang("Wrapped Double Topped Fries")
        .register();
    public static final ItemEntry<Item> STAMPPOT_BOWL = REGISTRATE.item("stamppot_bowl", Item::new)
        .tag(AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
        .properties(p -> p
            .stacksTo(1)
            .food((new FoodProperties.Builder())
                .nutrition(15)
                .saturationModifier(0.5f)
                .usingConvertsTo(Items.BOWL)
                .build()))
        .register();
    public static final ItemEntry<EffectTooltipItem> ENDERBALL = REGISTRATE.item("enderball", EffectTooltipItem::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .alwaysEdible()
            .nutrition(10)
            .saturationModifier(0.10f)
            .effect(() -> new MobEffectInstance(EffectRegistry.UNANCHORED, 600, 0), 1F)
            .build()))
        .register();
    public static final ItemEntry<BottleItem> KETCHUP_BOTTLE = REGISTRATE.item("ketchup_bottle", BottleItem::new)
        .tag(AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
        .properties(p -> p
            .stacksTo(16)
            .food((new FoodProperties.Builder())
                .nutrition(1)
                .saturationModifier(0.2f)
                .usingConvertsTo(Items.GLASS_BOTTLE)
                .build()))
        .lang("Ketchup Bottle")
        .register();
    public static final ItemEntry<BottleItem> MAYONNAISE_BOTTLE = REGISTRATE.item("mayonnaise_bottle", BottleItem::new)
        .tag(AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
        .properties(p -> p
            .stacksTo(16)
            .food((new FoodProperties.Builder())
                .nutrition(1)
                .saturationModifier(0.2f)
                .usingConvertsTo(Items.GLASS_BOTTLE)
                .build()))
        .lang("Mayonnaise Bottle")
        .register();
    public static final ItemEntry<Item> ROASTED_SUNFLOWER_SEEDS = REGISTRATE.item("roasted_sunflower_seeds", Item::new)
        .properties(p -> p.food((new FoodProperties.Builder())
            .nutrition(2)
            .saturationModifier(0.2f)
            .fast()
            .build()))
        .register();
    public static final ItemEntry<FryingOilBottleItem> FRYING_OIL_BOTTLE = REGISTRATE.item("frying_oil_bottle", FryingOilBottleItem::new)
        .tag(AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
        .properties(p -> p.stacksTo(16)
            .food((new FoodProperties.Builder())
                .alwaysEdible()
                .effect(() -> new MobEffectInstance(EffectRegistry.OILED_UP, 1200, 0), 1F)
                .effect(() -> new MobEffectInstance(MobEffects.POISON, 200, 0), 1F)
                .build()))
        .lang("Frying Oil Bottle")
        .register();
    public static final ItemEntry<DeferredSpawnEggItem> HERRING_SPAWN_EGG = REGISTRATE
        .item("herring_spawn_egg", props -> new DeferredSpawnEggItem(
            EntityRegistry.HERRING,
            0x649999,
            0xb18a53,
            props))
        .model((ctx, provider) -> provider.spawnEggItem(ctx.get()))
        .register();

    public static final ItemEntry<MobBucketItem> HERRING_BUCKET = REGISTRATE.item("herring_bucket",
            properties -> new MobBucketItem(
                EntityRegistry.HERRING.get(),
                Fluids.WATER,
                SoundEvents.BUCKET_EMPTY_FISH,
                properties.stacksTo(1)
            ))
        .lang("Bucket of Herring")
        .register();

    public static void register() {
    }

    private static ItemEntry<Item> simpleItem(String key) {
        return REGISTRATE.item(key, Item::new).register();
    }

    private static ItemBuilder<Item, CreateRegistrate> simpleFood(String key, int nutrition, float saturation) {
        return simpleFood(key, nutrition, saturation, null);
    }

    private static ItemBuilder<Item, CreateRegistrate> simpleFood(String key, int nutrition, float saturation, @Nullable String lang) {
        ItemBuilder<Item, CreateRegistrate> item = REGISTRATE.item(key, Item::new)
            .properties(p -> p.food((new FoodProperties.Builder())
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .build()));
        if (lang != null) item.lang(lang);
        return item;
    }

    private static ItemBuilder<WrappedItem, CreateRegistrate> simpleWrappedFood(String name, int nutrition, float saturation) {
        return REGISTRATE.item(name, WrappedItem::new)
            .properties(p -> p.food((new FoodProperties.Builder())
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .build()));
    }
}
