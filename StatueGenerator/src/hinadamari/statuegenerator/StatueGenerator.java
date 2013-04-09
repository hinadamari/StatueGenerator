package hinadamari.statuegenerator;

import hinadamari.statuegenerator.command.BaseCommand;
import hinadamari.statuegenerator.command.GenerateCommand;
import hinadamari.statuegenerator.command.HelpCommand;
import hinadamari.statuegenerator.command.ReloadCommand;
import hinadamari.statuegenerator.generator.ColorData;
import hinadamari.statuegenerator.generator.Timer;
import hinadamari.statuegenerator.util.Metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * StatueGenerator (StatueGenerator.java)
 * @author syam(syamn)
 * @author hinadamari
 */
public class StatueGenerator extends JavaPlugin{
    // ** Logger **
    public final static Logger log = Logger.getLogger("Minecraft");
    public final static String logPrefix = "[StatueGenerator] ";
    public final static String msgPrefix = "&6[StatueGenerator] &f";

    // ** Commands **
    private List<BaseCommand> commands = new ArrayList<BaseCommand>();

    // ** Private Classes **
    private ConfigurationManager config;

    // ** Instance **
    private static StatueGenerator instance;

    // ** Allowd Blocks **
    public List<String> allowedBlocks = new ArrayList<String>();

    /**
     * プラグイン起動処理
     */
    @Override
    public void onEnable(){
        instance  = this;

        PluginManager pm = getServer().getPluginManager();
        config = new ConfigurationManager(this);

        // loadconfig
        try{
            config.loadConfig(true);
        }catch (Exception ex){
            log.warning(logPrefix+"an error occured while trying to load the config file.");
            ex.printStackTrace();
        }

        // プラグインを無効にした場合進まないようにする
        if (!pm.isPluginEnabled(this)){
            return;
        }

        // コマンド登録
        registerCommands();

        settingColorData();

        // メッセージ表示
        PluginDescriptionFile pdfFile=this.getDescription();
        log.info("["+pdfFile.getName()+"] version "+pdfFile.getVersion()+" is enabled!");

        setupMetrics(); // mcstats
    }

    /**
     * 使用ブロックと色情報の設定
     */
    public void settingColorData() {
    	ColorData.init(getConfig().getStringList("allowed-blocks"));
    }

    /**
     * プラグイン停止処理
     */
    @Override
    public void onDisable(){
        Timer.stopAll();
        getServer().getScheduler().cancelTasks(this);

        // メッセージ表示
        PluginDescriptionFile pdfFile=this.getDescription();
        log.info("["+pdfFile.getName()+"] version "+pdfFile.getVersion()+" is disabled!");
    }

    /**
     * コマンドを登録
     */
    private void registerCommands(){
        // Intro Commands
        commands.add(new HelpCommand());

        // Main Commands
        commands.add(new GenerateCommand());

        // Admin Commands
        commands.add(new ReloadCommand());
    }

    /**
     * Metricsセットアップ
     */
    private void setupMetrics(){
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException ex) {
            log.warning(logPrefix+"cant send metrics data!");
            ex.printStackTrace();
        }
    }

    /**
     * コマンドが呼ばれた
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
        if (cmd.getName().equalsIgnoreCase("skingenerator")){
            if(args.length == 0){
                // 引数ゼロはヘルプ表示
                args = new String[]{"help"};
            }
            else if (args[0].equalsIgnoreCase("gen")){
                args[0] = "generate";
            }

            outer:
                for (BaseCommand command : commands.toArray(new BaseCommand[0])){
                    String[] cmds = command.getName().split(" ");
                    for (int i = 0; i < cmds.length;){
                        if (i >= args.length || !cmds[i].equalsIgnoreCase(args[i])){
                            continue outer;
                        }
                        // 実行
                        return command.run(this, sender, args, commandLabel);
                    }
                }
            // 有効コマンドなし デフォルトコマンド実行
            new HelpCommand().run(this, sender, args, commandLabel);
            return true;
        }
        return false;
    }

    /**
     * デバッグログ
     * @param msg
     */
    public void debug(final String msg){
        if (config.isDebug()){
            log.info(logPrefix+ "[DEBUG]" + msg);
        }
    }

    /* getter */
    /**
     * コマンドを返す
     * @return List<BaseCommand>
     */
    public List<BaseCommand> getCommands(){
        return commands;
    }

    /**
     * 設定マネージャを返す
     * @return ConfigurationManager
     */
    public ConfigurationManager getConfigs() {
        return config;
    }

    /**
     * インスタンスを返す
     * @return MotdManagerインスタンス
     */
    public static StatueGenerator getInstance(){
        return instance;
    }
}