package mod.emt.vintageangels.proxy;

import mod.emt.vintageangels.compat.JERIntegration;
import mod.emt.vintageangels.compat.ThaumcraftIntegration;
import mod.emt.vintageangels.init.VAEntities;
import mod.emt.vintageangels.init.VALootTables;
import mod.emt.vintageangels.util.CompatHelper;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit() {
    }

    public void init() {
        VAEntities.registerEntitySpawns();
        VALootTables.registerLootTables();

        if (CompatHelper.isJERLoaded) {
            JERIntegration.init();
        }

        if (CompatHelper.isThaumcraftLoaded) {
            MinecraftForge.EVENT_BUS.register(new ThaumcraftIntegration());
        }
    }

    public void postInit() {
    }
}
