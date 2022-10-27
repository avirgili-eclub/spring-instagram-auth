package virtech.com.polacore.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import virtech.com.polacore.config.feing.InstagramClientConfig;

@FeignClient(name = "instagramFeignClient",
        url="${api.instagram.url}",
        configuration = InstagramClientConfig.class )
//TODO: Sacar de aca para tener en un common/shared service.
public interface InstagramClient {
//    @PostMapping("/data")
//    @Headers({"Content-Type: application/json"})
//    CardAccountResponse getCardAccountData(@RequestBody CardAccountRequest request);
    @GetMapping("/data")
    @Headers({"Content-Type: application/json"})
    Object authUser(@RequestBody Object request);
}
