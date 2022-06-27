package jaas;
import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;
import java.util.Vector;

import javax.management.relation.Role;
import javax.security.auth.Subject;
	import javax.security.auth.callback.Callback;
	import javax.security.auth.callback.CallbackHandler;
	import javax.security.auth.callback.NameCallback;
	import javax.security.auth.callback.PasswordCallback;
	import javax.security.auth.callback.UnsupportedCallbackException;
	import javax.security.auth.login.LoginException;
	import javax.security.auth.spi.LoginModule;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import jfiles.AEScode;
import jfiles.Common;
import jfiles.Intermediate;



	public class AULoginModule implements LoginModule {

	  private String r;
	  private CallbackHandler handler;
	  private Subject subject;
	  private ComPrincipal userPrincipal;
	  private ComRole rolePrincipal;
	  private String login=null;
	  private List<String> userGroups;
	  private String name=null;
	  private String password=null;
	  private String[] str;
	  Common c= new Common();
	  @Override
	  public void initialize(Subject subject,
	      CallbackHandler callbackHandler,
	      Map<String, ?> sharedState,
	      Map<String, ?> options) {

		  System.out.println("initialize");
	    handler = callbackHandler;
	    this.subject = subject;
		
	  }
	  public void getDet(){
		  System.out.println("startgetDet");
	      Intermediate inte = new Intermediate();
	    	inte.selected();
			inte.getRole();
	    	if(Common.rl.equals("admin")) {
				r="admin";
	    		str=inte.anamepass();
	    		System.out.println("ad");
	    	}
	    	else {
				r="user";
	    		str=inte.unamepass();
	    		System.out.println("us");
	    	}
	    	
	    System.out.println(str[0]);
		System.out.println(str[1]);
	      System.out.println(name);
	      System.out.println(Common.cipherString);
	  }
	  
	  @Override
	  public boolean login() throws LoginException {
		  Callback[] callbacks = new Callback[2];
		  callbacks[0] = new NameCallback("login");
		  callbacks[1] = new PasswordCallback("password", true);
		  
		  
		  try {
			  handler.handle(callbacks);
			  name = ((NameCallback) callbacks[0]).getName();
			  Common.username = name;
			  password = String.copyValueOf(((PasswordCallback) callbacks[1]).getPassword());
			  Common.cipherString = AEScode.encrypt(password, Common.encKey);
			  System.out.println(Common.username);
			  System.out.println(Common.cipherString);
			  getDet();
	    	  if (name != null && 
	    	          name.equals(str[0]) &&
	    	          Common.cipherString != null &&
	    	        		  Common.cipherString.equals(str[1])) {
	    		        System.out.println("ss");
	    	    	  login = name;
	    	        userGroups = new ArrayList<String>();
	    	        userGroups.add(r);
	    	        System.out.println("Success");   
					Common.ussuccess="1";	
					name=null;
					password=null;
	    	  return true;

	      }
	      throw new LoginException("Authentication failed");
	      
	    } catch (IOException e) {
	    	System.out.println("IO");
	      throw new LoginException(e.getMessage());
	    } catch (UnsupportedCallbackException e) {
	    	System.out.println("UCE");
	      throw new LoginException(e.getMessage());
	    }
		catch(Exception e){
			System.out.println(e);
		}
		return false;

	  }
		@Override
	  public boolean commit() throws LoginException {
		  System.out.println("commit");
	    userPrincipal = new ComPrincipal(login);
		System.out.println(userPrincipal);
	    subject.getPrincipals().add(userPrincipal);

	    if (userGroups != null && userGroups.size() > 0) {
	      for (String groupName : userGroups) {
	        rolePrincipal = new ComRole(groupName);
	        subject.getPrincipals().add(rolePrincipal);
		System.out.println(rolePrincipal);
			
	      }
	    }
	    return true;
	  }

	  @Override
	  public boolean abort() throws LoginException {
	        name=null;
	        password=null;
			rolePrincipal=null;
			r=null;
	    return false;
	  }

	  @Override
	  public boolean logout() throws LoginException {
	    subject.getPrincipals().remove(userPrincipal);
	    subject.getPrincipals().remove(rolePrincipal);
		r=null;
        name=null;
        password=null;
	    rolePrincipal=null;
		Common.rl=null;
	    return true;
	  }




	}

