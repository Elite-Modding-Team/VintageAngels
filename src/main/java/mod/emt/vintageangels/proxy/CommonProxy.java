package mod.emt.vintageangels.proxy;

import mod.emt.vintageangels.init.VAEntities;
import mod.emt.vintageangels.init.VALootTables;

public class CommonProxy {
    public void preInit() {
    }

    public void init() {
        VAEntities.registerEntitySpawns();
        VALootTables.registerLootTables();
    }

    public void postInit() {
    }
}
