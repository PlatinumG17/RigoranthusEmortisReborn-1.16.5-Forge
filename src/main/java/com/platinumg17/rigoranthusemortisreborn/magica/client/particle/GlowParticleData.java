package com.platinumg17.rigoranthusemortisreborn.magica.client.particle;

import com.platinumg17.rigoranthusemortisreborn.core.registry.fluid.particles.EmortisParticleTypes;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.IParticleData;

public class GlowParticleData implements IParticleFactory<ColorParticleTypeData> {
    private final IAnimatedSprite spriteSet;
    public static final String NAME = "glow";

    public GlowParticleData(IAnimatedSprite sprite) {
        this.spriteSet = sprite;
    }

    @Override
    public Particle createParticle(ColorParticleTypeData data, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new ParticleGlow(worldIn, x,y,z,xSpeed, ySpeed, zSpeed, data.color.getRed(), data.color.getGreen(), data.color.getBlue(), 1.0f, .25f, 36, this.spriteSet, data.disableDepthTest);
    }

    public static IParticleData createData(ParticleColor color) {
        return new ColorParticleTypeData(EmortisParticleTypes.GLOW_TYPE, color, false);
    }

    public static IParticleData createData(ParticleColor color, boolean disableDepthTest) {
        return new ColorParticleTypeData(EmortisParticleTypes.GLOW_TYPE, color, disableDepthTest);
    }
}