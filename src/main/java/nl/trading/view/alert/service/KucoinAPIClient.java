package nl.trading.view.alert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.trading.view.alert.model.AccountDetails;
import nl.trading.view.alert.model.ActivePosition;
import nl.trading.view.alert.model.TickerInformation;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


@Service
public class KucoinAPIClient {

    private final RestTemplate restTemplate;

    @Value("${kucoin.futures.api.key}")
    private String apiKey;

    @Value("${kucoin.futures.api.secret}")
    private String apiSecret;

    @Value("${kucoin.futures.api.passphrase}")
    private String apiPassphrase;

    @Value("${kucoin.futures.api.baseurl}")
    private String apiBaseUrl;

    @Autowired
    public KucoinAPIClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public AccountDetails getAccountDetails(){
        String apiUrl = "/api/v1/account-overview?currency=USDT";
        String response = getDataFromKucoin(apiUrl,HttpMethod.GET,null);
        return (AccountDetails)convertJSONToObject(response,AccountDetails.class);
    }

    public ActivePosition getActivePosition(){
        String apiUrl = "/api/v1/position?symbol=ETHUSDTM";
        String response = getDataFromKucoin(apiUrl,HttpMethod.GET,null);
        return (ActivePosition)convertJSONToObject(response,ActivePosition.class);
    }

    public TickerInformation getTickerInfo(){
        String apiUrl = "/api/v1/contracts/ETHUSDTM";
        String response = getDataFromKucoin(apiUrl,HttpMethod.GET,null);
        return (TickerInformation) convertJSONToObject(response,TickerInformation.class);
    }

    private String getDataFromKucoin(String apiUrl, HttpMethod requestType,String body){
        HttpHeaders headers;
        body = body != null ? body : "";
        try {
            headers = getHeaders(requestType.toString(),apiUrl,body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiBaseUrl+apiUrl, requestType, request, String.class);
        return response.getBody();
    }


    private HttpHeaders getHeaders(String requestType, String requestUrl, String body) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        long now = System.currentTimeMillis();
        String signature = now + requestType.toUpperCase()+requestUrl+body;
        headers.add("KC-API-SIGN",encode(apiSecret,signature));
        headers.add("KC-API-PASSPHRASE",encode(apiSecret,apiPassphrase));
        headers.add("KC-API-KEY",apiKey);
        headers.add("KC-API-TIMESTAMP", String.valueOf(now));
        headers.add("KC-API-KEY-VERSION","2");
        return headers;
    }

    private String encode(String key, String data) throws Exception {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmacSHA256.init(secretKey);
        return Base64.encodeBase64String(hmacSHA256.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    private Object convertJSONToObject(String response, Class<?> cls){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
