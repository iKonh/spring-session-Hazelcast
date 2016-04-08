package config;

import java.net.URI;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class CustomRequestFactory extends HttpComponentsClientHttpRequestFactory {
    @Override
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        HttpClientContext context = HttpClientContext.create();
        context.setRequestConfig(getRequestConfig());
        return context;
    }

    protected RequestConfig getRequestConfig() {
        RequestConfig.Builder builder = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setAuthenticationEnabled(false)
                .setRedirectsEnabled(false);
        return builder.build();
    }
}
