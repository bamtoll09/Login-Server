package me.bamtoll.lee.loginserver.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    public enum Types {
        NOTICE, NORMAL
    }

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("contents")
    @Expose
    private String contents;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("writer")
    @Expose
    private String writer;

    @SerializedName("type")
    @Expose
    private int type;

    public Post(String _id, String title, String contents, String date, String writer, int type) {
        this._id = _id;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.writer = writer;
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }
}
