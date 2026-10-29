package mod.emt.vintageangels.util;

import mod.emt.vintageangels.config.VAConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConfigHelper {
    public static boolean isPickaxeToolAllowed(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        Item item = stack.getItem();

        for (String className : VAConfig.WEEPING_ANGEL.pickaxeToolClasses) {
            try {
                Class<?> clazz = Class.forName(className.trim());
                if (clazz.isInstance(item)) {
                    return true;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }

        return false;
    }
}
