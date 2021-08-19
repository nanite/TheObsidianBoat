package com.unrealdinnerbone.obsidianboat.item;

import com.unrealdinnerbone.obsidianboat.OB;
import com.unrealdinnerbone.obsidianboat.data.BoatTrigger;
import com.unrealdinnerbone.obsidianboat.entity.ObsidianBoatEntity;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;


import java.util.function.Predicate;

public class ObsidianBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE =  EntityPredicates.NO_SPECTATORS.and(Entity::isPickable);

    public ObsidianBoatItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity p_77659_2_, Hand hand) {
        ItemStack itemstack = p_77659_2_.getItemInHand(hand);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(world, p_77659_2_, RayTraceContext.FluidMode.ANY);
        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemstack);
        } else {
            Vector3d vector3d = p_77659_2_.getViewVector(1.0F);
            double d0 = 5.0D;
            List<Entity> list = world.getEntities(p_77659_2_, p_77659_2_.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vector3d vector3d1 = p_77659_2_.getEyePosition(1.0F);

                for(Entity entity : list) {
                    AxisAlignedBB axisalignedbb = entity.getBoundingBox().inflate((double)entity.getPickRadius());
                    if (axisalignedbb.contains(vector3d1)) {
                        return ActionResult.pass(itemstack);
                    }
                }
            }

            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
                ObsidianBoatEntity obsidianBoat = new ObsidianBoatEntity(world, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
                obsidianBoat.yRot = p_77659_2_.yRot;
                if (!world.noCollision(obsidianBoat, obsidianBoat.getBoundingBox().inflate(-0.1D))) {
                    return ActionResult.fail(itemstack);
                } else {
                    if (!world.isClientSide) {
                        world.addFreshEntity(obsidianBoat);
                        if (!p_77659_2_.abilities.instabuild) {
                            itemstack.shrink(1);
                        }
                        BlockState state = world.getBlockState(new BlockPos(raytraceresult.getLocation()));
                        if(state.is(Blocks.WATER) && p_77659_2_ instanceof ServerPlayerEntity) {
                            BoatTrigger.INSTANCE.trigger((ServerPlayerEntity) p_77659_2_);
                        }
                    }
                    p_77659_2_.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.sidedSuccess(itemstack, world.isClientSide());
                }
            } else {
                return ActionResult.pass(itemstack);
            }
        }
    }
}
