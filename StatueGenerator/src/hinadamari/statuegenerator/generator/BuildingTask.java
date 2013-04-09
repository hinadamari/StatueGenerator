package hinadamari.statuegenerator.generator;

//import hinadamari.skingenerator.exception.StateException;
import hinadamari.statuegenerator.StatueGenerator;
import hinadamari.statuegenerator.util.Actions;

import org.bukkit.Location;
import org.bukkit.Material;


/**
 * BuildingTask (BuildingTask.java)
 * @author syam(syamn)
 * @author hinadamari
 */
public class BuildingTask implements Runnable{
    private final StatueGenerator plugin;
    private final String senderName;
    private final Location loc;
    //private final Direction dir;

    private BlockData[][] blocks;
    //private int width;
    private int height;

    private long generator_Taked = 0;

    public BuildingTask(final StatueGenerator plugin, final String senderName, final Location playerLocation, final Direction dir){
        this.plugin = plugin;

        this.senderName = senderName;
        this.loc = playerLocation;
        //this.dir = dir;
    }

    public void putBlockData(final BlockData[][] blocks, final int width, final int height){
        this.blocks = blocks;
        //this.width = width;
        this.height = height;
    }

    @Override
    public void run(){
        plugin.debug("== Start BuildingTask ==");

        final int face = getPlayerDirection();
        if (face == -1){
            sendMessage("&cInvalid face Direction!");
            Timer.removeData(senderName);
            return;
        }
        plugin.debug("Using FaceDirection: " + face);

        int ax = 0, ay = 0, az = 0;
        int bx = 0, bz = 0;
        BlockData putData;
        int tmpface;

        // 0s 1w 2n 3e
        // 顔 正面 0
        tmpface = face;
        for (int x = 8; x <= 15; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 8);
                else if (tmpface == 1) az = -(x - 8);
                else if (tmpface == 2) ax = x - 8;
                else if (tmpface == 3) az = x - 8;

        		if (tmpface == 0) az = 0 - 2;
                else if (tmpface == 1) ax = 0 + 2;
                else if (tmpface == 2) az = 0 + 2;
                else if (tmpface == 3) ax = 0 - 2;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 顔 後ろ 2
        tmpface = (face + 2) % 4;
        for (int x = 24; x <= 31; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 24 - 7);
                else if (tmpface == 1) az = -(x - 24 - 7);
                else if (tmpface == 2) ax = x - 24 - 7;
                else if (tmpface == 3) az = x - 24 - 7;

        		if (tmpface == 0) az = 0 - 5;
                else if (tmpface == 1) ax = 0 + 5;
                else if (tmpface == 2) az = 0 + 5;
                else if (tmpface == 3) ax = 0 - 5;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 顔 右側 1
        tmpface = (face + 1) % 4;
        for (int x = 0; x <= 7; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 0 - 5);
                else if (tmpface == 1) az = -(x - 0 - 5);
                else if (tmpface == 2) ax = x - 0 - 5;
                else if (tmpface == 3) az = x - 0 - 5;

        		if (tmpface == 0) az = 0 - 0;
                else if (tmpface == 1) ax = 0 + 0;
                else if (tmpface == 2) az = 0 + 0;
                else if (tmpface == 3) ax = 0 - 0;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 顔 左側 3
        tmpface = (face + 3) % 4;
        for (int x = 16; x <= 23; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 16 - 2);
                else if (tmpface == 1) az = -(x - 16 - 2);
                else if (tmpface == 2) ax = x - 16 - 2;
                else if (tmpface == 3) az = x - 16 - 2;

        		if (tmpface == 0) az = 0 - 7;
                else if (tmpface == 1) ax = 0 + 7;
                else if (tmpface == 2) az = 0 + 7;
                else if (tmpface == 3) ax = 0 - 7;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 顔 上 up
        tmpface = face;
        for (int x = 8; x <= 15; x++){
        	for (int y = 0; y <= 7; y++){
        		if (tmpface == 0) ax = -(x - 8);
                else if (tmpface == 1) az = -(x - 8);
                else if (tmpface == 2) ax = x - 8;
                else if (tmpface == 3) az = x - 8;

        		if (tmpface == 0) az = -(y - 5);
                else if (tmpface == 1) ax = y - 5;
                else if (tmpface == 2) az = y - 5;
                else if (tmpface == 3) ax = -(y - 5);
                ay = 31;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 顔 下 up
        tmpface = face;
        for (int x = 16; x <= 23; x++){
        	for (int y = 0; y <= 7; y++){
        		if (tmpface == 0) ax = -(x - 16);
                else if (tmpface == 1) az = -(x - 16);
                else if (tmpface == 2) ax = x - 16;
                else if (tmpface == 3) az = x - 16;

        		if (tmpface == 0) az = -(y - 5);
                else if (tmpface == 1) ax = y - 5;
                else if (tmpface == 2) az = y - 5;
                else if (tmpface == 3) ax = -(y - 5);
                ay = 24;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 被 正面 0
        tmpface = face;
        for (int x = 40; x <= 47; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 40);
                else if (tmpface == 1) az = -(x - 40);
                else if (tmpface == 2) ax = x - 40;
                else if (tmpface == 3) az = x - 40;

        		if (tmpface == 0) az = 0 - 3;
                else if (tmpface == 1) ax = 0 + 3;
                else if (tmpface == 2) az = 0 + 3;
                else if (tmpface == 3) ax = 0 - 3;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 被 後ろ 2
        tmpface = (face + 2) % 4;
        for (int x = 56; x <= 63; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 56 - 7);
                else if (tmpface == 1) az = -(x - 56 - 7);
                else if (tmpface == 2) ax = x - 56 - 7;
                else if (tmpface == 3) az = x - 56 - 7;

        		if (tmpface == 0) az = 0 - 6;
                else if (tmpface == 1) ax = 0 + 6;
                else if (tmpface == 2) az = 0 + 6;
                else if (tmpface == 3) ax = 0 - 6;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 被 右側 1
        tmpface = (face + 1) % 4;
        for (int x = 32; x <= 39; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 32 - 5);
                else if (tmpface == 1) az = -(x - 32 - 5);
                else if (tmpface == 2) ax = x - 32 - 5;
                else if (tmpface == 3) az = x - 32 - 5;

        		if (tmpface == 0) az = 0 - 1;
                else if (tmpface == 1) ax = 0 + 1;
                else if (tmpface == 2) az = 0 + 1;
                else if (tmpface == 3) ax = 0 - 1;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 被 左側 3
        tmpface = (face + 3) % 4;
        for (int x = 48; x <= 55; x++){
        	for (int y = 8; y <= 15; y++){
        		if (tmpface == 0) ax = -(x - 48 - 2);
                else if (tmpface == 1) az = -(x - 48 - 2);
                else if (tmpface == 2) ax = x - 48 - 2;
                else if (tmpface == 3) az = x - 48 - 2;

        		if (tmpface == 0) az = 0 - 8;
                else if (tmpface == 1) ax = 0 + 8;
                else if (tmpface == 2) az = 0 + 8;
                else if (tmpface == 3) ax = 0 - 8;

                ay = -(y - 8) + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 被 上 up
        tmpface = face;
        for (int x = 40; x <= 47; x++){
        	for (int y = 0; y <= 7; y++){
        		if (tmpface == 0) ax = -(x - 40);
                else if (tmpface == 1) az = -(x - 40);
                else if (tmpface == 2) ax = x - 40;
                else if (tmpface == 3) az = x - 40;

        		if (tmpface == 0) az = -(y - 5);
                else if (tmpface == 1) ax = y - 5;
                else if (tmpface == 2) az = y - 5;
                else if (tmpface == 3) ax = -(y - 5);
                ay = 32;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 被 下 up
        tmpface = face;
        for (int x = 48; x <= 55; x++){
        	for (int y = 0; y <= 7; y++){
        		if (tmpface == 0) ax = -(x - 48);
                else if (tmpface == 1) az = -(x - 48);
                else if (tmpface == 2) ax = x - 48;
                else if (tmpface == 3) az = x - 48;

        		if (tmpface == 0) az = -(y - 5);
                else if (tmpface == 1) ax = y - 5;
                else if (tmpface == 2) az = y - 5;
                else if (tmpface == 3) ax = -(y - 5);
                ay = 23;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 脚 正面 0
        tmpface = face;
        for (int x = 4; x <= 7; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 4 - 0);
                else if (tmpface == 1) az = -(x - 4 - 0);
                else if (tmpface == 2) ax = x - 4 - 0;
                else if (tmpface == 3) az = x - 4 - 0;

        		if (tmpface == 0) az = 0 - 0;
                else if (tmpface == 1) ax = 0 + 0;
                else if (tmpface == 2) az = 0 + 0;
                else if (tmpface == 3) ax = 0 - 0;

                ay = -(y - 20) - 20 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bx -= 4;
                else if (tmpface == 1) bz -= 4;
                else if (tmpface == 2) bx += 4;
                else if (tmpface == 3) bz += 4;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 脚 後ろ 2
        tmpface = (face + 2) % 4;
        for (int x = 12; x <= 15; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 12 - 3);
                else if (tmpface == 1) az = -(x - 12 - 3);
                else if (tmpface == 2) ax = x - 12 - 3;
                else if (tmpface == 3) az = x - 12 - 3;

        		if (tmpface == 0) az = 0 - 3;
                else if (tmpface == 1) ax = 0 + 3;
                else if (tmpface == 2) az = 0 + 3;
                else if (tmpface == 3) ax = 0 - 3;

                ay = -(y - 20) - 20 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bx += 4;
                else if (tmpface == 1) bz += 4;
                else if (tmpface == 2) bx -= 4;
                else if (tmpface == 3) bz -= 4;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 脚 右側 1
        tmpface = (face + 1) % 4;
        for (int x = 0; x <= 3; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 0 - 3);
                else if (tmpface == 1) az = -(x - 0 - 3);
                else if (tmpface == 2) ax = x - 0 - 3;
                else if (tmpface == 3) az = x - 0 - 3;

        		if (tmpface == 0) az = 0 - 0;
                else if (tmpface == 1) ax = 0 + 0;
                else if (tmpface == 2) az = 0 + 0;
                else if (tmpface == 3) ax = 0 - 0;

                ay = -(y - 20) - 20 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bz += 7;
                else if (tmpface == 1) bx -= 7;
                else if (tmpface == 2) bz -= 7;
                else if (tmpface == 3) bx += 7;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 脚 左側 3
        tmpface = (face + 3) % 4;
        for (int x = 8; x <= 11; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 8 - 0);
                else if (tmpface == 1) az = -(x - 8 - 0);
                else if (tmpface == 2) ax = x - 8 - 0;
                else if (tmpface == 3) az = x - 8 - 0;

        		if (tmpface == 0) az = 0 - 3;
                else if (tmpface == 1) ax = 0 + 3;
                else if (tmpface == 2) az = 0 + 3;
                else if (tmpface == 3) ax = 0 - 3;

                ay = -(y - 20) - 20 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bz -= 1;
                else if (tmpface == 1) bx += 1;
                else if (tmpface == 2) bz += 1;
                else if (tmpface == 3) bx -= 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 胴 正面 0
        tmpface = face;
        for (int x = 20; x <= 27; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 20 - 0);
                else if (tmpface == 1) az = -(x - 20 - 0);
                else if (tmpface == 2) ax = x - 20 - 0;
                else if (tmpface == 3) az = x - 20 - 0;

        		if (tmpface == 0) az = 0 - 0;
                else if (tmpface == 1) ax = 0 + 0;
                else if (tmpface == 2) az = 0 + 0;
                else if (tmpface == 3) ax = 0 - 0;

                ay = -(y - 20) - 8 + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 胴 後ろ 2
        tmpface = (face + 2) % 4;
        for (int x = 32; x <= 39; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 32 - 7);
                else if (tmpface == 1) az = -(x - 32 - 7);
                else if (tmpface == 2) ax = x - 32 - 7;
                else if (tmpface == 3) az = x - 32 - 7;

        		if (tmpface == 0) az = 0 - 3;
                else if (tmpface == 1) ax = 0 + 3;
                else if (tmpface == 2) az = 0 + 3;
                else if (tmpface == 3) ax = 0 - 3;

                ay = -(y - 20) - 8 + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 胴 右側 1
        tmpface = (face + 1) % 4;
        for (int x = 16; x <= 19; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 16 - 3);
                else if (tmpface == 1) az = -(x - 16 - 3);
                else if (tmpface == 2) ax = x - 16 - 3;
                else if (tmpface == 3) az = x - 16 - 3;

        		if (tmpface == 0) az = 0 - 0;
                else if (tmpface == 1) ax = 0 + 0;
                else if (tmpface == 2) az = 0 + 0;
                else if (tmpface == 3) ax = 0 - 0;

                ay = -(y - 20) - 8 + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 胴 左側 3
        tmpface = (face + 3) % 4;
        for (int x = 28; x <= 31; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 28 - 0);
                else if (tmpface == 1) az = -(x - 28 - 0);
                else if (tmpface == 2) ax = x - 28 - 0;
                else if (tmpface == 3) az = x - 28 - 0;

        		if (tmpface == 0) az = 0 - 7;
                else if (tmpface == 1) ax = 0 + 7;
                else if (tmpface == 2) az = 0 + 7;
                else if (tmpface == 3) ax = 0 - 7;

                ay = -(y - 20) - 8 + height - 1;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 腕 正面 0
        tmpface = face;
        for (int x = 44; x <= 47; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 44 - 4);
                else if (tmpface == 1) az = -(x - 44 - 4);
                else if (tmpface == 2) ax = x - 44 - 4;
                else if (tmpface == 3) az = x - 44 - 4;

        		if (tmpface == 0) az = 0 - 0;
                else if (tmpface == 1) ax = 0 + 0;
                else if (tmpface == 2) az = 0 + 0;
                else if (tmpface == 3) ax = 0 - 0;

                ay = -(y - 20) - 8 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bx -= 12;
                else if (tmpface == 1) bz -= 12;
                else if (tmpface == 2) bx += 12;
                else if (tmpface == 3) bz += 12;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 腕 後ろ 2
        tmpface = (face + 2) % 4;
        for (int x = 52; x <= 55; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 52 + 1);
                else if (tmpface == 1) az = -(x - 52 + 1);
                else if (tmpface == 2) ax = x - 52 + 1;
                else if (tmpface == 3) az = x - 52 + 1;

        		if (tmpface == 0) az = 0 - 3;
                else if (tmpface == 1) ax = 0 + 3;
                else if (tmpface == 2) az = 0 + 3;
                else if (tmpface == 3) ax = 0 - 3;

                ay = -(y - 20) - 8 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bx += 12;
                else if (tmpface == 1) bz += 12;
                else if (tmpface == 2) bx -= 12;
                else if (tmpface == 3) bz -= 12;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 腕 右側 1
        tmpface = (face + 1) % 4;
        for (int x = 40; x <= 43; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 40 - 3);
                else if (tmpface == 1) az = -(x - 40 - 3);
                else if (tmpface == 2) ax = x - 40 - 3;
                else if (tmpface == 3) az = x - 40 - 3;

        		if (tmpface == 0) az = 0 - 4;
                else if (tmpface == 1) ax = 0 + 4;
                else if (tmpface == 2) az = 0 + 4;
                else if (tmpface == 3) ax = 0 - 4;

                ay = -(y - 20) - 8 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bz += 15;
                else if (tmpface == 1) bx -= 15;
                else if (tmpface == 2) bz -= 15;
                else if (tmpface == 3) bx += 15;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 腕 左側 3
        tmpface = (face + 3) % 4;
        for (int x = 48; x <= 51; x++){
        	for (int y = 20; y <= 31; y++){
        		if (tmpface == 0) ax = -(x - 48 - 0);
                else if (tmpface == 1) az = -(x - 48 - 0);
                else if (tmpface == 2) ax = x - 48 - 0;
                else if (tmpface == 3) az = x - 48 - 0;

        		if (tmpface == 0) az = 0 + 1;
                else if (tmpface == 1) ax = 0 - 1;
                else if (tmpface == 2) az = 0 - 1;
                else if (tmpface == 3) ax = 0 + 1;

                ay = -(y - 20) - 8 + height - 1;

                bx = ax;
                bz = az;
                if (tmpface == 0) bz -= 9;
                else if (tmpface == 1) bx += 9;
                else if (tmpface == 2) bz += 9;
                else if (tmpface == 3) bx -= 9;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 腕 上 up
        tmpface = face;
        for (int x = 44; x <= 47; x++){
        	for (int y = 16; y <= 19; y++){
        		if (tmpface == 0) ax = -(x - 44 - 4);
                else if (tmpface == 1) az = -(x - 44 - 4);
                else if (tmpface == 2) ax = x - 44 - 4;
                else if (tmpface == 3) az = x - 44 - 4;

        		if (tmpface == 0) az = -(y - 16 - 3);
                else if (tmpface == 1) ax = y - 16 - 3;
                else if (tmpface == 2) az = y - 16 - 3;
                else if (tmpface == 3) ax = -(y - 16 - 3);
                ay = 23;

                bx = ax;
                bz = az;
                if (tmpface == 0) bx -= 12;
                else if (tmpface == 1) bz -= 12;
                else if (tmpface == 2) bx += 12;
                else if (tmpface == 3) bz += 12;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 腕 下 down
        tmpface = face;
        for (int x = 48; x <= 51; x++){
        	for (int y = 16; y <= 19; y++){
        		if (tmpface == 0) ax = -(x - 48 - 4);
                else if (tmpface == 1) az = -(x - 48 - 4);
                else if (tmpface == 2) ax = x - 48 - 4;
                else if (tmpface == 3) az = x - 48 - 4;

        		if (tmpface == 0) az = y - 16 - 0;
                else if (tmpface == 1) ax = -(y - 16 - 0);
                else if (tmpface == 2) az = -(y - 16 - 0);
                else if (tmpface == 3) ax = y - 16 - 0;
                ay = 12;

                bx = ax;
                bz = az;
                if (tmpface == 0) bx -= 12;
                else if (tmpface == 1) bz -= 12;
                else if (tmpface == 2) bx += 12;
                else if (tmpface == 3) bz += 12;

                putData = blocks[x][y];
                if (putData.getID() == Material.AIR.getId()) continue;
                loc.clone().add(ax, ay, az).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
                loc.clone().add(bx, ay, bz).getBlock().setTypeIdAndData(putData.getID(), putData.getData(), true);
        	}
        }
        // 脚 下 down
        // 胴 下 up
        // 胴 上 up
        // 腕・脚の上下部分を左右対称にしたい


        plugin.debug("== Finish BuildingTask ==");

        long building_Taked = Timer.getDiffMillis(senderName);
        long total_Taked = this.generator_Taked + building_Taked;

        sendMessage("&aGenerated Skin block art!");
        sendMessage("&7Total " + total_Taked + "ms (background " + generator_Taked + "ms + building &c" + building_Taked + "ms&7)");
    }

    private int getPlayerDirection(){
        if (loc == null) return -1;
        double rotation = (loc.getYaw() - 90) % 360;
        if (rotation < 0) rotation += 360.0;
        // valid 0 - 360 rotate

        if (240 <= rotation && rotation <= 300){ // 270: 240 - 300
            return 0;
        } //315
        else if (330 <= rotation || rotation <= 30){ // 0: -30(330) - 30
            return 1;
        } //45
        else if (60 <= rotation && rotation <= 120){ // 90: 60.0 - 120
            return 2;
        } //135
        else if (150 <= rotation && rotation <= 210){ // 180: 150 - 210
            return 3;
        } //225

        return -1;
    }

    private void sendMessage(String msg){
        Actions.sendMessage(senderName, msg);
    }

    public void putGenTakedtime(final long taked){
        this.generator_Taked = taked;
    }
}
