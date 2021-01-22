package net.minecraft.src;

public class ItemAxe extends ItemTool {
    private static Block[] blocksEffectiveAgainst;

    protected ItemAxe(int var1, EnumToolMaterial var2) {
        super(var1, 3, var2, blocksEffectiveAgainst);
    }

    static {
        blocksEffectiveAgainst = new Block[]{Block.planks, Block.bookShelf, Block.wood, Block.chest};
    }
    
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
        if (var7 == 0) {
            --var5;
        }

        if (var7 == 1) {
            ++var5;
        }

        if (var7 == 2) {
            --var6;
        }

        if (var7 == 3) {
            ++var6;
        }

        if (var7 == 4) {
            --var4;
        }

        if (var7 == 5) {
            ++var4;
        }

        int var8 = var3.getBlockId(var4, var5, var6);
        if (var8 == 0) {
            var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            EntityLightningBolt e = new EntityLightningBolt(var3, var4, var5, var6);
            var3.addWeatherEffect(e);
            System.out.println("BRING ME THANOS");
        }

        var1.damageItem(1, var2);
        return true;
    }
}
