package us.codecraft.webmagic.samples.tdt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * t_tdt_event
 * @author 
 */
public class TdtEvent implements Serializable {
    private String id;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 事件参数
     */
    private String params;

    /**
     * 事件描述
     */
    private String eventDesc;

    /**
     * 事件所属类名
     */
    private String belongClassName;

    /**
     * 抓取网页
     */
    private String fromUrl;

    /**
     * 抓取时间
     */
    private Date addTime;

    /**
     * 抓取版本
     */
    private String version;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getBelongClassName() {
        return belongClassName;
    }

    public void setBelongClassName(String belongClassName) {
        this.belongClassName = belongClassName;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TdtEvent other = (TdtEvent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEventName() == null ? other.getEventName() == null : this.getEventName().equals(other.getEventName()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getEventDesc() == null ? other.getEventDesc() == null : this.getEventDesc().equals(other.getEventDesc()))
            && (this.getBelongClassName() == null ? other.getBelongClassName() == null : this.getBelongClassName().equals(other.getBelongClassName()))
            && (this.getFromUrl() == null ? other.getFromUrl() == null : this.getFromUrl().equals(other.getFromUrl()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEventName() == null) ? 0 : getEventName().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getEventDesc() == null) ? 0 : getEventDesc().hashCode());
        result = prime * result + ((getBelongClassName() == null) ? 0 : getBelongClassName().hashCode());
        result = prime * result + ((getFromUrl() == null) ? 0 : getFromUrl().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eventName=").append(eventName);
        sb.append(", params=").append(params);
        sb.append(", eventDesc=").append(eventDesc);
        sb.append(", belongClassName=").append(belongClassName);
        sb.append(", fromUrl=").append(fromUrl);
        sb.append(", addTime=").append(addTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}