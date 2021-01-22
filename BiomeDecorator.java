// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            WorldGenClay, WorldGenSand, Block, WorldGenMinable, 
//            WorldGenFlowers, BlockFlower, WorldGenBigMushroom, WorldGenReed, 
//            WorldGenCactus, MapGenWaterlily, World, WorldGenerator, 
//            BiomeGenBase, WorldGenTallGrass, BlockTallGrass, WorldGenDeadBush, 
//            BlockDeadBush, WorldGenPumpkin, WorldGenLiquids

public class BiomeDecorator
{

    protected World currentWorld;
    protected Random decoRNG;
    protected int chunk_X;
    protected int chunk_Z;
    protected TrickMCIntoParsingPostAdventureUpdateTerrain biome;
    protected WorldGenerator clayGen;
    protected WorldGenerator sandGen;
    protected WorldGenerator gravelAsSandGen;
    protected WorldGenerator dirtGen;
    protected WorldGenerator gravelGen;
    protected WorldGenerator coalGen;
    protected WorldGenerator ironGen;
    protected WorldGenerator goldGen;
    protected WorldGenerator redstoneGen;
    protected WorldGenerator diamondGen;
    protected WorldGenerator lapisGen;
    protected WorldGenerator plantYellowGen1;
    protected WorldGenerator plantYellowGen2;
    protected WorldGenerator mushroomBrownGen;
    protected WorldGenerator mushroomRedGen;
    protected WorldGenerator field_40720_u;
    protected WorldGenerator reedGen;
    protected WorldGenerator cactusGen;
    protected WorldGenerator field_40722_x;
    protected int field_40721_y;
    protected int treesPerChunk;
    protected int flowersPerChunk;
    protected int grassPerChunk;
    protected int deadBushPerChunk;
    protected int mushroomsPerChunk;
    protected int reedsPerChunk;
    protected int cactiPerChunk;
    protected int sandPerChunk;
    protected int sandPerChunk2;
    protected int clayPerChunk;
    protected int field_40718_J;
    public boolean field_40719_K;

    public BiomeDecorator(TrickMCIntoParsingPostAdventureUpdateTerrain trickMCIntoParsingPostAdventureUpdateTerrain)
    {
        dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
        gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
        coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
        ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
        goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
        redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
        diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
        lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
        plantYellowGen1 = new WorldGenFlowers(Block.plantYellow.blockID);
        plantYellowGen2 = new WorldGenFlowers(Block.plantRed.blockID);
        mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
        mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
        reedGen = new WorldGenReed();
        cactusGen = new WorldGenCactus();
        field_40721_y = 0;
        treesPerChunk = 0;
        flowersPerChunk = 2;
        grassPerChunk = 1;
        deadBushPerChunk = 0;
        mushroomsPerChunk = 0;
        reedsPerChunk = 0;
        cactiPerChunk = 0;
        sandPerChunk = 1;
        sandPerChunk2 = 3;
        clayPerChunk = 1;
        field_40718_J = 0;
        field_40719_K = true;
        biome = trickMCIntoParsingPostAdventureUpdateTerrain;
    }

    public void decorate(World world, Random random, int i, int j)
    {
        if(currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        } else
        {
            currentWorld = world;
            decoRNG = random;
            chunk_X = i;
            chunk_Z = j;
            decorate_do();
            currentWorld = null;
            decoRNG = null;
            return;
        }
    }

    protected void decorate_do()
    {        
    }

    protected void genStandardOre1(int i, WorldGenerator worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = chunk_X + decoRNG.nextInt(16);
            int j1 = decoRNG.nextInt(k - j) + j;
            int k1 = chunk_Z + decoRNG.nextInt(16);
            worldgenerator.generate(currentWorld, decoRNG, i1, j1, k1);
        }

    }

    protected void genStandardOre2(int i, WorldGenerator worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = chunk_X + decoRNG.nextInt(16);
            int j1 = decoRNG.nextInt(k) + decoRNG.nextInt(k) + (j - k);
            int k1 = chunk_Z + decoRNG.nextInt(16);
            worldgenerator.generate(currentWorld, decoRNG, i1, j1, k1);
        }

    }
}
