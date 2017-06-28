package com.bovink.androidlearing.bean;

/**
 * @author Retina975
 * @since 2017/06/28
 */

public class ImageFolder {

    private int count;//文件夹下的图片个数
    private String logo;//第一张图片的路径 传这个给小相册图片显示
    private String path;//文件夹路径
    private String name;//文件夹的名字


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
