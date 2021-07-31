package io.github.coolmineman.bruhcow.mixin;

import java.util.concurrent.ThreadLocalRandom;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(CowEntity.class)
public abstract class Bruh extends AnimalEntity {
    protected Bruh(EntityType<? extends AnimalEntity> arg, World arg2) {
        super(arg, arg2);
    }

    @Inject(method = "interactMob", at = @At("HEAD"))
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cb) {
        ItemStack s = player.getStackInHand(hand);
        if (s.getItem() instanceof ShearsItem && !this.isBaby()) {
            this.damage(DamageSource.player(player), 5);
            this.dropStack(new ItemStack(Items.LEATHER, ThreadLocalRandom.current().nextInt(4)));
        }
    }

}