package com.spm.teacherhelperv2.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description: Tree
 * date: 2020/5/18 10:28
 * author: Zhangjr
 * version: 1.0
 */
@Data
public class Tree<T> {
    /**
     * 节点ID
     */
    private String id;
    /**
     * 图标
     */
    private String icon;
    /**
     * url
     */
    private String url;
    /**
     * 显示节点文本
     */
    private String text;
    /**
     * 节点属性
     */
    private Map<String, Object> attributes;

    /**
     * 节点的子节点
     */
    private List<Tree<T>> children = new ArrayList<>();
    /**
     * 父ID
     */
    private String parentId;
    /**
     * 是否有父节点
     */
    private boolean hasParent = false;
    /**
     * 是否有子节点
     */
    private boolean hasChildren = false;

    public Tree() {
        super();
    }

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
