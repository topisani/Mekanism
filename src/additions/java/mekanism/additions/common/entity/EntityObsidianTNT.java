package mekanism.additions.common.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mekanism.additions.common.config.MekanismAdditionsConfig;
import mekanism.additions.common.registries.AdditionsEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityObsidianTNT extends TNTEntity {

    public EntityObsidianTNT(EntityType<EntityObsidianTNT> type, World world) {
        super(type, world);
        setFuse(MekanismAdditionsConfig.additions.obsidianTNTDelay.get());
        preventEntitySpawning = true;
    }

    public EntityObsidianTNT(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(world, x, y, z, igniter);
        setFuse(MekanismAdditionsConfig.additions.obsidianTNTDelay.get());
        preventEntitySpawning = true;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (isAlive() && getFuse() > 0) {
            world.addParticle(ParticleTypes.LAVA, func_226277_ct_(), func_226278_cu_() + 0.5, func_226281_cx_(), 0, 0, 0);
        }
    }

    @Override
    protected void explode() {
        world.createExplosion(this, func_226277_ct_(), func_226278_cu_() + (double) (getHeight() / 16.0F), func_226281_cx_(), MekanismAdditionsConfig.additions.obsidianTNTBlastRadius.get(), Mode.BREAK);
    }

    @Nonnull
    @Override
    public EntityType<?> getType() {
        return AdditionsEntityTypes.OBSIDIAN_TNT.getEntityType();
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}