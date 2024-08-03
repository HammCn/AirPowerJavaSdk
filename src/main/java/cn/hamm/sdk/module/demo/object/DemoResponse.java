package cn.hamm.sdk.module.demo.object;

import cn.hamm.sdk.common.base.AbstractResponse;
import cn.hamm.sdk.common.base.AirJson;
import cn.hamm.sdk.module.demo.DemoResponseModel;

/**
 * <h1>测试对象返回</h1>
 *
 * @author Hamm.cn
 */
public class DemoResponse extends AbstractResponse<DemoResponse> {
    private DemoResponseModel data;

    public DemoResponseModel getData() {
        return data;
    }

    public DemoResponse setData(DemoResponseModel data) {
        this.data = data;
        return this;
    }

    /**
     * <h2>解析数据</h2>
     *
     * @param data 解密后的data数据
     */
    @Override
    public DemoResponse parseData(String data) {
        return this.setData(AirJson.parse(data, DemoResponseModel.class));
    }
}
