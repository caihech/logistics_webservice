package com.api.webservice.utils;

/**
 * Created by caihe on 2018/6/15.
 */
public class EnumUtils {

    public enum ROLE {
        USER(1, "用户"),
        ADMINISTRATOR(2, "管理员");

        public long key;
        public String values;

        private ROLE(long _key, String _values) {
            this.key = _key;
            this.values = _values;
        }
    }

    public enum LOGIN_STATUS {

        DEFAULT(0, ""),
        SUCCESS(1, "成功"),
        FAILURE(2, "失败"),
        LOCK(2, "锁定");

        public long key;
        public String values;

        private LOGIN_STATUS(long _key, String _values) {
            this.key = _key;
            this.values = _values;
        }
    }

}