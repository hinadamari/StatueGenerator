package hinadamari.statuegenerator.util;

import hinadamari.statuegenerator.StatueGenerator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


/**
 * Actions (Actions.java)
 * @author syam(syamn)
 */
public class Actions {
    // Logger
    private static final Logger log = StatueGenerator.log;
    private static final String logPrefix = StatueGenerator.logPrefix;
    private static final String msgPrefix = StatueGenerator.msgPrefix;

    /*private final ArtGenerator plugin;

    public Actions(ArtGenerator plugin){
        this.plugin = plugin;
    }*/

    /****************************************/
    // メッセージ送信系関数
    /****************************************/
    /**
     * メッセージをユニキャスト
     * @param message メッセージ
     */
    public static void message(CommandSender sender, String message){
        if (sender != null && message != null){
            sender.sendMessage(message.replaceAll("&([0-9a-fk-or])", "\u00A7$1"));
        }
    }

    /**
     * タスク実行者にメッセージを送信する
     * @param msg
     */
    public static void sendMessage(String senderName, String msg){
        if (msg == null) return;

        if (senderName == null){
            Actions.message(Bukkit.getConsoleSender(), logPrefix + msg);
            return;
        }

        final Player player = Bukkit.getPlayerExact(senderName);
        if (player != null && player.isOnline()){
            Actions.message(player, msgPrefix + msg);
        }else{
            Actions.message(Bukkit.getConsoleSender(), logPrefix + " (Offline)" + senderName + ": " + msg);
        }
    }

    /**
     * メッセージをブロードキャスト
     * @param message メッセージ
     */
    public static void broadcastMessage(String message){
        if (message != null){
            message = message.replaceAll("&([0-9a-fk-or])", "\u00A7$1");
            //debug(message);//debug
            Bukkit.broadcastMessage(message);
        }
    }
    /**
     * メッセージをワールドキャスト
     * @param world
     * @param message
     */
    public static void worldcastMessage(World world, String message){
        if (world != null && message != null){
            message = message.replaceAll("&([0-9a-fk-or])", "\u00A7$1");
            for(Player player: world.getPlayers()){
                player.sendMessage(message);
            }
            log.info("[Worldcast]["+world.getName()+"]: " + message);
        }
    }
    /**
     * メッセージをパーミッションキャスト(指定した権限ユーザにのみ送信)
     * @param permission 受信するための権限ノード
     * @param message メッセージ
     */
    public static void permcastMessage(String permission, String message){
        // OK
        int i = 0;
        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            if (player.hasPermission(permission)){
                Actions.message(player, message);
                i++;
            }
        }

        log.info("Received "+i+"players: "+message);
    }

    /****************************************/
    // ユーティリティ
    /****************************************/
    /**
     * 文字配列をまとめる
     * @param s つなげるString配列
     * @param glue 区切り文字 通常は半角スペース
     * @return
     */
    public static String combine(String[] s, String glue)
    {
        int k = s.length;
        if (k == 0){ return null; }
        StringBuilder out = new StringBuilder();
        out.append(s[0]);
        for (int x = 1; x < k; x++){
            out.append(glue).append(s[x]);
        }
        return out.toString();
    }
    /**
     * コマンドをコンソールから実行する
     * @param command
     */
    public static void executeCommandOnConsole(String command){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }
    /**
     * 文字列の中に全角文字が含まれているか判定
     * @param s 判定する文字列
     * @return 1文字でも全角文字が含まれていればtrue 含まれていなければfalse
     * @throws UnsupportedEncodingException
     */
    public static boolean containsZen(String s)
            throws UnsupportedEncodingException {
        for (int i = 0; i < s.length(); i++) {
            String s1 = s.substring(i, i + 1);
            if (URLEncoder.encode(s1,"MS932").length() >= 4) {
                return true;
            }
        }
        return false;
    }
    /**
     * 現在の日時を yyyy-MM-dd HH:mm:ss 形式の文字列で返す
     * @return
     */
    public static String getDatetime(){

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
    /**
     * 座標データを ワールド名:x, y, z の形式の文字列にして返す
     * @param loc
     * @return
     */
    public static String getLocationString(Location loc){
        return loc.getWorld().getName()+":"+loc.getX()+","+loc.getY()+","+loc.getZ();
    }
    public static String getBlockLocationString(Location loc){
        return loc.getWorld().getName()+":"+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ();
    }
    /**
     * デバッグ用 syamnがオンラインならメッセージを送る
     * @param msg
     */
    public static void debug(String msg){
        OfflinePlayer syamn = Bukkit.getServer().getOfflinePlayer("syamn");
        if (syamn.isOnline()){
            Actions.message((Player) syamn, msg);
        }
    }

    /****************************************/
    /* ログ操作系 */
    /****************************************/
    /**
     * ログファイルに書き込み
     * @param file ログファイル名
     * @param line ログ内容
     */
    public static void log(String filepath, String line){
        TextFileHandler r = new TextFileHandler(filepath);
        try{
            r.appendLine("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "] " + line);
        } catch (IOException ex) {}
    }

    /****************************************/
    /* その他 */
    /****************************************/
    // プレイヤーがオンラインかチェックしてテレポートさせる
    public static void tpPlayer(Player player, Location loc){
        if (player == null || loc == null || !player.isOnline())
            return;
        player.teleport(loc);
    }

    // プレイヤーのインベントリをその場にドロップさせる
    public static void dropInventoryItems(Player player){
        if (player == null) return;

        PlayerInventory inv = player.getInventory();
        Location loc = player.getLocation();

        // インベントリアイテム
        for (ItemStack i : inv.getContents()) {
            if (i != null && i.getType() != Material.AIR) {
                inv.remove(i);
                player.getWorld().dropItemNaturally(loc, i);
            }
        }

        // 防具アイテム
        for (ItemStack i : inv.getArmorContents()){
            if (i != null && i.getType() != Material.AIR) {
                inv.remove(i);
                player.getWorld().dropItemNaturally(loc, i);
            }
        }
    }

    public static String getCardinalDirection(Location playerLoc){
        double rotation = (playerLoc.getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
    public static String getCardinalDirection(Player player) {
        return getCardinalDirection(player.getLocation());
    }
}