package us.codecraft.webmagic.samples.tdt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * t_tdt_class
 * @author 
 */
public class TdtClass implements Serializable {
    private String id;

    /**
     * 类名
     */
    private String className;

    /**
     * 类描述
     */
    private String classDesc;

    /**
     * 所属1级模块名称
     */
    private String moduleName1;

    /**
     * 所属2级模块名称
     */
    private String moduleName2;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getModuleName1() {
        return moduleName1;
    }

    public void setModuleName1(String moduleName1) {
        this.moduleName1 = moduleName1;
    }

    public String getModuleName2() {
        return moduleName2;
    }

    public void setModuleName2(String moduleName2) {
        this.moduleName2 = moduleName2;
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
        TdtClass other = (TdtClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassName() == null ? other.getClassName() == null : this.getClassName().equals(other.getClassName()))
            && (this.getClassDesc() == null ? other.getClassDesc() == null : this.getClassDesc().equals(other.getClassDesc()))
            && (this.getModuleName1() == null ? other.getModuleName1() == null : this.getModuleName1().equals(other.getModuleName1()))
            && (this.getModuleName2() == null ? other.getModuleName2() == null : this.getModuleName2().equals(other.getModuleName2()))
            && (this.getFromUrl() == null ? other.getFromUrl() == null : this.getFromUrl().equals(other.getFromUrl()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());
        result = prime * result + ((getClassDesc() == null) ? 0 : getClassDesc().hashCode());
        result = prime * result + ((getModuleName1() == null) ? 0 : getModuleName1().hashCode());
        result = prime * result + ((getModuleName2() == null) ? 0 : getModuleName2().hashCode());
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
        sb.append(", className=").append(className);
        sb.append(", classDesc=").append(classDesc);
        sb.append(", moduleName1=").append(moduleName1);
        sb.append(", moduleName2=").append(moduleName2);
        sb.append(", fromUrl=").append(fromUrl);
        sb.append(", addTime=").append(addTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}