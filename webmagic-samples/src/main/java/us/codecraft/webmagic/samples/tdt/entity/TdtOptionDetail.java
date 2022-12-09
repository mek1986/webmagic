package us.codecraft.webmagic.samples.tdt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * t_tdt_option_detail
 * @author 
 */
public class TdtOptionDetail implements Serializable {
    private String id;

    /**
     * 对象名称
     */
    private String objName;

    /**
     * 对象属性名称
     */
    private String attrName;

    /**
     * 对象属性类型
     */
    private String attrType;

    /**
     * 对象属性默认值
     */
    private String attrDefaultValue;

    /**
     * 对象属性描述
     */
    private String attrDesc;

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

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrDefaultValue() {
        return attrDefaultValue;
    }

    public void setAttrDefaultValue(String attrDefaultValue) {
        this.attrDefaultValue = attrDefaultValue;
    }

    public String getAttrDesc() {
        return attrDesc;
    }

    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc;
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
        TdtOptionDetail other = (TdtOptionDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getObjName() == null ? other.getObjName() == null : this.getObjName().equals(other.getObjName()))
            && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(other.getAttrName()))
            && (this.getAttrType() == null ? other.getAttrType() == null : this.getAttrType().equals(other.getAttrType()))
            && (this.getAttrDefaultValue() == null ? other.getAttrDefaultValue() == null : this.getAttrDefaultValue().equals(other.getAttrDefaultValue()))
            && (this.getAttrDesc() == null ? other.getAttrDesc() == null : this.getAttrDesc().equals(other.getAttrDesc()))
            && (this.getFromUrl() == null ? other.getFromUrl() == null : this.getFromUrl().equals(other.getFromUrl()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getObjName() == null) ? 0 : getObjName().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getAttrType() == null) ? 0 : getAttrType().hashCode());
        result = prime * result + ((getAttrDefaultValue() == null) ? 0 : getAttrDefaultValue().hashCode());
        result = prime * result + ((getAttrDesc() == null) ? 0 : getAttrDesc().hashCode());
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
        sb.append(", objName=").append(objName);
        sb.append(", attrName=").append(attrName);
        sb.append(", attrType=").append(attrType);
        sb.append(", attrDefaultValue=").append(attrDefaultValue);
        sb.append(", attrDesc=").append(attrDesc);
        sb.append(", fromUrl=").append(fromUrl);
        sb.append(", addTime=").append(addTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}