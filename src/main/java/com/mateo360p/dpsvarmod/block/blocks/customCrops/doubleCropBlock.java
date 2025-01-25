package com.mateo360p.dpsvarmod.block.blocks.customCrops;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.CropBlock.*;

public class doubleCropBlock extends DoublePlantBlock implements BonemealableBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static int GROW_2ND_AGE = 3;
    public static final int MAX_AGE = 7;
    public final Supplier<? extends ItemLike> seed;
    private static final VoxelShape[] LOWER_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), // AGE 0
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), // AGE 1
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), // AGE 2
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), //AGE 3 - 2ND BLOCK
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), // AGE 4
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), // AGE 5
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), // AGE 6
            customCropBlock.MAX_SHAPE}; //AGE 7
    private static final VoxelShape[] UPPER_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), //AGE 3 - 2ND BLOCK
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), //AGE 4
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), //AGE 5
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D), //AGE 6
            customCropBlock.MAX_SHAPE}; //AGE 7

    public doubleCropBlock(Supplier<? extends ItemLike> seed) {
        super(customCropBlock.DefBlockProperties);
        this.seed = seed;
        //Java optionals suck
    }

    protected ItemLike getBaseSeedId() {
        return this.seed.get();
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(getBaseSeedId());
    }

    private boolean isMaxAge(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && !this.isMaxAge(state);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext var) {
        return this.defaultBlockState();
    }

    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor accs, BlockPos pos, BlockPos pos2) {
        return !state.canSurvive(accs, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        super.createBlockStateDefinition(builder);
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collContext) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? UPPER_SHAPE_BY_AGE[Math.min(Math.abs(MAX_AGE - (state.getValue(AGE) + 1)), UPPER_SHAPE_BY_AGE.length - 1)] : LOWER_SHAPE_BY_AGE[state.getValue(AGE)];
    }

    //------Interactions------------------
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) { return state.is(Blocks.FARMLAND); }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext placeContext) { return false; }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack itemStack) { }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Ravager && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
            level.destroyBlock(pos, true, entity);

        super.entityInside(state, level, pos, entity);
    }

    public void randomTick(BlockState state, ServerLevel sLevel, BlockPos pos, RandomSource random) {
        float f = customCropBlock.getGrowthSpeed(this, sLevel, pos);
        boolean flag = random.nextInt((int)(25.0F / f) + 1) == 0;
        if (flag) grow(sLevel, state, pos, 1);
    }

    private void grow(ServerLevel sLevel, BlockState state, BlockPos pos, int num) {
        int i = Math.min(state.getValue(AGE) + num, MAX_AGE); //i = min(next AGE, maxAge) --> nextAge > maxAGE = cannot grow
        if (canGrow(sLevel, pos, state, i)) {
            sLevel.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
            if (i >= GROW_2ND_AGE) {
                BlockPos upperBlock = pos.above();
                sLevel.setBlock(upperBlock, copyWaterloggedFrom(sLevel, pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(i)).setValue(HALF, DoubleBlockHalf.UPPER)), 3);
            }
        }
    }

    //---Necessities--------------
    private boolean canGrowInto(LevelReader reader, BlockPos pos) {
        BlockState blockstate = reader.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(this);
    }

    private boolean canGrow(LevelReader reader, BlockPos pos, BlockState state, int num) {
        return !isMaxAge(state) && sufficientLight(reader, pos) && (num < GROW_2ND_AGE || canGrowInto(reader, pos.above()));
    }

    private static boolean sufficientLight(LevelReader reader, BlockPos pos) {
        return reader.getRawBrightness(pos, 0) >= 8 || reader.canSeeSky(pos);
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        if (!isLower(state)) { return super.canSurvive(state, reader, pos); }
        else { //For the lower part
            BlockPos below = pos.below();
            boolean isSoil = mayPlaceOn(reader.getBlockState(below), reader, below);
            /*if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
                isSoil = reader.getBlockState(below).canSustainPlant(reader, below, Direction.UP, this);*/
            return isSoil && sufficientLight(reader, pos) && (state.getValue(AGE) < GROW_2ND_AGE || isUpper(reader.getBlockState(pos.above())));
        }
    }

    //---Get divisions--------------
    private boolean isLower(BlockState state) {
        return state.is(this) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private boolean isUpper(BlockState state) {
        return state.is(this) && state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    @Nullable
    private PosAndState getLowerHalf(LevelReader reader, BlockPos pos, BlockState state) {
        if (isLower(state)) { return new PosAndState(pos, state); }
        else {
            BlockPos blockpos = pos.below();
            BlockState blockstate = reader.getBlockState(blockpos);
            return isLower(blockstate) ? new PosAndState(blockpos, blockstate) : null;
        }
    }

    //---Bone meal Lines------------
    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state, boolean bool) {
        PosAndState $posandstate = getLowerHalf(reader, pos, state);
        return $posandstate != null && canGrow(reader, $posandstate.pos, $posandstate.state, $posandstate.state.getValue(AGE) + 1);
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) { return true; }

    public void performBonemeal(ServerLevel sLevel, RandomSource random, BlockPos pos, BlockState state) {
        PosAndState $posandstate = getLowerHalf(sLevel, pos, state);
        if ($posandstate != null) grow(sLevel, $posandstate.state, $posandstate.pos, 1);
    }

    static record PosAndState(BlockPos pos, BlockState state) { }
}