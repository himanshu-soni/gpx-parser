package com.hs.gpxparser;

import com.hs.gpxparser.extension.DummyExtensionParser;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hs.gpxparser.extension.IExtensionParser;
import com.hs.gpxparser.modal.Bounds;
import com.hs.gpxparser.modal.Copyright;
import com.hs.gpxparser.modal.Email;
import com.hs.gpxparser.modal.GPX;
import com.hs.gpxparser.modal.Link;
import com.hs.gpxparser.modal.Metadata;
import com.hs.gpxparser.modal.Person;
import com.hs.gpxparser.modal.Route;
import com.hs.gpxparser.modal.Track;
import com.hs.gpxparser.modal.TrackSegment;
import com.hs.gpxparser.modal.Waypoint;
import com.hs.gpxparser.type.Fix;
import java.util.regex.Pattern;
import javax.xml.XMLConstants;

/**
 * <p>
 * This class defines methods for parsing and writing gpx files.
 * </p>
 * <br>
 * Usage for parsing a gpx file into a {@link GPX} object:<br>
 * <code>
 * GPXParser p = new GPXParser();<br>
 * FileInputStream in = new FileInputStream("inFile.gpx");<br>
 * GPX gpx = p.parseGPX(in);<br>
 * </code> <br>
 * Usage for writing a {@link GPX} object to a file:<br>
 * <code>
 * GPXParser p = new GPXParser();<br>
 * FileOutputStream out = new FileOutputStream("outFile.gpx");<br>
 * p.writeGPX(gpx, out);<br>
 * out.close();<br>
 * </code>
 */
public class GPXParser extends BaseGPX {
    
    // TFE, 20180109: use pattern for parsing to improve performance
    private final static Pattern datevaluePattern = Pattern.compile("([0-9\\-T]+:[0-9]{2}:[0-9.+]+):([0-9]{2})");

