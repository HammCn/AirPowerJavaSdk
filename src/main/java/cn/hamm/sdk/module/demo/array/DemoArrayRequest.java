package cn.hamm.sdk.module.demo.array;

import cn.hamm.sdk.common.base.AbstractRequest;

/**
 * <h2>测试数组请求</h2>
 *
 * @author Hamm.cn
 */
public class DemoArrayRequest extends AbstractRequest<DemoArrayResponse> {
    private String name;

    private Integer age;

    /**
     * <h2>获取API地址</h2>
     *
     * @return API地址
     */
    @Override
    protected String getApiUrl() {
        return "test/testArr";
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
