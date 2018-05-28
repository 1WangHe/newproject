package app.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class Demo {
	@ResponseBody
	@RequestMapping("demo")
	public String getDemo(){
		System.out.println("111111111111111");
		return "asdasfdga";
	}
	
	
	
}
