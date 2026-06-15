package com.dsdafg.mod111;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafxmod.FXModLoadingContext;
import com.dsdafg.mod111.entity.ModEntities;
import com.dsdafg.mod111.item.ModItems;
import com.dsdafg.mod111.block.ModBlocks;
import com.dsdafg.mod111.effect.ModEffects;

@Mod("mod111")
public class Mod111 {
    public static final String MOD_ID = "mod111";

    public Mod111() {
        IEventBus modEventBus = FXModLoadingContext.getInstance().getModEventBus();
        
        modEventBus.addListener(this::commonSetup);
        
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientSetup {
        @net.minecraftforge.api.distmarker.OnlyIn(Dist.CLIENT)
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
