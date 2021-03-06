package com.platinumg17.rigoranthusemortisreborn.canis.common;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisAttributes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisBeamEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import com.platinumg17.rigoranthusemortisreborn.entity.DelphicBloomEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.item.BiliBombEntitiy;
import com.platinumg17.rigoranthusemortisreborn.entity.item.BouncingProjectileEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.item.ConsumableProjectileEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.item.ReturningProjectileEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

public class SpecializedEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, EmortisConstants.MOD_ID);

    public static final RegistryObject<EntityType<CanisEntity>> CANIS = register("canis", CanisEntity::new, EntityClassification.CREATURE, (b) -> b
            .sized(1.4f, 2f)
            .setUpdateInterval(3).setTrackingRange(16)
            .setShouldReceiveVelocityUpdates(true));

    public static final RegistryObject<EntityType<CanisBeamEntity>> CANIS_BEAM = register("canis_beam", CanisBeamEntity::new, EntityClassification.MISC, (b) -> b
            .sized(0.25F, 0.25F)
            .setUpdateInterval(4).setTrackingRange(10)
            .setShouldReceiveVelocityUpdates(true)
            .setCustomClientFactory(CanisBeamEntity::new)
            .noSummon());

//    public static final RegistryObject<EntityType<REBoatEntityAzuloreal>> AZULOREAL_BOAT =
//            ENTITIES.register("azuloreal_boat", () -> EntityType.Builder.<REBoatEntityAzuloreal>of(REBoatEntityAzuloreal::new,
//                    EntityClassification.MISC).sized(0.5f, 0.5f).build(RigoranthusEmortisReborn.rl("azuloreal_boat").toString()));
//
//    public static final RegistryObject<EntityType<REBoatEntityJessic>> JESSIC_BOAT =
//            ENTITIES.register("jessic_boat", () -> EntityType.Builder.<REBoatEntityJessic>of(REBoatEntityJessic::new,
//                    EntityClassification.MISC).sized(0.5f, 0.5f).build(RigoranthusEmortisReborn.rl("jessic_boat").toString()));

    public static final RegistryObject<EntityType<ConsumableProjectileEntity>> CONSUMABLE_PROJECTILE = register("consumable_projectile", ConsumableProjectileEntity::new, EntityClassification.MISC, (b) -> b
            .sized(0.25F, 0.25F).setUpdateInterval(10).setTrackingRange(4));

    public static final RegistryObject<EntityType<ReturningProjectileEntity>> RETURNING_PROJECTILE = register("returning_projectile", ReturningProjectileEntity::new, EntityClassification.MISC, (b) -> b
            .sized(0.25F, 0.25F).setUpdateInterval(2).setTrackingRange(64));

    public static final RegistryObject<EntityType<BouncingProjectileEntity>> BOUNCING_PROJECTILE = register("bouncing_projectile", BouncingProjectileEntity::new, EntityClassification.MISC, (b) -> b
            .sized(0.25F, 0.25F).setUpdateInterval(2).setTrackingRange(10));

    public static final RegistryObject<EntityType<BiliBombEntitiy>> BILI_BOMB = register("bili_bomb", BiliBombEntitiy::new, EntityClassification.MISC, (b) -> b
            .sized(0.25F, 0.25F).setUpdateInterval(10).setTrackingRange(4));

    public static final RegistryObject<EntityType<DelphicBloomEntity>> DELPHIC_BLOOM = register("delphic_bloom", DelphicBloomEntity::new, EntityClassification.MISC, (b) -> b
            .sized(2F, 2F).setShouldReceiveVelocityUpdates(false).setTrackingRange(10));

    private static <E extends Entity, T extends EntityType<E>> RegistryObject<EntityType<E>> register(final String name, final EntityType.IFactory<E> sup, final EntityClassification classification, final Function<EntityType.Builder<E>, EntityType.Builder<E>> builder) {
        return register(name, () -> builder.apply(EntityType.Builder.of(sup, classification)).build(REUtil.getResourcePath(name)));
    }
    private static <E extends Entity, T extends EntityType<E>> RegistryObject<T> register(final String name, final Supplier<T> sup) {return ENTITIES.register(name, sup);}
    public static void addEntityAttributes() {
        GlobalEntityTypeAttributes.put(DELPHIC_BLOOM.get(),
                LivingEntity.createLivingAttributes().build());
        GlobalEntityTypeAttributes.put(CANIS.get(),
            MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.25D) // added
                .add(CanisAttributes.JUMP_POWER.get(), 1.0D) // was 0.42D
                .add(CanisAttributes.CRIT_CHANCE.get(), 0.01D)
                .add(CanisAttributes.CRIT_BONUS.get(), 1D)
                .build()
        );
    }
}