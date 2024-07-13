package cn.hamm.sdk.module.demo;

import cn.hamm.sdk.common.base.AirResponse;

/**
 * <h1>测试对象返回</h1>
 *
 * @author Hamm.cn
 */
public class DemoResponse extends AirResponse<DemoResponse> {
    private DemoResponse.DemoResponseModel data;

    public DemoResponse.DemoResponseModel getData() {
        return data;
    }

    public void setData(DemoResponse.DemoResponseModel data) {
        this.data = data;
    }

    public static class DemoResponseModel {
        private String name;

        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
