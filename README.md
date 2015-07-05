# gpx-parser
Java Parser for reading and writing GPX files

using base from [gpxparser](http://sourceforge.net/projects/gpxparser/)

Follows [GPX schema version 1.1](http://www.topografix.com/gpx/1/1/)


## Example Uses:
include jar to your classpath [Download JAR](https://github.com/himanshu-soni/gpx-parser/releases/download/0.9/gpx-parser-0.9.jar)

To read GPX file:


`GPXParser p = new GPXParser();`

`FileInputStream in = new FileInputStream("inFile.gpx");`

`GPX gpx = p.parseGPX(in);`

To write to GPX file:

`GPXWriter writer = new GPXWriter();`

`FileOutputStream out = new FileOutputStream("outFile.gpx");`

`writer.writeGPX(gpx, out);`

`out.close();`