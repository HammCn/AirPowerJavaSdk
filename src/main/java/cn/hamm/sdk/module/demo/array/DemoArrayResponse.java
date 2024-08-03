package cn.hamm.sdk.module.demo.array;

import cn.hamm.sdk.common.base.AbstractResponse;
import cn.hamm.sdk.common.base.AirJson;
import cn.hamm.sdk.module.demo.DemoResponseModel;

import java.util.List;

/**
 * <h1>测试返回数组</h1>
 *
 * @author Hamm.cn
 */
public class DemoArrayResponse extends AbstractResponse<DemoArrayResponse> {
    private List<DemoResponseModel> list;

    public List<DemoResponseModel> getList() {
        return list;
    }

    public DemoArrayResponse setList(List<DemoResponseModel> list) {
        this.list = list;
        return this;
    }

    @Override
    public DemoArrayResponse parseData(String data) {
        return this.setList(AirJson.parseList(data, DemoResponseModel[].class));
    }
}
