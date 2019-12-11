package com.ws.common.util;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Data
public class DateUtil {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    java.util.Date date = new java.util.Date();
}
