package io.yzecho.constant;

/**
 * @author yzecho
 * @desc
 * @date 27/07/2020 15:49
 */
public enum OrderEventType {

    CREATE("创建订单"),

    CANCEL("取消订单");

    private final String message;

    OrderEventType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
