package openAPI_week15;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MovieAPI {

	public static void main(String[] args) {
		//System.out.println("Hello world!");
//		curl "https://openapi.naver.com/v1/search/movie.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&genre=1" \
//	    -H "X-Naver-Client-Id: {QB1tA1Sc_UUTD3pYOBt1}" \
//	    -H "X-Naver-Client-Secret: {wpLKP4VDoe}" -v
		 String clientId = "QB1tA1Sc_UUTD3pYOBt1"; //애플리케이션 클라이언트 아이디값"
	        String clientSecret = "wpLKP4VDoe"; //애플리케이션 클라이언트 시크릿값"

	        String text = null;
	        try {
	        	Scanner scan = new Scanner(System.in);
	            String str;
	            System.out.print("검색어를 입력하세요 : ");
	            str = scan.nextLine();
	            text = URLEncoder.encode(str, "UTF-8");
	            scan.close();
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("검색어 인코딩 실패",e);
	        }

//	        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // json 결과
//	        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 寃곌낵
	        //movie
	        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;    // json 寃곌낵
	        //String apiURL = "https://openapi.naver.com/v1/search/movie.xml"+ text; // xml 寃곌낵

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);

	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
	        JSONArray infoArray = (JSONArray) jsonObject.get("items");
	        
	        for(int i = 0; i < infoArray.size(); i++) {
	            System.out.println("=item_" + i + "============================");
	            JSONObject itemObject = (JSONObject) infoArray.get(i);
	            System.out.println("title:\t" + itemObject.get("title"));
	            System.out.println("subtitle:\t" + itemObject.get("subtitle"));
	            System.out.println("director:\t" + itemObject.get("director"));
	            System.out.println("actor:\t" + itemObject.get("actor"));
	            System.out.println("userRating:\t" + itemObject.get("userRating") + "\n");
	 
	        }
	
	        //System.out.println(responseBody);
	    }

	    private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // �젙�긽 �샇異�
	                return readBody(con.getInputStream());
	            } else { // �뿉�윭 諛쒖깮
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }

	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	}

}