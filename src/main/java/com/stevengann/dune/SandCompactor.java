package com.stevengann.dune;

import java.util.function.Consumer;

import javax.lang.model.util.ElementScanner14;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SandCompactor extends HoeItem {
 
    /*public SandCompactor(Settings settings) {
        super(settings);
    }*/
 
    protected SandCompactor(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) 
    {
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) 
    {
        
        BlockPos pos = context.getBlockPos();
        BlockPos above = pos.add(0,1,0);
        BlockPos below = pos.add(0,-1,0);

        if(context.getWorld().getBlockState(pos).getBlock() == Blocks.SAND)
        {         
            if(context.getWorld().getBlockState(above).getBlock() == Blocks.SAND)
            {
                context.getWorld().removeBlock(pos, false);
                context.getWorld().setBlockState(above, Blocks.SANDSTONE.getDefaultState());
                context.getPlayer().playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);

                context.getStack().damage(2, 
                    (LivingEntity)context.getPlayer(), 
                    (Consumer<LivingEntity>)((p) -> { (p).sendToolBreakStatus(context.getHand()); })
                    );

            }
            else if(context.getWorld().getBlockState(below).getBlock() == Blocks.SAND)
            {
                context.getWorld().removeBlock(pos, false);
                context.getWorld().setBlockState(below, Blocks.SANDSTONE.getDefaultState());
                context.getPlayer().playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);

                context.getStack().damage(2, 
                    (LivingEntity)context.getPlayer(), 
                    (Consumer<LivingEntity>)((p) -> { (p).sendToolBreakStatus(context.getHand()); })
                    );
            }
        }
        else if(context.getWorld().getBlockState(pos).getBlock() == Blocks.SANDSTONE)
        {
            context.getPlayer().playSound(SoundEvents.BLOCK_SAND_STEP, 1.0F, 1.0F);
            context.getWorld().setBlockState(pos, Blocks.SAND.getDefaultState());

            context.getStack().damage(1, 
                    (LivingEntity)context.getPlayer(), 
                    (Consumer<LivingEntity>)((p) -> { (p).sendToolBreakStatus(context.getHand()); })
                    );
        }
        

		return ActionResult.PASS;
	}

    /*@Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
 
    // default white text
    tooltip.add( new TranslatableText("item.tutorial.fabric_item.tooltip") );
 
    // formatted red text
    tooltip.add( new TranslatableText("item.tutorial.fabric_item.tooltip").formatted(Formatting.RED) );
    }*/
}