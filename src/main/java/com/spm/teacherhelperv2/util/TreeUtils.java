package com.spm.teacherhelperv2.util;

import com.spm.teacherhelperv2.entity.Tree;

import java.util.*;

/**
 * description: TreeUtils
 * date: 2020/5/18 10:51
 * author: Zhangjr
 * version: 1.0
 */
public class TreeUtils {
    private TreeUtils(){};

    public static <T> Tree<T> buildTree(List<Tree<T>> nodes){
        if(nodes==null){
            return null;
        }
        List<Tree<T>> topNodes=new ArrayList<>();
        nodes.forEach(node -> {
            String pid=node.getParentId();
            if(pid==null||"0".equals(pid)){
                topNodes.add(node);
                return;
            }
            for(Tree<T> parent:nodes){
                String id=parent.getId();
                if(id!=null&&id.equals(pid)){
                    parent.getChildren().add(node);
                    parent.setHasChildren(true);
                    node.setHasParent(true);
                    return;
                }
            }
        });
        Tree<T> root=new Tree<>();
        root.setId("0");
        root.setParentId("");
        root.setHasParent(false);
        root.setHasChildren(true);
        root.setChildren(topNodes);
        root.setText("根节点");
        return root;

    }
}
