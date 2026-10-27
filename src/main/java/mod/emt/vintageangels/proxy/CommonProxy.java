package mod.emt.vintageangels.proxy;

import mod.emt.vintageangels.init.VAEntities;

public class CommonProxy {
    public void preInit() {
    }

    public void init() {
        VAEntities.registerEntitySpawns();
    }

    public void postInit() {
    }
}
