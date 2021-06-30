package de.aicard.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.aicard.domains.Social.Chat;
import de.aicard.domains.account.Account;
import de.aicard.domains.account.Professor;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.card.TextFile;
import de.aicard.domains.enums.AcademicGrade;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import javax.servlet.http.Cookie;
import java.util.ArrayList;

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
    
//  /*  public static Cookie addSessionValue(Cookie cookies[], String jsonKey, String jsonValue)
//    {
//
//    */}
//
    
//    public static String getSessionValueByKey(Cookie cookies[], String jsonKey)
//    {
//
//        if (cookies != null)
//            for (Cookie cookie : cookies)
//            {
//                if (cookie.getName().equals(cookieName))
//                {
////                    System.out.println(cookie.getValue());
//
//                    String cookieValue = cookie.getValue();
////                    String cookieValue = "{\"name\":\"derNameZumKey\"}";
//                    JsonObject jsonObject = new JsonParser().parse(cookieValue).getAsJsonObject();
//                    String test = jsonObject.get(jsonKey).toString();
//                    System.out.println(test);
//                    return jsonObject.get(jsonKey).toString();
////                    Gson gson = new Gson();
////
////
////                    String json = gson.toJson(new Card((CardContent) new TextFile("FrontSide"), (CardContent) new TextFile("BackSide")));
////                    System.out.println(json);
////
////                    JsonElement jsonElement = gson.toJsonTree(new Card((CardContent) new TextFile("FrontSide"), (CardContent) new TextFile("BackSide")));
////                    jsonElement.getAsJsonObject().addProperty("testProperty", "testValue");
////                    String newJson = gson.toJson(jsonElement);
////                    System.out.println(newJson);
////
////                    JsonElement jsonElement1 = gson.toJsonTree(new Object());
////                    System.out.println(" Test -> "  + jsonElement1);
////
////                    jsonElement1.getAsJsonObject().addProperty("key", "value");
////                    System.out.println("TEst 2 -> " + jsonElement1);
////                    return "";
//                }
//            }
//        return null;
//    }
    
    public static String getCookieContent(Cookie cookies[])
    {
        return "1";
    }
    
    
    
}
