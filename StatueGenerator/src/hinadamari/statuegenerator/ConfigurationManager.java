package hinadamari.statuegenerator;

import hinadamari.statuegenerator.util.FileStructure;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;


/**
 * ConfigurationManager (ConfigurationManager.java)
 * @author syam(syamn)
 */
public class ConfigurationManager {
    /* Current config.yml File Version! */
    private final int latestVersion = 1;

    // Logger
    private static final Logger log = StatueGenerator.log;
    //private static final String logPrefix = ArtGenerator.logPrefix;
    //private static final String msgPrefix = ArtGenerator.msgPrefix;
    private final StatueGenerator plugin;

    //private YamlConfiguration conf;
    private FileConfiguration conf;
    private File pluginDir;

    /**
     * Constructor
     */
    public ConfigurationManager(final StatueGenerator plugin){
        this.plugin = plugin;

        this.pluginDir = this.plugin.getDataFolder();
    }

    /**
     * Load config.yml
     */
    public void loadConfig(final boolean initialLoad) throws Exception{
        // create directories
        FileStructure.createDir(pluginDir);
        //FileStructure.createDir(new File(pluginDir, "image"));

        // get config.yml path
        File file = new File(pluginDir, "config.yml");
        if (!file.exists()){
            FileStructure.extractResource("/config.yml", pluginDir, false, false);
            log.info("config.yml is not found! Created default config.yml!");
        }

        plugin.reloadConfig();
        conf = plugin.getConfig();

        checkver(conf.getInt("ConfigVersion", 1));
    }

    /**
     * Check configuration file version
     */
    private void checkver(final int ver){
        // compare configuration file version
        if (ver < latestVersion){
            // first, rename old configuration
            final String destName = "oldconfig-v" + ver + ".yml";
            String srcPath = new File(pluginDir, "config.yml").getPath();
            String destPath = new File(pluginDir, destName).getPath();
            try{
                FileStructure.copyTransfer(srcPath, destPath);
                log.info("Copied old config.yml to "+destName+"!");
            }catch(Exception ex){
                log.warning("Failed to copy old config.yml!");
            }

            // force copy config.yml and languages
            FileStructure.extractResource("/config.yml", pluginDir, true, false);
            //Language.extractLanguageFile(true);

            plugin.reloadConfig();
            conf = plugin.getConfig();

            log.info("Deleted existing configuration file and generate a new one!");
        }
    }

    /* ***** Begin Configuration Getters *********************** */
    public boolean isDebug(){
        return conf.getBoolean("Debug", false);
    }
}
