package com.gjw.controller;

import com.gjw.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gjw19 on 2018/6/17.
 */
@RestController
public class BasicController {
    @Autowired
    public RedisOperator redis;

    public static final String USER_REDIS_SESSION = "user-redis-session";

    // 文件保存的命名空间
    public static final String FILE_SPACE = "D:/java程序/mini-douyin/upload";

    // ffmpeg所在目录
    public static final String FFMPEG_EXE = "D:\\ffmpeg\\bin\\ffmpeg.exe";

    // 每页分页的记录数
    public static final Integer PAGE_SIZE = 5;
}
