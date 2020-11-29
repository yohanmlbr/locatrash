package com.codev.locatrash.proxies;

import com.codev.locatrash.entity.request.GrandLyonAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="poubelleclient", url = "https://download.data.grandlyon.com/ws/grandlyon")
public interface TrashClient {
    int maxFeatures=4500;
    int start=2342;

    @RequestMapping(method = RequestMethod.GET, value ="/gin_nettoiement.gincorbeille/all.json?maxfeatures="+maxFeatures+"&start="+start)
    GrandLyonAPIResponse getAllPoubelles();
}
