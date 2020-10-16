package com.codev.locatrash.proxies;

import com.codev.locatrash.entity.request.GrandLyonAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="poubelleclient", url = "https://download.data.grandlyon.com/ws/grandlyon")
public interface PoubelleClient {
    @RequestMapping(method = RequestMethod.GET, value ="/gin_nettoiement.gincorbeille/all.json?maxfeatures=100&start=101")
    GrandLyonAPIResponse getAllPoubelles();
}
