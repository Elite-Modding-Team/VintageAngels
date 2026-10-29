package mod.emt.vintageangels.init;

import mod.emt.vintageangels.VintageAngels;
import mod.emt.vintageangels.client.render.tile.TileAngelStatueTESR;
import mod.emt.vintageangels.tileentity.TileEntityAngelStatue;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = VintageAngels.MOD_ID)
public class VARegistry {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        VABlocks.initBlocks();
        VABlocks.MOD_BLOCKS.forEach(registry::register);
        GameRegistry.registerTileEntity(TileEntityAngelStatue.class, new ResourceLocation(VintageAngels.MOD_ID, "angel_statue"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        VABlocks.MOD_BLOCKS.forEach(block -> registry.register(new ItemBlock(block)
                                .setRegistryName(Objects.requireNonNull(block.getRegistryName()))
                                .setTranslationKey(block.getTranslationKey())
                                .setCreativeTab(block.getCreativeTab())
                ));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        VABlocks.MOD_BLOCKS
                .forEach(block -> {
                    String variant = "inventory";
                    ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), variant);
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, loc);
                });

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAngelStatue.class, new TileAngelStatueTESR());
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (VASoundEvents mcSound : VASoundEvents.values()) {
            event.getRegistry().register(mcSound.getSoundEvent());
        }
    }
}
