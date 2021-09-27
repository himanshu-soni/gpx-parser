package me.himanshusoni.gpxparser;

import me.himanshusoni.gpxparser.extension.IExtensionParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;


class BaseGPX {

    // TFE, 20181105: add support for milli seconds in timestamps
    final static SimpleDateFormat xmlDateFormatWithMilli = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    final static int xmlDateFormatWithMilli_length = 24;
    final static SimpleDateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    final ArrayList<IExtensionParser> extensionParsers = new ArrayList<>();

    BaseGPX() {
        // TF, 20170515: iso6801 dates are always in GMT timezone
        xmlDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        // TFE, 20210927: better do the same for milli as well... bummer!
        xmlDateFormatWithMilli.setTimeZone(TimeZone.getTimeZone("GMT"));
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
