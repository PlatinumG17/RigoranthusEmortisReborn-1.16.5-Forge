package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellTier;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellStats;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;

public class AugmentExtendTime extends AbstractAugment {
    public static AugmentExtendTime INSTANCE = new AugmentExtendTime();

    private AugmentExtendTime() {
        super(GlyphLib.AugmentExtendTimeID, "Extend Time");
    }

    @Override
    public int getDominionCost() {
        return 10;
    }

    @Override
    public ISpellTier.Tier getTier() {
        return ISpellTier.Tier.TWO;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.CLOCK;
    }

    @Override
    public SpellStats.Builder applyModifiers(SpellStats.Builder builder, AbstractSpellPart spellPart) {
        builder.addDurationModifier(1.0);
        return super.applyModifiers(builder, spellPart);
    }

    @Override
    public String getBookDescription() {
        return "Extends the time that spells last, including buffs, fangs, and summons";
    }
}