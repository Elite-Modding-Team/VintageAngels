package mod.emt.vintageangels.init;

import mod.emt.vintageangels.VintageAngels;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum  VASoundEvents {
    ENTITY_WEEPING_ANGEL_CRUMBLE("entity.weeping_angel.crumble"),
    ENTITY_WEEPING_ANGEL_LIGHT("entity.weeping_angel.light"),
    ENTITY_WEEPING_ANGEL_MOVE("entity.weeping_angel.move"),
    ENTITY_WEEPING_ANGEL_TELEPORT_ACTIVATE("entity.weeping_angel.teleport_activate");

    private final SoundEvent soundEvent;

    VASoundEvents(String path) {
        ResourceLocation resourceLocation = new ResourceLocation(VintageAngels.MOD_ID, path);
        this.soundEvent = new SoundEvent(resourceLocation);
        this.soundEvent.setRegistryName(resourceLocation);
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }
}
