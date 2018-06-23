package com.api.webservice.utils;

/**
 * Created by caihe on 2018/6/15.
 */
public class EnumUtils {

    public enum Role {
        USER(1, "用户"),
        ADMINISTRATOR(2, "管理员");

        public long key;
        public String values;

        private Role(long _key, String _values) {
            this.key = _key;
            this.values = _values;
        }
    }

    public enum LoginStatus {

        DEFAULT(0, ""),
        SUCCESS(1, "成功"),
        FAILURE(2, "失败"),
        LOCK(2, "锁定");

        public int key;
        public String values;

        private LoginStatus(int _key, String _values) {
            this.key = _key;
            this.values = _values;
        }
    }

}