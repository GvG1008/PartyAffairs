package com.zqu.pa.entity.exam;

import java.util.ArrayList;
import java.util.List;

public class ExamInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamInfoExample() {
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

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Integer value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Integer value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Integer value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Integer value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Integer> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Integer> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNull() {
            addCriterion("branch_id is null");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNotNull() {
            addCriterion("branch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBranchIdEqualTo(Integer value) {
            addCriterion("branch_id =", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotEqualTo(Integer value) {
            addCriterion("branch_id <>", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThan(Integer value) {
            addCriterion("branch_id >", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("branch_id >=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThan(Integer value) {
            addCriterion("branch_id <", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThanOrEqualTo(Integer value) {
            addCriterion("branch_id <=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdIn(List<Integer> values) {
            addCriterion("branch_id in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotIn(List<Integer> values) {
            addCriterion("branch_id not in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdBetween(Integer value1, Integer value2) {
            addCriterion("branch_id between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("branch_id not between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andExamTitleIsNull() {
            addCriterion("exam_title is null");
            return (Criteria) this;
        }

        public Criteria andExamTitleIsNotNull() {
            addCriterion("exam_title is not null");
            return (Criteria) this;
        }

        public Criteria andExamTitleEqualTo(String value) {
            addCriterion("exam_title =", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleNotEqualTo(String value) {
            addCriterion("exam_title <>", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleGreaterThan(String value) {
            addCriterion("exam_title >", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleGreaterThanOrEqualTo(String value) {
            addCriterion("exam_title >=", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleLessThan(String value) {
            addCriterion("exam_title <", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleLessThanOrEqualTo(String value) {
            addCriterion("exam_title <=", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleLike(String value) {
            addCriterion("exam_title like", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleNotLike(String value) {
            addCriterion("exam_title not like", value, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleIn(List<String> values) {
            addCriterion("exam_title in", values, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleNotIn(List<String> values) {
            addCriterion("exam_title not in", values, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleBetween(String value1, String value2) {
            addCriterion("exam_title between", value1, value2, "examTitle");
            return (Criteria) this;
        }

        public Criteria andExamTitleNotBetween(String value1, String value2) {
            addCriterion("exam_title not between", value1, value2, "examTitle");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Long value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Long value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Long value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Long value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Long> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Long> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Long value1, Long value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Long value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Long value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Long value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Long value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Long value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Long> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Long> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Long value1, Long value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Long value1, Long value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andExamPeriodIsNull() {
            addCriterion("exam_period is null");
            return (Criteria) this;
        }

        public Criteria andExamPeriodIsNotNull() {
            addCriterion("exam_period is not null");
            return (Criteria) this;
        }

        public Criteria andExamPeriodEqualTo(Integer value) {
            addCriterion("exam_period =", value, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodNotEqualTo(Integer value) {
            addCriterion("exam_period <>", value, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodGreaterThan(Integer value) {
            addCriterion("exam_period >", value, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_period >=", value, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodLessThan(Integer value) {
            addCriterion("exam_period <", value, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("exam_period <=", value, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodIn(List<Integer> values) {
            addCriterion("exam_period in", values, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodNotIn(List<Integer> values) {
            addCriterion("exam_period not in", values, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodBetween(Integer value1, Integer value2) {
            addCriterion("exam_period between", value1, value2, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andExamPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_period not between", value1, value2, "examPeriod");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityIsNull() {
            addCriterion("single_quantity is null");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityIsNotNull() {
            addCriterion("single_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityEqualTo(Integer value) {
            addCriterion("single_quantity =", value, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityNotEqualTo(Integer value) {
            addCriterion("single_quantity <>", value, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityGreaterThan(Integer value) {
            addCriterion("single_quantity >", value, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_quantity >=", value, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityLessThan(Integer value) {
            addCriterion("single_quantity <", value, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("single_quantity <=", value, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityIn(List<Integer> values) {
            addCriterion("single_quantity in", values, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityNotIn(List<Integer> values) {
            addCriterion("single_quantity not in", values, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityBetween(Integer value1, Integer value2) {
            addCriterion("single_quantity between", value1, value2, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andSingleQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("single_quantity not between", value1, value2, "singleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityIsNull() {
            addCriterion("multiple_quantity is null");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityIsNotNull() {
            addCriterion("multiple_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityEqualTo(Integer value) {
            addCriterion("multiple_quantity =", value, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityNotEqualTo(Integer value) {
            addCriterion("multiple_quantity <>", value, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityGreaterThan(Integer value) {
            addCriterion("multiple_quantity >", value, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("multiple_quantity >=", value, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityLessThan(Integer value) {
            addCriterion("multiple_quantity <", value, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("multiple_quantity <=", value, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityIn(List<Integer> values) {
            addCriterion("multiple_quantity in", values, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityNotIn(List<Integer> values) {
            addCriterion("multiple_quantity not in", values, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityBetween(Integer value1, Integer value2) {
            addCriterion("multiple_quantity between", value1, value2, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andMultipleQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("multiple_quantity not between", value1, value2, "multipleQuantity");
            return (Criteria) this;
        }

        public Criteria andPassScoreIsNull() {
            addCriterion("pass_score is null");
            return (Criteria) this;
        }

        public Criteria andPassScoreIsNotNull() {
            addCriterion("pass_score is not null");
            return (Criteria) this;
        }

        public Criteria andPassScoreEqualTo(Integer value) {
            addCriterion("pass_score =", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotEqualTo(Integer value) {
            addCriterion("pass_score <>", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreGreaterThan(Integer value) {
            addCriterion("pass_score >", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("pass_score >=", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreLessThan(Integer value) {
            addCriterion("pass_score <", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreLessThanOrEqualTo(Integer value) {
            addCriterion("pass_score <=", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreIn(List<Integer> values) {
            addCriterion("pass_score in", values, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotIn(List<Integer> values) {
            addCriterion("pass_score not in", values, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreBetween(Integer value1, Integer value2) {
            addCriterion("pass_score between", value1, value2, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("pass_score not between", value1, value2, "passScore");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }
    }

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