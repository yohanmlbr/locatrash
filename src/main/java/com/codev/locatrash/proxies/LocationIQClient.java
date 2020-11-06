package com.codev.locatrash.proxies;

import com.codev.locatrash.entity.request.LocationIQAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="locationiqclient", url = "https://us1.locationiq.com/v1")
public interface LocationIQClient {
    String pk="pk.85fef269738343f2ecae0077b6ccd58f";

    @RequestMapping(method = RequestMethod.GET, value = "/search.php?key="+pk+"&format=json")
    List<LocationIQAPIResponse> getLatLongTrash(@RequestParam("q") String address);
}
