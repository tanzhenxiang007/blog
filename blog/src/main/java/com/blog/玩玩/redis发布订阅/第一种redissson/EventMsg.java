package com.blog.玩玩.redis发布订阅.第一种redissson;

/**
 * <p>
 *
 * </p>
 *
 * @ClassName EventMsg
 * @Author Bianhl(Reject Copy This Tag)
 * @Description TODO 描述文件用途
 * @Since create in 2023/7/11 11:14
 * @Version v1.0
 * @Copyright Copyright (c) 2023
 * @Company 广州云趣信息科技有限公司
 */
public class EventMsg {


    private String event;


    private String taskId;

    public EventMsg(String event, String taskId) {
        this.event = event;
        this.taskId = taskId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "EventMsg{" +
                "event='" + event + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
