package com.platinumg17.rigoranthusemortisreborn.canis.client.data;

//import com.mojang.datafixers.DSL;
//import com.mojang.datafixers.Typed;
//import com.mojang.datafixers.schemas.Schema;
//import com.mojang.serialization.Dynamic;
//import net.minecraft.util.datafix.TypeReferences;
//import net.minecraft.util.datafix.fixes.NamedEntityFix;
//
//public class CanisCollarColor extends NamedEntityFix {
//    public CanisCollarColor(Schema p_i49649_1_, boolean p_i49649_2_) {
//        super(p_i49649_1_, p_i49649_2_, "EntityCanisColorFix", TypeReferences.ENTITY, "rigoranthusemortisreborn:canis");
//    }
//
//    public Dynamic<?> fixTag(Dynamic<?> p_209655_1_) {
//        return p_209655_1_.update("CanisCollarColor", (p_209654_0_) -> {
//            return p_209654_0_.createByte((byte)(15 - p_209654_0_.asInt(0)));
//        });
//    }
//
//    protected Typed<?> fix(Typed<?> p_207419_1_) {
//        return p_207419_1_.update(DSL.remainderFinder(), this::fixTag);
//    }
//}