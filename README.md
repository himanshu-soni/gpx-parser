# gpx-parser
Java Parser for reading and writing GPX files

using base from [gpxparser](http://sourceforge.net/projects/gpxparser/)

Follows [GPX schema version 1.1](http://www.topografix.com/gpx/1/1/)


## Installation

**Gradle**

```groovy
compile 'me.himanshusoni.gpxparser:gpx-parser:1.13'
 // or 
implementation 'me.himanshusoni.gpxparser:gpx-parser:1.13'
```

**Maven**
```xml
<dependency>
    <groupId>me.himanshusoni.gpxparser</groupId>
    <artifactId>gpx-parser</artifactId>
    <version>1.13</version>
    <type>pom</type>
</dependency>
```

**Jar**

Download [Jar](https://jcenter.bintray.com/me/himanshusoni/gpxparser/gpx-parser/1.13/gpx-parser-1.13.jar) and include to your classpath.


## Example Uses:

To read GPX file:

```java
GPXParser p = new GPXParser();
FileInputStream in = new FileInputStream("inFile.gpx");
GPX gpx = p.parseGPX(in);
```

To write to GPX file:

```java
GPXWriter writer = new GPXWriter();
FileOutputStream out = new FileOutputStream("outFile.gpx");
writer.writeGPX(gpx, out);
out.close();
```
