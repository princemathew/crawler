import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class crawler {
  public static void main(String[] args) throws Exception  {

    Scanner s = new Scanner(System.in);
    System.out.print("Enter the url : ");
    String url = s.nextLine();

    URL urlobj = new URL(url); 
    URLConnection urlcon = urlobj.openConnection();

    System.out.print("\n\nHEADER DATA\n--------------------\n");
    Map<String, List<String>> header = urlcon.getHeaderFields();
    for (Map.Entry<String, List<String>> mp : header.entrySet())  { 
        System.out.print(mp.getKey() + " : "); 
        System.out.println(mp.getValue().toString()); 
    } 
    


    Document document = Jsoup.connect(url).get();
    System.out.println(document.title());
    
    System.out.print("\n\nLINKS\n--------------------\n");
    Elements links = document.select("a[href]");  
    for (Element link : links) {  
        System.out.println(link.attr("href"));    
    }  


    Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
    Matcher matcher = p.matcher(document.text());
    Set<String> emails = new HashSet<String>();
    while (matcher.find()) {
          emails.add(matcher.group());
    }

    System.out.println("\n\nEMAILS\n--------------------\n"+emails);


  }
}
