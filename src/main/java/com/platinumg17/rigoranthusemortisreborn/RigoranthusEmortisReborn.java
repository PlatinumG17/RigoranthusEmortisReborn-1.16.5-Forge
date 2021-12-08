package com.platinumg17.rigoranthusemortisreborn;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.platinumg17.rigoranthusemortisreborn.addon.AddonManager;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.FoodHandler;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.InteractionHandler;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.FamiliarCap;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.MappingUtil;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.blocks.BuildingBlockInit;
import com.platinumg17.rigoranthusemortisreborn.blocks.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.*;
import com.platinumg17.rigoranthusemortisreborn.canis.client.ClientSetup;
import com.platinumg17.rigoranthusemortisreborn.canis.client.data.REBlockstateProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.client.data.REItemModelProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CanisBeamRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CanisRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.world.HomeboundRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.event.ClientEventHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.Capabilities;
import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisEventHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.REBlockTagsProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.REItemTagsProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.RERecipeProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.common.commands.CanisReviveCommand;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.HelmetInteractionHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.MeatFoodHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccoutrementTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.CanisBedMaterials;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.skill.ChungusPupperSkill;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisRecipeSerializers;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigHandler;
import com.platinumg17.rigoranthusemortisreborn.core.events.advancements.REAdvancementProvider;
import com.platinumg17.rigoranthusemortisreborn.core.events.other.VanillaCompatRigoranthus;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.init.fluid.FluidRegistry;
import com.platinumg17.rigoranthusemortisreborn.core.init.fluid.particles.EmortisParticleTypes;
import com.platinumg17.rigoranthusemortisreborn.core.init.network.REPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.core.init.network.messages.Messages;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.IProxy;
import com.platinumg17.rigoranthusemortisreborn.magica.TextureEvent;
import com.platinumg17.rigoranthusemortisreborn.magica.client.ClientHandler;
import com.platinumg17.rigoranthusemortisreborn.magica.common.capability.DominionCapability;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.pathfinding.PathClientEventHandler;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.pathfinding.PathFMLEventHandler;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.WorldEvent;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.*;
import com.platinumg17.rigoranthusemortisreborn.world.trees.RigoranthusWoodTypes;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.platinumg17.rigoranthusemortisreborn.magica.common.datagen.DungeonLootGenerator.GLM;

