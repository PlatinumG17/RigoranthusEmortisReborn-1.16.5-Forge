package com.platinumg17.rigoranthusemortisreborn.magica.client.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class RenderTypes {

    static final IParticleRenderType RE_RENDER = new IParticleRenderType() {
        @Override
        public void begin(BufferBuilder buffer, TextureManager textureManager) {
            RenderSystem.depthMask(false);
            textureManager.bind(AtlasTexture.LOCATION_PARTICLES);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.alphaFunc(516, 0.003921569F);
            buffer.begin(7, DefaultVertexFormats.PARTICLE);
        }

        @Override
        public void end(Tessellator tessellator) {
            tessellator.end();
            RenderSystem.depthMask(true);
        }

        @Override
        public String toString() {
            return "rigoranthusemortisreborn:renderer";
        }
    };
    static final IParticleRenderType EMBER_RENDER = new IParticleRenderType() {
        @Override
        public void begin(BufferBuilder buffer, TextureManager textureManager) {
            RenderSystem.disableAlphaTest();

            RenderSystem.enableBlend();
            RenderSystem.alphaFunc(516, 0.3f);
            RenderSystem.enableCull();
            textureManager.bind(AtlasTexture.LOCATION_PARTICLES);
            RenderSystem.depthMask(false);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE.value);

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE);
        }

        @Override
        public void end(Tessellator tessellator) {
            tessellator.end();
            RenderSystem.enableDepthTest();
            RenderSystem.enableAlphaTest();

            RenderSystem.depthMask(true);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE.value);
            RenderSystem.disableCull();

            RenderSystem.alphaFunc(516, 0.1F);
        }

        @Override
        public String toString() {
            return "rigoranthusemortisreborn:em_rend";
        }
    };

    static final IParticleRenderType EMBER_RENDER_NO_MASK = new IParticleRenderType() {
        @Override
        public void begin(BufferBuilder buffer, TextureManager textureManager) {
            RenderSystem.disableAlphaTest();
            RenderSystem.disableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.enableFog();
            RenderSystem.alphaFunc(516, 0.3f);
            textureManager.bind(AtlasTexture.LOCATION_PARTICLES);
            RenderSystem.depthMask(false);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE.value);
            RenderSystem.disableCull();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE);
        }

        @Override
        public void end(Tessellator tessellator) {
            tessellator.end();
            RenderSystem.enableDepthTest();
            RenderSystem.enableAlphaTest();
            RenderSystem.depthMask(true);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE.value);
            RenderSystem.alphaFunc(516, 0.1F);
        }
        @Override
        public String toString() {
            return "rigoranthusemortisreborn:em_rend_no_mask";
        }
    };
}