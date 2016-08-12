package com.example.canrom7.mowa.DBbean;

import bean.Comment;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Canrom7 on 2016/8/12 .
 * Mailbox canrom7@163.com .
 */
public class Note extends BmobObject{
    private Integer noteId;
    private Integer noteType;
    private String noteAuthor;
    private String noteTitle;
    private String noteInfo;
    private String editSite;
    private BmobFile noteImage1;
    private BmobFile noteImage2;
    private BmobFile noteImage3;
    private User noteFans;
    private User noteLikes;
    private Comment noteComment;
    private Integer noteState;

    public String getEditSite() {
        return editSite;
    }

    public void setEditSite(String editSite) {
        this.editSite = editSite;
    }

    public String getNoteAuthor() {
        return noteAuthor;
    }

    public void setNoteAuthor(String noteAuthor) {
        this.noteAuthor = noteAuthor;
    }

    public Comment getNoteComment() {
        return noteComment;
    }

    public void setNoteComment(Comment noteComment) {
        this.noteComment = noteComment;
    }

    public User getNoteFans() {
        return noteFans;
    }

    public void setNoteFans(User noteFans) {
        this.noteFans = noteFans;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public BmobFile getNoteImage1() {
        return noteImage1;
    }

    public void setNoteImage1(BmobFile noteImage1) {
        this.noteImage1 = noteImage1;
    }

    public BmobFile getNoteImage2() {
        return noteImage2;
    }

    public void setNoteImage2(BmobFile noteImage2) {
        this.noteImage2 = noteImage2;
    }

    public BmobFile getNoteImage3() {
        return noteImage3;
    }

    public void setNoteImage3(BmobFile noteImage3) {
        this.noteImage3 = noteImage3;
    }

    public String getNoteInfo() {
        return noteInfo;
    }

    public void setNoteInfo(String noteInfo) {
        this.noteInfo = noteInfo;
    }

    public User getNoteLikes() {
        return noteLikes;
    }

    public void setNoteLikes(User noteLikes) {
        this.noteLikes = noteLikes;
    }

    public Integer getNoteState() {
        return noteState;
    }

    public void setNoteState(Integer noteState) {
        this.noteState = noteState;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public Integer getNoteType() {
        return noteType;
    }

    public void setNoteType(Integer noteType) {
        this.noteType = noteType;
    }

    @Override
    public String toString() {
        return "Note[" +
                "editSite='" + editSite + '\'' +
                ", noteId=" + noteId +
                ", getObjectId=" + getObjectId() +
                ", noteType=" + noteType +
                ", noteAuthor='" + noteAuthor + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteInfo='" + noteInfo + '\'' +
                ", noteImage1=" + noteImage1 +
                ", noteImage2=" + noteImage2 +
                ", noteImage3=" + noteImage3 +
                ", noteFans=" + noteFans +
                ", noteLikes=" + noteLikes +
                ", noteComment=" + noteComment +
                ", noteState=" + noteState +
                ']';
    }
}
