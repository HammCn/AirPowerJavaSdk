package cn.hamm.sdk.module.demolist;

import cn.hamm.sdk.common.base.AirResponse;

import java.util.List;

/**
 * <h1>测试返回数组</h1>
 *
 * @author Hamm.cn
 */
public class DemoArrayResponse extends AirResponse<DemoArrayResponse> {
    private List<DemoResponseModel> data;

    public List<DemoResponseModel> getData() {
        return data;
    }

    public void setData(List<DemoResponseModel> data) {
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
