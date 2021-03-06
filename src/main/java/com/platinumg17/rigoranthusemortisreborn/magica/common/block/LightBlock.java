package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.LightTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import java.util.Random;

public class LightBlock extends ModBlock {

    public static final Property<Integer> LIGHT_LEVEL = IntegerProperty.create("level", 0, 15);

    protected static final VoxelShape SHAPE = box(4.0D, 4.0D, 4.0D, 12.0D, 12.0D, 12.0D);

    public LightBlock() {
        super(defaultProperties().lightLevel((bs)-> bs.getValue(LIGHT_LEVEL) == 0 ? 14 :  bs.getValue(LIGHT_LEVEL)).noCollission().noOcclusion().dynamicShape().strength(0f,0f), "light_block");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new LightTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_LEVEL);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof LightTile){
            Random random = context.getLevel().random;
            LightTile tile = (LightTile) context.getLevel().getBlockEntity(context.getClickedPos());
            tile.red = Math.max(10, random.nextInt(255));
            tile.green = Math.max(10, random.nextInt(255));
            tile.blue = Math.max(10, random.nextInt(255));

        }
        return super.getStateForPlacement(context);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}