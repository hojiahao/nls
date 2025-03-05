package cn.edu.szu.nls.business.request;

import lombok.Data;

// @Data的作用是提供类的get、set、equals、hashCode、canEqual、toString方法
@Data
public class DemoQueryRequest {
    private String mobile;
}
