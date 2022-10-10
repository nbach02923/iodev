package vn.iodev.iosecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import vn.iodev.iosecurity.payload.CaNhanResponse;
import vn.iodev.iosecurity.payload.ToChucResponse;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@Component
public class HumanResourceService {
    @Autowired
    private LoadBalancerClient loadBalancer;

    public CaNhanResponse getCaNhanById(String caNhanId) {
        ServiceInstance serviceInstance = loadBalancer.choose(IOServiceEndpoint.HUMANRESOURCES_SERVICE_ID);
        String url = serviceInstance.getUri().toString();
        url = url + "/api/canhans/" + caNhanId;
        RestTemplate restTemplate = new RestTemplate();
        CaNhanResponse caNhan = restTemplate.getForObject(url, CaNhanResponse.class);

        return caNhan;
    }

    public ToChucResponse getToChucId(String toChucId) {
        ServiceInstance serviceInstance = loadBalancer.choose(IOServiceEndpoint.HUMANRESOURCES_SERVICE_ID);
        String url = serviceInstance.getUri().toString();
        url = url + "/api/tochucs/" + toChucId;
        RestTemplate restTemplate = new RestTemplate();
        ToChucResponse toChuc = restTemplate.getForObject(url, ToChucResponse.class);

        return toChuc;
    }
}
