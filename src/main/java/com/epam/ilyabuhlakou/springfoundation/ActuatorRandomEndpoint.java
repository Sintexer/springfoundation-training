package com.epam.ilyabuhlakou.springfoundation;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Random;

@Endpoint(id = "random")
@Component
public class ActuatorRandomEndpoint {

    private final Random random = new Random();

    @ReadOperation
    public RandomEndPointResponse getRandomData() {
        return new RandomEndPointResponse(random.nextBoolean(), random.nextInt());
    }

    private record RandomEndPointResponse(boolean randomBoolean, int randomInt) {
    }
}
