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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == BlockHitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vector3d = player.getViewVector(1.0F);
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vector3d1 = player.getEyePosition();

                for(Entity entity : list) {
                    if (entity.getBoundingBox().inflate(entity.getPickRadius()).contains(vector3d1)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
                ObsidianBoatEntity entity = new ObsidianBoatEntity(level, hitResult.getLocation());
                entity.yRotO = player.yRotO;
                entity.setYRot(player.getYRot());
                if (!level.noCollision(entity, entity.getBoundingBox().inflate(-0.1D))) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!level.isClientSide) {
                        level.addFreshEntity(entity);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        BlockState state = level.getBlockState(BlockPos.containing(hitResult.getLocation()));
                        if(state.is(Blocks.WATER) && player instanceof ServerPlayer serverplayer) {
                            OBRegistry.BOAT_TRIGGER.get().trigger(serverplayer);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }
}
