package mod.emt.vintageangels.compat;

import mod.emt.vintageangels.VintageAngels;
import mod.emt.vintageangels.init.VABlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

@SuppressWarnings("deprecation")
public class ThaumcraftIntegration {
    @SubscribeEvent
    public void registerAspects(AspectRegistryEvent event) {
        AspectEventProxy proxy = event.register;

        // Entities
        ThaumcraftApi.registerEntityTag(VintageAngels.MOD_ID + "." + "weeping_angel", new AspectList().add(Aspect.TRAP, 15).add(Aspect.MOTION, 15).add(Aspect.DARKNESS, 15));

        // Blocks
        proxy.registerObjectTag(new ItemStack(VABlocks.ANGEL_STATUE), new AspectList().add(Aspect.DARKNESS, 15).add(Aspect.COLD, 15).add(Aspect.VOID, 15));
    }
}
