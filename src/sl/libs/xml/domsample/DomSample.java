/*********************************
 * REF=> http://www.fireproject.jp/feature/xml/programing/java-dom.html
 *********************************/
package sl.libs.xml.domsample;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class DomSample {

    protected Document doc;
    protected int tab;

    public DomSample(String filename){
	try{
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db=dbf.newDocumentBuilder();
	    /* XML文書を読み込む.*/
	    doc=db.parse(new FileInputStream(filename));
	}catch(Exception e){
	    e.printStackTrace();
	}
	tab=0;
    }

    public DomSample(String uri, String keyWord) {
    	
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = null;
	    
		try {
			
			db = dbf.newDocumentBuilder();
			
		} catch (ParserConfigurationException e) {
			
			// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());

			doc = null;
			
		}
	    /* XML文書を読み込む.*/
	    
	    String url = uri + "/" + keyWord;
	    
	    URL u = null;
	    
		try {
			
			u = new URL(url);
			
		} catch (MalformedURLException e) {
			
			// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());

			doc = null;
			
		}
	    
		if (u != null && db != null) {
			
			try {
				
				doc=db.parse(u.openConnection().getInputStream());
				
			} catch (SAXException e) {
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {//if (u != null)
			
			// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "u == null");
			
		}//if (u != null)
		
    }//public DomSample(String uri, String keyWord)
    
    public static DomSample getDomSampleFromUri(String uri, String keyWord) {
    	
    	return new DomSample(uri, keyWord);
    }
    
    public void getNodeInfo(Node node){
		
    	tabbing();
		
		/* ノードの種類を出力 */
		// Log
		Log.d("DomSample.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Node type= "+node.getNodeType());
		
		/* ノード名を出力 */
		tabbing();
		// Log
		Log.d("DomSample.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Node name= "+node.getNodeName());
				
		/* ノードの値を出力 */

		// Log
		Log.d("DomSample.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Node value= "+node.getNodeValue());
		
    }//public void getNodeInfo(Node node)

    /* 全ノードを探索 */
    public void walkThrough(){
    	
    	if (doc == null) {
			
    		// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "doc => null");
			
			return;
    		
		}//if (doc == null)
    	
		Node root=doc.getDocumentElement();
		recursiveWalk(root);
    }

    private void recursiveWalk(Node node){
		/*
		これは,XML文書のインデントなどの空白のノードを読み飛ばすための処理.
		Node.TEXT_NODE ノードがテキストで,ノードの値の空白を除いた文字列の長さが0の場合は読み飛ばす.
		*/
		   
		if(node.getNodeType()==Node.TEXT_NODE && node.getNodeValue().trim().length()==0){
		    return;
		}
		getNodeInfo(node);
		tab++;
		/* node.getFirstChild : nodeの最初の子を得る */
		/* child.getNextSibling : childの兄弟ノードを得る */
		for(Node child=node.getFirstChild();child!=null;child=child.getNextSibling()){
		    recursiveWalk(child);
		}
		
		tab--;
		
    }//private void recursiveWalk(Node node)

    protected void tabbing(){
	for(int i=0;i<tab;i++){
	    System.out.print("\t");
	}
    }

//    public static void main(String args[]){
////		DomSample ds=new DomSample("sample.xml");
////    	DomSample ds=new DomSample("dat/sample.xml");
//    	DomSample ds=new DomSample("dat/furigana.xml");
//		ds.walkThrough();
//    }
}

//Node type= 1
//Node name= ResultSet
//Node value= null
//	Node type= 1
//	Node name= Result
//	Node value= null
//		Node type= 1
//		Node name= WordList
//		Node value= null
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= 洗濯
//				Node type= 1
//				Node name= Furigana
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= せんたく
//				Node type= 1
//				Node name= Roman
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= sentaku
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= 網
//				Node type= 1
//				Node name= Furigana
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= もう
//				Node type= 1
//				Node name= Roman
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= mou
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= （
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= 中
//				Node type= 1
//				Node name= Furigana
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= なか
//				Node type= 1
//				Node name= Roman
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= naka
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= ）
