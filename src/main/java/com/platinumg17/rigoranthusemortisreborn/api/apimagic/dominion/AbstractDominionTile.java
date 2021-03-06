package com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

import static com.platinumg17.rigoranthusemortisreborn.api.apimagic.MagicFluidTags.DOMINION_TAG;
import static com.platinumg17.rigoranthusemortisreborn.api.apimagic.MagicFluidTags.MAX_DOMINION_TAG;

public abstract class AbstractDominionTile extends TileEntity implements IDominionTile, ITickableTileEntity {
    private int dominion = 0;
    private int maxDominion = 0;
    public AbstractDominionTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        dominion = tag.getInt(DOMINION_TAG);
        maxDominion = tag.getInt(MAX_DOMINION_TAG);
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.putInt(DOMINION_TAG, getCurrentDominion());
        tag.putInt(MAX_DOMINION_TAG, getMaxDominion());
        return super.save(tag);
    }

    @Override
    public int setDominion(int dominion) {
        this.dominion = dominion;
        if(this.dominion > this.getMaxDominion())
            this.dominion = this.getMaxDominion();
        if(this.dominion < 0)
            this.dominion = 0;
        update();
        return this.dominion;
    }

    @Override
    public int addDominion(int dominionToAdd) {
        return this.setDominion(this.getCurrentDominion() + dominionToAdd);
    }

    @Override
    public int getCurrentDominion() {
        return this.dominion;
    }

    @Override
    public int removeDominion(int dominionToRemove) {
        this.setDominion(this.getCurrentDominion() - dominionToRemove);
        update();
        return this.getCurrentDominion();
    }

    @Override
    public void setMaxDominion(int max) {
        this.maxDominion = max;
        update();
    }

    public boolean update(){
        if(this.worldPosition != null && this.level != null){
            level.sendBlockUpdated(this.worldPosition, level.getBlockState(worldPosition),  level.getBlockState(worldPosition), 2);
            return true;
        }
        return false;
    }

    @Override
    public int getMaxDominion() {
        return maxDominion;
    }

    public boolean canAcceptDominion(){ return this.getCurrentDominion() < this.getMaxDominion(); }

    public int dominionCanAccept(IDominionTile tile){return tile.getMaxDominion() - tile.getCurrentDominion();}

    public int transferDominion(IDominionTile from, IDominionTile to){
        int transferRate = getTransferRate(from, to);
        from.removeDominion(transferRate);
        to.addDominion(transferRate);
        return transferRate;
    }

    public int getTransferRate(IDominionTile from, IDominionTile to){
        return Math.min(Math.min(from.getTransferRate(), from.getCurrentDominion()), to.getMaxDominion() - to.getCurrentDominion());
    }

    public int transferDominion(IDominionTile from, IDominionTile to, int fromTransferRate){
        int transferRate = getTransferRate(from, to, fromTransferRate);
        if(transferRate == 0)
            return 0;
        from.removeDominion(transferRate);
        to.addDominion(transferRate);
        return transferRate;
    }

    public int getTransferRate(IDominionTile from, IDominionTile to, int fromTransferRate){
        return Math.min(Math.min(fromTransferRate, from.getCurrentDominion()), to.getMaxDominion() - to.getCurrentDominion());
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 3, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition),pkt.getTag());
    }
}
