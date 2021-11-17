package com.platinumg17.rigoranthusemortisreborn.api;

//import com.platinumg17.rigoranthusemortisreborn.api.apimagic.event.familiar.AbstractFamiliarHolder;
//
//import com.platinumg17.rigoranthusemortisreborn.api.apimagic.event.spell.AbstractSpellPart;
//import com.platinumg17.rigoranthusemortisreborn.api.apimagic.event.spell.ISpellValidator;
//import com.platinumg17.rigoranthusemortisreborn.core.magica.common.items.FamiliarScript;
//import com.platinumg17.rigoranthusemortisreborn.core.magica.common.items.Glyph;
//import com.platinumg17.rigoranthusemortisreborn.core.magica.common.spell.validation.StandardSpellValidator;
//import com.platinumg17.rigoranthusemortisreborn.core.magica.setup.MagicItemsRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.platinumg17.rigoranthusemortisreborn.api.registry.*;
import net.minecraftforge.registries.IForgeRegistry;

///**
// * Main class of the Rigoranthus Emortis Reborn API.
// *
// * Obtain an instance with {@link RigoranthusEmortisRebornAPI#getInstance()}.
// */
public class RigoranthusEmortisRebornAPI {

    public static IForgeRegistry<Skill> SKILLS;
    public static IForgeRegistry<Accoutrement> ACCOUTERMENTS;
    public static IForgeRegistry<AccoutrementType> ACCOUTREMENT_TYPE;
    public static IForgeRegistry<IBeddingMaterial> BEDDING_MATERIAL;
    public static IForgeRegistry<ICasingMaterial> CASING_MATERIAL;

//    public List<VanillaPotionRecipe> vanillaPotionRecipes = new ArrayList<>();
//    private List<BrewingRecipe> brewingRecipes;
////
//    public List<BrewingRecipe> getAllPotionRecipes(){
//        if(brewingRecipes == null){
//            brewingRecipes = new ArrayList<>();
//            BrewingRecipeRegistry.getRecipes().forEach(ib ->{
//                if(ib instanceof BrewingRecipe)
//                    brewingRecipes.add((BrewingRecipe) ib);
//            });
//
//            vanillaPotionRecipes.forEach(vanillaPotionRecipe -> {
//                BrewingRecipe recipe = new BrewingRecipe(
//                        PotionIngredient.fromPotion(vanillaPotionRecipe.potionIn),
//                        Ingredient.of(vanillaPotionRecipe.reagent),
//                        PotionIngredient.fromPotion(vanillaPotionRecipe.potionOut).getStack()
//                );
//                brewingRecipes.add(recipe);
//            });
//
//        }
//        return brewingRecipes;
//    }
//
//    public enum PatchouliCategories{
//        spells,
//        machines,
//        equipment,
//        resources,
//        getting_started,
//        automation
//    }
//
//    /**
//     * Map of all spells to be registered in the spell system
//     *
//     * key: Unique spell ID. Please make this snake_case!
//     * value: Associated glyph
//     */
//    private HashMap<String, AbstractSpellPart> spell_map;
//
////    private HashMap<String, AbstractRitual> ritualMap;
//
//    private HashMap<String, AbstractFamiliarHolder> familiarHolderMap;
//
//    /**
//     * Contains the list of glyph item instances used by the glyph press.
//     */
//    private HashMap<String, Glyph> glyphMap;
//
//    private HashMap<String, FamiliarScript> familiarScriptMap;
//
////    /**
////     * Contains the list of parchment item instances created during registration
////     */
////    private HashMap<String, RitualTablet> ritualParchmentMap;
//
//    /** Validator to use when crafting a spell in the spell book. */
//    private ISpellValidator craftingSpellValidator;
//    /** Validator to use when casting a spell. */
//    private ISpellValidator castingSpellValidator;
//
////    private List<IEnchantingRecipe> enchantingApparatusRecipes;
//    /**
//     * Spells that all spellbooks contain
//     */
//    private List<AbstractSpellPart> startingSpells;
//
//    public List<AbstractSpellPart> getDefaultStartingSpells(){
//        return startingSpells;//spell_map.values().stream().filter(Config::isStarterEnabled).collect(Collectors.toList());
//    }
//
//    public boolean addStartingSpell(String tag){
//        if(RigoranthusEmortisRebornAPI.getInstance().getSpell_map().containsKey(tag)){
//            return startingSpells.add(RigoranthusEmortisRebornAPI.getInstance().getSpell_map().get(tag));
//        }else{
//            throw new IllegalStateException("Attempted to add a starting spell for an unregistered spell. Spells must be added to the Spell Map first!");
//        }
//    }
//
//    public Item getGlyphItem(String glyphName){
//        for(Item i : MagicItemsRegistry.RegistrationHandler.ITEMS){
//            if(i.getRegistryName().equals(new ResourceLocation(EmortisConstants.MOD_ID, getSpellRegistryName(glyphName)))){
//                return i;
//            }
//        }
//        return null;
//    }
//
//    public Item getGlyphItem(AbstractSpellPart spell){
//        return getGlyphItem(spell.tag);
//    }
//
//    public Item getFamiliarItem(String id){
//        return familiarScriptMap.get(id);
//    }
//
//    public AbstractSpellPart registerSpell(String id, AbstractSpellPart part){
//        glyphMap.put(id, new Glyph(getSpellRegistryName(id), part));
//        return spell_map.put(id, part);
//    }
//
//    public AbstractSpellPart registerSpell(AbstractSpellPart part){
//        return registerSpell(part.getTag(), part);
//    }
//
//
//    /**
//     * A registration helper for addons. Adds mana costs into the fallback cost map.
//     */
//    public AbstractSpellPart registerSpell(String id, AbstractSpellPart part, int manaCost){
////        Config.addonSpellCosts.put(id, manaCost);
//        return registerSpell(id, part);
//    }
//
////    public AbstractRitual registerRitual(String id, AbstractRitual ritual){
////        ritualParchmentMap.put(id, new RitualTablet(getRitualRegistryName(id), ritual));
////        return ritualMap.put(id, ritual);
////    }
//
//    public AbstractFamiliarHolder registerFamiliar(AbstractFamiliarHolder familiar){
//        this.familiarScriptMap.put(familiar.id, new FamiliarScript(familiar));
//        return familiarHolderMap.put(familiar.id, familiar);
//    }
//
////    public @Nullable AbstractRitual getRitual(String id){
////        if(!ritualMap.containsKey(id))
////            return null;
////        try{
////            return ritualMap.get(id).getClass().newInstance();
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////        return null;
////    }
//
////    public @Nullable AbstractRitual getRitual(String id, RitualTile tile, RitualContext context){
////        AbstractRitual ritual = getRitual(id);
////        if(ritual != null){
////            ritual.tile = tile;
////            ritual.setContext(context);
////        }
////        return ritual;
////    }
//
//    public String getSpellRegistryName(String id){
//        return "glyph_"+ id.toLowerCase();
//    }
//
//    public String getRitualRegistryName(String id){
//        return "ritual_"+ id.toLowerCase();
//    }
//
//    public Map<String, AbstractSpellPart> getSpell_map() {
//        return spell_map;
//    }
//
//    public Map<String, Glyph> getGlyphMap(){
//        return glyphMap;
//    }
//
////    public Map<String, AbstractRitual> getRitualMap(){
////        return ritualMap;
////    }
//
////    public Map<String, RitualTablet> getRitualItemMap(){
////        return ritualParchmentMap;
////    }
////
////    public List<IEnchantingRecipe> getEnchantingApparatusRecipes() {
////        return enchantingApparatusRecipes;
////    }
//
//    public Map<String, AbstractFamiliarHolder> getFamiliarHolderMap(){
//        return this.familiarHolderMap;
//    }
//    public Map<String, FamiliarScript> getFamiliarScriptMap(){
//        return this.familiarScriptMap;
//    }
//
////    public List<IEnchantingRecipe> getEnchantingApparatusRecipes(World world) {
////        List<IEnchantingRecipe> recipes = new ArrayList<>(enchantingApparatusRecipes);
////        RecipeManager manager = world.getRecipeManager();
////        for(IRecipe i : manager.getRecipes()){
////            if(i instanceof EnchantingApparatusRecipe){
////                recipes.add((IEnchantingRecipe) i);
////            }
////        }
////        return recipes;
////    }
//
////    public GlyphPressRecipe getGlyphPressRecipe(World world, Item reagent, @Nullable ISpellTier.Tier tier){
////        if(reagent == null || reagent == Items.AIR)
////            return null;
////
////        RecipeManager manager = world.getRecipeManager();
////        for(IRecipe i : manager.getRecipes()){
////            if(i instanceof GlyphPressRecipe){
////                if(((GlyphPressRecipe) i).reagent.getItem() == reagent && ((GlyphPressRecipe) i).tier == tier)
////                    return (GlyphPressRecipe) i;
////            }
////        }
////        return null;
////    }
//
//    /**
//     * Returns the {@link ISpellValidator} that enforces the standard rules for spell crafting.
//     * This validator relaxes the rule about starting with a cast method, to allow for spells that will be imprinted
//     * onto caster items, which generally have a built-in cast method.
//     */
//    public ISpellValidator getSpellCraftingSpellValidator() {
//        return craftingSpellValidator;
//    }
//
//    /**
//     * Returns the {@link ISpellValidator} that enforces the standard rules for spells at cast time.
//     * This validator enforces all rules, asserting that a spell can be cast.
//     */
//    public ISpellValidator getSpellCastingSpellValidator() {
//        return castingSpellValidator;
//    }
//
//    private RigoranthusEmortisRebornAPI(){
//        spell_map = new HashMap<>();
//        glyphMap = new HashMap<>();
//        startingSpells = new ArrayList<>();
////        enchantingApparatusRecipes = new ArrayList<>();
////        ritualMap = new HashMap<>();
////        ritualParchmentMap = new HashMap<>();
//        craftingSpellValidator = new StandardSpellValidator(false);
//        castingSpellValidator = new StandardSpellValidator(true);
//        familiarHolderMap = new HashMap<>();
//        familiarScriptMap = new HashMap<>();
//    }
//    /** Retrieves a handle to the singleton instance. */
//    public static RigoranthusEmortisRebornAPI getInstance() {
//        return RigoranthusEmortisRebornAPI;
//    }
//    private static final RigoranthusEmortisRebornAPI RigoranthusEmortisRebornAPI = new RigoranthusEmortisRebornAPI();

    public static final Logger LOGGER = LogManager.getLogger("rigoranthusemortisreborn");
}
