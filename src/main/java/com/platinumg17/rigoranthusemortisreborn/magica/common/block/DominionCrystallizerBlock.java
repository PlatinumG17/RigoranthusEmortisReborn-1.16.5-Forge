package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.DominionCrystallizerTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class DominionCrystallizerBlock extends ModBlock {

    public DominionCrystallizerBlock() {
        super(defaultProperties().noOcclusion(), LibBlockNames.DOMINION_CRYSTALLIZER);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DominionCrystallizerTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!(worldIn.getBlockEntity(pos) instanceof DominionCrystallizerTile))
            return super.use(state, worldIn, pos, player, handIn, hit);
        DominionCrystallizerTile tile = (DominionCrystallizerTile) worldIn.getBlockEntity(pos);
        ItemStack stack = tile.stack;
        worldIn.addFreshEntity(new ItemEntity(worldIn, player.getX(), player.getY(), player.getZ(), stack.copy()));
        tile.stack = ItemStack.EMPTY;
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if(!(worldIn.getBlockEntity(pos) instanceof DominionCrystallizerTile))
            return;
        DominionCrystallizerTile tile = (DominionCrystallizerTile) worldIn.getBlockEntity(pos);
        ItemStack stack = tile.stack;
        worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack.copy()));
        tile.stack = ItemStack.EMPTY;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    @Override public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) { return SHAPE; }
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(4.25, 15, 4.25, 11.75, 16, 11.75),
            Block.box(1, -3, 3, 2, 0, 4),
            Block.box(1, 0, 3, 2, 1, 7),
            Block.box(1, -3, 12, 2, 0, 13),
            Block.box(1, 0, 9, 2, 1, 13),
            Block.box(2, 2, 7, 14, 3, 9),
            Block.box(11, 2, 2, 12, 3, 3),
            Block.box(13, 1, 11, 15, 2, 12),
            Block.box(13, 2, 4, 14, 3, 5),
            Block.box(4, 1, 1, 5, 2, 3),
            Block.box(3, 1, 13, 4, 2, 14),
            Block.box(2, 1, 12, 3, 2, 14),
            Block.box(1, 1, 11, 3, 2, 12),
            Block.box(11, 2, 13, 12, 3, 14),
            Block.box(13, 2, 11, 14, 3, 12),
            Block.box(11, 1, 1, 12, 2, 3),
            Block.box(11, 1, 13, 12, 2, 15),
            Block.box(2, 2, 11, 3, 3, 12),
            Block.box(4, 2, 13, 5, 3, 14),
            Block.box(1, 1, 4, 3, 2, 5),
            Block.box(2, 1, 3, 3, 2, 4),
            Block.box(2, 1, 2, 4, 2, 3),
            Block.box(4, 1, 13, 5, 2, 15),
            Block.box(2, 2, 4, 3, 3, 5),
            Block.box(13, 1, 4, 15, 2, 5),
            Block.box(4, 2, 2, 5, 3, 3),
            Block.box(3, -3, 13, 4, -2, 14),
            Block.box(2, -3, 2, 4, -2, 3),
            Block.box(13, 1, 12, 14, 2, 13),
            Block.box(12, 1, 13, 14, 2, 14),
            Block.box(12, -3, 13, 14, -2, 14),
            Block.box(13, 1, 3, 14, 2, 4),
            Block.box(12, 1, 2, 14, 2, 3),
            Block.box(12, -3, 2, 14, -2, 3),
            Block.box(12, -3, 1, 13, 0, 2),
            Block.box(9, 0, 1, 13, 1, 2),
            Block.box(3, -3, 1, 4, 0, 2),
            Block.box(3, 0, 1, 7, 1, 2),
            Block.box(7, 2, 2, 9, 3, 14),
            Block.box(7, -3, 14, 9, 3, 15),
            Block.box(7, -3, 1, 9, 3, 2),
            Block.box(2, -3, 12, 3, -2, 14),
            Block.box(2, -3, 3, 3, -2, 4),
            Block.box(13, -3, 12, 14, -2, 13),
            Block.box(13, -3, 3, 14, -2, 4),
            Block.box(14, -3, 12, 15, 0, 13),
            Block.box(14, 0, 9, 15, 1, 13),
            Block.box(14, -3, 3, 15, 0, 4),
            Block.box(14, 0, 3, 15, 1, 7),
            Block.box(1, -3, 7, 2, 3, 9),
            Block.box(14, -3, 7, 15, 3, 9),
            Block.box(11.75, 13, 11.75, 13.75, 16, 13.75),
            Block.box(2.25, 13, 11.75, 4.25, 16, 13.75),
            Block.box(2.25, 13, 2.25, 4.25, 16, 4.25),
            Block.box(4.25, 15, 2.25, 6.25, 16, 3.25),
            Block.box(9.75, 15, 2.25, 11.75, 16, 3.25),
            Block.box(9.75, 15, 12.75, 11.75, 16, 13.75),
            Block.box(4.25, 15, 12.75, 6.25, 16, 13.75),
            Block.box(9.75, 15, 3.25, 10.75, 16, 12.75),
            Block.box(5.25, 15, 3.25, 6.25, 16, 12.75),
            Block.box(2.25, 15, 4.25, 3.25, 16, 6.25),
            Block.box(2.25, 15, 9.75, 3.25, 16, 11.75),
            Block.box(12.75, 15, 9.75, 13.75, 16, 11.75),
            Block.box(3.25, 15, 9.75, 12.75, 16, 10.75),
            Block.box(12.75, 15, 4.25, 13.75, 16, 6.25),
            Block.box(3.25, 15, 5.25, 12.75, 16, 6.25),
            Block.box(11.75, 13, 2.25, 13.75, 16, 4.25),
            Block.box(12, 2, 2.25, 13.75, 13, 4),
            Block.box(2.25, 2, 2.25, 4, 13, 4),
            Block.box(12, 2, 12, 13.75, 13, 13.75),
            Block.box(2.25, 2, 12, 4, 13, 13.75),
            Block.box(3, -3, 14, 4, 0, 15),
            Block.box(3, 0, 14, 7, 1, 15),
            Block.box(12, -3, 14, 13, 0, 15),
            Block.box(9, 0, 14, 13, 1, 15)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
}