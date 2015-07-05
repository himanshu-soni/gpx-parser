package com.hs.gpxparser.extension;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface IExtensionParser {

	public String getId();

	public Object parseExtensions(Node node);

	public void writeExtensions(Node node, Document doc);

}
