package com.hs.gpxparser.extension;

import com.hs.gpxparser.GPXConstants;
import com.hs.gpxparser.modal.Extension;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DummyExtensionParser implements IExtensionParser {
    @Override
    public String getId() {
            return "Basic Extension Parser";
    }

    @Override
    public Object parseExtensions(Node node) {
        // store all nodes under extension in DummyExtensionHolder - if any
        if (GPXConstants.NODE_EXTENSIONS.equals(node.getNodeName()) && (node.getChildNodes().getLength() > 0)) {
            return new DummyExtensionHolder(node.getChildNodes());
        } else {
            return null;
        }
    }

    @Override
    public void writeExtensions(Extension e, Node node, Document doc) {
        if(e.getExtensionData(getId()) != null) {
            // add all nodes from DummyExtensionHolder to the document
            final DummyExtensionHolder holder = (DummyExtensionHolder) e.getExtensionData(getId());
            final NodeList nodes = holder.getNodeList();
            
            // https://stackoverflow.com/questions/5786936/create-xml-document-using-nodelist
            for (int i = 0; i < nodes.getLength(); i++) {
                final Node extNode = nodes.item(i);
                final Node copyNode = doc.importNode(extNode, true);
                node.appendChild(copyNode);
            }
        }
    }
}
