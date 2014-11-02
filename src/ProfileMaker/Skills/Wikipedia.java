package ProfileMaker.Skills;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.NetworkManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thilina on 11/1/2014.
 */
public class Wikipedia {
    private NetworkManager networkManager;
    public Wikipedia() {
        networkManager=new NetworkManager();
    }
    public ArrayList<String> GetTerms(String searchTerm){
        ArrayList<String> terms=new ArrayList<String>();
        JSONObject json = null;
        searchTerm=searchTerm.replace(' ','-');
        String url="http://en.wikipedia.org/w/api.php?format=json&action=query&titles="+searchTerm;
        String result = networkManager.Get(url);
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
//            System.out.println(json.getJSONObject("query").getJSONObject("pages").keys());
            Iterator keys = json.getJSONObject("query").getJSONObject("pages").keys();
            while (keys.hasNext()){
                String key=keys.next().toString();
                System.out.println(key);
                if (!key.equals("-1")){
                    return tokenizePage(key);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  terms;
    }
    private ArrayList<String> tokenizePage(String pageId){
        ArrayList<String> terms=new ArrayList<String>();
        String url="http://en.wikipedia.org/wiki?curid="+pageId;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element body = doc != null ? doc.select("div[id=bodyContent]").first() : null;
        System.out.println(url);
        System.out.println(body.text());
        return terms;
    }

}
