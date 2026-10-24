package mod.emt.vintageangels;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = VintageAngels.MOD_ID,
        name = VintageAngels.MOD_NAME,
        version = VintageAngels.MOD_VERSION
)
public class MCommodities {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = "Vintage Angels";
    public static final String MOD_VERSION = Tags.VERSION;

    public static final String CLIENT_PROXY = "mod.emt.vintageangels.proxy.ClientProxy";
    public static final String COMMON_PROXY = "mod.emt.vintageangels.proxy.CommonProxy";

    @Mod.Instance(MOD_ID)
    public static VintageAngels instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
