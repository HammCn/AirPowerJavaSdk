package cn.hamm.sdk.model.demo;

import cn.hamm.sdk.common.base.AbstractResponse;

public class DemoResponse extends AbstractResponse<DemoResponse> {
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
