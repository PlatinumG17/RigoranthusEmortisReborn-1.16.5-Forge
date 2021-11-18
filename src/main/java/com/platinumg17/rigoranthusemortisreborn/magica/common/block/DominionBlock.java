package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractDominionTile;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public abstract class DominionBlock extends ModBlock{
    public DominionBlock(String registryName) {
        super(registryName);
    }

    public DominionBlock(Properties properties, String registry) {
        super(properties, registry);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide && handIn == Hand.MAIN_HAND){
            if(worldIn.getBlockEntity(pos) instanceof AbstractDominionTile){
                AbstractDominionTile tile = (AbstractDominionTile) worldIn.getBlockEntity(pos);
                if(player.getItemInHand(handIn).getItem() == MagicItemsRegistry.bucketOfMana){
                    if(tile.getMaxDominion() - tile.getCurrentDominion() >= 1000){
                        tile.addDominion(1000);
                        if(!player.isCreative())
                            player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                    }
                    return super.use(state, worldIn, pos, player, handIn, hit);
                }else if(player.getItemInHand(handIn).getItem() instanceof BucketItem && ((BucketItem)player.getItemInHand(handIn).getItem()).getFluid() == Fluids.EMPTY){
                    if(tile.getCurrentDominion() >= 1000){
                        if(player.getItemInHand(handIn).getCount() == 1){
                            player.setItemInHand(handIn, new ItemStack(MagicItemsRegistry.bucketOfMana));
                            tile.removeDominion(1000);
                        }else if(player.addItem(new ItemStack(MagicItemsRegistry.bucketOfMana))) {
                            player.getItemInHand(handIn).shrink(1);
                            tile.removeDominion(1000);
                        }
                    }else if(tile.getCurrentDominion() >= 1000 && player.getItemInHand(handIn).getCount() == 1){
                        tile.removeDominion(1000);
                        player.setItemInHand(player.getUsedItemHand(),new ItemStack(MagicItemsRegistry.bucketOfMana));
                    }
                }
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}