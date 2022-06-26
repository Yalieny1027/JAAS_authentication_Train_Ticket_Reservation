package jaas;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import com.sun.security.auth.callback.TextCallbackHandler;

public class JaasLc {
    public void newlogin(){
        LoginContext lc = null;
      try {
          lc = new LoginContext("Web_Train", 
                      new CallbackHandler());
      } catch (LoginException le) {
          System.err.println("Cannot create LoginContext. "
              + le.getMessage());
          System.exit(-1);
      } catch (SecurityException se) {
          System.err.println("Cannot create LoginContext. "
              + se.getMessage());
          System.exit(-1);
      } 

      try {
    
          // attempt authentication
          lc.login();
    
      } catch (LoginException le) {
    
          System.err.println("Authentication failed: ");
          System.err.println("  " + le.getMessage());
          System.exit(-1);
    
      }
    
      System.out.println("Authentication succeeded!");
    }
    public void newlogout(){

    }
}
