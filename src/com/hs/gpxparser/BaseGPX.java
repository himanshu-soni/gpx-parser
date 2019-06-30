package com.hs.gpxparser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import com.hs.gpxparser.extension.IExtensionParser;

class BaseGPX {

    // TFE, 20181105: add support for milli seconds in timestamps
    // TFE, 20190630: should be done using java 8... now we also have time zones to take care of
    
    // 2019-06-18T07:49:03.993+0200Z
    final static SimpleDateFormat xmlDateFormatWithMilliAndZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ'Z'");
    final static int xmlDateFormatWithMilliAndZone_length = 29;
    // 2019-06-18T07:49:03.993Z
    final static SimpleDateFormat xmlDateFormatWithMilli = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    final static int xmlDateFormatWithMilli_length = 24;
    // 2019-06-30T13:50:56+0200Z
    final static SimpleDateFormat xmlDateFormatAndZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ'Z'");
    final static int xmlDateFormatAndZone_length = 25;
    // 2019-06-30T13:50:56Z
    final static SimpleDateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    final static int xmlDateFormat_length = 20;
    final ArrayList<IExtensionParser> extensionParsers = new ArrayList<>();

    BaseGPX() {
        // TFE, 20190630: needs to be set on all formats...
        xmlDateFormatWithMilli.setTimeZone(TimeZone.getTimeZone("GMT"));
        // TF, 20170515: iso6801 dates are always in GMT timezone
        xmlDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * Adds a new extension parser to be used when parsing a gpx steam
     *
     * @param parser an instance of a {@link IExtensionParser} implementation
     */
    public void addExtensionParser(IExtensionParser parser) {
        this.extensionParsers.add(parser);
    }

    /**
     * Removes an extension parser previously added
     *
     * @param parser an instance of a {@link IExtensionParser} implementation
     */
    public void removeExtensionParser(IExtensionParser parser) {
        this.extensionParsers.remove(parser);
    }
}
