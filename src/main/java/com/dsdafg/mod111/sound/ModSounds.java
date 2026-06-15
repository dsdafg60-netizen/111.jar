package com.dsdafg.mod111.sound;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import com.dsdafg.mod111.Mod111;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Mod111.MOD_ID);

    public static final RegistryObject<SoundEvent> BUNNYMAN_SCREAM = 
        SOUND_EVENTS.register("bunnyman_scream", 
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Mod111.MOD_ID, "bunnyman_scream")));

    public static final RegistryObject<SoundEvent> HALLUCINATION_SOUND = 
        SOUND_EVENTS.register("hallucination_sound", 
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Mod111.MOD_ID, "hallucination_sound")));

    public static final RegistryObject<SoundEvent> DARKNESS_AMBIENT = 
        SOUND_EVENTS.register("darkness_ambient", 
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Mod111.MOD_ID, "darkness_ambient")));
}
