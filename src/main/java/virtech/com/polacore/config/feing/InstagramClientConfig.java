package virtech.com.polacore.config.feing;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InstagramClientConfig {
    private final RestTemplate restTemplate;

    final static Logger logger = LoggerFactory.getLogger(InstagramClientConfig.class);

    @Value("${api.instagram.client-id}")
    private String clientId;
    @Value("${api.instagram.redirect-uri}")
    private String redirectUri;
    @Value("${api.instagram.scope}")
    private String scope;
    @Value("${api.instagram.response-type}")
    private String responseType;

    @Value("${api.instagram.url}")
    private String url;

    public InstagramClientConfig(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor(){
        return new RequestInterceptor() {
            @SneakyThrows
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("Content-Type","application/json");
                requestTemplate.header("charset","UTF-8");
                requestTemplate.header("Authorization","Bearer " + getCode());
                logger.info("#### RequestInterceptor requestTemplate: {}", requestTemplate);
            }
        };
    }


    public String getCode() throws ParseException {

        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("client_id", clientId);
        bodyParams.put("redirect_uri", redirectUri);
        bodyParams.put("scope", scope);
        bodyParams.put("response_type", responseType);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(bodyParams, headers);

        logger.info("Headers params {} ",headers);
        logger.info("Body params {} ",bodyParams);

        ResponseEntity response = restTemplate.exchange(url +"/oauth/authorize/", HttpMethod.GET, entity, String.class);

        logger.info("IGResponse result {} ", response);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(Objects.requireNonNull(response.getBody()).toString());

        String textoToken = (String) jsonObject.get("access");

        logger.info("TextoToken result {} ", textoToken);

        return textoToken;
    }
}
