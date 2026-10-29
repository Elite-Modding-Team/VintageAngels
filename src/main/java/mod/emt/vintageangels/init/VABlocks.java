package mod.emt.vintageangels.init;

import mod.emt.vintageangels.block.BlockAngelStatue;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class VABlocks {
    public static BlockAngelStatue ANGEL_STATUE = null;

    public static final List<Block> MOD_BLOCKS = new ArrayList<>();

    public static void initBlocks() {
        MOD_BLOCKS.add(ANGEL_STATUE = new BlockAngelStatue());
    }
}
