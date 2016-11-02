package lh.test.addresses.error;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Contains error details from web requests
 * @author Lennon
 *
 */
public class DataError implements ResponseErrorHandler{
	private static Logger LOG = Logger.getLogger(DataError.class);
	private String errorCode;
	private String url;
	private String message;
	
	public DataError(String errorCode, String url, String message){
		this.errorCode = errorCode;
		this.url = url;
		this.message = message;
	}
	
	public DataError(String url){
		this.url = url;
	}
	
	public DataError(String message, String errorCode){
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public DataError(){
		
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString(){
		JSONObject json = new JSONObject(this);
		return json.toString();
		
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		if (response.getStatusCode() != HttpStatus.OK){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		if (response.getStatusCode() != HttpStatus.OK){
			LOG.info("Issue detected making request");
			this.errorCode = response.getStatusCode().toString();
			this.message = response.getBody().toString();
		}
		
	}
}