@Mod(EmortisConstants.MOD_ID)
@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Bus.MOD)
public class RigoranthusEmortisReborn {
    public static IProxy proxy = DistExecutor.runForDist(()-> () -> new ClientProxy(), () -> ()-> new ServerProxy());
	public static final Logger LOGGER = LogManager.getLogger(EmortisConstants.MOD_ID);
	public static final String MOD_ID = "rigoranthusemortisreborn";
    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> {});
    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(EmortisConstants.CHANNEL_NAME)
            .clientAcceptedVersions(EmortisConstants.PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(EmortisConstants.PROTOCOL_VERSION::equals)
            .networkProtocolVersion(EmortisConstants.PROTOCOL_VERSION::toString)
            .simpleChannel();
    public static boolean caelusLoaded = false;
    public static ItemGroup RIGORANTHUS_EMORTIS_GROUP = new ItemGroup(EmortisConstants.MOD_ID) {
        @Override public ItemStack makeIcon() {
            return new ItemStack(ItemInit.PACT_OF_SERVITUDE.get());
        }
    };

	public RigoranthusEmortisReborn() {
        caelusLoaded = ModList.get().isLoaded("caelus");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        REGISTRY_HELPER.register(modEventBus);
        modEventBus.addListener(this::gatherData);                  	modEventBus.addListener(this::enqueueIMC);
		modEventBus.addListener(this::setup);                           modEventBus.addListener(this::processIMC);
        forgeEventBus.addListener(this::onServerStarting);              forgeEventBus.addListener(this::registerCommands);
        forgeEventBus.register(new CanisEventHandler());                modEventBus.addListener(this::doClientStuff);
        modEventBus.register(Registration.class);                       modEventBus.addListener(CanisRegistries::newRegistry);

        APIRegistry.registerSpells();                                   MappingUtil.setup();
        Registration.init();                                            FluidRegistry.register(modEventBus);
        BlockInit.register(modEventBus);		                        ItemInit.ITEMS.register(modEventBus);
        RigoranthusSoundRegistry.SOUND_EVENTS.register(modEventBus);    BuildingBlockInit.register(modEventBus);
        EmortisParticleTypes.PARTICLES.register(modEventBus);           CanisRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        CanisBlocks.BLOCKS.register(modEventBus);                       CanisItems.ITEMS.register(modEventBus);
        CanisTileEntityTypes.TILE_ENTITIES.register(modEventBus);       SpecializedEntityTypes.ENTITIES.register(modEventBus);
        CanisSerializers.SERIALIZERS.register(modEventBus);             CanisContainerTypes.CONTAINERS.register(modEventBus);
        CanisSkills.SKILLS.register(modEventBus);                       CanisAttributes.ATTRIBUTES.register(modEventBus);
        CanisAccouterments.ACCOUTERMENTS.register(modEventBus);         CanisAccoutrementTypes.ACCOUTREMENT_TYPE.register(modEventBus);
        CanisBedMaterials.CASINGS.register(modEventBus);                CanisBedMaterials.BEDDINGS.register(modEventBus);

        Messages.registerMessages("rigoranthusemortisreborn_network");

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_CONFIG);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rigoranthusemortisreborn/RigoranthusEmortisReborn-common.toml"));

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(PathClientEventHandler.class));
        Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(PathFMLEventHandler.class);
        //Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(RigoranthusConfiguredFeatures.class);
        MinecraftForge.EVENT_BUS.register(this);
        ModSetup.initGeckolib();
        //  Client Events  //
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::doClientStuff);   //            modEventBus.addListener(CanisBlocks::registerBlockColors);
            modEventBus.addListener(CanisItems::registerItemColors);
            modEventBus.addListener(ClientEventHandler::onModelBakeEvent);
                forgeEventBus.register(new ClientEventHandler());
                forgeEventBus.addListener(HomeboundRenderer::onWorldRenderLast);
//            Minecraft mc = Minecraft.getInstance();
//            if (mc != null) { // If mc is null we are running data gen so no need to add listener ((IReloadableResourceManager) mc.getResourceManager()).registerReloadListener(CanisTextureManager.INSTANCE);}
        });
        GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
        ConfigHandler.init(modEventBus);
        AddonManager.init();
    }
    @SubscribeEvent public static void registerItems(RegistryEvent.Register<Item> event) {Registration.registerItems(event);}
//    @SubscribeEvent public static void registerBlocks(RegistryEvent.Register<Block> event) {Registration.registerBlocks(event);}

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            VanillaCompatRigoranthus.registerCompostables();       VanillaCompatRigoranthus.registerFlammables();
            VanillaCompatRigoranthus.registerDispenserBehaviors(); REPacketHandler.setupChannel();
            DominionCapability.register();                         FamiliarCap.register();
            APIRegistry.registerAmalgamatorRecipes();              Networking.registerMessages();
            ModPotions.addRecipes();                               CanisReviveCommand.registerSerializers();
            CanisPacketHandler.init();                             InteractionHandler.registerHandler(new HelmetInteractionHandler());
            FoodHandler.registerHandler(new MeatFoodHandler());    FoodHandler.registerDynPredicate(ChungusPupperSkill.INNER_DYN_PRED);
            ConfigHandler.initSkillConfig();                       SpecializedEntityTypes.addEntityAttributes();
            CanisEntity.initDataParameters();                      Capabilities.init();
            WorldEvent.registerFeatures();                         WorldEvent.addBiomeTypes();
            WoodType.register(RigoranthusWoodTypes.AZULOREAL);     WoodType.register(RigoranthusWoodTypes.JESSIC);
