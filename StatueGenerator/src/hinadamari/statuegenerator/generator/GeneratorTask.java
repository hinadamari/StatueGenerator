package hinadamari.statuegenerator.generator;

import hinadamari.statuegenerator.StatueGenerator;
import hinadamari.statuegenerator.util.Actions;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
//import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//import syam.artgenerator.exception.StateException;

/**
 * GeneratorTask (GeneratorTask.java)
 * @author syam(syamn)
 * @author hinadamari
 */
public class GeneratorTask implements Runnable{
    // Logger
    //private static final Logger log = ArtGenerator.log;
    //private static final String logPrefix = ArtGenerator.logPrefix;
    //private static final String msgPrefix = ArtGenerator.msgPrefix;

    private final StatueGenerator plugin;

    private String senderName;
    private Location senderLocation;
    private Direction dir;

    private boolean isURL = false;
    private boolean isFile = false;
    private URL url = null;
    private File file = null;

    BufferedImage img = null;

    public GeneratorTask(final StatueGenerator plugin, final CommandSender sender, final Direction direction){
        this.plugin = plugin;

        this.senderName = sender.getName();
        this.senderLocation = (sender instanceof Player) ? ((Player) sender).getLocation() : null;
        this.dir = direction;
    }

    public void setSource(final URL url){
        if (url == null) return;
        this.isURL = true;
        this.url = url;
    }
    public void setSource(final File file){
        if (file == null) return;
        this.isFile = true;
        this.file = file;
    }


    @Override
    public void run(){
        plugin.debug("== Start GeneratorTask by " + senderName + " ==");

        // get image
        try{
            img = getImage();
        }catch (IOException ex){
            sendMessage("&cFailed to read source image!");
            if (plugin.getConfigs().isDebug()){
                ex.printStackTrace();
            }
            Timer.removeData(senderName);
            return;
        }

        final int width = img.getWidth();
        final int height = img.getHeight();

        plugin.debug("Finish read the source image: width=" + width + ", height=" + height);

        // loop image bits
        plugin.debug("Start image convert loop");
        BlockData[][] blocks = new BlockData[width][height];
        for (int x = 0; x <= img.getWidth() - 1; x++){
            for (int y = 0; y <= img.getHeight() - 1; y++){
                //Color closest = getClosestMatch(x, y);

                //String[] blockStr = ColorData.getBlockStr(closest).split(":");
                String blockStr = getClosestMatch(x, y);
                String[] splitted;
                if (blockStr == null){
                    splitted = new String[]{"0"};
                }else{
                    splitted = blockStr.trim().split(":");
                }
                final BlockData block = new BlockData(
                        Integer.parseInt(splitted[0]),
                        (splitted.length > 1) ? Byte.parseByte(splitted[1]) : (byte) 0);

                // put data
                blocks[x][y] = block;
            }
        }
        plugin.debug("Finish image convert loop");

        // build
        BuildingTask task = new BuildingTask(plugin, senderName, senderLocation, dir);
        task.putBlockData(blocks, width, height);

        synchronized (GeneratorTask.class) {
            task.putGenTakedtime(Timer.getDiffMillis(senderName));
            int taskID = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, task, 0L);
            Timer.putTask(senderName, taskID);
            plugin.debug("Started BuildingTask (TaskID:" + taskID + ")");
        }
        plugin.debug("== Finish GeneratorTask ==");
    }

    /**
     * Get image
     * @return
     * @throws IOException
     */
    private BufferedImage getImage() throws IOException{
        if (isURL){
            plugin.debug("Getting image from uri: " + url.getPath());
            return ImageIO.read(url);
        }
        else if (isFile){
            plugin.debug("Getting image from file: " + file.getPath());
            return ImageIO.read(file);
        }
        plugin.debug("returned null source!");
        return null;
    }

    /**
     * プラグインに登録済みの中から一番近い色のブロックを返す
     * @param x
     * @param y
     * @return
     */
    private String getClosestMatch(final int x, final int y){
        if (img == null || (img.getRGB(x, y) >> 24 & 0xFF) < 10){
            return null;
        }

        int prevMin = 195075; //255^2 * 3; // possibly max value
        String closestColorBlock = null;

        for (Color col : ColorData.getColorMap().keySet()){
            int diff = getColorDiff(new Color(img.getRGB(x, y)), col);
            if (diff >= prevMin) continue;

            prevMin = diff;
            closestColorBlock = ColorData.getBlockStr(col);
        }

        plugin.debug("ClosestColorBlock Selected: " + closestColorBlock + ", prevMin=" + prevMin);
        return closestColorBlock;
    }
    /**
     * 色と色の差をintで返す
     * @param c1
     * @param c2
     * @return
     */
    private int getColorDiff(final Color c1, final Color c2){
        int diff = 0;
        diff += Math.pow(c1.getRed() - c2.getRed(), 2);
        diff += Math.pow(c1.getGreen() - c2.getGreen(), 2);
        diff += Math.pow(c1.getBlue() - c2.getBlue(), 2);
        return diff;
    }

    private void sendMessage(String msg){
        Actions.sendMessage(senderName, msg);
    }
}
