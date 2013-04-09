package hinadamari.statuegenerator.generator;

import hinadamari.statuegenerator.StatueGenerator;
import hinadamari.statuegenerator.util.Actions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


/**
 * Timer (Timer.java)
 * @author syam(syamn)
 */
public class Timer {
    private static Map<String, Integer> taskMap = new ConcurrentHashMap<String, Integer>();
    private static Map<Integer, Long> taskStartTime = new ConcurrentHashMap<Integer, Long>();

    public static void putTask(final String senderName, final int taskID){
        taskMap.put(senderName, taskID);
        taskStartTime.put(taskID, System.currentTimeMillis());
    }

    public static boolean isRunning(final String senderName){
        return taskMap.containsKey(senderName);
    }

    public static long getDiffMillis(final String senderName){
        if (taskMap.containsKey(senderName)){
            return System.currentTimeMillis() - taskStartTime.remove(taskMap.remove(senderName));
        }else{
            return -1L;
        }
    }

    public static void removeData(final String senderName){
        if (taskMap.containsKey(senderName)){
            taskStartTime.remove(taskMap.remove(senderName));
        }
    }

    public static void stopAll(){
        for (String name : taskMap.keySet()){
            StatueGenerator.getInstance().getServer().getScheduler().cancelTask(taskMap.remove(name));
            Player player = Bukkit.getPlayerExact(name);
            if (player != null && player.isOnline()){
                Actions.message(player, StatueGenerator.msgPrefix+ "&cあなたの作成タスクはキャンセルされました");
            }
        }

        taskMap.clear();
        taskStartTime.clear();
    }
}
