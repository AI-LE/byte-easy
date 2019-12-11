package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;

public class WechatContent extends BaseEntity {

    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章作者
     */
    private String author;
    /**
     * 文章路径
     */
    private String path;
    /**
     * 文章发表的公众号
     */
    private String gzh;
    /**
     * 文章创建时间
     */
    private String createtime;
    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章关键字
     * @return
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGzh() {
        return gzh;
    }

    public void setGzh(String gzh) {
        this.gzh = gzh;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }




}
