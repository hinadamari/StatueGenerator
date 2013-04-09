package hinadamari.statuegenerator.command;

import hinadamari.statuegenerator.Perms;
import hinadamari.statuegenerator.StatueGenerator;
import hinadamari.statuegenerator.exception.CommandException;
import hinadamari.statuegenerator.generator.Direction;
import hinadamari.statuegenerator.generator.GeneratorTask;
import hinadamari.statuegenerator.generator.Timer;
import hinadamari.statuegenerator.util.Actions;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * GenerateCommand (GenerateCommand.java)
 * @author syam(syamn)
 * @author hinadamari
 */
public class GenerateCommand extends BaseCommand{
    public GenerateCommand(){
        bePlayer = true;
        name = "generate";
        argLength = 1;
        usage = "<PlayerID> <- generate dot art";
    }

    @Override
    public void execute() throws CommandException {
        // check source: URL
        URL url = null;
        try{
            url = new URL(StatueGenerator.getInstance().getConfig().getString("skin-url").replace("%player%", args.get(0)));
        }catch(MalformedURLException ex){
        	throw new CommandException(msgPrefix+ "&cSkin data not found! : " + args.get(0));
        }

        // check source: File
        /*File file = null;
        if (url == null){
            file = new File(plugin.getDataFolder().getPath() + System.getProperty("file.separator") + "image", args.get(0));
            if (!file.exists()){
                throw new CommandException(msgPrefix+ "&cFile not found! " + file.getPath());
            }
            if (!file.canRead()){
                throw new CommandException(msgPrefix+ "&cCould not read the file! " + file.getPath());
            }
        }*/

        // check direction
        Direction dir = null;
        /*if (args.size() >= 2){
            final String str1 = args.get(1);
            for(Direction d : Direction.values()){
                if (d.name().equalsIgnoreCase(str1)){
                    dir = d;
                }
            }
            if (dir == null)
                throw new CommandException(msgPrefix+ "&cInvalid direction parameter (UP/DOWN): " + str1);
        }else{
            dir = Direction.FACE;
        }*/
        dir = Direction.FACE;

        // check already running, synchronized check
        synchronized (GenerateCommand.class){
            if (Timer.isRunning(sender.getName())){
                throw new CommandException(msgPrefix+ "&cYou are already running generator task!");
            }
            final GeneratorTask task = new GeneratorTask(plugin, sender, dir);
            task.setSource(url);
            @SuppressWarnings("deprecation")
			final int taskID = plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, task, 0L);
            Timer.putTask(sender.getName(), taskID);
        }

        Actions.message(sender, msgPrefix + "&aStarted to generate image..");
    }

    @Override
    public boolean permission() {
        return Perms.GENERATE.has(sender);
    }
}