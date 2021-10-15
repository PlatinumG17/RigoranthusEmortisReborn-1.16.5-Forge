package com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class OpulentArmor extends ArmorItem {
    private boolean previousEquip = false;

    public OpulentArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot2").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot3").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot4").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot5").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot6").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot7").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".opulent_ingot8").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY));
        }
    }

    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player) {
        if (Config.enableArmorSetBonuses.get()) {
            ItemStack boots = player.getItemBySlot(EquipmentSlotType.FEET);
            ItemStack legs = player.getItemBySlot(EquipmentSlotType.LEGS);
            ItemStack chest = player.getItemBySlot(EquipmentSlotType.CHEST);
            ItemStack helm = player.getItemBySlot(EquipmentSlotType.HEAD);
            if (boots.getItem() == Registration.OPULENT_NETHERITE_BOOTS && legs.getItem() == Registration.OPULENT_NETHERITE_LEGGINGS && chest.getItem() == Registration.OPULENT_NETHERITE_CHESTPLATE && helm.getItem() == Registration.OPULENT_NETHERITE_HELMET) {
                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.OPULENT_SET_BONUS, 5, 1));
                this.previousEquip = true;
            } else if (this.previousEquip) {
                player.removeEffect(RigoranthusEffectRegistry.OPULENT_SET_BONUS);
                this.previousEquip = false;
            }
        }
    }
}