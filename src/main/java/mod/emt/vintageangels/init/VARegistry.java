package mod.emt.vintageangels.init;

import mod.emt.vintageangels.VintageAngels;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = VintageAngels.MOD_ID)
public class VARegistry {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        //Blocks.initBlocks();
        //Blocks.MOD_BLOCKS.forEach(registry::register);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        //Items.initItems();
        //Items.MOD_ITEMS.forEach(registry::register);
        VABlocks.MOD_BLOCKS.forEach(block -> registry.register(new ItemBlock(block)
                                .setRegistryName(Objects.requireNonNull(block.getRegistryName()))
                                .setTranslationKey(block.getTranslationKey())
                                .setCreativeTab(block.getCreativeTab())
                ));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        /*VABlocks.MOD_BLOCKS.stream()
                .forEach(block -> {
                    String variant = "normal";
                    ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), variant);
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, loc);
                });

        EBTItems.MOD_ITEMS.forEach(item -> {
            ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "normal");
            ModelLoader.setCustomModelResourceLocation(item, 0, loc);
        });*/
    }
}
