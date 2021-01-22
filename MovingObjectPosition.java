package net.minecraft.src;
import net.minecraft.client.*;
public class MovingObjectPosition {
    public EnumMovingObjectType typeOfHit;
    public int blockX;
    public int blockY;
    public int blockZ;
    public int sideHit;
    public Vec3D hitVec;
    public Entity entityHit;
    protected Minecraft mc;

    public MovingObjectPosition(int var1, int var2, int var3, int var4, Vec3D var5) {
        this.typeOfHit = EnumMovingObjectType.TILE;
        this.blockX = var1;
        this.blockY = var2;
        this.blockZ = var3;
        this.sideHit = var4;
        this.hitVec = Vec3D.createVector(var5.xCoord, var5.yCoord, var5.zCoord);
    }

    public MovingObjectPosition(Entity var1) {
        this.typeOfHit = EnumMovingObjectType.ENTITY;
        this.entityHit = var1;
        this.hitVec = Vec3D.createVector(var1.posX, var1.posY, var1.posZ);
    }
    
    // public void spawnLightningBoltAtLook(EntityPlayer player) {
    //    MovingObjectPosition position = player.rayTrace(128.0D, 1.0F);
    //    double x, y, z;
    //    if(object != null) {
    //        EntityLightningBolt e = new EntityLightningBolt(this.mc.theWorld, (double)hitVec.xCoord, (double)hitVec.yCoord, (double)hitVec.zCoord);
    //        player.worldObj.addWeatherEffect(e);
    //        return;
    //    }
    //}
}
