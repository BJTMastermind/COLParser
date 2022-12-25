# COLParser

A small Java library for parsing .col files used by Minecraft: Legacy Console Edition into a easy to use format. This library is read-only, It cannot create/modify .col files as of now.

## How To Use

* Download `COLParser.jar` from the [Releases](https://github.com/BJTMastermind/COLParser/releases) tab.
* Add the library into your project.
* Import `me.bjtmastermind.colparser.Parser` and `me.bjtmastermind.colparser.COLFile` to use.

Example Code

```java
Parser parser = new Parser(new File("path/to/colours.col"));
COLFile colFile = parser.parse();

for (String color : colFile.getColors().keySet()) {
    System.out.println(color+" | "+colFile.getColors().get(color));
}
for (String color : colFile.getWaterColors().keySet()) {
    System.out.print(color+" | ");
    for (int i = 0; i < 3; i++) {
        System.out.print(COLFile.toHex(colFile.getWaterColors().get(color)[i])+" ");
    }
    System.out.println("");
}
```

Output

```
Mob_Zombie_Colour1 | #00AFAF
Grass_FrozenOcean | #80B497
Foliage_Evergreen | #619961
Water_FrozenRiver | #FFFFFF
Sky_FrozenOcean | #80A1FF
... // Rest of Colors
default | #800089CA #00007CB7 #0000000F 
plains | #A544AFF5 #0044AFF5 #0000000F 
desert | #A532A598 #0032A598 #0000000F 
mega_spruce_taiga_mutated | #A52D6D77 #002D6D77 #0000000F 
cold_ocean | #A52080C9 #0014559B #0000003C
... // Rest of Water Colors
```

## Minimum Java Version

* Java 8

## COL File Format

### Byte Order

The COL file format is stored with **Little Endian** byte order.

### Parsing COL File

| Name | Size (in bytes) | Description |
| - | - | - |
| HasWaterColors (Int) | 4 | If this col file has the biomes water colors |
| ColorCount (Int) | 4 | The number of colors in this col file |

Now loop the next section `ColorCount` times until you have parsed all colors in the file.

| Name | Size (in bytes) | Description |
| - | - | - |
| NameLength (Short) | 2 | The length of this colors name |
| Name (String) | Variable | The name of this color. Size is the result of Name Length |
| TheColor | 4 | The Hex ARGB color value for this color |

After all Colors are parsed if `HasWaterColor` is greater then 0 follow the next section

| Name | Size (in bytes) | Description |
| - | - | - |
| WaterColorCount (Int) | 4 | The number of water colors in this col file |

Now loop the next section `WaterColorCount` times just like before but now each color has 3 values with it.

| Name | Size (in bytes) | Description |
| - | - | - |
| NameLength (Short) | 2 | The length of this colors name |
| Name (String) | Variable | The name of this color. Size is the result of Name Length |
| WaterSurfaceColor | 4 | The Hex ARGB color value for the water surface color |
| UnderwaterColor | 4 | The Hex ARGB color value for the underwater color |
| UnderwaterFogColor | 4 | The Hex ARGB color value for the underwater fog color |