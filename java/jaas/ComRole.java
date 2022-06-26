package jaas;
import java.security.Principal;

public class ComRole implements Principal {
	  
	  private String name;
	  
	  public ComRole(String name) {
	   super();
	   this.name = name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  @Override
	  public String getName() {
	    return name;
	  }

	}