package com.dsdafg.mod111.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class FearEffect extends MobEffect {
    public FearEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000); // Тёмно-красный цвет
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Эффект боязни замедляет и ослабляет зрение
        if (entity instanceof net.minecraft.world.entity.player.Player player) {
            // Добавляем замедление
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN, 100, amplifier
            ));
        }
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}