//            EmortisSurfaceBuilder.Configured.registerConfiguredSurfaceBuilders();
            if (Config.verdurousWoodlandsSpawnWeight.get() > 0) {
                BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(WorldEvent.verdurousWoodlandsKey, Config.verdurousWoodlandsSpawnWeight.get()));}
            if (Config.verdurousFieldsSpawnWeight.get() > 0) {
                BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(WorldEvent.verdurousFieldsKey, Config.verdurousFieldsSpawnWeight.get()));}
        });
    }
    @SubscribeEvent
    public void onServerStarting(final FMLServerStartingEvent event) {LOGGER.info("Ah baby! Da mod man make a message thing!");}
    public void registerCommands(final RegisterCommandsEvent event) {CanisReviveCommand.register(event.getDispatcher());}

    public void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientSetup.setupScreenManagers(event);
            ClientSetup.setupCollarRenderers(event);
            ClientSetup.setupTileEntityRenderers(event);
            makeBow(ItemInit.BONE_BOW.get());
            Atlases.addWoodType(RigoranthusWoodTypes.AZULOREAL);
            Atlases.addWoodType(RigoranthusWoodTypes.JESSIC);
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_LEAF_CARPET.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.STRIPPED_JESSIC_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_HEDGE.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_LEAF_CARPET.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_HEDGE.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_STAIRS.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_SLAB.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_STAIRS.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_SLAB.get(), RenderType.cutout());

//            RenderTypeLookup.setRenderLayer(Registration.LUMISHROOM.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockInit.DWELLER_BRAIN.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(FluidRegistry.CADAVEROUS_ICHOR_FLUID.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(FluidRegistry.CADAVEROUS_ICHOR_BLOCK.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(FluidRegistry.CADAVEROUS_ICHOR_FLOWING.get(), RenderType.translucent());
        });
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.CONSUMABLE_PROJECTILE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.RETURNING_PROJECTILE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.BOUNCING_PROJECTILE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.BILI_BOMB.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
//        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.PAINTING, manager -> new RenderHangingArt<>(manager, new ResourceLocation("rigoranthusemortisreborn:painting")));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.CANIS.get(), CanisRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.CANIS_BEAM.get(), manager -> new CanisBeamRenderer<>(manager, event.getMinecraftSupplier().get().getItemRenderer()));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TextureEvent::textEvent);
    }
    private void makeBow(Item item) {
        ItemModelsProperties.register(item, new ResourceLocation("pull"), (p_239429_0_, p_239429_1_, p_239429_2_) -> {if (p_239429_2_ == null) {return 0.0F;} else {return p_239429_2_.getUseItem() != p_239429_0_ ? 0.0F : (float) (p_239429_0_.getUseDuration() - p_239429_2_.getUseItemRemainingTicks()) / 20.0F;}});
        ItemModelsProperties.register(item, new ResourceLocation("pulling"), (p_239428_0_, p_239428_1_, p_239428_2_) -> p_239428_2_ != null && p_239428_2_.isUsingItem() && p_239428_2_.getUseItem() == p_239428_0_ ? 1.0F : 0.0F);
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
        ModSetup.sendIntercoms();
    }

    protected void processIMC(final InterModProcessEvent event) {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        AddonManager.init();
    }

    private void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        if (event.includeClient()) {
            REBlockstateProvider blockstates = new REBlockstateProvider(gen, event.getExistingFileHelper());
            gen.addProvider(blockstates);
            gen.addProvider(new REItemModelProvider(gen, blockstates.getExistingHelper()));
        }
        if (event.includeServer()) {
            gen.addProvider(new REAdvancementProvider(gen));
            REBlockTagsProvider blockTagProvider = new REBlockTagsProvider(gen, event.getExistingFileHelper());
            gen.addProvider(blockTagProvider);
            gen.addProvider(new REItemTagsProvider(gen, blockTagProvider, event.getExistingFileHelper()));
            gen.addProvider(new RERecipeProvider(gen));
        }
    }
//    @SubscribeEvent
//    public static void onServerStopped(final FMLServerStoppingEvent event) {
//        Pathfinding.shutdown();
//    }
}