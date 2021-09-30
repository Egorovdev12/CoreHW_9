import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final String SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My first http client")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(20000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        // Создание объекта запроса c заголовками
        HttpGet request = new HttpGet(SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        // Отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);


        List<Post> postList = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Post>>() {});
        //postList.forEach(System.out::println);
        postList.stream()
                .filter(x -> x.getUpvotes() != 0 && x.getUpvotes() > 0)
                .sorted((p1, p2) -> p2.getUpvotes() - p1.getUpvotes())
                .forEach(System.out::println);
    }
}