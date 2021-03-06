package com.platinumg17.rigoranthusemortisreborn.magica.common.entity;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.ISummon;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SummonHorse extends HorseEntity implements ISummon {
    public int ticksLeft;
    private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(SummonHorse.class, DataSerializers.OPTIONAL_UUID);
    public SummonHorse(EntityType<? extends HorseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected boolean canParent() {
        return false;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.of(Util.NIL_UUID));
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

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        onSummonDeath(level, cause, false);
    }

    @Override
    public boolean canTakeItem(ItemStack itemstackIn) {
        return false;
    }

    @Override
    protected void dropEquipment() { }

    @Override
    protected int getExperienceReward(PlayerEntity player) {
        return 0;
    }

    public Inventory getHorseInventory(){
        return this.inventory;
    }

    @Override
    public void openInventory(PlayerEntity playerEntity) { }

    @Override
    public boolean canMate(AnimalEntity otherAnimal) {
        return false;
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

    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("left", ticksLeft);
        writeOwner(compound);
    }

    @Override
    public int getTicksLeft() {
        return ticksLeft;
    }

    @Override
    public void setTicksLeft(int ticks) {
        this.ticksLeft = ticks;
    }


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