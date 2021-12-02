package com.platinumg17.rigoranthusemortisreborn.magica.client;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile.*;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.ICHOR_CRYSTALLIZER_TILE, IchorCrystallizerRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.GLYPH_PRESS_TILE, PressRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.SPLINTERED_PEDESTAL_TILE, SplinteredPedestalRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.PSYGLYPHIC_AMALG_TILE, PsyglyphicAmalgamatorRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.SCRIBES_TABLE_TILE, ScribesRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.LIGHT_TILE, LightRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.PORTAL_TILE_TYPE, PortalTileRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.INTANGIBLE_AIR_TYPE, IntangibleAirRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.SUMMONING_CRYSTAL_TILE, SummoningCrystalRenderer::new);
//        ClientRegistry.bindTileEntityRenderer(BlockRegistry.POTION_MELDER_TYPE, PotionMelderRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.RITUAL_TILE, RitualBrazierRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.PHANTOM_TILE, MageBlockRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.CRYSTALLIZER_TILE, CrystallizerRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.PSYGLYPHIC_TILE, PsyglyphicRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.DOMINION_EXTRACTOR_TILE, DominionExtractorRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.ICHOR_EXTRACTOR_TILE, IchorExtractorRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.RUNE_TILE, RuneRenderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.RELAY_DEPOSIT_TILE, (t) -> new GenericRenderer(t, "relay_deposit"));
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.EMORTIC_RELAY_TILE, (t) -> new GenericRenderer(t, "emortic_relay"));
        ClientRegistry.bindTileEntityRenderer(BlockRegistry.EMORTIC_RELAY_SPLITTER_TILE, (t) -> new GenericRenderer(t, "relay_splitter"));

        RenderTypeLookup.setRenderLayer(BlockRegistry.EMORTIC_RELAY, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.RELAY_DEPOSIT, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.RUNE_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.EMORTIC_CORE_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.CRYSTALLIZER_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.DOMINION_EXTRACTOR_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.ICHOR_EXTRACTOR_BLOCK, RenderType.cutout());

        RenderTypeLookup.setRenderLayer(BlockRegistry.CREATIVE_ICHOR_JAR, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.ICHOR_JAR, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.DOMINION_JAR, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.ICHOR_CRYSTALLIZER_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.GLYPH_PRESS_BLOCK, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.SPLINTERED_PEDESTAL, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.PSYGLYPHIC_AMALG_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.LIGHT_BLOCK, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.PHANTOM_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.SCRIBES_BLOCK, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.DOMINION_BERRY_BUSH, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.LAVA_LILY, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.CREATIVE_DOMINION_JAR, RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockRegistry.DOMINION_GEM_BLOCK, RenderType.translucent());
//        RenderTypeLookup.setRenderLayer(BlockRegistry.POTION_JAR, RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegistry.POTION_MELDER, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.RITUAL_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.SCONCE_BLOCK, RenderType.cutout());
        event.enqueueWork(() -> {
            ItemModelsProperties.register(MagicItemsRegistry.ENCHANTERS_SHIELD, new ResourceLocation(EmortisConstants.MOD_ID,"blocking"), (p_239421_0_, p_239421_1_, p_239421_2_) -> {
                return p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == p_239421_0_ ? 1.0F : 0.0F;
            });
        });
    }
//    @SubscribeEvent
//    public static void initColors(final ColorHandlerEvent.Item event) {
//        event.getItemColors().register((stack, color) -> color > 0 ? -1 :
//            (PotionUtils.getPotion(stack) != Potions.EMPTY ? PotionUtils.getColor(stack) : -1), MagicItemsRegistry.POTION_FLASK);
//
//        event.getItemColors().register((stack, color) -> color > 0 ? -1 :
//                        (PotionUtils.getPotion(stack) != Potions.EMPTY ? PotionUtils.getColor(stack) : -1), MagicItemsRegistry.POTION_FLASK_EXTEND_TIME);
//
//        event.getItemColors().register((stack, color) -> color > 0 ? -1 :
//            (PotionUtils.getPotion(stack) != Potions.EMPTY ? PotionUtils.getColor(stack) : -1), MagicItemsRegistry.POTION_FLASK_AMPLIFY);
//
//        event.getBlockColors().register((state, reader, pos, tIndex) ->
//            reader != null && pos != null && reader.getBlockEntity(pos) instanceof PotionJarTile
//                ? ((PotionJarTile) reader.getBlockEntity(pos)).getColor() : -1, BlockRegistry.POTION_JAR);
//    }
}