package com.platinumg17.rigoranthusemortisreborn.util;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;

import java.util.function.Supplier;

public class MagicHitTargetUtil {
    public enum Type {
        GREEN(() -> new RedstoneParticleData(0F, 1F, 0F, 2F), false, false),
        CRIT(() -> ParticleTypes.ENCHANTED_HIT, false, false),
        ENCHANT(() -> ParticleTypes.ENCHANT, false, true),
        RED(() -> new RedstoneParticleData(20F, 0F, 0F, 2F), false, false),
        INK(() -> ParticleTypes.SQUID_INK, true, false),
        CIRCEAN(() -> new RedstoneParticleData(20F, 20F, 20F, 2F), true, false),
        APOGEE(() -> ParticleTypes.END_ROD, true, false);

        private final Supplier<IParticleData> particle;
        private final boolean explosiveFinish;
        private final boolean extraParticles;

        Type(Supplier<IParticleData> particle, boolean explosiveFinish, boolean extraParticles) {
            this.particle = particle;
            this.explosiveFinish = explosiveFinish;
            this.extraParticles = extraParticles;
        }

        public int toInt() {return ordinal();}

        public static Type fromInt(int i) {
            if(i >= 0 && i < values().length)
                return values()[i];
            else return ENCHANT;
        }
    }

    public static void particleEffect(Type type, ClientWorld world, Vector3d pos, Vector3d lookVector, int length, boolean collides) {
        particleEffect(type.particle.get(), type.explosiveFinish, type.extraParticles, world, pos, lookVector, length, collides);
    }

    public static void particleEffect(IParticleData particle, boolean explosiveFinish, boolean extraParticles, ClientWorld world, Vector3d pos, Vector3d lookVector, int length, boolean collides) {
        for(int step = 0; step <= length; step++) {
            pathParticles(particle, extraParticles, world, pos.add(lookVector.scale(step / 2D)), step);

            if(collides && step == length) {
                // uses the vector to a prior position before it was inside a block/entity so that the flash particle is not obscured and particles can fly out
                collisionEffect(particle, explosiveFinish, world, pos.add(lookVector.scale((step - 1) / 2D)));
            }
        }
    }

    private static void pathParticles(IParticleData particle, boolean extraParticles, ClientWorld world, Vector3d vecPos, int i) {
        // starts particle trail along vector path after a few runs, its away from the players vision so not to obscure everything
        if(i >= 5) {
            float offsetX = (world.random.nextFloat() - .5F) / 4;
            float offsetY = (world.random.nextFloat() - .5F) / 4;
            float offsetZ = (world.random.nextFloat() - .5F) / 4;
            world.addParticle(particle, true, vecPos.x + offsetX, vecPos.y + offsetY, vecPos.z + offsetZ, 0.0D, 0.0D, 0.0D);
        //  increases particle visibility
            if(extraParticles) {
                for(float a = 0; a < 4; a++) {
                    float extraOffsetX = (world.random.nextFloat() - .5F) / 5;
                    float extraOffsetY = (world.random.nextFloat() - .5F) / 5;
                    float extraOffsetZ = (world.random.nextFloat() - .5F) / 5;
                    world.addParticle(particle, true, vecPos.x + extraOffsetX, vecPos.y + extraOffsetY, vecPos.z + extraOffsetZ, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    private static void collisionEffect(IParticleData particle, boolean explosiveFinish, ClientWorld world, Vector3d vecPos) {
        int particles = 25 + world.random.nextInt(10);
        //   if  explosiveFinish = true : adds a sound + flash
        if(explosiveFinish) {
            world.playLocalSound(vecPos.x, vecPos.y, vecPos.z, SoundEvents.SHULKER_BULLET_HIT, SoundCategory.BLOCKS, 1.2F, 0.6F, false);
            world.addParticle(ParticleTypes.FLASH, vecPos.x, vecPos.y, vecPos.z, 0.0D, 0.0D, 0.0D);
            for(int a = 0; a < particles; a++)
                world.addParticle(particle, true, vecPos.x, vecPos.y, vecPos.z, world.random.nextGaussian() * 0.12D, world.random.nextGaussian() * 0.12D, world.random.nextGaussian() * 0.12D);
        }
        //   if  explosiveFinish = false : adds a small particle effect
        else {
            for(int a = 0; a < particles; a++)
                world.addParticle(ParticleTypes.CRIT, true, vecPos.x, vecPos.y, vecPos.z, world.random.nextGaussian(), world.random.nextGaussian(), world.random.nextGaussian());
        }
    }
}
