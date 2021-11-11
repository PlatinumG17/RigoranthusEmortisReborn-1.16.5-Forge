package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class MoveToClosestItemGoal extends Goal {

    protected final CanisEntity canis;
    protected final Predicate<ItemEntity> predicate;
    protected final Comparator<Entity> sorter;
    protected final double followSpeed;
    protected final PathNavigator canisNavigator;
    protected final float minDist;

    protected ItemEntity target;
    private int timeToRecalcPath;
    private float maxDist;
    private float oldWaterCost;
    private double oldRangeSense;

    public MoveToClosestItemGoal(CanisEntity canisIn, double speedIn, float maxDist, float stopDist, @Nullable Predicate<ItemStack> targetSelector) {
        this.canis = canisIn;
        this.canisNavigator = canisIn.getNavigation();
        this.followSpeed = speedIn;
        this.maxDist = maxDist;
        this.minDist = stopDist;
        this.predicate = (entity) -> {
            if (entity.isInvisible()) {
                return false;
            } else if (targetSelector != null && !targetSelector.test(entity.getItem())) {
                return false;
            } else {
                return entity.distanceTo(this.canis) <= EntityUtil.getFollowRange(this.canis);
            }
        };
        this.sorter = new EntityUtil.Sorter(canisIn);
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        double d0 = EntityUtil.getFollowRange(this.canis);
        List<ItemEntity> list = this.canis.level.getEntitiesOfClass(ItemEntity.class, this.canis.getBoundingBox().inflate(d0, 4.0D, d0), this.predicate);
        if (list.isEmpty()) {
            return false;
        } else {
            Collections.sort(list, this.sorter);
            this.target = list.get(0);
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        ItemEntity target = this.target;
        if (target == null || !target.isAlive()) {
            return false;
        } else {
            double d0 = EntityUtil.getFollowRange(this.canis);
            double dist = this.canis.distanceToSqr(target);
            if (dist > d0 * d0 || dist < this.minDist * this.minDist) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.canis.getPathfindingMalus(PathNodeType.WATER);
        this.canis.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        this.oldRangeSense = this.canis.getAttribute(Attributes.FOLLOW_RANGE).getValue();
        this.canis.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.maxDist);
    }

    @Override
    public void tick() {
        if (!this.canis.isInSittingPose()) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;

                if (!this.canisNavigator.moveTo(this.target, this.followSpeed)) {
                    this.canis.getLookControl().setLookAt(this.target, 10.0F, this.canis.getMaxHeadXRot());
                }
            }
        }
    }

    @Override
    public void stop() {
        this.target = null;
        this.canisNavigator.stop();
        this.canis.setPathfindingMalus(PathNodeType.WATER, this.oldWaterCost);
        this.canis.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.oldRangeSense);
    }
}