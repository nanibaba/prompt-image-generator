package org.aubg;

import java.util.HashMap;
import java.util.Map;

public class ColorCodeGenerator {
    public int redValue; 
    public int greenValue;
    public int blueValue; 
    private static String[] colorCode; 

    public ColorCodeGenerator(String color) {
        colorCode = generateColorCode(color);
        this.redValue = Integer.parseInt(colorCode[0]);
        this.greenValue = Integer.parseInt(colorCode[1]);
        this.blueValue = Integer.parseInt(colorCode[2]);
    }

    public static String[] generateColorCode(String color){
        Map<String, String> colorCodes = new HashMap<>();
        colorCodes.put("red", "255, 0, 0");
        colorCodes.put("blue", "0, 0, 255");
        colorCodes.put("green", "0, 128, 0");
        colorCodes.put("yellow", "255, 255, 0");
        colorCodes.put("orange", "255, 165, 0");
        colorCodes.put("purple", "128, 0, 128");
        colorCodes.put("pink", "255, 192, 203");
        colorCodes.put("brown", "165, 42, 42");
        colorCodes.put("black", "0, 0, 0");
        colorCodes.put("white", "255, 255, 255");
        colorCodes.put("gray", "128, 128, 128");
        colorCodes.put("cyan", "0, 255, 255");
        colorCodes.put("magenta", "255, 0, 255");
        colorCodes.put("teal", "0, 128, 128");
        colorCodes.put("maroon", "128, 0, 0");
        colorCodes.put("olive", "128, 128, 0");
        colorCodes.put("navy", "0, 0, 128");
        colorCodes.put("lavender", "230, 230, 250");
        colorCodes.put("beige", "210, 180, 140");
        colorCodes.put("turquoise", "64, 224, 208");
        colorCodes.put("crimson", "220, 20, 60");
        colorCodes.put("indigo", "75, 0, 130");
        colorCodes.put("violet", "238, 130, 238");
        colorCodes.put("gold", "255, 215, 0");
        colorCodes.put("silver", "192, 192, 192");
        colorCodes.put("lime", "0, 255, 0");
        colorCodes.put("charcoal", "54, 69, 79");
        colorCodes.put("coral", "255, 127, 80");
        colorCodes.put("khaki", "240, 230, 140");
        colorCodes.put("emerald", "80, 200, 120");
        colorCodes.put("sapphire", "15, 82, 186");
        colorCodes.put("ruby", "224, 17, 95");
        colorCodes.put("fuchsia", "255, 0, 255");
        colorCodes.put("mint", "189, 252, 201");
        colorCodes.put("ivory", "255, 255, 240");
        colorCodes.put("bronze", "205, 127, 50");
        colorCodes.put("burgundy", "128, 0, 32");
        colorCodes.put("aqua", "0, 255, 255");
        colorCodes.put("amber", "255, 191, 0");
        colorCodes.put("mauve", "224, 176, 255");
        colorCodes.put("periwinkle", "204, 204, 255");
        colorCodes.put("ochre", "204, 119, 34");
        colorCodes.put("sienna", "136, 45, 23");
        colorCodes.put("cerulean", "0, 123, 167");
        colorCodes.put("tan", "255, 165, 79");
        colorCodes.put("taupe", "72, 60, 50");
        colorCodes.put("salmon", "250, 128, 114");
        colorCodes.put("plum", "221, 160, 221");
        colorCodes.put("jade", "0, 168, 107");
        colorCodes.put("cobalt", "0, 71, 171");
        colorCodes.put("ash", "178, 190, 181");
        colorCodes.put("mustard", "255, 219, 88");
        colorCodes.put("raspberry", "227, 11, 93");
        colorCodes.put("rose", "255, 0, 127");
        colorCodes.put("lemon", "255, 247, 0");
        colorCodes.put("azure", "0, 127, 255");
        colorCodes.put("pearl", "234, 224, 200");
        colorCodes.put("tangerine", "242, 133, 0");
        colorCodes.put("champagne", "247, 231, 206");
        colorCodes.put("copper", "184, 115, 51");
        colorCodes.put("moss", "138, 154, 91");
        colorCodes.put("seafoam", "159, 226, 191");
        colorCodes.put("wheat", "245, 245, 220");
        colorCodes.put("eggplant", "97, 64, 81");
        colorCodes.put("sepia", "112, 66, 20");

        String targetCode = colorCodes.get(color); 

        return targetCode.split(", ");
    }
}
