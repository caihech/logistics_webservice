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

}