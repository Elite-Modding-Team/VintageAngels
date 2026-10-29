package mod.emt.vintageangels.compat;

import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.compatibility.JERAPI;
import mod.emt.vintageangels.entity.EntityWeepingAngel;
import mod.emt.vintageangels.init.VALootTables;
import net.minecraft.world.World;

public class JERIntegration {
    public static void init() {
        IJERAPI jerApi = JERAPI.getInstance();
        IMobRegistry jerMobRegistry = jerApi.getMobRegistry();
        World jerWorld = jerApi.getWorld();

        jerMobRegistry.register(new EntityWeepingAngel(jerWorld), LightLevel.hostile, VALootTables.WEEPING_ANGEL);
    }
}
