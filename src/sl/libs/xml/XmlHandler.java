/*********************************
 * 20130214_140339
 * REF=> http://axbeak.blog60.fc2.com/blog-entry-287.html
 *********************************/
package sl.libs.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class XmlHandler {
	 
//	private final String filename;
	private String filename;

	private Document doc;
	/**
	* コンストラクタ
	* @param filename 読込ファイルパス
	*/
	public XmlHandler(String filename) {
		this.filename = filename;
	}

	public XmlHandler() {
//		this.filename = filename;
	}

	/**
	* XMLファイルの内容を表示する
	* @throws SAXException
	* @throws IOException
	* @throws ParserConfigurationException
	*/
	public void showXml() throws SAXException, IOException,
				ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().parse(new File(filename));
		showChildNodes(doc.getChildNodes(), 0);
	}

	public void
	showXml(InputStream is)
	throws SAXException, IOException, ParserConfigurationException {
		
		Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
		showChildNodes(doc.getChildNodes(), 0);
		
	}//showXml(InputStream is)

	public void
//	showXml(String xmlString)
	showXml(String uri)
	throws SAXException, IOException, ParserConfigurationException {
		
		// Log
		Log.d("XmlHandler.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Entering...");
		
		Document doc = DocumentBuilderFactory.newInstance()
//					.newDocumentBuilder().parse(xmlString);
					.newDocumentBuilder().parse(uri);
		showChildNodes(doc.getChildNodes(), 0);
		
		// Log
		Log.d("XmlHandler.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Exiting...");
		
	}//showXml(InputStream is)

	/**
	* XMLの要素を取り出しつつ表示する
	* @param nodeList
	* @param level
	*/
	private void showChildNodes(NodeList nodeList, int level) {
		if (nodeList == null) {
				return;
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.TEXT_NODE) {
						String text = node.getNodeValue().trim();
						if (!text.isEmpty()) {
//								System.out.println(String.format("%sText:%s",
//												indent(level), text));
								
								String message = String.format("%sText:%s",
										indent(level), text);
								// Log
								Log.d("XmlHandler.java"
										+ "["
										+ Thread.currentThread()
												.getStackTrace()[2]
												.getLineNumber()
										+ ":"
										+ Thread.currentThread()
												.getStackTrace()[2]
												.getMethodName() + "]",
										message);
						}
						continue;
				}
				if (!(node instanceof Element)) {
						continue;
				}

						Element element = (Element) node;
						String tagName = element.getTagName();
						String attributes = getAttributeStrings(element.getAttributes());
//						System.out.println(String.format(
//										"%sTagName:%s, Attributes:[%s]", indent(level), tagName,
//										attributes));
						String message = String.format(
								"%sTagName:%s, Attributes:[%s]", indent(level), tagName,
								attributes);
						// Log
						Log.d("XmlHandler.java"
								+ "["
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getLineNumber()
								+ ":"
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getMethodName() + "]",
								message);


						showChildNodes(element.getChildNodes(), level + 1);
		}//for (int i = 0; i < nodeList.getLength(); i++)
		 
	}//private void showChildNodes(NodeList nodeList, int level)

			/**
	* Attributesの内容を文字列で取得
	* @param attributes
	* @return Attributesの文字列
	*/
	private String getAttributeStrings(NamedNodeMap attributes) {
		if (attributes == null) {
				return "";
				}

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < attributes.getLength(); i++) {
						Node node = attributes.item(i);
						String nodeName = node.getNodeName();
						String nodeValue = node.getNodeValue();
						sb.append(nodeName).append("=").append(nodeValue);

						if (i < attributes.getLength() - 1) {
								sb.append(", ");
						}
				}

				return sb.toString();
				 
	}//private String getAttributeStrings(NamedNodeMap attributes)

	/**
	* インデント作成
	* @param level
	* @return インデント文字列
	*/
	public static String indent(int level) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < level; i++) {
			sb.append('\t');
		}
		return sb.toString();

	}//public static String indent(int level)

	public Document getDoc() {
		return doc;
	}

	public Document getDoc(String uri) {
		
		try {
			
			return DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(uri);
			
		} catch (SAXException e) {
			
			// Log
			Log.d("XmlHandler.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {
			
			// Log
			Log.d("XmlHandler.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (ParserConfigurationException e) {
			
			// Log
			Log.d("XmlHandler.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
//		return doc;
	}//public Document getDoc(String uri)
	
}//public class XmlHandler
