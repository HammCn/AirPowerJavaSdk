package cn.hamm.sdk.model.demo;

import cn.hamm.sdk.common.base.AbstractRequest;

public class DemoRequest extends AbstractRequest<DemoResponse> {
    private String name;

    private Integer age;

    /**
     * <h2>获取API地址</h2>
     *
     * @return API地址
     */
    @Override
    protected String getApiUrl() {
        return "test/test";
    }

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
