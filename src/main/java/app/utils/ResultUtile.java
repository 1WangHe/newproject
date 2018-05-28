package app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultUtile {  
    private boolean success;  
    private Integer errorcode;
    private Object body;  
    
    public  ResultUtile(){
    	this.success=true;
    	this.errorcode=1;
    	
    }
    
    public  ResultUtile(boolean success,Integer errorcode){
    	this.success=success;
    	this.errorcode=errorcode;
    }
  
    public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public Integer getErrorcode() {
		return errorcode;
	}



	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}



	public Object getBody() {
		return body;
	}



	public void setBody(Object body) {
		this.body = body;
	}



	public String toString() {  
//      return JSONObject.toJSONString(this);  
  
        ObjectMapper mapper = new ObjectMapper();  
        String jsonStr = "";  
        try{  
            jsonStr = mapper.writeValueAsString(this);  
        }catch(JsonProcessingException e){  
        }  
        return jsonStr;  
    }  
}  
