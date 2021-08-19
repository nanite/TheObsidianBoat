package com.unrealdinnerbone.obsidianboat.entity;

import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.FluidTags;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class ObsidianBoatEntity extends BoatEntity {

    public ObsidianBoatEntity(EntityType<? extends ObsidianBoatEntity> entityType, World level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public ObsidianBoatEntity(World world, double x, double y, double z) {
        this(OB.ENTITY_TYPE.get(), world);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vector3d.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public ObsidianBoatEntity(World worldIn) {
        this(OB.ENTITY_TYPE.get(), worldIn);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public float getWaterLevelAbove() {
        AxisAlignedBB boundingBox = this.getBoundingBox();
        int minX = MathHelper.floor(boundingBox.minX);
        int maxX = MathHelper.ceil(boundingBox.maxX);
        int maxY = MathHelper.floor(boundingBox.maxY);
        int minY = MathHelper.ceil(boundingBox.maxY - this.lastYd);
        int minZ = MathHelper.floor(boundingBox.minZ);
        int maxZ = MathHelper.ceil(boundingBox.maxZ);
        BlockPos.Mutable blockPos = new BlockPos.Mutable();

        for(int y = maxY; y < minY; ++y) {
            float f = 0.0F;
            for(int x = minX; x < maxX; ++x) {
                for(int z = minZ; z < maxZ; ++z) {
                    blockPos.set(x, y, z);
                    FluidState fluidstate = this.level.getFluidState(blockPos);
                    if (fluidstate.is(FluidTags.LAVA)) {
                        f = Math.max(f, fluidstate.getHeight(this.level, blockPos));
                    }
                }
            }

            if (f < 1.0F) {
                return blockPos.getY() + f;
            }
        }

        return minY + 1;
    }

    @Override
    public boolean checkInWater() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        int minX = MathHelper.floor(axisalignedbb.minX);
        int maxX = MathHelper.ceil(axisalignedbb.maxX);
        int minY = MathHelper.floor(axisalignedbb.minY);
        int maxY = MathHelper.ceil(axisalignedbb.minY + 0.001D);
        int minZ = MathHelper.floor(axisalignedbb.minZ);
        int maxZ = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        this.waterLevel = Double.MIN_VALUE;
        BlockPos.Mutable blockPos = new BlockPos.Mutable();

        for(int x = minX; x < maxX; ++x) {
            for(int y = minY; y < maxY; ++y) {
                for(int z = minZ; z < maxZ; ++z) {
                    blockPos.set(x, y, z);
                    FluidState fluidstate = this.level.getFluidState(blockPos);
                    if (fluidstate.is(FluidTags.LAVA)) {
                        float f = y + fluidstate.getHeight(this.level, blockPos);
                        this.waterLevel = Math.max(f, this.waterLevel);
                        flag |= axisalignedbb.minY < f;
                    }
                }
            }
        }
        return flag;
    }

    @Nullable
    public BoatEntity.Status isUnderwater() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        double yOffset = axisalignedbb.maxY + 0.001D;
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.maxY);
        int l = MathHelper.ceil(yOffset);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        BlockPos.Mutable blockPos = new BlockPos.Mutable();

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    blockPos.set(k1, l1, i2);
                    FluidState fluidstate = this.level.getFluidState(blockPos);
                    if (fluidstate.is(FluidTags.LAVA) && yOffset < (double)((float)blockPos.getY() + fluidstate.getHeight(this.level, blockPos))) {
                        if (!fluidstate.isSource()) {
                            return BoatEntity.Status.UNDER_FLOWING_WATER;
                        }

                        flag = true;
                    }
                }
            }
        }

        return flag ? BoatEntity.Status.UNDER_WATER : null;
    }

    @Override
    protected void checkFallDamage(double amount, boolean onGround, BlockState state, BlockPos blockPos) {
        this.lastYd = this.getDeltaMovement().y;
    }

    @Override
    protected boolean canAddPassenger(Entity p_184219_1_) {
        return this.getPassengers().size() < 2 && !this.isEyeInFluid(FluidTags.LAVA);
    }

    @Override
    public void floatBoat() {
        double d0 = (double)-0.04F;
        double d1 = this.isNoGravity() ? 0.0D : (double)-0.04F;
        double d2 = 0.0D;
        this.invFriction = 0.05F;
        if (this.oldStatus == BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.ON_LAND) {
            this.waterLevel = this.getY(1.0D);
            this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
            this.lastYd = 0.0D;
            this.status = BoatEntity.Status.IN_WATER;
        } else {
            if (this.status == BoatEntity.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getY()) / (double)this.getBbHeight() + 0.35;
                this.invFriction = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.invFriction = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_WATER) {
                d2 = 0.01F;
                this.invFriction = 0.45F;
            } else if (this.status == BoatEntity.Status.IN_AIR) {
                this.invFriction = 0.9F;
            } else if (this.status == BoatEntity.Status.ON_LAND) {
                this.invFriction = this.landFriction;
                if (this.getControllingPassenger() instanceof PlayerEntity) {
                    this.landFriction /= 2.0F;
                }
            }

            Vector3d vector3d = this.getDeltaMovement();
            this.setDeltaMovement(vector3d.x * (double)this.invFriction, vector3d.y + d1, vector3d.z * (double)this.invFriction);
            this.deltaRotation *= this.invFriction;
            if (d2 > 0.0D) {
                Vector3d vector3d1 = this.getDeltaMovement();
                this.setDeltaMovement(vector3d1.x, (vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, vector3d1.z);
            }
        }

    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public Item getDropItem() {
        return OB.ITEM.get();
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(OB.ITEM.get());
    }

}
