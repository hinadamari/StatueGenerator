package hinadamari.statuegenerator.generator;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataMap (DataMap.java)
 * @author syam(syamn)
 * @author hinadamari
 */
public class ColorData {

    private static Map<Color, String> colorMap = new HashMap<Color, String>();

    public static void init(List<String> allowedBlocks){
        colorMap.clear();

        // put colors
        for (String id : allowedBlocks) {

        	switch(id.trim()) {
    	    	case "35":
    	    	case "35:0":
        	    	putColor("35:0", 221, 221, 221);
        	    	break;
        	    case "35:1":
        	    	putColor("35:1", 219, 125, 62);
        	    	break;
        	    case "35:2":
        	    	putColor("35:2", 179, 80, 188);
        	    	break;
        	    case "35:3":
        	    	putColor("35:3", 106, 137, 201);
        	    	break;
        	    case "35:4":
        	    	putColor("35:4", 176, 165, 39);
        	    	break;
        	    case "35:5":
        	    	putColor("35:5", 65, 173, 56);
        	    	break;
        	    case "35:6":
        	    	putColor("35:6", 208, 131, 152);
        	    	break;
        	    case "35:7":
        	    	putColor("35:7", 64, 64, 64);
        	    	break;
        	    case "35:8":
        	    	putColor("35:8", 154, 160, 160);
        	    	break;
        	    case "35:9":
        	    	putColor("35:9", 46, 110, 137);
        	    	break;
        	    case "35:10":
        	    	putColor("35:10", 126, 61, 181);
        	    	break;
        	    case "35:11":
        	    	putColor("35:11", 46, 56, 141);
        	    	break;
        	    case "35:12":
        	    	putColor("35:12", 79, 50, 31);
        	    	break;
        	    case "35:13":
        	    	putColor("35:13", 53, 70, 27);
        	    	break;
        	    case "35:14":
        	    	putColor("35:14", 150, 52, 49);
        	    	break;
        	    case "35:15":
        	    	putColor("35:15", 25, 22, 22);
        	    	break;
        	    case "1":
        	    	putColor("1", 125, 125, 125);
        	    	break;
        	    case "3":
        	    	putColor("3", 136, 98, 68);
        	    	break;
        	    case "4":
        	    	putColor("4", 123, 123, 123);
        	    	break;
        	    case "7":
        	    	putColor("7", 82, 82, 82);
        	    	break;
        	    case "5":
        	    case "5:0":
        	    	putColor("5", 155, 126, 78);
        	    	break;
        	    case "12":
        	    	putColor("12", 218, 211, 159);
        	    	break;
        	    case "13":
        	    	putColor("13", 127, 124, 122);
        	    	break;
        	    case "22":
        	    	putColor("22", 29, 72, 166);
        	    	break;
        	    case "24":
        	    	putColor("24", 218, 211, 159);
        	    	break;
        	    case "25":
        	    	putColor("25", 88, 61, 48);
        	    	break;
        	    case "41":
        	    	putColor("41", 246, 229, 74);
        	    	break;
        	    case "42":
        	    	putColor("42", 212, 212, 212);
        	    	break;
        	    case "45":
        	    	putColor("45", 149, 104, 91);
        	    	break;
        	    case "48":
        	    	putColor("48", 106, 122, 106);
        	    	break;
        	    case "49":
        	    	putColor("49", 19, 18, 28);
        	    	break;
        	    case "57":
        	    	putColor("57", 75, 213, 206);
        	    	break;
        	    case "80":
        	    	putColor("80", 241, 251, 251);
        	    	break;
        	    case "82":
        	    	putColor("82", 158, 164, 176);
        	    	break;
        	    case "86":
        	    	putColor("86", 190, 115, 21);
        	    	break;
        	    case "91":
        	    	putColor("91", 190, 115, 21);
        	    	break;
        	    case "87":
        	    	putColor("87", 115, 58, 56);
        	    	break;
        	    case "88":
        	    	putColor("88", 85, 65, 52);
        	    	break;
        	    case "89":
        	    	putColor("89", 141, 116, 68);
        	    	break;
        	    case "5:1":
        	    	putColor("5:1", 103, 77, 46);
        	    	break;
        	    case "5:2":
        	    	putColor("5:2", 194, 178, 122);
        	    	break;
        	    case "5:3":
        	    	putColor("5:3", 152, 109, 76);
        	    	break;
        	    case "19":
        	    	putColor("19", 187, 187, 60);
        	    	break;
        	    case "112":
        	    	putColor("112", 43, 22, 26);
        	    	break;
        	    case "121":
        	    	putColor("121", 221, 224, 166);
        	    	break;
        	    case "133":
        	    	putColor("133", 83, 216, 118);
        	    	break;
        	    case "152":
        	    	putColor("152", 182, 30, 10);
        	    	break;
        	    case "155":
        	    	putColor("155", 235, 232, 225);
        	    	break;
        	    case "43:8":
        	    	putColor("43:8", 153, 153, 153);
        	    	break;
        	    case "43:9":
        	    	putColor("43:9", 219, 211, 159);
        	    	break;
        	    // 1.6.x
        	    case "159":
    	    	case "159:0":
        	    	putColor("159:0", 210, 178, 161);
        	    	break;
        	    case "159:1":
        	    	putColor("159:1", 162, 84, 38);
        	    	break;
        	    case "159:2":
        	    	putColor("159:2", 150, 88, 109);
        	    	break;
        	    case "159:3":
        	    	putColor("159:3", 113, 109, 138);
        	    	break;
        	    case "159:4":
        	    	putColor("159:4", 186, 133, 35);
        	    	break;
        	    case "159:5":
        	    	putColor("159:5", 104, 118, 53);
        	    	break;
        	    case "159:6":
        	    	putColor("159:6", 162, 78, 79);
        	    	break;
        	    case "159:7":
        	    	putColor("159:7", 58, 43, 36);
        	    	break;
        	    case "159:8":
        	    	putColor("159:8", 135, 107, 98);
        	    	break;
        	    case "159:9":
        	    	putColor("159:9", 87, 91, 91);
        	    	break;
        	    case "159:10":
        	    	putColor("159:10", 118, 70, 86);
        	    	break;
        	    case "159:11":
        	    	putColor("159:11", 74, 60, 91);
        	    	break;
        	    case "159:12":
        	    	putColor("159:12", 77, 51, 36);
        	    	break;
        	    case "159:13":
        	    	putColor("159:13", 76, 83, 42);
        	    	break;
        	    case "159:14":
        	    	putColor("159:14", 143, 61, 47);
        	    	break;
        	    case "159:15":
        	    	putColor("159:15", 37, 23, 16);
        	    	break;
        	    case "170":
        	    	putColor("170", 158, 120, 18);
        	    	break;
        	    case "172":
        	    	putColor("172", 151, 93, 67);
        	    	break;
        	    case "173":
        	    	putColor("173", 18, 18, 18);
        	    	break;
        	}
        }

    }

    private static void putColor(final String block, final int r, final int g, final int b){
        colorMap.put(new Color(r, g, b), block.trim());
    }

    public static String getBlockStr(final Color color){
        if (color == null) return "0";
        return (colorMap.containsKey(color)) ? colorMap.get(color) : "0";
    }

    public static Map<Color, String> getColorMap(){
        return colorMap;
    }
}
