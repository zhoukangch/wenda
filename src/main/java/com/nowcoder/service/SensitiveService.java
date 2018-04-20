package com.nowcoder.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class SensitiveService implements InitializingBean {
    /*
    *默认敏感词替换符
    */
    private static final String DEFAULT_REPLACEMENT="***";

    private class TreeNode{
        private char content;
        private Map<Character,TreeNode> subNode;
        private boolean isEnd;
        TreeNode(char content){
            this.content=content;
            isEnd=false;
            subNode=new HashMap<Character, TreeNode>();
        }

        TreeNode getSubNode(char key){
            if(this.subNode.containsKey(key)){
                return this.subNode.get(key);
            }else{
                return null;
            }
        }

        boolean getIsEnd(){
            return this.isEnd;
        }

        void setIsEnd(boolean isEnd){
            this.isEnd=isEnd;
        }
    }

    private TreeNode root=new TreeNode(' ');

    private void addWord(String word){
        TreeNode current=root;
        for(char c:word.toCharArray()){
            TreeNode next=current.getSubNode(c);
            if(next==null){
                next=new TreeNode(c);
                current.subNode.put(c,next);
            }
            current=next;
        }
        current.setIsEnd(true);
    }

    public String filter(String text){
        if(text==null||text.length()==0) return text;
        StringBuffer sb=new StringBuffer();
        TreeNode current=root;
        int head=0;
        int foot=0;
        while(head<text.length()){
            if(current.getSubNode(text.charAt(foot))==null){
                sb.append(text.charAt(foot));
                head++;
                foot++;
            }else{
                while(foot<text.length()&&current.getSubNode(text.charAt(foot))!=null){
                    current=current.getSubNode(text.charAt(foot));
                    foot++;
                    if(current.getIsEnd()==true){
                        sb.append(DEFAULT_REPLACEMENT);
                        head=foot;
                        current=root;
                        break;
                    }
                }
                while(head!=foot){
                    sb.append(text.charAt(head));
                    head++;
                }
            }
        }
        return sb.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("sensitive.txt");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));

        String str=null;
        while((str=bufferedReader.readLine())!=null){
            addWord(str);
        }
    }
}
