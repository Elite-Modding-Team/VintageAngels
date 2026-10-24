package mod.emt.vintageangels.init;

import mod.emt.vintageangels.VintageAngels;
import mod.emt.vintageangels.client.render.RenderWeepingAngel;
import mod.emt.vintageangels.entity.EntityWeepingAngel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = VintageAngels.MOD_ID)
public class VAEntities {
    private static int entityID = 0;

    public static void registerEntity(String name, Class<? extends Entity> clazz, int eggColor1, int eggColor2) {
        EntityRegistry.registerModEntity(new ResourceLocation(VintageAngels.MOD_ID, name), clazz, VintageAngels.MOD_ID + "." + name, entityID++, VintageAngels.instance, 80, 1, true, eggColor1, eggColor2);
    }

    @SubscribeEvent
    public static void registerEntities(@NotNull final RegistryEvent.Register<EntityEntry> event) {
        registerEntity("weeping_angel", EntityWeepingAngel.class, 0, 0xffffff);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(@NotNull final ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityWeepingAngel.class, new RenderWeepingAngel.Factory());
    }

    public static void registerEntitySpawns() {
        EntitySpawnPlacementRegistry.setPlacementType(EntityWeepingAngel.class, EntityLiving.SpawnPlacementType.ON_GROUND);
    }

    public static Biome[] getMonsterEntityBiomes(Class<? extends Entity> spawn) {
        List<Biome> biomes = new ArrayList<>();

        for (Biome biome : ForgeRegistries.BIOMES) {
            List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.MONSTER);

            for (Biome.SpawnListEntry list : spawnList) {
                if (list.entityClass == spawn) {
                    biomes.add(biome);
                    break;
                }
            }
        }

        return biomes.toArray(new Biome[0]);
    }
}
