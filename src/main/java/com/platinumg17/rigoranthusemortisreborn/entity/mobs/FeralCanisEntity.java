package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;

public class FeralCanisEntity extends MonsterEntity implements IAnimatable {

    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(FeralCanisEntity.class, DataSerializers.INT);
    private final AnimationFactory animationFactory = new AnimationFactory(this);

//    @Nonnull
//    private Animation animation = Animation.IDLE;

    public FeralCanisEntity(EntityType<? extends FeralCanisEntity> entity, World worldIn) {
        super(entity, worldIn);
        this.noCulling = true;
        this.moveControl = new MovementController(this);
    }
//    @Override
//    public IPacket<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (!this.dead && !this.isDeadOrDying()) {
//            if (this.getState() == State.ATTACKING) {
//                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chordata.attack", false));
//                return PlayState.CONTINUE;
//            }
//        }
//        if (event.isMoving()) {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chordata.walk", true));
//        } else {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chordata.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    @Override
    protected void updateControlFlags() {
        super.updateControlFlags();
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
        this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
        this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
    }

//    @Override
//    public boolean causeFallDamage(float distance, float damageMultiplier) {
//        return false;
//    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    public enum State {IDLE}

    public State getState() {
        State[] states = State.values();
        return states[MathHelper.clamp(this.entityData.get(STATE), 0, states.length - 1)];
    }

    public void setState(State state) {
        this.entityData.set(STATE, state.ordinal());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true));
//        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, (float) 0.3));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(0, new SwimGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }


//    @OnlyIn(Dist.CLIENT)
//    public Vector3d getLeashOffset() {
//        return new Vector3d(0.0D, (0.6F * this.getEyeHeight()), (this.getBbWidth() * 0.4F));
//    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {

        BlockPos blockpos = this.getOnPos();
        ItemStack stack = player.getItemInHand(hand);

        LightningBoltEntity entityLightningBolt = EntityType.LIGHTNING_BOLT.create(level);
        SunderedCadaverEntity sunderedCadaver = RigoranthusEntityTypes.SUNDERED_CADAVER.get().create(level);
        CanisEntity canis = SpecializedEntityTypes.CANIS.get().create(level);

//        MobEntity sunderedCadaver = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), level);
//            sunderedCadaver.moveTo(this.getX(), this.getY(), this.getZ(), level.getRandom().nextFloat() * 360F, 0);

        if (stack.getItem() == ItemInit.PACT_OF_SERVITUDE.get()) {

            if (!player.level.isClientSide) {
                if (!player.abilities.instabuild) {
                    stack.shrink(1);
                }
                level.playSound(player, blockpos, SoundEvents.BOOK_PAGE_TURN, SoundCategory.NEUTRAL, 1.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.4F + 1.0F); // was  0.2F + 1.0F

                if ((Math.random() <= 0.15)) {
                    if (player.level.isClientSide) {
                        this.level.addParticle(ParticleTypes.SOUL, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D); //((ServerWorld) level).sendParticles(player, true, 5, 3, 3, IPacket <?> level) //    (ParticleTypes.SOUL, this.blockPosition(), 5, 3, 3, 3, 1);
                        this.level.addParticle(ParticleTypes.SOUL, this.getRandomX(1.5D), this.getRandomY() + 0.8D, this.getRandomZ(1.5D), 0.0D, 0.0D, 0.0D);
                    }

                    level.playSound(null, blockpos, SoundEvents.WOLF_HOWL, SoundCategory.NEUTRAL, 1f, 0.8f);
                    this.setNoActionTime(60); // Alternatively, use --> this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 7));
                    this.setSecondsOnFire(3);

                    player.displayClientMessage(new StringTextComponent("\u00A76The Pact was Successful. \u00A7cThe Beasts Impurities will now be Expelled."), (true));

                    canis.setTame(true);
                    canis.setOwnerUUID(player.getUUID());
                    canis.setHealth(canis.getMaxHealth());
                    canis.setOrderedToSit(false);
                    canis.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                    level.addFreshEntity(canis);
                    this.remove();
                }
            }
            return ActionResultType.SUCCESS;
        }
        else {
            if ((Math.random() <= 0.15)) {
                if (this.level instanceof ServerWorld) {
                    entityLightningBolt.setVisualOnly(true);
                    this.thunderHit(((ServerWorld) this.level), entityLightningBolt); // used to contain .getLevel() after the parentheses
                    sunderedCadaver.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                    level.addFreshEntity(sunderedCadaver);
                    level.addFreshEntity(sunderedCadaver);
                }
                this.level.playSound(null, blockpos, SoundEvents.WOLF_GROWL, SoundCategory.NEUTRAL, 1f, 0.8f);

//                sunderedCadaver.finalizeSpawn((ServerWorld) level, level.getCurrentDifficultyAt(this.blockPosition()),
//                        SpawnReason.MOB_SUMMONED, null, null);                 // TODO  " level.addFreshEntity() " may be necessary. FinalizeSpawn may break shit

                if (player.level.isClientSide) {
                    player.displayClientMessage(new StringTextComponent("\u00A7cMake Yourself Scarce Weakling...."), (false));
                }
            }
            return ActionResultType.FAIL;
        }
    }
