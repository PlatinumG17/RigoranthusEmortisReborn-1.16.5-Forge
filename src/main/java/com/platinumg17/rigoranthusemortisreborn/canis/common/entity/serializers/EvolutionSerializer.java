package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

//import net.minecraft.network.PacketBuffer;
//import net.minecraft.network.datasync.IDataSerializer;
//import com.platinumg17.rigoranthusemortisreborn.api.feature.CanisStage;

//public class EvolutionSerializer implements IDataSerializer<CanisStage> {
//    @Override
//    public void write(PacketBuffer buf, CanisStage value) {
//        buf.writeInt(value.getStage(CanisStage.Stage.CHORDATA.getStageNum()));
//        buf.writeInt(value.getStage(CanisStage.Stage.KYPHOS));
//        buf.writeInt(value.getStage(CanisStage.Stage.CAVALIER));
//        buf.writeInt(value.getStage(CanisStage.Stage.HOMINI));
//    }
//
//    @Override
//    public CanisStage read(PacketBuffer buf) {
//        return new CanisStage(buf.readEnum(CanisStage.Stage.class));
//    }
//
//    @Override
//    public CanisStage copy(CanisStage value) {
//        return value.copy();
//    }
//}

