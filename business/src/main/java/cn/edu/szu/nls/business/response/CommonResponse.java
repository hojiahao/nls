package cn.edu.szu.nls.business.response;

import lombok.Data;
import lombok.NoArgsConstructor;

// @Data的作用是提供类的get、set、equals、hashCode、canEqual、toString方法
@Data
@NoArgsConstructor
public class CommonResponse<T> {
    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回泛型数据，自定义类型
     * 使用泛型 T 而不是 Object，主要是为了提供更高的类型安全性和灵活性。
     * 通过定义 T，在类的实例化时可以指定具体的类型，编译器能在编译期间进行类型检查，从而避免了运行时强制类型转换带来的潜在错误，同时提高了代码的可读性和可维护性。
     */
    private T content;

    public CommonResponse(T content) {
        this.content = content;
    }

    public CommonResponse(boolean success, String msg, T content) {
        this.success = success;
        this.msg = msg;
        this.content = content;
    }
}
