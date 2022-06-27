package helpers;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.openqa.selenium.Proxy;

public class ProxyHelper {
    private static BrowserMobProxy browserMobProxy;

    private static Proxy proxy;

    public static void startBrowserMobProxyServer() {
        System.out.println("starting proxy server...");
        browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.addRequestFilter(new RequestFilter() {
            @Override
            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
                request.headers().remove("User-Agent");
                request.headers().add("User-Agent","Boxed-Automation-46241e82dafd3707");
//                request.headers().forEach(h -> {
//                    System.out.println("uri " + request.getUri() + " header key " + h.getKey() + " value " + h.getValue());
//                });

                // in the request filter, you can return an HttpResponse object to "short-circuit" the request
                return null;
            }
        });
        browserMobProxy.start();

        System.out.println("is proxy server started " + browserMobProxy.isStarted());
    }

    private static Proxy getSeleniumProxy() {
        proxy = ClientUtil.createSeleniumProxy(browserMobProxy);
        proxy.setSslProxy("localhost:" + browserMobProxy.getPort());

        return proxy;
    }

    public static Proxy getProxy() {
        return getSeleniumProxy();
    }

    public static void stopServer() {
        if(browserMobProxy != null) {
            System.out.println("stopping proxy server...");
            browserMobProxy.stop();
            System.out.println("is proxy server started " + browserMobProxy.isStarted());
        }
    }
}
