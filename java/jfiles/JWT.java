package jfiles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JWT{
    public void declare(){
        String n="yalieny";
	String jws = Jwts.builder().setSubject(Common.username).signWith(SignatureAlgorithm.HS256,n).compact();
	System.out.println(jws);	 	
    }
}