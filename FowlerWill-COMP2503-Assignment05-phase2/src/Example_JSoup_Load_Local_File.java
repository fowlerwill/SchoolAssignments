import java.io.*;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Example_JSoup_Load_Local_File
{
    public static void main(String[] args)
    {
        try
        {
            File input = new File("web/p1.html");
            Document doc = Jsoup.parse(input, "UTF-8", "");

            Element tag = doc.child(0);

            System.out.println(tag);
        }
        catch(Exception ex)
        {
            System.out.println("web/p1.html");
            ex.printStackTrace();
        }
    }
}
