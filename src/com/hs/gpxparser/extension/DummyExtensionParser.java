package com.hs.gpxparser.extension;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DummyExtensionParser implements IExtensionParser {

	@Override
	public String getId() {
		return "Basic Extension Parser";
	}

	@Override
	public Object parseExtensions(Node node) {
		// TODO get your object from the node
		return null;
	}

	@Override
	public void writeExtensions(Node node, Document doc) {
		// TODO write your object to node
	}
}
