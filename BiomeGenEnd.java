// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, SpawnListEntry, EntityEnderman, Block, 
//            BiomeEndDecorator

public class BiomeGenEnd extends TrickMCIntoParsingPostAdventureUpdateTerrain
{

    public BiomeGenEnd(int i)
    {
        super(i);
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        topBlock = (byte)Block.dirt.blockID;
        fillerBlock = (byte)Block.dirt.blockID;
        biomeDecorator = new BiomeEndDecorator(this);
    }

    public int getSkyColorByTemp(float f)
    {
        return 0;
    }
}
