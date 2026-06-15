package com.dsdafg.mod111.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class HallucinationEffect extends MobEffect {
    public HallucinationEffect() {
        super(MobEffectCategory.HARMFUL, 0x9D4EDD); // Фиолетовый цвет
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Галлюцинации добавляют размытие и слепоту
        if (entity instanceof net.minecraft.world.entity.player.Player player) {
            // Добавляем слепоту (галлюцинация)
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                net.minecraft.world.effect.MobEffects.BLINDNESS, 60, 0
            ));
            
            // Добавляем тошноту для эффекта галлюцинаций
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                net.minecraft.world.effect.MobEffects.NAUSEA, 40, 0
            ));
        }
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}
