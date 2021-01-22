package net.minecraft.src;

import java.lang.reflect.Array;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

public class EntityPlayerSP extends EntityPlayer {
    public MovementInput movementInput;
    protected Minecraft mc;
    private MouseFilter field_21903_bJ = new MouseFilter();
    private MouseFilter field_21904_bK = new MouseFilter();
    private MouseFilter field_21902_bL = new MouseFilter();
    boolean isFlying = false;
    boolean isFuckingImmortal = false;

    public EntityPlayerSP(Minecraft var1, World var2, Session var3, int var4) {
        super(var2);
        this.mc = var1;
        this.dimension = var4;
        if (var3 != null && var3.username != null && var3.username.length() > 0) {
            this.skinUrl = "http://s3.amazonaws.com/MinecraftSkins/" + var3.username + ".png";
        }

        this.username = var3.username;
    }

    public void moveEntity(double var1, double var3, double var5) {
        super.moveEntity(var1, var3, var5);
    }

    public void updatePlayerActionState() {
        super.updatePlayerActionState();
        this.moveStrafing = this.movementInput.moveStrafe;
        this.moveForward = this.movementInput.moveForward;
        this.isJumping = this.movementInput.jump;
    }

    public void onLivingUpdate() {
        if (!this.mc.statFileWriter.hasAchievementUnlocked(AchievementList.openInventory)) {
            this.mc.guiAchievement.queueAchievementInformation(AchievementList.openInventory);
        }
        
        if(isFlying)
        {
        	mc.thePlayer.motionY = mc.thePlayer.movementInput.jump ? 0.5 : mc.thePlayer.movementInput.sneak ? -0.5 : 0;
        }

        this.prevTimeInPortal = this.timeInPortal;
        if (this.inPortal) {
            if (!this.worldObj.multiplayerWorld && this.ridingEntity != null) {
                this.mountEntity((Entity)null);
            }

            if (this.mc.currentScreen != null) {
                this.mc.displayGuiScreen((GuiScreen)null);
            }

            if (this.timeInPortal == 0.0F) {
                this.mc.sndManager.playSoundFX("portal.trigger", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            }

            this.timeInPortal += 0.0125F;
            if (this.timeInPortal >= 1.0F) {
                this.timeInPortal = 1.0F;
                if (!this.worldObj.multiplayerWorld) {
                    this.timeUntilPortal = 10;
                    this.mc.sndManager.playSoundFX("portal.travel", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
                    byte byte0 = 0;
                    if(dimension == -1)
                    {
                        byte0 = 0;
                    } else
                    {
                        byte0 = -1;
                    }
                    mc.usePortal(byte0);
                }
            }

            this.inPortal = false;
        } else {
            if (this.timeInPortal > 0.0F) {
                this.timeInPortal -= 0.05F;
            }

            if (this.timeInPortal < 0.0F) {
                this.timeInPortal = 0.0F;
            }
        }

        if (this.timeUntilPortal > 0) {
            --this.timeUntilPortal;
        }
        
        if(isFuckingImmortal)
        {
        	mc.thePlayer.setHealth((int) Double.POSITIVE_INFINITY);
        }

        this.movementInput.updatePlayerMoveState(this);
        if (this.movementInput.sneak && this.ySize < 0.2F) {
            this.ySize = 0.2F;
        }

        this.pushOutOfBlocks(this.posX - (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ + (double)this.width * 0.35D);
        this.pushOutOfBlocks(this.posX - (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ - (double)this.width * 0.35D);
        this.pushOutOfBlocks(this.posX + (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ - (double)this.width * 0.35D);
        this.pushOutOfBlocks(this.posX + (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ + (double)this.width * 0.35D);
        super.onLivingUpdate();
    }

    public void resetPlayerKeyState() {
        this.movementInput.resetKeyState();
    }

    public void handleKeyPress(int var1, boolean var2) {
        this.movementInput.checkKeyForMovementInput(var1, var2);
        if(var2)
        	if(var1 == Keyboard.KEY_F)
        	{
        		isFlying = !isFlying;
        	}
        if(var2 && isFlying)
        	if(var1 == Keyboard.KEY_N)
        	{
        		mc.thePlayer.noClip = !mc.thePlayer.noClip;
        	}
        		
    }

    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
        var1.setInteger("Score", this.score);
    }

    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
        this.score = var1.getInteger("Score");
    }

    public void closeScreen() {
        super.closeScreen();
        this.mc.displayGuiScreen((GuiScreen)null);
    }

    public void displayGUIEditSign(TileEntitySign var1) {
        this.mc.displayGuiScreen(new GuiEditSign(var1));
    }

    public void displayGUIChest(IInventory var1) {
        this.mc.displayGuiScreen(new GuiChest(this.inventory, var1));
    }

    public void displayWorkbenchGUI(int var1, int var2, int var3) {
        this.mc.displayGuiScreen(new GuiCrafting(this.inventory, this.worldObj, var1, var2, var3));
    }

    public void displayGUIFurnace(TileEntityFurnace var1) {
        this.mc.displayGuiScreen(new GuiFurnace(this.inventory, var1));
    }

    public void displayGUIDispenser(TileEntityDispenser var1) {
        this.mc.displayGuiScreen(new GuiDispenser(this.inventory, var1));
    }

    public void onItemPickup(Entity var1, int var2) {
        this.mc.effectRenderer.addEffect(new EntityPickupFX(this.mc.theWorld, var1, this, -0.5F));
    }

    public int getPlayerArmorValue() {
        return this.inventory.getTotalArmorValue();
    }

    public void sendChatMessage(String var1) {
    	if(var1.startsWith("give "))
    	{
    		String[] var2 = var1.split(" ");
    		if(var2.length == 4)
        	{
    			try {
    				mc.ingameGUI.addChatMessage("§7Giving " + mc.session.username + " some "+ Block.blocksList[Integer.valueOf(var2[1])].getBlockName());
    			}
    			catch(ArrayIndexOutOfBoundsException e) {
    				mc.ingameGUI.addChatMessage("§7Giving " + mc.session.username + " some "+ Item.itemsList[Integer.valueOf(var2[1])].getItemName());
    			}
        		mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(Integer.valueOf(var2[1]), Integer.valueOf(var2[2]), Integer.valueOf(var2[3])));
        	}
        	else if(var2.length == 3)
    		{
        		try {
    				mc.ingameGUI.addChatMessage("§7Giving " + mc.session.username + " some "+ Block.blocksList[Integer.valueOf(var2[1])].getBlockName());
    			}
    			catch(ArrayIndexOutOfBoundsException e) {
    				mc.ingameGUI.addChatMessage("§7Giving " + mc.session.username + " some "+ Item.itemsList[Integer.valueOf(var2[1])].getItemName());
    			}
        		mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(Integer.valueOf(var2[1]), Integer.valueOf(var2[2]), 0));
    		}
        	else if(var2.length == 2)
        	{
        		try {
    				mc.ingameGUI.addChatMessage("§7Giving " + mc.session.username + " some "+ Block.blocksList[Integer.valueOf(var2[1])].getBlockName());
    			}
    			catch(ArrayIndexOutOfBoundsException e) {
    				mc.ingameGUI.addChatMessage("§7Giving " + mc.session.username + " some "+ Item.itemsList[Integer.valueOf(var2[1])].getItemName());
    			}
        		mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(Integer.valueOf(var2[1]), 1, 0));
        	}
    	}
    	
    	else if(var1.equals("deathtothecapitalists"))
    	{
    		for(int i = 0; i < this.mc.theWorld.loadedEntityList.size(); ++i) {
                Entity entity = (Entity)this.mc.theWorld.loadedEntityList.get(i);
                if(entity == mc.thePlayer)return;
                entity.setEntityDead();
    		}
    	}
    	else if(var1.startsWith("tp "))
    	{
    		String[] var2 = var1.split(" ");
    		if(var2.length == 4)
        	{
    			mc.thePlayer.moveEntity(Double.valueOf(var2[1]), Double.valueOf(var2[2]), Double.valueOf(var2[3]));
        	}
    		else
    		{
    			mc.ingameGUI.addChatMessage("you used the command wrong");
    		}
    	}
    	else if(var1.equals("godmode"))
    	{
    		isFuckingImmortal = !isFuckingImmortal;
    	}
    	else if(var1.equals("provemodificationstationwrong") && isFuckingImmortal)System.out.print(this.health);
    	// Never mind
    }

    public boolean isSneaking() {
        return this.movementInput.sneak && !this.sleeping;
    }

    public void setHealth(int var1) {
        int var2 = this.health - var1;
        if (var2 <= 0) {
            this.health = var1;
            if (var2 < 0) {
                this.heartsLife = this.heartsHalvesLife / 2;
            }
        } else {
            this.field_9346_af = var2;
            this.prevHealth = this.health;
            this.heartsLife = this.heartsHalvesLife;
            this.damageEntity(var2);
            this.hurtTime = this.maxHurtTime = 10;
        }

    }

    public void respawnPlayer() {
        this.mc.respawn(false, 0);
    }

    public void func_6420_o() {
    }

    public void addChatMessage(String var1) {
        this.mc.ingameGUI.addChatMessageTranslate(var1);
    }

    public void addStat(StatBase var1, int var2) {
        if (var1 != null) {
            if (var1.isAchievement()) {
                Achievement var3 = (Achievement)var1;
                if (var3.parentAchievement == null || this.mc.statFileWriter.hasAchievementUnlocked(var3.parentAchievement)) {
                    if (!this.mc.statFileWriter.hasAchievementUnlocked(var3)) {
                        this.mc.guiAchievement.queueTakenAchievement(var3);
                    }

                    this.mc.statFileWriter.readStat(var1, var2);
                }
            } else {
                this.mc.statFileWriter.readStat(var1, var2);
            }

        }
    }

    private boolean isBlockTranslucent(int var1, int var2, int var3) {
        return this.worldObj.isBlockNormalCube(var1, var2, var3);
    }

    protected boolean pushOutOfBlocks(double var1, double var3, double var5) {
    	if(mc.thePlayer.noClip)return false;    	
        int var7 = MathHelper.floor_double(var1);
        int var8 = MathHelper.floor_double(var3);
        int var9 = MathHelper.floor_double(var5);
        double var10 = var1 - (double)var7;
        double var12 = var5 - (double)var9;
        if (this.isBlockTranslucent(var7, var8, var9) || this.isBlockTranslucent(var7, var8 + 1, var9)) {
            boolean var14 = !this.isBlockTranslucent(var7 - 1, var8, var9) && !this.isBlockTranslucent(var7 - 1, var8 + 1, var9);
            boolean var15 = !this.isBlockTranslucent(var7 + 1, var8, var9) && !this.isBlockTranslucent(var7 + 1, var8 + 1, var9);
            boolean var16 = !this.isBlockTranslucent(var7, var8, var9 - 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 - 1);
            boolean var17 = !this.isBlockTranslucent(var7, var8, var9 + 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 + 1);
            byte var18 = -1;
            double var19 = 9999.0D;
            if (var14 && var10 < var19) {
                var19 = var10;
                var18 = 0;
            }

            if (var15 && 1.0D - var10 < var19) {
                var19 = 1.0D - var10;
                var18 = 1;
            }

            if (var16 && var12 < var19) {
                var19 = var12;
                var18 = 4;
            }

            if (var17 && 1.0D - var12 < var19) {
                var19 = 1.0D - var12;
                var18 = 5;
            }

            float var21 = 0.1F;
            if (var18 == 0) {
                this.motionX = (double)(-var21);
            }

            if (var18 == 1) {
                this.motionX = (double)var21;
            }

            if (var18 == 4) {
                this.motionZ = (double)(-var21);
            }

            if (var18 == 5) {
                this.motionZ = (double)var21;
            }
        }

        return false;
    }
    
    public void func_40182_b(int i)
    {
    	mc.usePortal(i);
    }
}
