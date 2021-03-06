package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.IPacket;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.CanisData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class CanisPacket<T extends CanisData> implements IPacket<T> {

    @Override
    public void encode(T data, PacketBuffer buf) {
        buf.writeInt(data.entityId);
    }

    @Override
    public abstract T decode(PacketBuffer buf);

    @Override
    public final void handle(T data, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity target = ctx.get().getSender().level.getEntity(data.entityId);

            if (!(target instanceof CanisEntity)) {
                return;
            }
            this.handleCanis((CanisEntity) target, data, ctx);
        });
        ctx.get().setPacketHandled(true);
    }
    public abstract void handleCanis(CanisEntity canisIn, T data, Supplier<NetworkEvent.Context> ctx);
}
