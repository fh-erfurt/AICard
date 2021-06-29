package de.aicard.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Cookie;

@Setter
@Getter
@RequiredArgsConstructor
public class Session {

    static final String cookieName = "javaSession";
    final String cookieValue;  // you could assign it some encoded value
    static final Boolean useSecureCookie = true;
    static final int expiryTime = 60 * 60 * 24;  // 24h in seconds
    static final String cookiePath = "/";

    public Cookie setSession(){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setSecure(useSecureCookie);  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
        cookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
        cookie.setPath(cookiePath);
        return cookie;
    }

    static public Cookie delSession(Cookie cookies[]){
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("javaSession")) {
//                    System.out.println(cookie.getValue());
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        return null;
    }
    
    public static String getCookieContent(Cookie cookies[], String cookieName)
    {
        if (cookies != null)
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(cookieName))
                {
//                    System.out.println(cookie.getValue());
                    return cookie.getValue();
                }
            }
        return "No Cookies found";
    }
}
