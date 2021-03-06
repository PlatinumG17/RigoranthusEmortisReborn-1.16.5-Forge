package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellTier;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellStats;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;

public class AugmentDampen extends AbstractAugment {
    public static AugmentDampen INSTANCE = new AugmentDampen();

    private AugmentDampen() {
        super(GlyphLib.AugmentDampenID, "Dampen");
    }

    @Override
    public int getDominionCost() {
        return -5;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.NETHER_BRICK;
    }

    @Override
    public ISpellTier.Tier getTier() {
        return Tier.TWO;
    }

    @Override
    public String getBookDescription() {
        return "Decreases the power of most spells. Decreases dominion cost slightly, but never completely.";
    }

    @Override
    public SpellStats.Builder applyModifiers(SpellStats.Builder builder, AbstractSpellPart spellPart) {
        builder.addAmplification(-1.0);
        return super.applyModifiers(builder, spellPart);
    }
}