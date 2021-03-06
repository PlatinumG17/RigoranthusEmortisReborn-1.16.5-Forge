package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment;

import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellStats;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;

public class AugmentAmplify extends AbstractAugment {
    public static AugmentAmplify INSTANCE = new AugmentAmplify();


    private AugmentAmplify() {
        super(GlyphLib.AugmentAmplifyID, "Amplify");
    }

    @Override
    public int getDominionCost() {
        return 20;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.DIAMOND;
    }

    @Override
    public Tier getTier() {
        return Tier.ONE;
    }

    @Override
    public SpellStats.Builder applyModifiers(SpellStats.Builder builder, AbstractSpellPart spellPart) {
        builder.addAmplification(1.0);
        return super.applyModifiers(builder, spellPart);
    }

    @Override
    public String getBookDescription() {
        return "Additively increases the power of most spell effects. Can increase the harvest level of Break and increases the damage of spells.";
    }
}