package mod.emt.vintageangels.util;

import mod.emt.vintageangels.config.VAConfig;
import net.minecraftforge.fml.common.Loader;

public class CompatHelper {
    public static final boolean isJERLoaded = Loader.isModLoaded("jeresources") && VAConfig.MOD_INTEGRATION.JERIntegration;
    public static final boolean isThaumcraftLoaded = Loader.isModLoaded("thaumcraft") && VAConfig.MOD_INTEGRATION.thaumcraftIntegration;
}
