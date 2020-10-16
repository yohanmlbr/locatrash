package com.codev.locatrash.proxies;

import com.codev.locatrash.entity.request.LocationIQAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="locationiqclient", url = "https://us1.locationiq.com/v1/search.php?key=pk.85fef269738343f2ecae0077b6ccd58f&format=json")
public interface LocationIQClient {
    @RequestMapping(method = RequestMethod.GET)
    List<LocationIQAPIResponse> getLatLongTrash(@RequestParam("q") String address);
}
