package com.hs.gpxparser.extension;

import com.hs.gpxparser.modal.Extension;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface IExtensionParser {

	public String getId();

	public Object parseExtensions(Node node);

        // TFE, 20180216: can't write any data out if the node hasn't been passed as well!
	public void writeExtensions(Extension e, Node node, Document doc);

}
