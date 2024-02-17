package org.example;
import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            String webURL = "https://playwright.dev/";
            String webPageName = extractWebPageName(webURL);
            System.out.println("Web page: "+extractWebPageName(webURL));
            Response response =page.navigate(webURL);

            //Stage 1 tasks:
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(webPageName +".png")));
            String ipAddress = response.serverAddr().ipAddress;
            System.out.println(ipAddress);

            // redirection to API page
            redirectToNewPage();

            //Stage 2 task
            String sslDetails = SSLDetails.getSSLDetails(response).toString();
            System.out.println(sslDetails);

            //Stage 3 tasks
            //Page source
            String pageSource = page.content();
            System.out.println(pageSource);
            // natural language content extracted from source
            Map<String, String> content = new HashMap<>();
            content = getContent(page);

            //storing in MongoDB
            MongoDB.mongoDB(ipAddress, sslDetails, pageSource, content);
            browser.close();

        }
    }
    public static String extractWebPageName(String urlString) {
        String regex = "^(?:https?://)?(?:www\\.)?([^/.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(urlString);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "webpage";
        }
    }

    public static Map<String, String> getContent(Page page) {
        Map<String, String> map = new HashMap<>();

        // locale
        String locale = page.getAttribute("meta[property='og:locale']", "content");
        System.out.println("Content value of meta tag locale: " + locale);
        map.put("locale", locale);

        // language
        String language = page.getAttribute("meta[name='docsearch:language']", "content");
        System.out.println("Content value of meta tag language: " + language);
        map.put("language", language);

        // complete content of the page
        // System.out.println("Content of the page :: " + page.content());

        return map;
    }

    public static void redirectToNewPage() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            String webURL = "https://playwright.dev/";
            String webPageName = extractWebPageName(webURL);
            System.out.println("Web page: " + extractWebPageName(webURL));
            Response response = page.navigate(webURL);
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(webPageName +"_before_redirect.png")));
            String redirectURL = "https://playwright.dev/java/docs/api/class-playwright";
            page.navigate(redirectURL);
            String redirectPageName = extractWebPageName(redirectURL);
            System.out.println("Web page: " + extractWebPageName(redirectURL));
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(redirectPageName +"_after_redirect.png")));
        } catch (PlaywrightException e) {
            e.printStackTrace();
        }
    }
}