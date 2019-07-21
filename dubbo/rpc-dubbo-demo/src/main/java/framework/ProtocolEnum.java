package framework;

public enum ProtocolEnum {

    /**
     * http
     * */
    HTTP,

    /**
     * dubbo
     * */
    DUBBO;

    public static ProtocolEnum getEnumByName(String name) {

//        ProtocolEnum[] values = ProtocolEnum.values();
        ProtocolEnum protocolEnum = ProtocolEnum.valueOf(name);
        if (protocolEnum != null) {
            return protocolEnum;
        }else {
            return ProtocolEnum.HTTP;
        }

    }
}
