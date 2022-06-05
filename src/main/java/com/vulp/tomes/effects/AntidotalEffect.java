package com.vulp.tomes.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class AntidotalEffect extends TomeEffect {

    public AntidotalEffect() {
        super(EffectType.BENEFICIAL, 44934, false);
    }

    @Override
    void potionTick(LivingEntity entityLivingBaseIn, int amplifier) {

    }

    @Override
    boolean readyToTick(int duration, int amplifier) {
        return false;
    }
}
