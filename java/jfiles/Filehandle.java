package jfiles;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import jfiles.Metadata.Fileh;

public class Filehandle implements Intermediary{
    
    Metadata metadata = new Metadata();
    Metadata.Fileh fil = metadata.new Fileh();
 	Common c = new Common();
 	
    
    //*****user******
    public int userdatafile(String user_name,String user_age,String user_gender,String user_phone,String user_pass){
        logindet();
        fil.readxml();
        
        File file = new File(fil.userpath());
        FileWriter fWriter;
        try{
            file.createNewFile();
            fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(String.format("%-20s %-20s %-20s %-20s %-20s\n", user_name, user_age ,user_gender ,user_phone ,user_pass ));
            bWriter.flush();
            bWriter.close();
            return 1;
        }catch(IOException e){
            e.printStackTrace();
        }
        return 0;

    }

    public void logindet(){
        fil.readxml();
        Common c = new Common();
        File file = new File(fil.logindetpath());
        FileWriter fWriter;
        try{
            file.createNewFile();
            fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(String.format("%-20s %-20s\n", c.getNamec() , c.getGRole()));
            bWriter.flush();
            bWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void getRole(){
        BufferedReader br = null;
        try{	
        fil.readxml();
            br = new BufferedReader(new FileReader(fil.logindetpath()));		
            int c=0;
            String contentLine = br.readLine();
            
            while (contentLine != null) {
            if ((contentLine.contains(Common.username))) {
                c++;
                int i=0;
            String a[]= new String[2];
            StringTokenizer st=new StringTokenizer(contentLine," ");
            while(st.hasMoreTokens())
            {   
            String key = st.nextToken();
            a[i]=key;
            i++; 
            }
            Common.user_name=a[0];
            Common.rl=a[1];
            System.out.println(Common.rl);
            }
            contentLine = br.readLine();
         }	
        if(c == 0){
        }
    }   catch (IOException ioe) 
        {
        ioe.printStackTrace();
        }
        finally 
        {
        try {
           if (br != null)
          br.close();
        } 
        catch (IOException ioe) {}
     }
    }
    public int user_read(String name,String pass) throws Exception{
        BufferedReader br = null;
        try{	

        fil.readxml();
        
            br = new BufferedReader(new FileReader(fil.userpath()));		
            int c=0;
            String contentLine = br.readLine();
            
            while (contentLine != null) {
            if ((contentLine.contains(name)) && (contentLine.contains(pass))) {
                c++;
                int i=0;
            String a[]= new String[5];
            StringTokenizer st=new StringTokenizer(contentLine," ");
            while(st.hasMoreTokens())
            {   
            String key = st.nextToken();
            a[i]=key;
            i++; 
            }
            Common.user_name=a[0];
            Common.user_age=a[1];
            Common.user_gender=a[2];
            Common.user_phone=a[3];
                return 1;
            }
            contentLine = br.readLine();
         }	
        if(c == 0){
            return 0;
        }
    }   catch (IOException ioe) 
        {
        ioe.printStackTrace();
        }
        finally 
        {
        try {
           if (br != null)
          br.close();
        } 
        catch (IOException ioe) {}
     }
     return 0;	
 } 
 public void userlogin(){
    try {
        if(user_read(Common.username,Common.cipherString)== 1){
            Common.ussuccess="1";
        }
        else{
            Common.ussuccess="0";
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void usersignup(){
try{
    if(Validation.isValidString(Common.username)== true && Validation.isValidAge(Common.userage)==true && Validation.isValidGender(Common.usergender)==true && Validation.isValidNumber(Common.userphone)==true )
    {
    if(userdatafile(Common.username, Common.userage,Common.usergender,Common.userphone,Common.cipherString)== 1){
        Common.ussignsuccess="1";
    }
    else{
        Common.ussignsuccess="0";
    }
}
else{
    Common.ussignsuccess="0";
}
}
catch(Exception w1)
{
}
}

    public void view_train(){
    try {
        fil.readxml();
        File textFile = new File(fil.trainpath());
        Scanner fileScanner = new Scanner(textFile);
        String[] strArray= new String[8];
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            strArray = line.split("\\s+");
            for(int i=0; i<strArray.length; i++) {
                Common.traindata.add(strArray[i]);
            }
        }
        fileScanner.close();
    } catch (Exception e) {
    }    
    }

    public void view_yourtic(){
        try {
            String fileName = Common.user_name + ".txt";
        File textFile = new File(fileName);
            Scanner fileScanner = new Scanner(textFile);
            String[] strArray= new String[9];
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                strArray = line.split("\\s+");
                for(int i=0; i<strArray.length; i++) {
                    // System.out.println(strArray[i]);
                    Common.yourticdata.add(strArray[i]);
                }
            }
            fileScanner.close();
            
        } catch (Exception e) {
        }    
    }

    //******admin*******

    public int admindatafile(){
        logindet();
        fil.readxml();
        File file = new File(fil.adminpath());
        FileWriter fWriter;
        try{
            file.createNewFile();
            fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(String.format("%-20s %-20s\n", Common.adminname,Common.cipherString ));
            bWriter.flush();
            bWriter.close();
            return 1;
        }catch(IOException e){
            e.printStackTrace();
        }
        return 0;
    }

     public int admin_read(String in_ad,String in_pa){
        BufferedReader br = null;
        try{	
            fil.readxml();
            int c=0;
            br = new BufferedReader(new FileReader(fil.adminpath()));
            String contentLine = br.readLine();
        while (contentLine != null) {
            if ((contentLine.contains(in_ad)) && (contentLine.contains(in_pa))) {
                c++;
                return 1;
            }
            contentLine = br.readLine();
        }
        if(c == 0){
            return 0;
        }
    }catch (IOException ioe) 
    {
    ioe.printStackTrace();
    }
    finally 
    {
    try {
       if (br != null)
      br.close();
    } 
    catch (IOException ioe) {}
 }
    return 0;	
} 


     public String[] anamepass() {
         String[] str = new String[2];
     	System.out.println("enter anamepass");
    	 Common admin = new Common();
    	    try{
    	        if(admin_read(Common.username, Common.cipherString)== 1){
                    str[0]=Common.username;
                    str[1]=Common.cipherString;
    	            Common.adsuccess="1";
                    return(str);
    	        }
    	        else{
    	            Common.adsuccess="0";
                    return(str);
    	        }
    	    }
    	    catch(Exception w1){
    	    }
            return(str);
    	    }
public void adminlogin(){
//    System.out.println("file");
    try{
        if(admin_read(Common.adminname, Common.cipherString)== 1){
            Common.adsuccess="1";
        }
        else{
            Common.adsuccess="0";
        }
    }
    catch(Exception w1)
    {
    // System.out.println(w1);	
    }
}

public void adminsignup(){
    try{
        if(Validation.isValidString(Common.adminname)== true)
        {
        if(admindatafile()== 1){
            Common.adsignsuccess="1";
        }
        else{
            Common.adsignsuccess="0";
        }
    }
    else{
        Common.adsignsuccess="0";
    }
    }
    catch(Exception w1)
    {
    }
}

    public int admintrainfile(String train_source,String train_destination,String train_no,String train_time,String train_cost1,String train_cost2,String train_cost3,String train_availtic){
        fil.readxml();
        File file = new File(fil.trainpath());
        FileWriter fWriter;
        try{
            file.createNewFile();
            fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(String.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",train_source,train_destination,train_no,train_time,train_cost1,train_cost2,train_cost3,train_availtic));
            bWriter.flush();
            bWriter.close();
            return 1;
        }catch(IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    public String[] unamepass() {
        String[] str = new String[2];
    	System.out.println("enter");
    	try {
            if(user_read(Common.username,Common.cipherString)== 1){
	            str[0]=Common.username;
                str[1]=Common.cipherString;
                Common.ussuccess="1";
                return(str);
            }
            else{
                Common.ussuccess="0";
                return(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return(str);         
    }
    public void view_user(){
        try {
            fil.readxml();
            File textFile = new File(fil.userpath());
            Scanner fileScanner = new Scanner(textFile);
            String[] strArray= new String[4];
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                strArray = line.split("\\s+");
                for(int i=0; i<4; i++) {
                    Common.userdata.add(strArray[i]);
                }
            }
            fileScanner.close();
            
        } catch (Exception e) {
        }    
        
    }

    public void addtrain(){

        try{
            if(Validation.isValidString(Common.trainsource)== true && Validation.isValidString(Common.traindestination)==true && Validation.isValidtrainNumber(Common.traintrainno)==true && Validation.isValidprice(Common.trainfirst)==true && Validation.isValidprice(Common.trainsecond)==true && Validation.isValidprice(Common.traineconomy)==true && Validation.isValidprice(Common.trainavailabletic)==true )
            {
            if(admintrainfile(Common.trainsource, Common.traindestination, Common.traintrainno, Common.traindeparttime, Common.trainfirst, Common.trainsecond, Common.traineconomy, Common.trainavailabletic) == 1){
                Common.addtrainsuccess="1";
                
            }
            else{
                Common.addtrainsuccess="0";
            }
        }
        }
        catch(Exception w1)
        {
        // System.out.println(w1);	
        }
    }
    //*******Book*********

    public void view_alltic(){
        try {
            fil.readxml();
            File textFile = new File(fil.ticketpath());
            Scanner fileScanner = new Scanner(textFile);
            String[] strArray= new String[9];
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                strArray = line.split("\\s+");
                for(int i=0; i<strArray.length; i++) {
                    // System.out.println(strArray[i]);
                    Common.allticdata.add(strArray[i]);
                }
            }
            fileScanner.close();
            
        } catch (Exception e) {
        }    
    }

    public void booktic(){
        if(Validation.isValidString(Common.trainsource)==true && Validation.isValidString(Common.traindestination)==true && Validation.isValidCla(Common.trainclass)==true && Validation.isValidprice(Common.trainnumtic)== true){
            try{
                if(train_check(Common.trainsource, Common.traindestination, Common.trainclass, Common.trainnumtic)== 1){
                    Common.bookticsuccess="1";
                }
                else{
                    Common.bookticsuccess="0";
                }
            }
            catch(Exception w1)
            {
            // System.out.println(w1);	
            }
        }
    }
    public int train_check(String src,String des,String cla,String tic){
        BufferedReader br = null;
        try{	
            int c=0;
            fil.readxml();
            br = new BufferedReader(new FileReader(fil.trainpath()));
            String contentLine = br.readLine();
            while (contentLine != null) {
                if ((contentLine.contains(src)) && (contentLine.contains(des))) {
                    c++;
                    tic_price(contentLine ,cla, tic);
                    return 1;
                }
                contentLine = br.readLine();
                }
            if(c == 0){
                return 0;
            }
    }
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
        finally 
    {
    try {
       if (br != null)
      br.close();
    } 
    catch (IOException ioe) {}
 }
        return 0;	
    }
    public void tic_price(String contentLine,String cla,String tic){
        String cost;
        int tot=0;
        int i=0;
        String a[]= new String[8];
        StringTokenizer st=new StringTokenizer(contentLine," ");
        while(st.hasMoreTokens())
        {   
            String key = st.nextToken();
            a[i]=key;
            i++; 
        } 
        if(cla.equals("F")){
            cost=a[4];
            tot =Integer.parseInt(cost)*Integer.parseInt(tic);
        }
        else if(cla.equals("S")){
            cost=a[5];
            tot =Integer.parseInt(cost)*Integer.parseInt(tic);
        }
        else if(cla.equals("E")){
            cost=a[6];
            tot =Integer.parseInt(cost)*Integer.parseInt(tic);
        }
        write_tick(tot, a[0],a[1],a[2],a[3]);
    }
    public void write_tick(Integer tot,String src,String des, String trno , String time){
        String total= tot.toString();
        String fileName = Common.user_name + ".txt";

        File file = new File(fileName);
        FileWriter fWriter;
        fil.readxml();
        File data = new File(fil.ticketpath());
        FileWriter fW;
    try{
        file.createNewFile();
        fW = new FileWriter(data, true);
        BufferedWriter bW = new BufferedWriter(fW);
        bW.write(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", Common.user_name,Common.user_age,Common.user_gender,Common.user_phone,src,des,trno,time,total));
        bW.flush();
        bW.close();
    }
    catch(IOException e){
        e.printStackTrace();
    }
    try{
        file.createNewFile();
        fWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.write(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", Common.user_name,Common.user_age,Common.user_gender,Common.user_phone,src,des,trno,time,total));
        bWriter.flush();
        bWriter.close();

    }catch(IOException e){
        e.printStackTrace();
    }
    }
    public void selected(){
//        System.out.println("file");
        // Train.lay();
    }
}
