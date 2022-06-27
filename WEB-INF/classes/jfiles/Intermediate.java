package jfiles;

import java.util.ArrayList;
import java.util.List;


interface Intermediary {
    public void booktic();
    public void view_alltic();
    public void view_user();
    public void view_train();
    public void view_yourtic();
    public void addtrain();
    public void usersignup();
    public void adminsignup();
    public void getRole();
    public String[] anamepass();
    public String[] unamepass();
    
}

public class Intermediate implements Intermediary {
    Filehandle file = new Filehandle();
    Elasticsearch elas = new Elasticsearch();
    Intermediary inter;
    Metadata metadata = new Metadata();
    Metadata.Selected sel = metadata.new Selected();
	

    
    public void selected() {
    	sel.readxml();
    	System.out.println(sel.select());
    	if(sel.select().equals("elas")) {
    		inter=elas;
    	}
    	else {
    		inter=file;
    		}
    }
        public void booktic() {
            inter.booktic();
        }
        public void view_alltic() {
            inter.view_alltic();
        }
        public void view_user() {
            inter.view_user();
        }
        public void view_train() {
            inter.view_train();
        }
        public void view_yourtic() {
            inter.view_yourtic();
        }
        public void addtrain() {
            inter.addtrain();
        }
        public void usersignup() {
            inter.usersignup();
        }
        public void adminsignup() {
            inter.adminsignup();
        }
        public void getRole(){
            inter.getRole();
        }
		public String[] anamepass() {
			return inter.anamepass();
		}
		public String[] unamepass() {
			return inter.unamepass();
		}

}
