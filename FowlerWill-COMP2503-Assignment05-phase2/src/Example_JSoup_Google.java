import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Example_JSoup_Google
{
  public static void main(String[] args)
  {
      try
      {
        Document doc = Jsoup.connect("http://www.google.ca").get();
      
        Element tag = doc.child(0);
        
        List<Node> e = tag.child(1).childNodes();
        
        
        System.out.println(tag.child(1).child(0));
       
    }
    catch(Exception ex)
    {
        System.out.println("Unable to open page: www.google.com");
        ex.printStackTrace();
    }
        
  }
}
