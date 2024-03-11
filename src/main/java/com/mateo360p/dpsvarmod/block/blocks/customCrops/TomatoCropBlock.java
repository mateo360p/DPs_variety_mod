package com.mateo360p.dpsvarmod.block.blocks.customCrops;

import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class TomatoCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public TomatoCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.TOMATO.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    public InteractionResult use(BlockState mState, Level mLevel, BlockPos mPos, Player mPlayer, InteractionHand mHand, BlockHitResult mHit) {
        int i = mState.getValue(AGE);
        boolean flag = i == 7;
        if (!flag && mPlayer.getItemInHand(mHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 6) {
            int j = 1 + mLevel.random.nextInt(2);
            popResource(mLevel, mPos, new ItemStack(ModItems.TOMATO.get(), j + (flag ? 1 : 0)));
            mLevel.playSound((Player)null, mPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + mLevel.random.nextFloat() * 0.4F);
            BlockState blockstate = mState.setValue(AGE, Integer.valueOf(6));
            mLevel.setBlock(mPos, blockstate, 2);
            mLevel.gameEvent(GameEvent.BLOCK_CHANGE, mPos, GameEvent.Context.of(mPlayer, blockstate));
            return InteractionResult.sidedSuccess(mLevel.isClientSide);
        } else {
            return super.use(mState, mLevel, mPos, mPlayer, mHand, mHit);
        }
    }
}