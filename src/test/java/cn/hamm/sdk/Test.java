package cn.hamm.sdk;

import cn.hamm.sdk.common.base.AirClient;
import cn.hamm.sdk.common.base.AirConfig;
import cn.hamm.sdk.common.base.AirConstant;
import cn.hamm.sdk.common.base.AirJson;
import cn.hamm.sdk.common.enums.AirArithmetic;
import cn.hamm.sdk.module.demo.array.DemoArrayRequest;
import cn.hamm.sdk.module.demo.array.DemoArrayResponse;
import cn.hamm.sdk.module.demo.object.DemoRequest;
import cn.hamm.sdk.module.demo.object.DemoResponse;

public class Test {
    public static void main(String... args) {
        test();
        testArray();
    }

    private static void test() {
        AirClient client = getAirClient();
        DemoRequest demoRequest = new DemoRequest();
        demoRequest.setName("Hamm");
        demoRequest.setAge(18);
        DemoResponse response = client.request(demoRequest);
        System.out.println("RESPONSE:" + AirJson.toString(response));
    }

    private static void testArray() {
        AirClient client = getAirClient();
        DemoArrayRequest demoArrayRequest = new DemoArrayRequest();
        demoArrayRequest.setName("Hamm");
        demoArrayRequest.setAge(18);
        DemoArrayResponse response = client.request(demoArrayRequest);
        System.out.println("RESPONSE:" + AirJson.toString(response));
    }

    private static AirClient getAirClient() {
        AirConfig config = AirConfig.create()
                .setAppKey("Lm3icGBsGdL5Tvr50x8x5v8NLUsHQyh3")
                .setAppSecret("c+ulfDzAQFhVGq2IH14zBLgo7JVHNzcHwurNZFX2d7Q=")
                .setPublicKey("-----BEGIN PUBLIC KEY-----\n" +
                        "    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu0IVxyryrJKIqML5C2Ji\n" +
                        "4wnpZ0+J+ZGgcOgDJP6N4qP1mWv2dc4UxQiLu1/Qf77qR3gwo9hct3uR5vnKN8CU\n" +
                        "7j6ax4R4NFuMh5JPQ7yet/FLIp8oq8BAHFcY86MoWrvq7Vk0CbXw14LosEacY2NZ\n" +
                        "    IIPB0dJNUM+fR8GFwnw4sFRAgk1gMpQ4wq927KBpeRIhz41sm48SFJKn67/xnytw\n" +
                        "    AOstsgdwwIhA/qn/EkuIVILhQ4nllqqp06ZsEMoJmBN/l13gnLdl2pmGF6bwK3zL\n" +
                        "5OyRdQrYrQMDGRH7j/614+wiRfeGDfYJvd78oNmrkMo2RPXR1D6RP9pFe/YPWTt8\n" +
                        "            FwIDAQAB\n" +
                        "-----END PUBLIC KEY-----")
                .setArithmetic(AirArithmetic.AES)
                .setGateway(AirConstant.GATEWAY_LOCAL);
        return AirClient.create(config);
    }
}