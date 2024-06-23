package cn.hamm.sdk;

import cn.hamm.sdk.common.base.AirClient;
import cn.hamm.sdk.common.base.AirConfig;
import cn.hamm.sdk.common.base.AirConstant;
import cn.hamm.sdk.common.enums.AirArithmetic;
import cn.hamm.sdk.model.demo.DemoRequest;
import cn.hamm.sdk.model.demo.DemoResponse;

public class Test {
    public static void main(String... args) {
        AirConfig config = AirConfig.create()
                .setAppKey("DdhGUoOec8OQBqgn4tevJEYpoMrnuiRk")
                .setAppSecret("+SSKeF9k0G3f7pZz6PUmE1C4/HUbGsnTObk4s9ljBe0=")
                .setPublicKey("123123")
                .setArithmetic(AirArithmetic.AES)
                .setGateway(AirConstant.GATEWAY_LOCAL);
        AirClient client = AirClient.create(config);
        DemoRequest demoRequest = new DemoRequest();
        demoRequest.setName("Hamm");
        demoRequest.setAge(18);
        DemoResponse response = client.request(demoRequest);
        System.out.println("RESPONSE:" + response);
    }
}
