package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Random;

public class NecrawFasciiEntity extends ZombieEntity {
    public NecrawFasciiEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, Config.necrawFasciiMaxHealth.get())
                .add(Attributes.MOVEMENT_SPEED, Config.necrawFasciiMovementSpeed.get())
                .add(Attributes.ATTACK_DAMAGE, Config.necrawFasciiAttackDamage.get())
                .add(Attributes.ARMOR, Config.necrawFasciiArmorValue.get())
                .add(Attributes.ATTACK_KNOCKBACK, Config.necrawFasciiAttackKnockback.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, Config.necrawFasciiKnockbackResistance.get())
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, (float) 0.5));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player)
    {
        return 10 + this.level.random.nextInt(5);
    }
    @Override
    protected SoundEvent getAmbientSound()
    {
        return RigoranthusSoundRegistry.NECRAW_AMBIENT.get();
    }
    @Override
    protected SoundEvent getDeathSound()
    {
        return RigoranthusSoundRegistry.NECRAW_DEATH.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return RigoranthusSoundRegistry.NECRAW_HURT.get();}
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.DROWNED_STEP, 0.20F, 0.5F);}

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if (!super.doHurtTarget(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).addEffect(new EffectInstance(RigoranthusEffectRegistry.NECROTIZING_FASCIITIS, 5000));
            }
            return true;
        }
    }
    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.WITHER)
            return false;
//        if (source.getMsgId().equals("witherSkull"))
//            return false;
        return super.hurt(source, amount);
    }
    public void aiStep() {
        super.aiStep();
        for(int i = 0; i < 3; i++){
            this.level.addParticle(ParticleTypes.FALLING_NECTAR, this.getRandomX(1.0), this.getRandomY(), this.getRandomZ(1.0), 0, 0, 0);
        }
    }
}