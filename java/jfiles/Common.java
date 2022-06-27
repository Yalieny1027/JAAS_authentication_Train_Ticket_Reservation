package jfiles;

import java.util.ArrayList;
import java.util.List;


public class Common {
	
    private String source,destination,trainNo,departTime,first,second,economy,AdminName,AdminPass,availableTickets,totalPrice,classHere,numTic;
    public String admin_name,admin_pass;
    public String train_source,train_destination,train_no,train_time,train_cost1,train_cost2,train_cost3,train_availtic,numtic;    
    public static String adminname,adminpassword,uname,username,userpassword,userage,usergender,userphone,trainsource,traindestination,traintrainno,traindeparttime,trainfirst,trainsecond,traineconomy,trainavailabletic,trainclass,trainnumtic;
    public static int num=0;
    public static String adsuccess,ussuccess,ussignsuccess,adsignsuccess,addtrainsuccess,bookticsuccess,success;
    public static String user_gender,user_pass,user_phone,user_age,total_Price;
    public static String user_name = "",cipherString,rl;
    public static byte[] encKey = new byte[]{-32, 74, 61, 28, -125, 118, -124, 85, 37, -23, 88, 110, 32, -76, -55, 74};
    public static ArrayList<String> traindata = new ArrayList<String>();
    public static ArrayList<String> userdata = new ArrayList<String>();
    public static ArrayList<String> allticdata = new ArrayList<String>();
    public static ArrayList<String> yourticdata = new ArrayList<String>();
    public static String grole,namec;
    
    public String getNumTic(){
        return numTic;
    }
    public String getClassHere(){
        return classHere;
    }
    public String getTotalPrice(){
        return totalPrice;
    }
    public String getAdminName() {
        return AdminName;
    }
    public String getAdminPass() {
        return AdminPass;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public String getTrainNo() {
        return trainNo;
    }
    public String getDepartTime() {
        return departTime;
    }
    public String getFirst() {
        return first;
    }
    public String getSecond() {
        return second;
    }
    public String getEconomy() {
        return economy;
    }
    public String getAvailableTickets() {
        return availableTickets;
    }
    public String getName() {
        return uname;
    }
    public String getAge() {
        return userage;
    }
    public String getGender() {
        return usergender;
    }
    public String getPhone() {
        return userphone;
    }
    public String getpassword() {
        return userpassword;
    }
    public String getGRole() {
        return grole;
    }
    public String getNamec() {
        return namec;
    }
    
    public void setNamec(String namec) {
        Common.namec = namec;
    }
    public void setGRole(String grole) {
        Common.grole = grole;
    }
    public void setNumTic(String numTic) {
        this.numTic = numTic;
    }
    public void setClassHere(String classHere){
        this.classHere = classHere;
    }
    public void setTotalPrice(String totalPrice){
        this.totalPrice = totalPrice;
    }
    public void setAdminname(String adminname) {
        this.AdminName = adminname;
    }
    public void setAdminpass(String adminpass) {
        this.AdminPass = adminpass;
    }
    public void setName(String name) {
        Common.uname = name;
    }
    public void setAge(String age) {
        Common.userage = age;
    }
    public void setGender(String gender) {
        Common.usergender = gender;
    }
    public void setPhone(String phone) {
        Common.userphone = phone;
    }
    public void setPassword(String password) {
        Common.userpassword = password;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }
    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }
    public void setFirst(String first) {
        this.first = first;
    }
    public void setSecond(String second) {
        this.second = second;
    }
    public void setEconomy(String economy) {
        this.economy = economy;
    }
    public void setAvailableTickets(String availableTickets) {
        this.availableTickets = availableTickets;
    }
public class Cookies{
    private String cname,cage,cgender,cphone;
    public List<Cookies> cookies = new ArrayList<Cookies>();
    public Cookies(String cname,String cage,String cphone, String cgender){
        this.cname=cname;
        this.cage=cage;
        this.cphone=cphone;
        this.cgender=cgender;
    }
    public String getcname(){
        return cname;
    }
    public String getcage(){
        return cage;
    }
    public String getcgender(){
        return cgender;
    }
    public String getcphone(){
        return cphone;
    }
    public void setcname(String cname){
        this.cname = cname;
    }
    public void setcage(String cage){
        this.cage = cage;
    }
    public void setcgender(String cgender){
        this.cgender = cgender;
    }
    public void setcphone(String cphone){
        this.cphone = cphone;
    }
}
}