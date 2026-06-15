package com.dsdafg.mod111.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.nbt.CompoundTag;
import com.dsdafg.mod111.effect.ModEffects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.dsdafg.mod111.Mod111;

@Mod.EventBusSubscriber(modid = Mod111.MOD_ID)
public class FearHandler {
    private static final String FEAR_NBT_KEY = "Mod111Fear";
    private static final String STRESS_NBT_KEY = "Mod111Stress";
    private static final int MAX_FEAR = 100;

    /**
     * Добавляет боязнь игроку (0-100)
     */
    public static void addFear(Player player, int amount) {
        int currentFear = getFear(player);
        int newFear = Math.min(currentFear + amount, MAX_FEAR);
        setFear(player, newFear);
        
        // Если боязнь высокая, добавляем эффект боязни
        if (newFear >= 50) {
            int amplifier = (newFear - 50) / 25; // 0-1
            player.addEffect(new MobEffectInstance(
                ModEffects.FEAR.get(), 
                200, // 10 секунд
                amplifier, 
                false, 
                false
            ));
        }
    }

    /**
     * Добавляет стресс игроку
     */
    public static void addStress(Player player, int amount) {
        int currentStress = getStress(player);
        int newStress = currentStress + amount;
        setStress(player, newStress);
        
        // Стресс может быть неограниченным, но влияет на боязнь
        addFear(player, amount / 2);
    }

    /**
     * Получает текущий уровень боязни
     */
    public static int getFear(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getInt(FEAR_NBT_KEY);
    }

    /**
     * Устанавливает уровень боязни
     */
    public static void setFear(Player player, int fear) {
        CompoundTag tag = player.getPersistentData();
        tag.putInt(FEAR_NBT_KEY, Math.max(0, Math.min(fear, MAX_FEAR)));
    }

    /**
     * Получает текущий уровень стресса
     */
    public static int getStress(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getInt(STRESS_NBT_KEY);
    }

    /**
     * Устанавливает уровень стресса
     */
    public static void setStress(Player player, int stress) {
        CompoundTag tag = player.getPersistentData();
        tag.putInt(STRESS_NBT_KEY, Math.max(0, stress));
    }

    /**
     * Запускает галлюцинации
     */
    public static void triggerHallucinations(Player player) {
        player.addEffect(new MobEffectInstance(
            ModEffects.HALLUCINATION.get(),
            100, // 5 секунд
            0,
            false,
            false
        ));
        
        // Добавляем стресс
        addStress(player, 15);
    }

    /**
     * Уменьшает боязнь со временем (когда светло и нет врагов)
     */
    public static void reduceFear(Player player, int amount) {
        int currentFear = getFear(player);
        setFear(player, Math.max(0, currentFear - amount));
    }

    /**
     * Уменьшает стресс со временем (просто ждать)
     */
    public static void reduceStress(Player player, int amount) {
        int currentStress = getStress(player);
        setStress(player, Math.max(0, currentStress - amount));
    }

    /**
     * Обработчик тика для автоматического снижения боязни и стресса
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide) {
            Player player = event.player;
            
            // Уменьшаем боязнь, если игрок на свету (уровень света >= 14)
            if (player.level().getBrightness(net.minecraft.world.phys.BlockPos.containing(player.getX(), player.getEyeY(), player.getZ())) >= 14) {
                reduceFear(player, 1); // Медленное снижение на свету
            }
            
            // Уменьшаем стресс с течением времени (просто ждать)
            if (player.tickCount % 20 == 0) { // Каждую секунду
                reduceStress(player, 2);
            }
        }
    }

    /**
     * Увеличиваем боязнь в темноте
     */
    @SubscribeEvent
    public static void onDarknessTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide) {
            Player player = event.player;
            
            // Если игрок в полной темноте (уровень света 0), добавляем боязнь
            if (player.level().getBrightness(net.minecraft.world.phys.BlockPos.containing(player.getX(), player.getEyeY(), player.getZ())) == 0) {
                if (player.tickCount % 40 == 0) { // Каждые 2 секунды
                    addFear(player, 1); // Медленное добавление боязни в темноте
                }
            }
        }
    }
}
