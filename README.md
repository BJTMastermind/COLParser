# COLParser

A small Java library for parsing .col files used by Minecraft: Legacy Console Edition into a easy to use format. This library supports reading and writing.

## How To Use

* Download `COLParser.jar` from the [Releases](https://github.com/BJTMastermind/COLParser/releases) tab.
* Add the library into your project.
* Import `me.bjtmastermind.colparser.COLFile` to use.

Example Code (Parsing)

```java
COLFile colFile = new COLFile();
colFile.parse(new File("path/to/colours.col"));

System.out.println(colFile.printColors());
System.out.println(colFile.printWaterColors());
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

Example Code (Assembling)

```
colFile.assemble("path/to/new_colors.col");
```

Output: Generates new .col file in specified location

## Minimum Java Version

* Java 8

## COLFile Class Docs

* See [COLFile_Class_Docs.md](https://github.com/BJTMastermind/COLParser/blob/main/COLFile_Class_Docs.md)

## COL File Format

* See [COL_File_Format.md](https://github.com/BJTMastermind/COLParser/blob/main/COL_File_Format.md)