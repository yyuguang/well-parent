package com.lnzz.eduservice.pojo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：EduChapterVo
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/16 14:33
 * @Description
 */
@Data
public class EduChapterVo {

    private String id;

    private String title;

    private List<EduVideoVo> children = new ArrayList<>();
}
