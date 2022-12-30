## COL File Format

### Byte Order

The COL file format is stored with **Big Endian** byte order.

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
 
