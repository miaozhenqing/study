package com.example.qzm.study.function.file.watchservice;

import java.nio.file.*;
import java.util.List;

/**
 * @ClassName watchservice
 * @Description 监控文件变动
 * @Version 1.0
 **/
public class watchservice {
    public static void main(String[] args) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            String filePath = "D:\\file_watchservice";
            Paths.get(filePath).register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
            while (true){
                WatchKey watchKey = watchService.take();
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for(WatchEvent event:watchEvents){
                    if(StandardWatchEventKinds.ENTRY_CREATE == event.kind()){
                        System.out.println("创建：[" + filePath + "/" + event.context() + "]");
                    }
                    if(StandardWatchEventKinds.ENTRY_MODIFY == event.kind()){
                        System.out.println("修改：[" + filePath + "/" + event.context() + "]");
                    }
                    if(StandardWatchEventKinds.ENTRY_DELETE == event.kind()){
                        System.out.println("删除：[" + filePath + "/" + event.context() + "]");
                    }
                    watchKey.reset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
