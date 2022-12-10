package us.codecraft.webmagic.samples.tdt.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TdtMethodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public TdtMethodExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("method_name is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("method_name is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("method_name =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("method_name <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("method_name >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("method_name >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("method_name <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("method_name <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("method_name like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("method_name not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("method_name in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("method_name not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("method_name between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("method_name not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodCallIsNull() {
            addCriterion("method_call is null");
            return (Criteria) this;
        }

        public Criteria andMethodCallIsNotNull() {
            addCriterion("method_call is not null");
            return (Criteria) this;
        }

        public Criteria andMethodCallEqualTo(String value) {
            addCriterion("method_call =", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallNotEqualTo(String value) {
            addCriterion("method_call <>", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallGreaterThan(String value) {
            addCriterion("method_call >", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallGreaterThanOrEqualTo(String value) {
            addCriterion("method_call >=", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallLessThan(String value) {
            addCriterion("method_call <", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallLessThanOrEqualTo(String value) {
            addCriterion("method_call <=", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallLike(String value) {
            addCriterion("method_call like", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallNotLike(String value) {
            addCriterion("method_call not like", value, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallIn(List<String> values) {
            addCriterion("method_call in", values, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallNotIn(List<String> values) {
            addCriterion("method_call not in", values, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallBetween(String value1, String value2) {
            addCriterion("method_call between", value1, value2, "methodCall");
            return (Criteria) this;
        }

        public Criteria andMethodCallNotBetween(String value1, String value2) {
            addCriterion("method_call not between", value1, value2, "methodCall");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNull() {
            addCriterion("return_type is null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNotNull() {
            addCriterion("return_type is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeEqualTo(String value) {
            addCriterion("return_type =", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotEqualTo(String value) {
            addCriterion("return_type <>", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThan(String value) {
            addCriterion("return_type >", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("return_type >=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThan(String value) {
            addCriterion("return_type <", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThanOrEqualTo(String value) {
            addCriterion("return_type <=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLike(String value) {
            addCriterion("return_type like", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotLike(String value) {
            addCriterion("return_type not like", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIn(List<String> values) {
            addCriterion("return_type in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotIn(List<String> values) {
            addCriterion("return_type not in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeBetween(String value1, String value2) {
            addCriterion("return_type between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotBetween(String value1, String value2) {
            addCriterion("return_type not between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andParamsIsNull() {
            addCriterion("params is null");
            return (Criteria) this;
        }

        public Criteria andParamsIsNotNull() {
            addCriterion("params is not null");
            return (Criteria) this;
        }

        public Criteria andParamsEqualTo(String value) {
            addCriterion("params =", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotEqualTo(String value) {
            addCriterion("params <>", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThan(String value) {
            addCriterion("params >", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThanOrEqualTo(String value) {
            addCriterion("params >=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThan(String value) {
            addCriterion("params <", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThanOrEqualTo(String value) {
            addCriterion("params <=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLike(String value) {
            addCriterion("params like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotLike(String value) {
            addCriterion("params not like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsIn(List<String> values) {
            addCriterion("params in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotIn(List<String> values) {
            addCriterion("params not in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsBetween(String value1, String value2) {
            addCriterion("params between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotBetween(String value1, String value2) {
            addCriterion("params not between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andMethodDescIsNull() {
            addCriterion("method_desc is null");
            return (Criteria) this;
        }

        public Criteria andMethodDescIsNotNull() {
            addCriterion("method_desc is not null");
            return (Criteria) this;
        }

        public Criteria andMethodDescEqualTo(String value) {
            addCriterion("method_desc =", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescNotEqualTo(String value) {
            addCriterion("method_desc <>", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescGreaterThan(String value) {
            addCriterion("method_desc >", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescGreaterThanOrEqualTo(String value) {
            addCriterion("method_desc >=", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescLessThan(String value) {
            addCriterion("method_desc <", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescLessThanOrEqualTo(String value) {
            addCriterion("method_desc <=", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescLike(String value) {
            addCriterion("method_desc like", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescNotLike(String value) {
            addCriterion("method_desc not like", value, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescIn(List<String> values) {
            addCriterion("method_desc in", values, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescNotIn(List<String> values) {
            addCriterion("method_desc not in", values, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescBetween(String value1, String value2) {
            addCriterion("method_desc between", value1, value2, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andMethodDescNotBetween(String value1, String value2) {
            addCriterion("method_desc not between", value1, value2, "methodDesc");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignIsNull() {
            addCriterion("raw_method_sign is null");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignIsNotNull() {
            addCriterion("raw_method_sign is not null");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignEqualTo(String value) {
            addCriterion("raw_method_sign =", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignNotEqualTo(String value) {
            addCriterion("raw_method_sign <>", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignGreaterThan(String value) {
            addCriterion("raw_method_sign >", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignGreaterThanOrEqualTo(String value) {
            addCriterion("raw_method_sign >=", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignLessThan(String value) {
            addCriterion("raw_method_sign <", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignLessThanOrEqualTo(String value) {
            addCriterion("raw_method_sign <=", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignLike(String value) {
            addCriterion("raw_method_sign like", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignNotLike(String value) {
            addCriterion("raw_method_sign not like", value, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignIn(List<String> values) {
            addCriterion("raw_method_sign in", values, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignNotIn(List<String> values) {
            addCriterion("raw_method_sign not in", values, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignBetween(String value1, String value2) {
            addCriterion("raw_method_sign between", value1, value2, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andRawMethodSignNotBetween(String value1, String value2) {
            addCriterion("raw_method_sign not between", value1, value2, "rawMethodSign");
            return (Criteria) this;
        }

        public Criteria andMethodCateIsNull() {
            addCriterion("method_cate is null");
            return (Criteria) this;
        }

        public Criteria andMethodCateIsNotNull() {
            addCriterion("method_cate is not null");
            return (Criteria) this;
        }

        public Criteria andMethodCateEqualTo(String value) {
            addCriterion("method_cate =", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateNotEqualTo(String value) {
            addCriterion("method_cate <>", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateGreaterThan(String value) {
            addCriterion("method_cate >", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateGreaterThanOrEqualTo(String value) {
            addCriterion("method_cate >=", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateLessThan(String value) {
            addCriterion("method_cate <", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateLessThanOrEqualTo(String value) {
            addCriterion("method_cate <=", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateLike(String value) {
            addCriterion("method_cate like", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateNotLike(String value) {
            addCriterion("method_cate not like", value, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateIn(List<String> values) {
            addCriterion("method_cate in", values, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateNotIn(List<String> values) {
            addCriterion("method_cate not in", values, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateBetween(String value1, String value2) {
            addCriterion("method_cate between", value1, value2, "methodCate");
            return (Criteria) this;
        }

        public Criteria andMethodCateNotBetween(String value1, String value2) {
            addCriterion("method_cate not between", value1, value2, "methodCate");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameIsNull() {
            addCriterion("belong_class_name is null");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameIsNotNull() {
            addCriterion("belong_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameEqualTo(String value) {
            addCriterion("belong_class_name =", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameNotEqualTo(String value) {
            addCriterion("belong_class_name <>", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameGreaterThan(String value) {
            addCriterion("belong_class_name >", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("belong_class_name >=", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameLessThan(String value) {
            addCriterion("belong_class_name <", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameLessThanOrEqualTo(String value) {
            addCriterion("belong_class_name <=", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameLike(String value) {
            addCriterion("belong_class_name like", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameNotLike(String value) {
            addCriterion("belong_class_name not like", value, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameIn(List<String> values) {
            addCriterion("belong_class_name in", values, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameNotIn(List<String> values) {
            addCriterion("belong_class_name not in", values, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameBetween(String value1, String value2) {
            addCriterion("belong_class_name between", value1, value2, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andBelongClassNameNotBetween(String value1, String value2) {
            addCriterion("belong_class_name not between", value1, value2, "belongClassName");
            return (Criteria) this;
        }

        public Criteria andFromUrlIsNull() {
            addCriterion("from_url is null");
            return (Criteria) this;
        }

        public Criteria andFromUrlIsNotNull() {
            addCriterion("from_url is not null");
            return (Criteria) this;
        }

        public Criteria andFromUrlEqualTo(String value) {
            addCriterion("from_url =", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotEqualTo(String value) {
            addCriterion("from_url <>", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlGreaterThan(String value) {
            addCriterion("from_url >", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlGreaterThanOrEqualTo(String value) {
            addCriterion("from_url >=", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLessThan(String value) {
            addCriterion("from_url <", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLessThanOrEqualTo(String value) {
            addCriterion("from_url <=", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLike(String value) {
            addCriterion("from_url like", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotLike(String value) {
            addCriterion("from_url not like", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlIn(List<String> values) {
            addCriterion("from_url in", values, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotIn(List<String> values) {
            addCriterion("from_url not in", values, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlBetween(String value1, String value2) {
            addCriterion("from_url between", value1, value2, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotBetween(String value1, String value2) {
            addCriterion("from_url not between", value1, value2, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}