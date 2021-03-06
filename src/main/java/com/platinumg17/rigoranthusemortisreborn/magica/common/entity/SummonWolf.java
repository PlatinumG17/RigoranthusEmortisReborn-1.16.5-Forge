package com.platinumg17.rigoranthusemortisreborn.magica.common.entity;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.ISummon;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SummonWolf extends WolfEntity implements ISummon {
    public int ticksLeft;
    public boolean isHostileSummon;
    public SummonWolf(EntityType<? extends WolfEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

    }

    @Override
    public void tick() {
        super.tick();
        if(!level.isClientSide){
            ticksLeft--;
            if(ticksLeft <= 0) {
                ParticleUtil.spawnPoof((ServerWorld) level, blockPosition());
                this.remove();
                onSummonDeath(level, null, true);
            }
        }
    }
    private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(SummonWolf.class, DataSerializers.OPTIONAL_UUID);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.of(Util.NIL_UUID));
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        onSummonDeath(level, cause, false);
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.ticksLeft = compound.getInt("left");
        this.isHostileSummon = compound.getBoolean("hostileSummon");
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("left", ticksLeft);
        compound.putBoolean("hostileSummon", isHostileSummon);
    }
    @Override protected int getExperienceReward(PlayerEntity player) {return 0;}
    @Override public int getTicksLeft() {return ticksLeft;}
    @Override public void setTicksLeft(int ticks) {this.ticksLeft = ticks;}


    @Nullable
    @Override
    public UUID getOwnerID() {
        return !this.getEntityData().get(OWNER_UUID).isPresent() ? this.getUUID() : this.getEntityData().get(OWNER_UUID).get();
    }

    @Override
    public void setOwnerID(UUID uuid) {
        this.getEntityData().set(OWNER_UUID, Optional.ofNullable(uuid));
    }
}