package ResourcesTestData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils{
	
public static RequestSpecification reqBase;

public RequestSpecification requestSpecification() throws IOException
{
	if(reqBase==null)
	{
	PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
    reqBase = new RequestSpecBuilder().setBaseUri(setProperties("baseUrl")).addQueryParam("key", "qaclick123")
    		.addFilter(RequestLoggingFilter.logRequestTo(log))
    		.addFilter(ResponseLoggingFilter.logResponseTo(log))
    .setContentType(ContentType.JSON).build();
    return reqBase;
	}
	return reqBase;

}

public static String setProperties(String key) throws IOException
{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("C:\\Users\\fahadmohd\\eclipse-workspace\\APIFramework\\src\\test\\java\\ResourcesTestData\\Global.properties");
	prop.load(fis);
	return prop.getProperty(key);
	
}

//json path method
public String getJsonPath(Response response, String key)
{
	String resp = response.asString();
	JsonPath jsp = new JsonPath(resp);
	return jsp.get(key).toString();
	
	
}
}
