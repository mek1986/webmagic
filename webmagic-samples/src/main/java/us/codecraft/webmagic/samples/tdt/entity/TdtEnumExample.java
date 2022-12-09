package us.codecraft.webmagic.samples.tdt.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TdtEnumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public TdtEnumExample() {
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

        public Criteria andConstNameIsNull() {
            addCriterion("const_name is null");
            return (Criteria) this;
        }

        public Criteria andConstNameIsNotNull() {
            addCriterion("const_name is not null");
            return (Criteria) this;
        }

        public Criteria andConstNameEqualTo(String value) {
            addCriterion("const_name =", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameNotEqualTo(String value) {
            addCriterion("const_name <>", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameGreaterThan(String value) {
            addCriterion("const_name >", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameGreaterThanOrEqualTo(String value) {
            addCriterion("const_name >=", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameLessThan(String value) {
            addCriterion("const_name <", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameLessThanOrEqualTo(String value) {
            addCriterion("const_name <=", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameLike(String value) {
            addCriterion("const_name like", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameNotLike(String value) {
            addCriterion("const_name not like", value, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameIn(List<String> values) {
            addCriterion("const_name in", values, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameNotIn(List<String> values) {
            addCriterion("const_name not in", values, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameBetween(String value1, String value2) {
            addCriterion("const_name between", value1, value2, "constName");
            return (Criteria) this;
        }

        public Criteria andConstNameNotBetween(String value1, String value2) {
            addCriterion("const_name not between", value1, value2, "constName");
            return (Criteria) this;
        }

        public Criteria andConstValueDescIsNull() {
            addCriterion("const_value_desc is null");
            return (Criteria) this;
        }

        public Criteria andConstValueDescIsNotNull() {
            addCriterion("const_value_desc is not null");
            return (Criteria) this;
        }

        public Criteria andConstValueDescEqualTo(String value) {
            addCriterion("const_value_desc =", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescNotEqualTo(String value) {
            addCriterion("const_value_desc <>", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescGreaterThan(String value) {
            addCriterion("const_value_desc >", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescGreaterThanOrEqualTo(String value) {
            addCriterion("const_value_desc >=", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescLessThan(String value) {
            addCriterion("const_value_desc <", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescLessThanOrEqualTo(String value) {
            addCriterion("const_value_desc <=", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescLike(String value) {
            addCriterion("const_value_desc like", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescNotLike(String value) {
            addCriterion("const_value_desc not like", value, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescIn(List<String> values) {
            addCriterion("const_value_desc in", values, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescNotIn(List<String> values) {
            addCriterion("const_value_desc not in", values, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescBetween(String value1, String value2) {
            addCriterion("const_value_desc between", value1, value2, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueDescNotBetween(String value1, String value2) {
            addCriterion("const_value_desc not between", value1, value2, "constValueDesc");
            return (Criteria) this;
        }

        public Criteria andConstValueIsNull() {
            addCriterion("const_value is null");
            return (Criteria) this;
        }

        public Criteria andConstValueIsNotNull() {
            addCriterion("const_value is not null");
            return (Criteria) this;
        }

        public Criteria andConstValueEqualTo(String value) {
            addCriterion("const_value =", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueNotEqualTo(String value) {
            addCriterion("const_value <>", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueGreaterThan(String value) {
            addCriterion("const_value >", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueGreaterThanOrEqualTo(String value) {
            addCriterion("const_value >=", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueLessThan(String value) {
            addCriterion("const_value <", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueLessThanOrEqualTo(String value) {
            addCriterion("const_value <=", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueLike(String value) {
            addCriterion("const_value like", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueNotLike(String value) {
            addCriterion("const_value not like", value, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueIn(List<String> values) {
            addCriterion("const_value in", values, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueNotIn(List<String> values) {
            addCriterion("const_value not in", values, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueBetween(String value1, String value2) {
            addCriterion("const_value between", value1, value2, "constValue");
            return (Criteria) this;
        }

        public Criteria andConstValueNotBetween(String value1, String value2) {
            addCriterion("const_value not between", value1, value2, "constValue");
            return (Criteria) this;
        }

        public Criteria andModuleName1IsNull() {
            addCriterion("module_name1 is null");
            return (Criteria) this;
        }

        public Criteria andModuleName1IsNotNull() {
            addCriterion("module_name1 is not null");
            return (Criteria) this;
        }

        public Criteria andModuleName1EqualTo(String value) {
            addCriterion("module_name1 =", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1NotEqualTo(String value) {
            addCriterion("module_name1 <>", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1GreaterThan(String value) {
            addCriterion("module_name1 >", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1GreaterThanOrEqualTo(String value) {
            addCriterion("module_name1 >=", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1LessThan(String value) {
            addCriterion("module_name1 <", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1LessThanOrEqualTo(String value) {
            addCriterion("module_name1 <=", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1Like(String value) {
            addCriterion("module_name1 like", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1NotLike(String value) {
            addCriterion("module_name1 not like", value, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1In(List<String> values) {
            addCriterion("module_name1 in", values, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1NotIn(List<String> values) {
            addCriterion("module_name1 not in", values, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1Between(String value1, String value2) {
            addCriterion("module_name1 between", value1, value2, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName1NotBetween(String value1, String value2) {
            addCriterion("module_name1 not between", value1, value2, "moduleName1");
            return (Criteria) this;
        }

        public Criteria andModuleName2IsNull() {
            addCriterion("module_name2 is null");
            return (Criteria) this;
        }

        public Criteria andModuleName2IsNotNull() {
            addCriterion("module_name2 is not null");
            return (Criteria) this;
        }

        public Criteria andModuleName2EqualTo(String value) {
            addCriterion("module_name2 =", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2NotEqualTo(String value) {
            addCriterion("module_name2 <>", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2GreaterThan(String value) {
            addCriterion("module_name2 >", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2GreaterThanOrEqualTo(String value) {
            addCriterion("module_name2 >=", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2LessThan(String value) {
            addCriterion("module_name2 <", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2LessThanOrEqualTo(String value) {
            addCriterion("module_name2 <=", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2Like(String value) {
            addCriterion("module_name2 like", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2NotLike(String value) {
            addCriterion("module_name2 not like", value, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2In(List<String> values) {
            addCriterion("module_name2 in", values, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2NotIn(List<String> values) {
            addCriterion("module_name2 not in", values, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2Between(String value1, String value2) {
            addCriterion("module_name2 between", value1, value2, "moduleName2");
            return (Criteria) this;
        }

        public Criteria andModuleName2NotBetween(String value1, String value2) {
            addCriterion("module_name2 not between", value1, value2, "moduleName2");
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