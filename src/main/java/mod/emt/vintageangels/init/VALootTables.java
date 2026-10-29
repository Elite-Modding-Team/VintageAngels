package mod.emt.vintageangels.init;

import mod.emt.vintageangels.VintageAngels;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class VALootTables {
    public static final ResourceLocation WEEPING_ANGEL = new ResourceLocation(VintageAngels.MOD_ID, "entities/weeping_angel");

    public static void registerLootTables() {
        LootTableList.register(WEEPING_ANGEL);
    }
}
