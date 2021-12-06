package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RitualTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RitualVesselModel extends AnimatedGeoModel<RitualTile> {
    public static final ResourceLocation model = new ResourceLocation(EmortisConstants.MOD_ID, "geo/ritual_vessel.geo.json");
    public static final ResourceLocation texture = new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/ritual_vessel.png");
    public static final ResourceLocation anim = new ResourceLocation(EmortisConstants.MOD_ID, "animations/ritual_vessel.json");

    @Override
    public ResourceLocation getModelLocation(RitualTile extractorTile) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(RitualTile extractorTile) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RitualTile extractorTile) {
        return anim;
    }
}