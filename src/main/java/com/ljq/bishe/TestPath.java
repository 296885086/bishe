package com.ljq.bishe;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class TestPath {
    public static void main(String args[]){
        System.out.println(Class.class.getClass().getResource("/").getPath());
        System.getProperty("user.dir");
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        System.out.println(com.getPath());
    }
}
