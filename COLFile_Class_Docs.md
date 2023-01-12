## COLFile Class Docs

This library was made in Java 8 for Java 8 and newer.

### Field Summary

| Modifier and Type | Field and Description |
| - | - |
| private int | colorCount<br>Holds the number of colors in the colors field. |
| private LinkedHashMap\<String, Integer\> | colors<br>Represents all the colors in the COL file. |
| private int | waterColorCount<br>Holds the number of water colors in the waterColors field. |
| private LinkedHashMap\<String, int[]\> | waterColors<br>Represents all the water colors in the COL file. |

### Constructor Summary

| Constructor and Description |
| - |
| COLFile()<br>Constructs a new COLFile without colors or waterColors specified. |
| COLFile(LinkedHashMap\<String, Integer\> colors)<br>Constructs a new COLFile with the colors specified but without waterColors specified. |
| COLFile(LinkedHashMap\<String, Integer\> colors, LinkedHashMap\<String, int[]\>  waterColors)<br>Constructs a new COLFile with the colors and waterColors specified. |

### Method Summary

| Modifier and Type | Method and Description |
| - | - |
| COLFile | addColor(String name, int color)<br>Adds a color to the color set. |
| COLFile | addWaterColor(String name, int waterColor)<br>Adds a water color to the color set. |
| void | assemble(String outputFilepath)<br>Generates a .col file with the information from the COLFile class instance it was called from. |
| int | getColorCount()<br>Returns the number of colors in the colors field. |
| int | getWaterColorCount()<br>Returns the number of water colors in the waterColors field. |
| boolean | hasWaterColor()<br>Returns whether or not the COLFile has at least one water color. |
| COLFile | parse(File file)<br>Parses the given .col file into a COLFile object. |
| String | printColors()<br>Prints all the colors name, value pairs from the color field. |
| String | printWaterColors()<br>Prints all the water colors name, [value, value, value] pairs from the waterColors field. |
| COLFile | setColors(LinkedHashMap\<String, Integer\> colors)<br>Replaces the current color set with a new color set. |
| COLFile | setWaterColors(LinkedHashMap\<String, int[]\> colors)<br>Replaces the current water color set with a new water color set. |
| static LinkedList\<Integer\> | toARGB(int color)<br>Converts the given color to an RGB color formated [Alpha, Red, Green, Blue]. |
| static String | toARGBHex(int color)<br>Converts the given color to an RGB hex color formatted #AARRGGBB. |
 
