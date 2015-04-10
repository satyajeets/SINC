package caching;

import java.util.HashMap;
import java.util.List;


/**
 * Created by rushabhmehta91 on 4/6/15.
 */
public class Content {
    public HashMap<Integer, Integer> listofScoreOnInterfaces;
    private int contentId;
    private String contentName;
    private int maxNScore;
    private int timeToLive;
    private List<Integer> trail;
    private long sizeInBytes;
    private Object contentCache;

    // constructors start
    public Content(int contentId, String contentName, List<Integer> trail, long sizeInBytes, Object contentCache) {
        this.contentId = contentId;

        this.contentName = contentName;
        this.contentCache = contentCache;
        this.maxNScore = 100;
        this.timeToLive = 999999;
        this.trail = trail;
        this.sizeInBytes = sizeInBytes;
    }

    public Content(int contentId, String contentName, int maxNScore, int timeToLive, List<Integer> trail, long sizeInBytes, Object contentCache) {
        this.contentId = contentId;

        this.contentName = contentName;
        this.maxNScore = maxNScore;
        this.timeToLive = timeToLive;
        this.trail = trail;
        this.sizeInBytes = sizeInBytes;
        this.contentCache = contentCache;
    }
    // constructors ends

    //getter and setters starts
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public int getMaxNScore() {
        return maxNScore;
    }

    public void setMaxNScore(int maxNScore) {
        this.maxNScore = maxNScore;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public HashMap<Integer, Integer> getListofScoreOnInterfaces() {
        return listofScoreOnInterfaces;
    }

    public void setListofScoreOnInterfaces(HashMap<Integer, Integer> listofScoreOnInterfaces) {
        this.listofScoreOnInterfaces = listofScoreOnInterfaces;
    }

    public List<Integer> getTrail() {
        return trail;
    }

    public void setTrail(List<Integer> trail) {
        this.trail = trail;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public Object getContentCache() {
        return contentCache;
    }

    public void setContentCache(Object contentCache) {
        this.contentCache = contentCache;
    }
    //getter and setters ends

}