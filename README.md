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
```

Output

```
Mob_Zombie_Colour1 | #00AFAF
Grass_FrozenOcean | #80B497
Foliage_Evergreen | #619961
Water_FrozenRiver | #FFFFFF
Sky_FrozenOcean | #80A1FF
...
```

## Minimum Java Version

* Java 8

## COL File Format (Might Be Inaccurate)

### Byte Order

The COL file format is stored with **Little Endian** byte order.

### Parsing COL File

| Name | Size (in bytes) | Description |
| - | - | - |
| ColorCount (Long) | 8 | The number of colors in this col file |

Now loop the next section `ColorCount` times until you have parsed all colors in the file.

| Name | Size (in bytes) | Description |
| - | - | - |
| null termination | 1 | `00` byte indicating the end of a color |
| NameLength (Byte) | 1 | The length of this colors name |
| Name (String) | Variable | The name of this color. Size is the result of Name Length |
| null termination | 1 | `00` byte indicating the end of `Name` |
| TheColor | 3 | The Hex color value for this color |
