package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.IDominionTile;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class RelayDepositTile extends EmorticRelayTile {
    public RelayDepositTile(TileEntityType<?> type){
        super(type);
    }

    public RelayDepositTile(){
        super(BlockRegistry.RELAY_DEPOSIT_TILE);
    }

    @Override
    public void tick() {
        super.tick();
        if(!level.isClientSide && level.getGameTime() % 20 == 0 && getCurrentDominion() > 0){
            List<BlockPos> posList = DominionUtil.canGiveDominionAny(worldPosition, level, 5);
            for(BlockPos jarPos : posList) {
                if(this.getCurrentDominion() == 0)
                    break;

                if (jarPos != null && !jarPos.equals(this.getToPos()) && !jarPos.equals(this.getFromPos()) && level.getBlockEntity(jarPos) instanceof DominionJarTile) {
                    transferDominion(this, (IDominionTile) level.getBlockEntity(jarPos));
                    ParticleUtil.spawnFollowProjectile(level, this.worldPosition, jarPos);
                }
            }
        }
    }

    @Override
    public boolean setTakeFrom(BlockPos pos) {
        return super.setTakeFrom(pos);
    }

    @Override
    public boolean setSendTo(BlockPos pos) {
        return super.setSendTo(pos);
    }
}