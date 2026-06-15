package com.dsdafg.mod111.entity.custom;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import com.dsdafg.mod111.event.FearHandler;
import com.dsdafg.mod111.sound.ModSounds;

public class BunnymanEntity extends Monster {
    private int lookCooldown = 0;
    private static final int LOOK_COOLDOWN_MAX = 200; // 10 секунд

    public BunnymanEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 50;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2d, false));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f));
        
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSyncedData() {
        super.defineSyncedData();
    }

    @Override
    public void tick() {
        super.tick();
        
        if (!this.level().isClientSide) {
            // Проверяем, смотрит ли игрок на Bunnyman
            if (this.lookCooldown > 0) {
                this.lookCooldown--;
            }
            
            for (Player player : this.level().players()) {
                if (this.canSee(player) && player.distanceTo(this) < 32) {
                    Vec3 playerLook = player.getLookAngle();
                    Vec3 directionToBunnyman = this.position().subtract(player.position()).normalize();
                    
                    double dotProduct = playerLook.dot(directionToBunnyman);
                    
                    // Если игрок смотрит на Bunnyman (угол < 45 градусов)
                    if (dotProduct > 0.7d && this.lookCooldown <= 0) {
                        this.scarePlayer(player);
                        this.lookCooldown = LOOK_COOLDOWN_MAX;
                    }
                }
            }
        }
    }

    private void scarePlayer(Player player) {
        // Воспроизводим страшный звук
        if (!this.level().isClientSide) {
            this.playSound(ModSounds.BUNNYMAN_SCREAM.get(), 1.0f, 1.0f);
        }
        
        // Наносим урон
        player.hurt(this.damageSources().mobAttack(this), 5.0f);
        
        // Повышаем боязнь
        FearHandler.addFear(player, 30);
        
        // Добавляем эффект галлюцинации
        FearHandler.triggerHallucinations(player);
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(difficulty);
    }

    @Override
    public SpawnGroupData finalizeSpawn(net.minecraft.world.level.LevelAccessor level, 
                                        DifficultyInstance difficulty, 
                                        net.minecraft.world.entity.MobSpawnType reason, 
                                        SpawnGroupData spawnData) {
        return super.finalizeSpawn(level, difficulty, reason, spawnData);
    }

    @Override
    protected double getBlockSpeedFactor() {
        return super.getBlockSpeedFactor();
    }

    public static void setAttributes() {
        Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 30.0d)
            .add(Attributes.MOVEMENT_SPEED, 0.35d)
            .add(Attributes.ATTACK_DAMAGE, 5.0d)
            .add(Attributes.FOLLOW_RANGE, 35.0d)
            .add(Attributes.ATTACK_KNOCKBACK, 0.5d);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("LookCooldown", this.lookCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("LookCooldown")) {
            this.lookCooldown = nbt.getInt("LookCooldown");
        }
    }
}
