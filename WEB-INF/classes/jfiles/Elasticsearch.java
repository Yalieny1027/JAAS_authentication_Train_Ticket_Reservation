package jfiles;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Elasticsearch implements Intermediary{
 	private static String[] connect = new String[4];
    private static int p1,p2;
    private static String indexuse,indexadm,indextra,indextic,typeuse,typeadm,typetra,typetic,indexlog,typelog;

    private static RestHighLevelClient restHighLevelClient = null;
    private static ObjectMapper objectMapper = new ObjectMapper();

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

    Metadata metadata = new Metadata();
    Metadata.Elas elas = metadata.new Elas();

    // OPEN CONNECTION
     private synchronized RestHighLevelClient makeConnection() {
        elas.readxml();
        connect=elas.connect();
        p1 = Integer.parseInt(connect[1]);
        p2 = Integer.parseInt(connect[2]);
        if(restHighLevelClient == null) {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(connect[0], p1, connect[3]),
                            new HttpHost(connect[0], p2, connect[3])));
        }
        return restHighLevelClient;
    }
    // CLOSE CONNECTION

    private synchronized void closeConnection() throws IOException {
        makeConnection();
        restHighLevelClient.close();
        restHighLevelClient = null;
    }

    // USER
    Common userlogin(Common common){
    	
        elas.readxml();
        indexuse=elas.indexuser();
        typeuse=elas.typeuser();
        GetRequest getPersonRequest = new GetRequest(indexuse, typeuse, common.username);
        GetResponse getResponse = null;
                try {
                    getResponse = restHighLevelClient.get(getPersonRequest, RequestOptions.DEFAULT);
                    return getResponse != null ?
                    objectMapper.convertValue(getResponse.getSourceAsMap(), Common.class) : null;
                } catch (java.io.IOException e){
                    e.getLocalizedMessage();
                    System.out.println("Getting io exception\n\n\n");
                }
        return common;
    }

    public void getRole(){
        elas.readxml();
        indexlog=elas.indexlogindet();
        typelog=elas.typelogindet();
        int n=0;String na=null,r=null;
        SearchRequest searchRequest = new SearchRequest(indexlog);
         SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
         sourceBuilder.query(QueryBuilders.matchAllQuery());
         sourceBuilder.from(0);
         sourceBuilder.size(1000);
         searchRequest.source(sourceBuilder);
         try {
             Common user = new Common();
             makeConnection();
             SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
             SearchHit[] searchHits = searchResponse.getHits().getHits();
             for (SearchHit searchHit : searchHits) {
             String hitJson = searchHit.getSourceAsString();
             ObjectMapper objectMapper = new ObjectMapper();
             Common source = objectMapper.readValue(hitJson, Common.class);
             if(source.getNamec().equals(Common.username)){
                 na=source.getNamec();
                 r=source.getGRole();
                 n++;
                 break;                 
             }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         if(n!=0){
            Common.rl=r;
            System.out.println(na);
            System.out.println(r);
         }
         
         else{
            System.out.println("fail");
        }
         try {
             closeConnection();
         } catch (IOException e) {
             e.printStackTrace();
         }

    }
     public void passnameu() {
    	 elas.readxml();
         indexuse=elas.indexuser();
         typeuse=elas.typeuser();
         SearchRequest searchRequest = new SearchRequest(indexuse);
         SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
         sourceBuilder.query(QueryBuilders.matchAllQuery());
         sourceBuilder.from(0);
         sourceBuilder.size(1000);
         searchRequest.source(sourceBuilder);
         try {
             Common user = new Common();
             Common.Cookies cook = user.new Cookies(null,null,null,null);
         
             makeConnection();
             SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
             SearchHit[] searchHits = searchResponse.getHits().getHits();
             for (SearchHit searchHit : searchHits) {
                 String hitJson = searchHit.getSourceAsString();
                 ObjectMapper objectMapper = new ObjectMapper();
                 Common source = objectMapper.readValue(hitJson, Common.class);
                 
                 if(source.getName().equals(Common.username) ){
                     System.out.println(Common.username);                
                     if(source.getpassword().equals(Common.cipherString)) {
                         Common.user_name=source.getName();        
                         Common.user_age=source.getAge();        
                         Common.user_gender=source.getGender();        
                         Common.user_phone=source.getPhone(); 
                     cook.cookies.add(user.new Cookies(Common.user_name, Common.user_age, Common.user_phone,Common.user_gender));
                     cook.cookies.forEach(cs -> {
                        System.out.println("Name : " + cs.getcname() + ", Age : " + cs.getcage());
                    });
                     Common.num++;
                 break;
                }
                else{
                    System.out.println("fail password");
                }
            }
            else{
                System.out.println("fail username");
            }                
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         
         try {
             closeConnection();
         } catch (IOException e) {
             e.printStackTrace();
         }

    	 
     }
     public String[] unamepass() {
    	 passnameu();
		 String str[] = new String[2];
    	 if(Common.num!=0) {
              str[0]=Common.username;
              str[1]=Common.cipherString;
        	 Common.adsuccess="1";
        	 Common.num=0;
			 return(str);
         }
         else {
        	 Common.adsuccess="0";
             return(str);
         }
    	      }
     
    Common insertDetails(Common user){
        makeConnection();
        elas.readxml();
        indexuse=elas.indexuser();
        typeuse=elas.typeuser();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        dataMap.put("age", user.getAge());
        dataMap.put("gender", user.getGender());
        dataMap.put("phone", user.getPhone());
        dataMap.put("password", user.getpassword());
        IndexRequest request = new IndexRequest(indexuse, typeuse,user.getName()).source(dataMap);
        try {
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void logindet(){
        Common det = new Common();
        makeConnection();
        elas.readxml();
        indexlog=elas.indexlogindet();
        typelog=elas.typelogindet();
        Map<String,Object> dataMap = new HashMap<>();
        System.out.println(det.getNamec()+" "+det.getGRole());
        dataMap.put("namec", det.getNamec());
        dataMap.put("grole", det.getGRole());
        IndexRequest request = new IndexRequest(indexlog, typelog,det.getNamec()).source(dataMap);
        try {
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    @Override
//	public void userlogin(){
//
//        makeConnection();
//        Common user = new Common();
//            user.setName(Common.username);
//            user.setPassword(Common.cipherString);
//            Common gettingval = userlogin(user);
//            try{
//                if(gettingval.getName().equals(Common.username) && gettingval.getpassword().equals(Common.cipherString)){
//                    Common.user_name=gettingval.getName();
//                    Common.user_age=gettingval.getAge();
//                    Common.user_gender=gettingval.getGender();
//                    Common.user_phone=gettingval.getPhone();
//                    Common.ussuccess="1";
//                }
//                else{
//                    Common.ussuccess="0";
//                }
//            }catch(NullPointerException e){
//                Common.ussuccess="0";
//            }
//            try {
//            closeConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    @Override
	public void usersignup(){
        Common c = new Common();
        c.setName(Common.username);
        c.setAge(Common.userage);
        c.setGender(Common.usergender);
        c.setPhone(Common.userphone);
        c.setPassword(Common.cipherString);
        c.setNamec(Common.username);
        c.setGRole("user");
        if(Validation.isValidString(Common.username) && Validation.isValidAge(Common.userage) && Validation.isValidGender(Common.usergender) && Validation.isValidNumber(Common.userphone) )
        {
                logindet();
                Common.ussignsuccess="1";
                insertDetails(c);
            }
        else{
                Common.ussignsuccess="0";
            }

    }
    Common view_yourtic(Common person){
        makeConnection();
        SearchRequest searchRequest = new SearchRequest(Common.user_name.replaceAll("\\s", "").toLowerCase());
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.from(0);
        sourceBuilder.size(1000);
        searchRequest.source(sourceBuilder);

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            for (SearchHit searchHit : searchHits) {
            String hitJson = searchHit.getSourceAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            Common source = objectMapper.readValue(hitJson, Common.class);
            Common.yourticdata.add(source.getName());
            Common.yourticdata.add(source.getAge());
            Common.yourticdata.add(source.getGender());
            Common.yourticdata.add(source.getPhone());
            Common.yourticdata.add(source.getSource());
            Common.yourticdata.add(source.getDestination());
            Common.yourticdata.add(source.getDepartTime());
            Common.yourticdata.add(source.getTrainNo());
            Common.yourticdata.add(source.getTotalPrice());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
	public void view_yourtic() {
        Common c = new Common();
        try{
            view_yourtic(c);
        }catch (ElasticsearchStatusException e){
            Common.yourticdata=null;
        }

    }

    Common yourTickets(Common user){

        makeConnection();
        elas.readxml();
        System.out.println(Common.username.replaceAll("\\s", "").toLowerCase());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", Common.user_name);
        dataMap.put("age", Common.user_age);
        dataMap.put("gender", Common.user_gender);
        dataMap.put("phone", Common.user_phone);
        dataMap.put("trainNo",user.getTrainNo());
        dataMap.put("source",user.getSource());
        dataMap.put("destination",user.getDestination());
        dataMap.put("departTime",user.getDepartTime());
        dataMap.put("totalPrice",Common.total_Price);
        IndexRequest request = new IndexRequest(Common.username.replaceAll("\\s", "").toLowerCase(),elas.yourtic(),Common.username.replaceAll("\\s", "").toLowerCase()).source(dataMap);
        try {
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }


// ADMIN

     public String[] anamepass() {
    	 elas.readxml();
    	    indexadm=elas.indexadmin();
    	    typeadm=elas.typeadmin();
            String[] str = new String[2];
            int s=0;
         SearchRequest searchRequest = new SearchRequest(indexadm);
         SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
         sourceBuilder.query(QueryBuilders.matchAllQuery());
         sourceBuilder.from(0);
         sourceBuilder.size(1000);
         searchRequest.source(sourceBuilder);
         try {
             makeConnection();
             SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
             SearchHit[] searchHits = searchResponse.getHits().getHits();
             for (SearchHit searchHit : searchHits) {
             String hitJson = searchHit.getSourceAsString();
             ObjectMapper objectMapper = new ObjectMapper();
             Common source = objectMapper.readValue(hitJson, Common.class);
             if(source.getAdminName().equals(Common.username) ){
                 if(source.getAdminPass().equals(Common.cipherString)){
 	            s++;
 	        }
        }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         try {
             closeConnection();
         } catch (IOException e) {
             e.printStackTrace();
         }
         if(s!=0){
            System.out.println(Common.username);
            System.out.println(Common.cipherString);
             str[0]=Common.username;
             str[1]=Common.cipherString;
             return(str);
         }
         else{
             return(str);
         }
     }

    Common adminDetail(Common admin){
    makeConnection();
    elas.readxml();
    indexadm=elas.indexadmin();
    typeadm=elas.typeadmin();
    Map<String,Object> dataMap = new HashMap<>();
    dataMap.put("adminname",admin.getAdminName());
    dataMap.put("adminpass",admin.getAdminPass());
    IndexRequest request = new IndexRequest(indexadm,typeadm,admin.getAdminName()).source(dataMap);
    try {
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        closeConnection();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return admin;
}
 Common adminlogin(Common common){
    elas.readxml();
    indexadm=elas.indexadmin();
    typeadm=elas.typeadmin();
    GetRequest getPersonRequest = new GetRequest(indexadm,typeadm, common.username);
    GetResponse getResponse = null;
            try {
                getResponse = restHighLevelClient.get(getPersonRequest, RequestOptions.DEFAULT);
                return getResponse != null ?
                objectMapper.convertValue(getResponse.getSourceAsMap(), Common.class) : null;
            } catch (java.io.IOException e){
                e.getLocalizedMessage();
            }
    return common;
}

 Common insertTrainDetails(Common train){
    makeConnection();
    elas.readxml();
    indextra=elas.indextrain();
    typetra=elas.typetrain();
    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("source", train.getSource());
    dataMap.put("destination", train.getDestination());
    dataMap.put("trainNo", train.getTrainNo());
    dataMap.put("departTime", train.getDepartTime());
    dataMap.put("first", train.getFirst());
    dataMap.put("second", train.getSecond());
    dataMap.put("economy", train.getEconomy());
    dataMap.put("availableTickets", train.getAvailableTickets());
    IndexRequest request = new IndexRequest(indextra,typetra,train.getTrainNo()).source(dataMap);
    try {
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        closeConnection();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return train;
}

//@Override
//public void adminlogin(){
//    makeConnection();
//    Common admin = new Common();
//    admin.setAdminname(Common.adminname);
//    admin.setAdminpass(Common.cipherString);
//
//                    Common checkh = adminlogin(admin);
//                    if(checkh.getAdminName().equals(Common.adminname) && checkh.getAdminPass().equals(Common.cipherString)){
//                        Common.adsuccess="1";
//                }
//                else{
//                    Common.adsuccess="0";
//                }
//                try {
//                    closeConnection();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//}
@Override
public void adminsignup(){
    Common admin = new Common();
    admin.setAdminname(Common.adminname);
    admin.setAdminpass(Common.cipherString);
    admin.setNamec(Common.adminname);
    admin.setGRole("admin");
    if(Validation.isValidString(Common.adminname))
    {
        logindet();
        Common.adsignsuccess="1";
        adminDetail(admin);
    }
    else{
        Common.adsignsuccess="0";
    }

}


@Override
public void addtrain(){
    Common c = new Common();
    c.setSource(Common.trainsource);
    c.setDestination(Common.traindestination);
    c.setTrainNo(Common.traintrainno);
    c.setDepartTime(Common.traindeparttime);
    c.setFirst(Common.trainfirst);
    c.setSecond(Common.trainsecond);
    c.setEconomy(Common.traineconomy);
    c.setAvailableTickets(Common.trainavailabletic);

    if(Validation.isValidString(Common.trainsource) && Validation.isValidString(Common.traindestination) && Validation.isValidtrainNumber(Common.traintrainno) && Validation.isValidprice(Common.trainfirst) && Validation.isValidprice(Common.trainsecond) && Validation.isValidprice(Common.traineconomy) && Validation.isValidprice(Common.trainavailabletic) ){
        Common.addtrainsuccess="1";
        insertTrainDetails(c);
    }
    else{
        Common.addtrainsuccess="0";
    }
}
    Common view_train(Common person){
        makeConnection();
        elas.readxml();
        indextra=elas.indextrain();
        typetra=elas.typetrain();

        SearchRequest searchRequest = new SearchRequest(indextra);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.from(0);
        sourceBuilder.size(1000);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            for (SearchHit searchHit : searchHits) {
            String hitJson = searchHit.getSourceAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            Common source = objectMapper.readValue(hitJson, Common.class);
            Common.traindata.add(source.getSource());
            Common.traindata.add(source.getDestination());
            Common.traindata.add(source.getDepartTime());
            Common.traindata.add(source.getTrainNo());
            Common.traindata.add(source.getFirst());
            Common.traindata.add(source.getSecond());
            Common.traindata.add(source.getEconomy());
            Common.traindata.add(source.getAvailableTickets());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
    @Override
	public void view_train(){
        makeConnection();
        Common c=new Common();
        view_train(c);
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Common view_user(Common person){
        elas.readxml();
        indexuse=elas.indexuser();
        typeuse=elas.typeuser();

        SearchRequest searchRequest = new SearchRequest(indexuse);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.from(0);
        sourceBuilder.size(1000);
        searchRequest.source(sourceBuilder);
        try {
            makeConnection();
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            // System.out.println("searchHits :"+searchHits);
            for (SearchHit searchHit : searchHits) {
            String hitJson = searchHit.getSourceAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            Common source = objectMapper.readValue(hitJson, Common.class);
            Common.userdata.add(source.getName());
            Common.userdata.add(source.getAge());
            Common.userdata.add(source.getGender());
            Common.userdata.add(source.getPhone());
//            person=source;
//             System.out.println(source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
    @Override
	public void view_user() {
        Common c = new Common();
        view_user(c);
    }


    Common view_alltickets(Common person){
        makeConnection();
        elas.readxml();
        indextic=elas.indexticket();
        typetic=elas.typeticket();
        SearchRequest searchRequest = new SearchRequest(indextic);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.from(0);
        sourceBuilder.size(1000);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            for (SearchHit searchHit : searchHits) {
            String hitJson = searchHit.getSourceAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            Common source = objectMapper.readValue(hitJson, Common.class);
            Common.allticdata.add(source.getName());
            Common.allticdata.add(source.getAge());
            Common.allticdata.add(source.getGender());
            Common.allticdata.add(source.getPhone());
            Common.allticdata.add(source.getSource());
            Common.allticdata.add(source.getDestination());
            Common.allticdata.add(source.getDepartTime());
            Common.allticdata.add(source.getTrainNo());
            Common.allticdata.add(source.getTotalPrice());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
    @Override
	public void view_alltic(){
        Common c=new Common();
        view_alltickets(c);
    }

    Common allticket(Common user){

        elas.readxml();
        indextic=elas.indexticket();
        typetic=elas.typeticket();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", Common.user_name);
        dataMap.put("age", Common.user_age);
        dataMap.put("gender", Common.user_gender);
        dataMap.put("phone", Common.user_phone);
        dataMap.put("trainNo",user.getTrainNo());
        dataMap.put("source",user.getSource());
        dataMap.put("destination",user.getDestination());
        dataMap.put("departTime",user.getDepartTime());
        dataMap.put("totalPrice",Common.total_Price);

        IndexRequest request = new IndexRequest(indextic,typetic).source(dataMap);
        // System.out.println(request);
        try {
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            // System.out.println(indexResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }


    // BOOKINGS
    private Common getTrainBySrcDes(Common person) {
        elas.readxml();
        indextra=elas.indextrain();
        typetra=elas.typetrain();

        GetRequest getPersonRequest = new GetRequest(indextra,typetra, person.getTrainNo());
        // System.out.println(getPersonRequest);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getPersonRequest, RequestOptions.DEFAULT);
            // System.out.println(getResponse);
            return getResponse != null ?
            objectMapper.convertValue(getResponse.getSourceAsMap(), Common.class) : null;
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
            // System.out.println("Getting io exception\n\n\n");
        }
        // System.out.println(person);
                return person;
    }

    @Override
	public void booktic(){
        makeConnection();
            Common c = new Common();
            c.setSource(Common.trainsource);
            c.setDestination(Common.traindestination);
            c.setClassHere(Common.trainclass);
            c.setNumTic(Common.trainnumtic);
            c.setTrainNo(Common.traintrainno);
            if(Validation.isValidString(Common.trainsource) && Validation.isValidString(Common.traindestination) && Validation.isValidCla(Common.trainclass) && Validation.isValidprice(Common.trainnumtic)){

                    Common srcdes = getTrainBySrcDes(c);
                    if(srcdes.getSource().equals(Common.trainsource) && srcdes.getDestination().equals(Common.traindestination)){
                        Common.bookticsuccess="1";
                        write_ticket(Common.trainclass,Common.trainnumtic,srcdes);
                            }
                            else{
                                Common.bookticsuccess="0";
                            }
                        }
                    else{
                        Common.bookticsuccess="0";
                    }
    }
    public void write_ticket(String cla,String num,Common c) {

        String cost;
        int tot=0;
        if(cla.equals("F")){
            cost= c.getFirst();
            tot =Integer.parseInt(cost)*Integer.parseInt(num);
            c.setTotalPrice(String.valueOf(tot));
            Common.total_Price = String.valueOf(tot);
            allticket(c);
            yourTickets(c);
        }
        else if(cla.equals("S")){
            cost= c.getSecond();
            tot =Integer.parseInt(cost)*Integer.parseInt(num);
            c.setTotalPrice(String.valueOf(tot));
            Common.total_Price = String.valueOf(tot);
            allticket(c);
            yourTickets(c);
        }
        else if(cla.equals("E")){
            cost= c.getEconomy();
            tot =Integer.parseInt(cost)*Integer.parseInt(num);
            c.setTotalPrice(String.valueOf(tot));
            Common.total_Price = String.valueOf(tot);
            allticket(c);
            yourTickets(c);
        }

    }
    // SELECTED DB
    public void selected(){
        System.out.println("Elas");
    }
}
