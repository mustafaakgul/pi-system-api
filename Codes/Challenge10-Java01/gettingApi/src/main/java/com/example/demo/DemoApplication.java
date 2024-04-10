package com.example.demo;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Dictionary;

import org.apache.http.ssl.TrustStrategy;

//import org.apache.commons.codec.binary.Base64;

@SpringBootApplication
public class DemoApplication{

    //private static String TargertURL = "https://{hostname}/piwebapi/streams/{webID}/interpolated?startTime=T-10d&endTime=T&Interval=1h";
    //public static final String apiUrl = "https://devdata.osisoft.com/piwebapi/streams/F1DPW6Wlk0_Utku9vWTvxg45oAMRcAAAUElTUlYxXENJVFlCSUtFU18oVE8pQklLRV8wMS4gQ0VSVE9TQSAgIFAuTEUgQVZJU19FTVBUWSBTTE9UUw/value";
    public static final String apiUrl = "https://172.16.4.95/piwebapi";
    //public static final String username = "webapiuser";
    public static final String username = "{USER}\\PI4Dev";
    //public static final String password = "!try3.14webapi!";
    public static final String password = "Password1";


    public static void main(String[] args) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SpringApplication.run(DemoApplication.class, args);

        //URL requestURL = new URL(TargertURL);
        //HttpsURLConnection myRequest = (HttpsURLConnection)requestURL.openConnection();
        //myRequest.connect();

        System.out.println("asd");
/**
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
*/
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        //RestTemplate restTemplate = new RestTemplate(requestFactory);
        RestTemplate restTemplate = new DemoApplication().restTemplatex();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, //
                HttpMethod.GET, entity, String.class);
        String result = response.getBody();

        //Dictionary dictionary = new Dictionary();


        System.out.println(result);
    }

    @Bean
    public RestTemplate restTemplatex()
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }



}
