package ProfileMaker.Skills;

import PhraseExtractor.PhraseAnalyzer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Thilina on 11/1/2014.
 */
public class SkillsExtractor {
    PhraseAnalyzer phraseAnalyzer;

    public SkillsExtractor() {
        this.phraseAnalyzer = new PhraseAnalyzer();
    }

    public static void main(String[] args){
        SkillsExtractor sk=new SkillsExtractor();
        sk.ExtractSkills("https://www.linkedin.com/in/nisansadds");
    }
//    private ArrayList<Strin>

    public void ExtractSkills(String url){
        ArrayList<Skill> skills=new ArrayList<Skill>();
        Skill sk;
        Wikipedia wiki=new Wikipedia();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements skillElements = doc != null ? doc.select("span[class=skill-pill]") : null;
        for (Element skillElement : skillElements) {
            System.out.println(skillElement.text());
//            writeFile();
            wiki.GetTermsGoogle(skillElement.text());
        }
    }

}
