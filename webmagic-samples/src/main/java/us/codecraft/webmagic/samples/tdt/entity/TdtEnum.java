package us.codecraft.webmagic.samples.tdt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * t_tdt_enum
 * @author 
 */
public class TdtEnum implements Serializable {
    private String id;

    /**
     * 常量名
     */
    private String constName;

    /**
     * 常量值描述
     */
    private String constValueDesc;

    /**
     * 常量值
     */
    private String constValue;

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
     * 抓取版本
     */
    private String version;

    /**
     * 抓取时间
     */
    private Date addTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName = constName;
    }

    public String getConstValueDesc() {
        return constValueDesc;
    }

    public void setConstValueDesc(String constValueDesc) {
        this.constValueDesc = constValueDesc;
    }

    public String getConstValue() {
        return constValue;
    }

    public void setConstValue(String constValue) {
        this.constValue = constValue;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
        TdtEnum other = (TdtEnum) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getConstName() == null ? other.getConstName() == null : this.getConstName().equals(other.getConstName()))
            && (this.getConstValueDesc() == null ? other.getConstValueDesc() == null : this.getConstValueDesc().equals(other.getConstValueDesc()))
            && (this.getConstValue() == null ? other.getConstValue() == null : this.getConstValue().equals(other.getConstValue()))
            && (this.getModuleName1() == null ? other.getModuleName1() == null : this.getModuleName1().equals(other.getModuleName1()))
            && (this.getModuleName2() == null ? other.getModuleName2() == null : this.getModuleName2().equals(other.getModuleName2()))
            && (this.getFromUrl() == null ? other.getFromUrl() == null : this.getFromUrl().equals(other.getFromUrl()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getConstName() == null) ? 0 : getConstName().hashCode());
        result = prime * result + ((getConstValueDesc() == null) ? 0 : getConstValueDesc().hashCode());
        result = prime * result + ((getConstValue() == null) ? 0 : getConstValue().hashCode());
        result = prime * result + ((getModuleName1() == null) ? 0 : getModuleName1().hashCode());
        result = prime * result + ((getModuleName2() == null) ? 0 : getModuleName2().hashCode());
        result = prime * result + ((getFromUrl() == null) ? 0 : getFromUrl().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", constName=").append(constName);
        sb.append(", constValueDesc=").append(constValueDesc);
        sb.append(", constValue=").append(constValue);
        sb.append(", moduleName1=").append(moduleName1);
        sb.append(", moduleName2=").append(moduleName2);
        sb.append(", fromUrl=").append(fromUrl);
        sb.append(", version=").append(version);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}