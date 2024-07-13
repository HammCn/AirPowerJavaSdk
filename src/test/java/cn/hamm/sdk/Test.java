package cn.hamm.sdk;

import cn.hamm.sdk.common.base.AirClient;
import cn.hamm.sdk.common.base.AirConfig;
import cn.hamm.sdk.common.base.AirConstant;
import cn.hamm.sdk.common.enums.AirArithmetic;
import cn.hamm.sdk.module.demo.DemoRequest;
import cn.hamm.sdk.module.demo.DemoResponse;
import cn.hamm.sdk.module.demolist.DemoArrayRequest;
import cn.hamm.sdk.module.demolist.DemoArrayResponse;

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
        System.out.println("RESPONSE:" + response);
    }

    private static void testArray() {
        AirClient client = getAirClient();
        DemoArrayRequest demoArrayRequest = new DemoArrayRequest();
        demoArrayRequest.setName("Hamm");
        demoArrayRequest.setAge(18);
        DemoArrayResponse response = client.request(demoArrayRequest);
        System.out.println("RESPONSE:" + response);
    }

    private static AirClient getAirClient() {
        AirConfig config = AirConfig.create()
                .setAppKey("hIGgB8mx3kNx0UKCKdFiYbFeSVJe6tNP")
                .setAppSecret("sjaNnvKO7pSf/LCe//Z+rhwmv4gJtvp4MaJ4boqBYyg=")
                .setPublicKey(" -----BEGIN PUBLIC KEY-----\n" +
                        " MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyoM7Sj+dNu5B8wzmCfUu\n" +
                        " 9baFOvt/BQk1AzT7+H557y9ax8D5Hlf6/zUVZsgr/fseistBTYZHuL5BzjyGl4q5\n" +
                        " UaIgqcZo6rYuDYzqELMdvMOxYumtQ1d3kJ+41UJD1Va7bdKnWrWVzOI11W/73ZX5\n" +
                        " 7dm2t5eguqKTgQ25pORhytoUn4iJZ3C1DHsYzgOc6EB0n3VtcxT9adBnKhuRcEhZ\n" +
                        " Q/1g5vnyYJFonBCyBEJBB7hd4m0kNBeL25zSuer3io5UpCrDuVE0AEuXDFqkU1mE\n" +
                        " Dd1H232OSk5WDgRJE7NtDb3G/fjZe//pHiXa9KJst7PWOUL3O5Wo6oMliIC2wm4J\n" +
                        " MwIDAQAB\n" +
                        " -----END PUBLIC KEY-----")
                .setArithmetic(AirArithmetic.AES)
                .setGateway(AirConstant.GATEWAY_LOCAL);
        return AirClient.create(config);
    }
}