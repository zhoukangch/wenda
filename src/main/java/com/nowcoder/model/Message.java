package com.nowcoder.model;

import java.util.Date;

public class Message {
    private int id ;
    private  int fromid;
    private int toid;
    private String content;
    private int conversation_id;
    private Date created_date;

    public Message(){}

    public Date getCreated_date() {
        return created_date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromid=" + fromid +
                ", toid=" + toid +
                ", content='" + content + '\'' +
                ", conversation_id=" + conversation_id +
                ", created_date=" + created_date +
                '}';
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getConversation_id() {

        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getToid() {

        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public int getFromid() {

        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
