package mod.emt.vintageangels.config;

import mod.emt.vintageangels.VintageAngels;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("cfg." + VintageAngels.MOD_ID)
@Config(modid = VintageAngels.MOD_ID, name = "VintageAngels")
public class VAConfig {
    @Config.LangKey("config.vintageangels.weeping_angel")
    @Config.Comment("Weeping Angel settings")
    public static final WeepingAngel WEEPING_ANGEL = new WeepingAngel();

    public static class WeepingAngel {
        @Config.Name("Armor")
        @Config.Comment("The amount of armor the Weeping Angel has")
        public double armor = 8.0;

        @Config.Name("Attack Damage")
        @Config.Comment("The attack damage of the Weeping Angel")
        public double attackDamage = 11.0;

        @Config.Name("Follow Range")
        @Config.Comment("The follow range of the Weeping Angel")
        public double followRange = 80.0;

        @Config.Name("Max Health")
        @Config.Comment("The maximum health of the Weeping Angel")
        public double maxHealth = 40.0;

        @Config.Name("Movement Speed")
        @Config.Comment("The movement speed of the Weeping Angel")
        public double movementSpeed = 0.6;

        @Config.Name("Knockback Resistance")
        @Config.Comment("The knockback resistance (in percentage) of the Weeping Angel")
        @Config.RangeDouble(min = 0.0, max = 1.0)
        public double knockbackResistance = 1.0;

        @Config.Name("Spawn Max")
        @Config.Comment("The maximum spawn group size of the Weeping Angel")
        @Config.RangeInt(min = 0)
        public int spawnMax = 1;

        @Config.Name("Spawn Min")
        @Config.Comment("The minimum spawn group size of the Weeping Angel")
        @Config.RangeInt(min = 0)
        public int spawnMin = 1;

        @Config.Name("Spawn Weight")
        @Config.Comment("The spawn weight of the Weeping Angel")
        @Config.RangeInt(min = 0)
        public int spawnWeight = 5;

        @Config.Name("Step Height")
        @Config.Comment({"The step height of the Weeping Angel", "This affects the height of blocks they can step up onto without jumping"})
        public double stepHeight = 1.0;

        @Config.Name("Swim Speed")
        @Config.Comment("The swim speed of the Weeping Angel")
        public double swimSpeed = 2.0;

        @Config.Name("Teleport Chance: Target")
        @Config.Comment("The chance for the Weeping Angel to teleport a target when it damages them")
        @Config.RangeDouble(min = 0.0, max = 1.0)
        public double teleportChanceTarget = 0.25;

        @Config.Name("Teleport Range: Target")
        @Config.Comment("The range distance when the Weeping Angel teleports a target")
        public double teleportRangeTarget = 64.0;
    }

    @Mod.EventBusSubscriber(modid = VintageAngels.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(VintageAngels.MOD_ID)) {
                ConfigManager.sync(VintageAngels.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
