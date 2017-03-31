package com.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MessageController {

    private final OAuth2RestTemplate resourceServerProxy;

    @Value("${resource.server.url}")
    private String resourceServer;

    @Autowired
    public MessageController(OAuth2RestTemplate resourceServerProxy) {
        this.resourceServerProxy = resourceServerProxy;
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.GET)
    public Map getMessage() {
        return resourceServerProxy.getForObject(resourceServer, Map.class);
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.POST)
    public void saveMessage(@RequestBody String newMessage) {
        resourceServerProxy.postForLocation(resourceServer, newMessage);
    }
}
