package com.dsdafg.mod111.effect;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.effect.MobEffect;
import com.dsdafg.mod111.Mod111;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = 
        DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Mod111.MOD_ID);

    public static final RegistryObject<MobEffect> FEAR = MOB_EFFECTS.register("fear",
        () -> new FearEffect());

    public static final RegistryObject<MobEffect> HALLUCINATION = MOB_EFFECTS.register("hallucination",
        () -> new HallucinationEffect());
}