/*
    @Override
    public ActionResultType mobInteract(PlayerEntity sourceentity, Hand hand) {
        ItemStack itemstack = sourceentity.getItemInHand(hand);
        Item item = itemstack.getItem();
        BlockPos blockpos = sourceentity.blockPosition();

        if (this.isTamed() && sourceentity.isSecondaryUseActive()) {
            this.openInventory(sourceentity);
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        if (!itemstack.isEmpty()) {
            if (itemstack.getItem() == ItemInit.PACT_OF_SERVITUDE.get()) {
                sourceentity.swing(Hand.MAIN_HAND, true);
                if (!sourceentity.isCreative()) {
                    itemstack.shrink(1);
                }
                if (this.level.isClientSide()) {
                    level.playSound(null, blockpos, SoundEvents.BOOK_PAGE_TURN, SoundCategory.NEUTRAL, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                }
            if (!this.hasChest() && itemstack.getItem() == Blocks.CHEST.asItem()) {
                this.setChest(true);
                this.playChestEquipsSound();
                if (!sourceentity.abilities.instabuild) {
                    itemstack.shrink(1);
                }
                this.createInventory();
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
            if (!this.isSaddled() && itemstack.getItem() == Items.SADDLE) {
                this.openInventory(sourceentity);
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
*/
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, Config.feralCanisChordataMaxHealth.get())
                .add(Attributes.MOVEMENT_SPEED, Config.feralCanisChordataMovementSpeed.get())
                .add(Attributes.ATTACK_DAMAGE, Config.feralCanisChordataAttackDamage.get())
                .add(Attributes.ARMOR, Config.feralCanisChordataArmorValue.get())
                .add(Attributes.ATTACK_KNOCKBACK, Config.feralCanisChordataAttackKnockback.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, Config.feralCanisChordataKnockbackResistance.get())
                .add(Attributes.FOLLOW_RANGE, 25.0D);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {

        LightningBoltEntity entityLightningBolt = EntityType.LIGHTNING_BOLT.create(level);
        SunderedCadaverEntity sunderedCadaver = RigoranthusEntityTypes.SUNDERED_CADAVER.get().create(level);
//        MobEntity sunderedCadaver = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), level);
//            sunderedCadaver.moveTo(this.getX(), this.getY(), this.getZ(), level.getRandom().nextFloat() * 360F, 0);

        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if (this.getLastHurtByMob() instanceof PlayerEntity) {
            if ((Math.random() < 0.1)) {
                if (this.level.isClientSide) {
                    this.lastHurtByPlayer.displayClientMessage(new StringTextComponent("\u00A7cMake Yourself Scarce, Weakling...."), (false));
                }
                if (level instanceof ServerWorld) {
                    entityLightningBolt.setVisualOnly(true);
                    this.thunderHit(((ServerWorld) this.level), entityLightningBolt);
                    sunderedCadaver.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                    level.addFreshEntity(sunderedCadaver);
                    level.addFreshEntity(sunderedCadaver);

//                    MobEntity entityToSpawn = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), level);
//                    entityToSpawn.moveTo(this.getX(), this.getY(), this.getZ(), level.getRandom().nextFloat() * 360F, 0);
//                    entityToSpawn.finalizeSpawn((ServerWorld) level, level.getCurrentDifficultyAt(this.blockPosition()),
//                            SpawnReason.MOB_SUMMONED, null, null);

//                    level.addFreshEntity(entityToSpawn);
//                    sunderedCadaver.finalizeSpawn((ServerWorld) level, level.getCurrentDifficultyAt(this.blockPosition()), SpawnReason.MOB_SUMMONED, null, null);
//                    sunderedCadaver.finalizeSpawn((ServerWorld) level, level.getCurrentDifficultyAt(this.blockPosition()), SpawnReason.MOB_SUMMONED, null, null);
                }
            }
        }
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.DROWN)
            return false;
        if (source == DamageSource.LIGHTNING_BOLT)
            return false;
        return super.hurt(source, amount);
    }
}