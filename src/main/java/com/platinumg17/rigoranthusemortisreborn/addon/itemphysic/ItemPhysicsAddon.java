package com.platinumg17.rigoranthusemortisreborn.addon.itemphysic;

import java.lang.reflect.Method;
import java.util.Collection;
import com.google.common.collect.Lists;

import com.platinumg17.rigoranthusemortisreborn.addon.Addon;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.ReflectionUtil;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;

public class ItemPhysicsAddon implements Addon {

    private static final String className = "team.creative.itemphysic.api.ItemPhysicAPI";

    private static final String methodName = "addSortingObjects";
    private static final Class<?>[] paramTypes = new Class[] {String.class, Object[].class};

    private static final String swimmingItems = "swimmingItems";
    private static final String burningItems = "burningItems";
    private static final String undestroyableItems = "undestroyableItems";
    private static final String ignitingItems = "ignitingItems";

    @Override
    public void exec() {
        Class<?> API_CLASS = ReflectionUtil.getClass(className);

        Method addMethod = ReflectionUtil.getMethod(API_CLASS, methodName, paramTypes);

        ReflectionUtil.invokeStaticMethod(addMethod, swimmingItems,
                CanisItems.BREEDING_BONE, CanisItems.HOMINI_TREAT, CanisItems.MASTER_TREAT,
                CanisItems.SUPER_TREAT, CanisItems.TRAINING_TREAT, CanisItems.COLLAR_SHEARS,
                CanisItems.THROW_BONE, CanisItems.WOOL_COLLAR, CanisItems.TREAT_BAG,
                CanisItems.CHEW_STICK,
                ItemInit.RAZORTOOTH_KUNAI, ItemInit.THROWING_KNIFE, ItemInit.RAZORTOOTH_FRISBEE
        );

        ReflectionUtil.invokeStaticMethod(addMethod, burningItems,
                CanisBlocks.CANIS_BED, CanisItems.BREEDING_BONE, CanisItems.HOMINI_TREAT,
                CanisItems.MASTER_TREAT, CanisItems.SUPER_TREAT,  CanisItems.TRAINING_TREAT,
                CanisItems.COLLAR_SHEARS, CanisItems.THROW_BONE,  CanisItems.WOOL_COLLAR,
                CanisItems.TREAT_BAG, CanisItems.CHEW_STICK

        );
    }

    @Override
    public Collection<String> getMods() {
        return Lists.newArrayList("itemphysic");
    }

    @Override
    public String getName() {
        return "Item Physics Addon";
    }
}