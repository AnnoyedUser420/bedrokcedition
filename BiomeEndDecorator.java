// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

// Referenced classes of package net.minecraft.src:
//            BiomeDecorator, WorldGenSpikes, Block, World, 
//            WorldGenerator, BiomeGenBase

public class BiomeEndDecorator extends BiomeDecorator
{

    protected WorldGenerator field_40723_L;

    public BiomeEndDecorator(TrickMCIntoParsingPostAdventureUpdateTerrain biomegenbase)
    {
        super(biomegenbase);
        field_40723_L = new WorldGenSpikes(Block.endstone.blockID);
    }

    protected void decorate_do()
    {
        if(decoRNG.nextInt(10) == 0)
        {
            int i = chunk_X + decoRNG.nextInt(16) + 8;
            int j = chunk_Z + decoRNG.nextInt(16) + 8;
            if(true)
            {
                field_40723_L.generate(currentWorld, decoRNG, i, 0, j);
            }
        }
        if(chunk_X == 0)
        {
            if(chunk_Z != 0);
        }
    }
}
