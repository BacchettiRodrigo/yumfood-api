package br.com.reignited.yumfood.core.web;

import org.springframework.http.MediaType;

public class YumMediaTypes {

    public static final String V1_APPLICATION_MEDIA_VALUE = "application/vnd.yumfood.v1+json";
    public static final MediaType V1_APPLICATION_JSON = MediaType.valueOf(V1_APPLICATION_MEDIA_VALUE);
    public static final String V2_APPLICATION_MEDIA_VALUE = "application/vnd.yumfood.v2+json";
    public static final MediaType V2_APPLICATION_JSON = MediaType.valueOf(V2_APPLICATION_MEDIA_VALUE);

}
