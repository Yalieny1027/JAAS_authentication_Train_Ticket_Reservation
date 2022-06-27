package jfiles;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Metadata {
    public String adminpath,userpath,trainpath,ticketpath,logindetpath;
    public String[] path = new String[5];
    public String[] con = new String[4];
    public String[] inty= new String[10];
    public String selected;
    public String indexadmin,typeadmin,indexuser,typeuser,indextrain,typetrain,indexticket,typeticket,typeyourtic,indexlogindet,typelogindet;
    public class Fileh{
        void readxml(){
            try{
                File file = new File("D:\\Web_Train\\files\\metadata.xml"); 
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();  
                Document doc = db.parse(file);
                NodeList nodeList = doc.getElementsByTagName("file");  
                for (int itr = 0; itr < nodeList.getLength(); itr++)   
                {  
                    Node node = nodeList.item(itr);
                    Element eElement = (Element) node;
                    path[itr] = eElement.getElementsByTagName("path").item(0).getTextContent(); 
                }
               }catch(Exception e){System.out.println(e);}
        }
        String adminpath(){
            adminpath=path[2];
            return adminpath;
        }
        String userpath(){
            userpath=path[1];
            return userpath;
        }
        String trainpath(){
            trainpath=path[0];
            return trainpath;
        }
        String ticketpath(){
            ticketpath=path[3];
            return ticketpath;
        } 
        String logindetpath(){
            logindetpath=path[4];
            return logindetpath;
        }
    }
    public class Elas{
        void readxml(){

            try{
                File file = new File("D:\\Web_Train\\files\\metadata.xml"); 
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();  
                Document doc = db.parse(file);
                NodeList nodeList = doc.getElementsByTagName("elas");  
                Node n0 = nodeList.item(0);
                Element e0 = (Element) n0;
                for(int i=0; i<4;i++){
                    con[i]=e0.getElementsByTagName("con").item(i).getTextContent();
                }
                int k=0;
                for (int itr = 1; itr < nodeList.getLength()-1 ; itr++)   
                {
                Node n1 = nodeList.item(itr);
                Element e1 = (Element) n1;
                inty[k]=e1.getElementsByTagName("index").item(0).getTextContent();
                inty[++k]=e1.getElementsByTagName("type").item(0).getTextContent();
                k++;
                }
                Node n6 = nodeList.item(6);  
                Element e6 = (Element) n6;
                typeyourtic=e6.getElementsByTagName("type").item(0).getTextContent();

               }catch(Exception e){}

        }
        String[] connect(){
            return con;
        } 
        String indexadmin(){
            indexadmin=inty[0];
            return indexadmin;
        }
        String indexuser(){
            indexuser=inty[2];
            return indexuser;
        }
        String indextrain(){
            indextrain=inty[4];
            return indextrain;
        }
        String indexticket(){
            indexticket=inty[6];
            return indexticket;
        }
        String typeadmin(){
            typeadmin=inty[1];
            return typeadmin;
        }
        String typeuser(){
            typeuser=inty[3];
            return typeuser;
        }
        String typetrain(){
            typetrain=inty[5];
            return typetrain;
        }
        String typeticket(){
            typeticket=inty[7];
            return typeticket;
        }
        String indexlogindet(){
            indexlogindet=inty[8];
            return indexlogindet;
        }
        String typelogindet(){
            typelogindet=inty[9];
            return typelogindet;
        }
        String yourtic(){
            return typeyourtic;
        }
    }
    public class Selected{
    	public void readxml() {
    		try{
                File file = new File("D:\\Web_Train\\files\\metadata.xml"); 
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();  
                Document doc = db.parse(file);
                NodeList nodeList = doc.getElementsByTagName("selected");
                Node node = nodeList.item(0);
                Element eElement = (Element) node;
                selected = eElement.getElementsByTagName("path").item(0).getTextContent();
    		}catch(Exception e){  
    			System.out.println(e);
    		}
    		
    	}
    	public String select() {
    		return selected;
    		
    	}
    }
    
    
}
