package com.muhammet.config;

public class RestApis {
    public static final String DEV = "/dev";
    public static final String VERSION = "/v1";
    public static final String ENDPOINT = DEV+VERSION;
    public static final String POST = ENDPOINT+ "/post";
    public static final String COMMENT = ENDPOINT+ "/comment";

    public static final String CREATE_POST = "/create-post";
    public static final String GET_ALL = "/get-all";
}
