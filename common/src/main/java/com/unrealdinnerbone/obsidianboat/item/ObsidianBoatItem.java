package com.unrealdinnerbone.obsidianboat.item;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import com.unrealdinnerbone.obsidianboat.entity.ObsidianBoatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class ObsidianBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE =  EntitySelector.NO_SPECTATORS.and(Entity::isPickable);

    public ObsidianBoatItem() {
        super(new Item.Properties()
                .fireResistant()
                .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player p_77659_2_, InteractionHand hand) {
        ItemStack itemstack = p_77659_2_.getItemInHand(hand);
        BlockHitResult raytraceresult = getPlayerPOVHitResult(world, p_77659_2_, ClipContext.Fluid.ANY);
        if (raytraceresult.getType() == BlockHitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vector3d = p_77659_2_.getViewVector(1.0F);
            double d0 = 5.0D;
            List<Entity> list = world.getEntities(p_77659_2_, p_77659_2_.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vector3d1 = p_77659_2_.getEyePosition(1.0F);

                for(Entity entity : list) {
                    AABB axisalignedbb = entity.getBoundingBox().inflate((double)entity.getPickRadius());
                    if (axisalignedbb.contains(vector3d1)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (raytraceresult.getType() == BlockHitResult.Type.BLOCK) {
                ObsidianBoatEntity entity = new ObsidianBoatEntity(world, raytraceresult.getLocation());
                entity.yRotO = p_77659_2_.yRotO;
                if (!world.noCollision(entity, entity.getBoundingBox().inflate(-0.1D))) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!world.isClientSide) {
                        world.addFreshEntity(entity);
                        if (!p_77659_2_.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        BlockState state = world.getBlockState(BlockPos.containing(raytraceresult.getLocation()));
                        if(state.is(Blocks.WATER) && p_77659_2_ instanceof ServerPlayer player) {
                            OBRegistry.BOAT_TRIGGER.get().trigger(player);
                        }
                    }
                    p_77659_2_.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }
}
