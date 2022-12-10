package us.codecraft.webmagic.samples.tdt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * t_tdt_method
 * @author 
 */
public class TdtMethod implements Serializable {
    private String id;

    /**
     * 方法名称
     */
    private String methodName;

    private String methodCall;

    /**
     * 返回值类型
     */
    private String returnType;

    /**
     * 方法参数：json字符串
     */
    private String params;

    /**
     * 方法描述
     */
    private String methodDesc;

    /**
     * 原始函数签名
     */
    private String rawMethodSign;

    /**
     * 方法分类
     */
    private String methodCate;

    /**
     * 方法所属类名
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodCall() {
        return methodCall;
    }

    public void setMethodCall(String methodCall) {
        this.methodCall = methodCall;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public String getRawMethodSign() {
        return rawMethodSign;
    }

    public void setRawMethodSign(String rawMethodSign) {
        this.rawMethodSign = rawMethodSign;
    }

    public String getMethodCate() {
        return methodCate;
    }

    public void setMethodCate(String methodCate) {
        this.methodCate = methodCate;
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
        TdtMethod other = (TdtMethod) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMethodName() == null ? other.getMethodName() == null : this.getMethodName().equals(other.getMethodName()))
            && (this.getMethodCall() == null ? other.getMethodCall() == null : this.getMethodCall().equals(other.getMethodCall()))
            && (this.getReturnType() == null ? other.getReturnType() == null : this.getReturnType().equals(other.getReturnType()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getMethodDesc() == null ? other.getMethodDesc() == null : this.getMethodDesc().equals(other.getMethodDesc()))
            && (this.getRawMethodSign() == null ? other.getRawMethodSign() == null : this.getRawMethodSign().equals(other.getRawMethodSign()))
            && (this.getMethodCate() == null ? other.getMethodCate() == null : this.getMethodCate().equals(other.getMethodCate()))
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
        result = prime * result + ((getMethodName() == null) ? 0 : getMethodName().hashCode());
        result = prime * result + ((getMethodCall() == null) ? 0 : getMethodCall().hashCode());
        result = prime * result + ((getReturnType() == null) ? 0 : getReturnType().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getMethodDesc() == null) ? 0 : getMethodDesc().hashCode());
        result = prime * result + ((getRawMethodSign() == null) ? 0 : getRawMethodSign().hashCode());
        result = prime * result + ((getMethodCate() == null) ? 0 : getMethodCate().hashCode());
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
        sb.append(", methodName=").append(methodName);
        sb.append(", methodCall=").append(methodCall);
        sb.append(", returnType=").append(returnType);
        sb.append(", params=").append(params);
        sb.append(", methodDesc=").append(methodDesc);
        sb.append(", rawMethodSign=").append(rawMethodSign);
        sb.append(", methodCate=").append(methodCate);
        sb.append(", belongClassName=").append(belongClassName);
        sb.append(", fromUrl=").append(fromUrl);
        sb.append(", addTime=").append(addTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}