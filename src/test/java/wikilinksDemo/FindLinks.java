package wikilinksDemo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

 public class FindLinks {
 public static WebDriver driver;
 public static String homepage="https://en.wikipedia.org/wiki/Hello";
 static HttpURLConnection huc=null;
 static int responseCode=200;
 static String url = "";
	public static void main(String[] args) {
		System.out.println("Enter a number between 1 to 20 ");
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(homepage);
		
		List<WebElement >links= driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
		while(it.hasNext()){
	
			for(int i=0;i<n;i++)
			{
				url = it.next().getAttribute("href");
		if(url == null || url.isEmpty()){
		links.remove(url);
		continue;
		}
		if(!url.startsWith(homepage)){
			links.remove(url);
		continue;
		}
		try {
		huc = (HttpURLConnection)(new URL(url).openConnection());
		huc.setRequestMethod("HEAD");
		huc.connect();
		responseCode = huc.getResponseCode();
		if(responseCode >= 400){
		System.out.println(url+" is a broken link");
		}
		else{
		System.out.println(url  +" is a valid link");
	  List<String> goodLinks=new ArrayList<String>();
		goodLinks.add(url);
		}} 
    catch (IOException e) {
  e.printStackTrace();
    }		}
		}
		driver.quit();
		}
 }
