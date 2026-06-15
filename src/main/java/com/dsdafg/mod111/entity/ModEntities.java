package com.dsdafg.mod111.entity;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import com.dsdafg.mod111.entity.custom.BunnymanEntity;
import com.dsdafg.mod111.Mod111;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Mod111.MOD_ID);

    public static final RegistryObject<EntityType<BunnymanEntity>> BUNNYMAN = 
        ENTITIES.register("bunnyman", () -> 
            EntityType.Builder.of(BunnymanEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("bunnyman"));
}