	/**
	 * Parses a stream containing GPX data
	 *
	 * @param in
	 *            the input stream
	 * @return {@link GPX} object containing parsed data, or null if no gpx data
	 *         was found in the seream
	 * @throws Exception
	 */
	public GPX parseGPX(InputStream in) throws Exception {
                // TFE, 20180217: add default parser if none set
                if (this.extensionParsers.isEmpty()) {
                    this.extensionParsers.add(new DummyExtensionParser());
                }
            
                // TFE, 20190905: fix for issue #12
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(in);
		Node firstChild = doc.getFirstChild();
		if (firstChild != null && GPXConstants.NODE_GPX.equals(firstChild.getNodeName())) {
			GPX gpx = new GPX();

			NamedNodeMap attrs = firstChild.getAttributes();

			for (int idx = 0; idx < attrs.getLength(); idx++) {
				Node attr = attrs.item(idx);
				if (GPXConstants.ATTR_VERSION.equals(attr.getNodeName())) {
					gpx.setVersion(attr.getNodeValue());
				} else if (GPXConstants.ATTR_CREATOR.equals(attr.getNodeName())) {
					gpx.setCreator(attr.getNodeValue());
                                // TFE, 20180201: support multiple xmlns attributes
				} else if (attr.getNodeName().startsWith(GPXConstants.ATTR_XMLNS)) {
					gpx.addXmlns(attr.getNodeName(), attr.getNodeValue());
                                }
			}

			NodeList nodes = firstChild.getChildNodes();
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node currentNode = nodes.item(idx);
				if (GPXConstants.NODE_METADATA.equals(currentNode.getNodeName())) {
					Metadata m = this.parseMetadata(currentNode);
					if (m != null) {
						gpx.setMetadata(m);
					}
				} else if (GPXConstants.NODE_WPT.equals(currentNode.getNodeName())) {
					Waypoint w = this.parseWaypoint(currentNode);
					if (w != null) {
						gpx.addWaypoint(w);
					}
				} else if (GPXConstants.NODE_RTE.equals(currentNode.getNodeName())) {
					Route rte = this.parseRoute(currentNode);
					if (rte != null) {
						gpx.addRoute(rte);
					}
				} else if (GPXConstants.NODE_TRK.equals(currentNode.getNodeName())) {
					Track trk = this.parseTrack(currentNode);
					if (trk != null) {
						gpx.addTrack(trk);
					}
				} else if (GPXConstants.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					for (IExtensionParser parser : this.extensionParsers) {
						Object data = parser.parseExtensions(currentNode);
						gpx.addExtensionData(parser.getId(), data);
					}
				}
			}
			return gpx;
		} else {
			throw new IllegalAccessException("Not a valid GPX file.");
		}
	}

	private Metadata parseMetadata(Node node) throws DOMException, ParseException {
		if (node == null) {
			return null;
		}

		Metadata m = new Metadata();

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstants.NODE_NAME.equals(currentNode.getNodeName())) {
					m.setName(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_DESC.equals(currentNode.getNodeName())) {
					m.setDesc(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_AUTHOR.equals(currentNode.getNodeName())) {
					Person author = this.parsePerson(currentNode);
					if (author != null) {
						m.setAuthor(author);
					}
				} else if (GPXConstants.NODE_COPYRIGHT.equals(currentNode.getNodeName())) {
					Copyright copyright = this.parseCopyright(currentNode);
					if (copyright != null) {
						m.setCopyright(copyright);
					}
				} else if (GPXConstants.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = this.parseLink(currentNode);
					if (link != null) {
						m.addLink(link);
					}
				} else if (GPXConstants.NODE_TIME.equals(currentNode.getNodeName())) {
					m.setTime(this.getNodeValueAsDate(currentNode));
				} else if (GPXConstants.NODE_KEYWORDS.equals(currentNode.getNodeName())) {
					m.setKeywords(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_BOUNDS.equals(currentNode.getNodeName())) {
					Bounds bounds = this.parseBounds(currentNode);
					if (bounds != null) {
						m.setBounds(bounds);
					}
				} else if (GPXConstants.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<IExtensionParser> it = this.extensionParsers.iterator();
					while (it.hasNext()) {
						IExtensionParser parser = it.next();
						Object data = parser.parseExtensions(currentNode);
						m.addExtensionData(parser.getId(), data);
					}
				}
			}
		}
		return m;
	}

	private Person parsePerson(Node node) {
		if (node == null) {
			return null;
		}

		Person p = new Person();
		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstants.NODE_NAME.equals(currentNode.getNodeName())) {
					p.setName(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_EMAIL.equals(currentNode.getNodeName())) {
					Email email = this.parseEmail(currentNode);
					if (email != null) {
						p.setEmail(email);
					}
				} else if (GPXConstants.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = this.parseLink(currentNode);
					if (link != null) {
						p.setLink(link);
					}
				}
			}
		}

		return p;
	}

	private Copyright parseCopyright(Node node) {
		if (node == null) {
			return null;
		}

		Copyright c = new Copyright(null);

		NamedNodeMap attrs = node.getAttributes();

		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstants.ATTR_AUTHOR.equals(attr.getNodeName())) {
				c.setAuthor(attr.getNodeValue());
			}
		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstants.NODE_YEAR.equals(currentNode.getNodeName())) {
					c.setYear(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_LICENSE.equals(currentNode.getNodeName())) {
					c.setLicense(this.getNodeValueAsString(currentNode));
				}
			}
		}

		return c;
	}

	private Link parseLink(Node node) {
		if (node == null) {
			return null;
		}

		Link l = new Link(null);
		NamedNodeMap attrs = node.getAttributes();

		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstants.ATTR_HREF.equals(attr.getNodeName())) {
				l.setHref(attr.getNodeValue());
			}
		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstants.NODE_TEXT.equals(currentNode.getNodeName())) {
					l.setText(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_TYPE.equals(currentNode.getNodeName())) {
					l.setType(this.getNodeValueAsString(currentNode));
				}
			}
		}

		return l;
	}

	private Bounds parseBounds(Node node) {
		if (node == null) {
			return null;
		}

		Bounds b = new Bounds(0, 0, 0, 0);

		NamedNodeMap attrs = node.getAttributes();

		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstants.ATTR_MINLAT.equals(attr.getNodeName())) {
				b.setMinLat(Double.parseDouble(attr.getNodeValue()));
			} else if (GPXConstants.ATTR_MINLON.equals(attr.getNodeName())) {
				b.setMinLon(Double.parseDouble(attr.getNodeValue()));
			} else if (GPXConstants.ATTR_MAXLAT.equals(attr.getNodeName())) {
				b.setMaxLat(Double.parseDouble(attr.getNodeValue()));
			} else if (GPXConstants.ATTR_MAXLON.equals(attr.getNodeName())) {
				b.setMaxLon(Double.parseDouble(attr.getNodeValue()));
			}
		}

		return b;
	}

	private Email parseEmail(Node node) {
		if (node == null) {
			return null;
		}

		Email e = new Email(null, null);

		NamedNodeMap attrs = node.getAttributes();
		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstants.ATTR_ID.equals(attr.getNodeName())) {
				e.setId(attr.getNodeValue());
			} else if (GPXConstants.ATTR_DOMAIN.equals(attr.getNodeName())) {
				e.setDomain(attr.getNodeValue());
			}
		}

		return e;
	}

	private Waypoint parseWaypoint(Node node) throws Exception {
		if (node == null) {
			return null;
		}
		Waypoint w = new Waypoint(0, 0);
		NamedNodeMap attrs = node.getAttributes();
		// check for lat attribute
		Node latNode = attrs.getNamedItem(GPXConstants.ATTR_LAT);
		if (latNode != null) {
			Double latVal = null;
			latVal = Double.parseDouble(latNode.getNodeValue());
			w.setLatitude(latVal);
		} else {
			throw new Exception("no lat value in waypoint data.");
		}
		// check for lon attribute
		Node lonNode = attrs.getNamedItem(GPXConstants.ATTR_LON);
		if (lonNode != null) {
			Double lonVal = Double.parseDouble(lonNode.getNodeValue());
			w.setLongitude(lonVal);
		} else {
			throw new Exception("no lon value in waypoint data.");
		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstants.NODE_ELE.equals(currentNode.getNodeName())) {
					w.setElevation(this.getNodeValueAsDouble(currentNode));
				} else if (GPXConstants.NODE_TIME.equals(currentNode.getNodeName())) {
					w.setTime(this.getNodeValueAsDate(currentNode));
				} else if (GPXConstants.NODE_MAGVAR.equals(currentNode.getNodeName())) {
					w.setMagneticVariation(this.getNodeValueAsDouble(currentNode));
				} else if (GPXConstants.NODE_GEOIDHEIGHT.equals(currentNode.getNodeName())) {
					w.setGeoIdHeight(this.getNodeValueAsDouble(currentNode));
				} else if (GPXConstants.NODE_NAME.equals(currentNode.getNodeName())) {
					w.setName(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_CMT.equals(currentNode.getNodeName())) {
					w.setComment(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_DESC.equals(currentNode.getNodeName())) {
					w.setDescription(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_SRC.equals(currentNode.getNodeName())) {
					w.setSrc(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = this.parseLink(currentNode);
					if (link != null) {
						w.addLink(link);
					}
				} else if (GPXConstants.NODE_SYM.equals(currentNode.getNodeName())) {
					w.setSym(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_TYPE.equals(currentNode.getNodeName())) {
					w.setType(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_FIX.equals(currentNode.getNodeName())) {
					w.setFix(this.getNodeValueAsFixType(currentNode));
				} else if (GPXConstants.NODE_SAT.equals(currentNode.getNodeName())) {
					w.setSat(this.getNodeValueAsInteger(currentNode));
				} else if (GPXConstants.NODE_HDOP.equals(currentNode.getNodeName())) {
					w.setHdop(this.getNodeValueAsDouble(currentNode));
				} else if (GPXConstants.NODE_VDOP.equals(currentNode.getNodeName())) {
					w.setVdop(this.getNodeValueAsDouble(currentNode));
				} else if (GPXConstants.NODE_PDOP.equals(currentNode.getNodeName())) {
					w.setPdop(this.getNodeValueAsDouble(currentNode));
				} else if (GPXConstants.NODE_AGEOFGPSDATA.equals(currentNode.getNodeName())) {
					w.setAgeOfGPSData(this.getNodeValueAsDouble(currentNode));
				} else if (GPXConstants.NODE_DGPSID.equals(currentNode.getNodeName())) {
					w.setdGpsStationId(this.getNodeValueAsInteger(currentNode));
				} else if (GPXConstants.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<IExtensionParser> it = this.extensionParsers.iterator();
					while (it.hasNext()) {
						IExtensionParser parser = it.next();
						Object data = parser.parseExtensions(currentNode);
						w.addExtensionData(parser.getId(), data);
					}
				}
			}
		}

		return w;
	}

	private Route parseRoute(Node node) throws Exception {
		if (node == null) {
			return null;
		}
		Route rte = new Route();
		NodeList nodes = node.getChildNodes();
		if (nodes != null) {
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node currentNode = nodes.item(idx);
				if (GPXConstants.NODE_NAME.equals(currentNode.getNodeName())) {
					rte.setName(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_CMT.equals(currentNode.getNodeName())) {
					rte.setComment(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_DESC.equals(currentNode.getNodeName())) {
					rte.setDescription(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_SRC.equals(currentNode.getNodeName())) {
					rte.setSrc(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = this.parseLink(currentNode);
					if (link != null) {
						rte.addLink(link);
					}
				} else if (GPXConstants.NODE_NUMBER.equals(currentNode.getNodeName())) {
					rte.setNumber(this.getNodeValueAsInteger(currentNode));
				} else if (GPXConstants.NODE_TYPE.equals(currentNode.getNodeName())) {
					rte.setType(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<IExtensionParser> it = this.extensionParsers.iterator();
					while (it.hasNext()) {
						while (it.hasNext()) {
							IExtensionParser parser = it.next();
							Object data = parser.parseExtensions(currentNode);
							rte.addExtensionData(parser.getId(), data);
						}
					}
				} else if (GPXConstants.NODE_RTEPT.equals(currentNode.getNodeName())) {
					Waypoint wp = this.parseWaypoint(currentNode);
					if (wp != null) {
						rte.addRoutePoint(wp);
					}
				}
			}
		}

		return rte;
	}

	private Track parseTrack(Node node) throws Exception {
		if (node == null) {
			return null;
		}
		Track trk = new Track();
		NodeList nodes = node.getChildNodes();
		if (nodes != null) {
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node currentNode = nodes.item(idx);
				if (GPXConstants.NODE_NAME.equals(currentNode.getNodeName())) {
					trk.setName(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_CMT.equals(currentNode.getNodeName())) {
					trk.setComment(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_DESC.equals(currentNode.getNodeName())) {
					trk.setDescription(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_SRC.equals(currentNode.getNodeName())) {
					trk.setSrc(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = this.parseLink(currentNode);
					if (link != null) {
						trk.addLink(link);
					}
				} else if (GPXConstants.NODE_NUMBER.equals(currentNode.getNodeName())) {
					trk.setNumber(this.getNodeValueAsInteger(currentNode));
				} else if (GPXConstants.NODE_TYPE.equals(currentNode.getNodeName())) {
					trk.setType(this.getNodeValueAsString(currentNode));
				} else if (GPXConstants.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<IExtensionParser> it = this.extensionParsers.iterator();
					while (it.hasNext()) {
						while (it.hasNext()) {
							IExtensionParser parser = it.next();
							Object data = parser.parseExtensions(currentNode);
							trk.addExtensionData(parser.getId(), data);
						}
					}
				} else if (GPXConstants.NODE_TRKSEG.equals(currentNode.getNodeName())) {
					TrackSegment trackSeg = this.parseTrackSegment(currentNode);
					if (trackSeg != null) {
						trk.addTrackSegment(trackSeg);
					}
				}
			}
		}

		return trk;
	}

	private TrackSegment parseTrackSegment(Node node) throws Exception {
		if (node == null) {
			return null;
		}

		TrackSegment ts = new TrackSegment();
		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstants.NODE_TRKPT.equals(currentNode.getNodeName())) {
					Waypoint w = this.parseWaypoint(currentNode);
					if (w != null) {
						ts.addWaypoint(w);
					}
				} else if (GPXConstants.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<IExtensionParser> it = this.extensionParsers.iterator();
					while (it.hasNext()) {
						while (it.hasNext()) {
							IExtensionParser parser = it.next();
							Object data = parser.parseExtensions(currentNode);
							ts.addExtensionData(parser.getId(), data);
						}
					}
				}
			}
		}

		return ts;
	}

	private Date getNodeValueAsDate(Node node) throws DOMException, ParseException {
		Date val = null;
		try {
                    // TFE, 20180109: use pre-compiled pattern instead of String.replaceAll
//			val = xmlDateFormat.parse(node.getFirstChild().getNodeValue()
//					.replaceAll("([0-9\\-T]+:[0-9]{2}:[0-9.+]+):([0-9]{2})", "$1$2"));
                    // TFE, 20180223: allow for errors with missing "Z" at the end of the string...
                    String nodeValue = node.getFirstChild().getNodeValue();
                    if (!nodeValue.endsWith("Z")) {
                        nodeValue += "Z";
                    }
                    // TFE, 20181105: add support for milli seconds during read
                    final String parseValue = datevaluePattern.matcher(nodeValue).replaceAll("$1$2");
                    switch (parseValue.length()) {
                        case xmlDateFormatWithMilliAndZone_length:
                            val = xmlDateFormatWithMilliAndZone.parse(parseValue);
                            break;
                        case xmlDateFormatWithMilli_length:
                            val = xmlDateFormatWithMilli.parse(parseValue);
                            break;
                        case xmlDateFormatAndZone_length:
                            val = xmlDateFormatAndZone.parse(parseValue);
                            break;
                        default:
                            val = xmlDateFormat.parse(parseValue);
                    }
//                    if (parseValue.length() == xmlDateFormatWithMilli_length) {
//                        val = xmlDateFormatWithMilli.parse(parseValue);
//                    } else {
//                        val = xmlDateFormat.parse(parseValue);
//                    }
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	private Double getNodeValueAsDouble(Node node) {
		return Double.parseDouble(node.getFirstChild().getNodeValue());
	}

	private Fix getNodeValueAsFixType(Node node) {
		return Fix.returnType(node.getFirstChild().getNodeValue());
	}

	private Integer getNodeValueAsInteger(Node node) {
		return Integer.parseInt(node.getFirstChild().getNodeValue());
	}

	private String getNodeValueAsString(Node node) {
		if (node == null) {
			return null;
		}

		Node child = node.getFirstChild();
		if (child == null) {
			return null;
		}
		return child.getNodeValue();
	}

}