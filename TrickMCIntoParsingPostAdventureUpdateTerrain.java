// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, BlockGrass, WorldGenTrees, WorldGenBigTree, 
//            WorldGenForest, WorldGenSwamp, SpawnListEntry, EntitySheep, 
//            EntityPig, EntityChicken, EntityCow, EntitySpider, 
//            EntityZombie, EntitySkeleton, EntityCreeper, EntitySlime, 
//            EntityEnderman, EntitySquid, BiomeDecorator, EnumCreatureType, 
//            IBlockAccess, WorldChunkManager, ColorizerGrass, ColorizerFoliage, 
//            BiomeGenOcean, BiomeGenPlains, BiomeGenDesert, BiomeGenHills, 
//            BiomeGenForest, BiomeGenTaiga, BiomeGenSwamp, BiomeGenRiver, 
//            BiomeGenHell, BiomeGenEnd, BiomeGenSnow, BiomeGenMushroomIsland, 
//            WorldGenerator, World

public abstract class TrickMCIntoParsingPostAdventureUpdateTerrain
{

    public static final BiomeGenBase biomeList[] = new BiomeGenBase[256];
    public static final TrickMCIntoParsingPostAdventureUpdateTerrain sky = (new BiomeGenEnd(9)).setColor(0x8080ff).setBiomeName("Sky").setDisableRain();
    public String biomeName;
    public int color;
    public byte topBlock;
    public byte fillerBlock;
    public int field_6502_q;
    public float minHeight;
    public float maxHeight;
    public float temperature;
    public float rainfall;
    public int field_40256_A;
    public Object biomeDecorator;
    protected List spawnableMonsterList;
    protected List spawnableCreatureList;
    protected List spawnableWaterCreatureList;
    private boolean enableSnow;
    private boolean enableRain;
    public final int biomeID;

    protected TrickMCIntoParsingPostAdventureUpdateTerrain(int i)
    {
        topBlock = (byte)Block.grass.blockID;
        fillerBlock = (byte)Block.dirt.blockID;
        field_6502_q = 0x4ee031;
        minHeight = 0.1F;
        maxHeight = 0.3F;
        temperature = 0.5F;
        rainfall = 0.5F;
        field_40256_A = 0xffffff;
        spawnableMonsterList = new ArrayList();
        spawnableCreatureList = new ArrayList();
        spawnableWaterCreatureList = new ArrayList();
        enableRain = true;
        biomeID = i;
        biomeDecorator = createBiomeDecorator();
    }

    protected BiomeDecorator createBiomeDecorator()
    {
        return new BiomeDecorator(this);
    }

    private TrickMCIntoParsingPostAdventureUpdateTerrain setTemperatureRainfall(float f, float f1)
    {
        if(f > 0.1F && f < 0.2F)
        {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        } else
        {
            temperature = f;
            rainfall = f1;
            return this;
        }
    }

    private TrickMCIntoParsingPostAdventureUpdateTerrain setMinMaxHeight(float f, float f1)
    {
        minHeight = f;
        maxHeight = f1;
        return this;
    }

    private TrickMCIntoParsingPostAdventureUpdateTerrain setDisableRain()
    {
        enableRain = false;
        return this;
    }
    
    protected TrickMCIntoParsingPostAdventureUpdateTerrain setBiomeName(String s)
    {
        biomeName = s;
        return this;
    }

    protected TrickMCIntoParsingPostAdventureUpdateTerrain func_4124_a(int i)
    {
        field_6502_q = i;
        return this;
    }

    protected TrickMCIntoParsingPostAdventureUpdateTerrain setColor(int i)
    {
        color = i;
        return this;
    }

    public int getSkyColorByTemp(float f)
    {
        f /= 3F;
        if(f < -1F)
        {
            f = -1F;
        }
        if(f > 1.0F)
        {
            f = 1.0F;
        }
        return java.awt.Color.getHSBColor(0.6222222F - f * 0.05F, 0.5F + f * 0.1F, 1.0F).getRGB();
    }

    public List getSpawnableList(EnumCreatureType enumcreaturetype)
    {
        if(enumcreaturetype == EnumCreatureType.monster)
        {
            return spawnableMonsterList;
        }
        if(enumcreaturetype == EnumCreatureType.creature)
        {
            return spawnableCreatureList;
        }
        if(enumcreaturetype == EnumCreatureType.waterCreature)
        {
            return spawnableWaterCreatureList;
        } else
        {
            return null;
        }
    }

    public boolean getEnableSnow()
    {
        return enableSnow;
    }

    public boolean canSpawnLightningBolt()
    {
        if(enableSnow)
        {
            return false;
        } else
        {
            return enableRain;
        }
    }

    public float getSpawningChance()
    {
        return 0.1F;
    }

    public final int func_35476_e()
    {
        return (int)(rainfall * 65536F);
    }

    public final int func_35474_f()
    {
        return (int)(temperature * 65536F);
    }

    public void func_35477_a(World world, Random random, int i, int j)
    {
        ((BiomeDecorator) biomeDecorator).decorate(world, random, i, j);
    }

    public int func_40254_a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        double d = iblockaccess.getWorldChunkManager().func_35554_b(i, j, k);
        double d1 = iblockaccess.getWorldChunkManager().func_35558_c(i, k);
        return ColorizerGrass.getGrassColor(d, d1);
    }

    public int func_40255_b(IBlockAccess iblockaccess, int i, int j, int k)
    {
        double d = iblockaccess.getWorldChunkManager().func_35554_b(i, j, k);
        double d1 = iblockaccess.getWorldChunkManager().func_35558_c(i, k);
        return ColorizerFoliage.getFoliageColor(d, d1);
    }
    
}
