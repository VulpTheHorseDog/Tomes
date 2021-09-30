package com.vulp.tomes.spells.active;

import com.vulp.tomes.entities.SpectralSteedEntity;
import com.vulp.tomes.init.EntityInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class GhostlySteedSpell extends ActiveSpell {

    public GhostlySteedSpell(Enchantment.Rarity rarity, boolean isActive, boolean isRare) {
        super(rarity, isActive, isRare);
    }

    @Override
    public int getSpellCost() {
        return 35;
    }

    @Override
    public boolean onCast(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            Vector3d startPoint = playerIn.getEyePosition(0);
            RayTraceContext raytraceContext = new RayTraceContext(startPoint, startPoint.add(playerIn.getLookVec().scale(4.0D)), RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, null);
            BlockRayTraceResult rayHit = worldIn.rayTraceBlocks(raytraceContext);
            if (rayHit.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos pos = rayHit.getPos();
                SpectralSteedEntity entity = (SpectralSteedEntity) EntityInit.spectral_steed.spawn(((ServerPlayerEntity)playerIn).getServerWorld(), null, playerIn, pos, SpawnReason.MOB_SUMMONED, true, false);
                if (entity != null) {
                    entity.lifeTimer = 12000;
                    entity.setHorseTamed(true);
                    entity.saddleUp();
                }
                return true;
            }
        } return false;
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public boolean canTick() {
        return false;
    }

    @Override
    public void tick(World world, Entity entity) {

    }
}