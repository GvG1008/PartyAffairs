package com.zqu.pa.entity.vote;

import java.util.ArrayList;
import java.util.List;

public class VoteChoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VoteChoiceExample() {
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

        public Criteria andChoiceIdIsNull() {
            addCriterion("choice_id is null");
            return (Criteria) this;
        }

        public Criteria andChoiceIdIsNotNull() {
            addCriterion("choice_id is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceIdEqualTo(Long value) {
            addCriterion("choice_id =", value, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdNotEqualTo(Long value) {
            addCriterion("choice_id <>", value, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdGreaterThan(Long value) {
            addCriterion("choice_id >", value, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("choice_id >=", value, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdLessThan(Long value) {
            addCriterion("choice_id <", value, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdLessThanOrEqualTo(Long value) {
            addCriterion("choice_id <=", value, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdIn(List<Long> values) {
            addCriterion("choice_id in", values, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdNotIn(List<Long> values) {
            addCriterion("choice_id not in", values, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdBetween(Long value1, Long value2) {
            addCriterion("choice_id between", value1, value2, "choiceId");
            return (Criteria) this;
        }

        public Criteria andChoiceIdNotBetween(Long value1, Long value2) {
            addCriterion("choice_id not between", value1, value2, "choiceId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNull() {
            addCriterion("vote_id is null");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNotNull() {
            addCriterion("vote_id is not null");
            return (Criteria) this;
        }

        public Criteria andVoteIdEqualTo(Long value) {
            addCriterion("vote_id =", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotEqualTo(Long value) {
            addCriterion("vote_id <>", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThan(Long value) {
            addCriterion("vote_id >", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("vote_id >=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThan(Long value) {
            addCriterion("vote_id <", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThanOrEqualTo(Long value) {
            addCriterion("vote_id <=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIn(List<Long> values) {
            addCriterion("vote_id in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotIn(List<Long> values) {
            addCriterion("vote_id not in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdBetween(Long value1, Long value2) {
            addCriterion("vote_id between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotBetween(Long value1, Long value2) {
            addCriterion("vote_id not between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andChoiceContentIsNull() {
            addCriterion("choice_content is null");
            return (Criteria) this;
        }

        public Criteria andChoiceContentIsNotNull() {
            addCriterion("choice_content is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceContentEqualTo(String value) {
            addCriterion("choice_content =", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentNotEqualTo(String value) {
            addCriterion("choice_content <>", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentGreaterThan(String value) {
            addCriterion("choice_content >", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentGreaterThanOrEqualTo(String value) {
            addCriterion("choice_content >=", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentLessThan(String value) {
            addCriterion("choice_content <", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentLessThanOrEqualTo(String value) {
            addCriterion("choice_content <=", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentLike(String value) {
            addCriterion("choice_content like", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentNotLike(String value) {
            addCriterion("choice_content not like", value, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentIn(List<String> values) {
            addCriterion("choice_content in", values, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentNotIn(List<String> values) {
            addCriterion("choice_content not in", values, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentBetween(String value1, String value2) {
            addCriterion("choice_content between", value1, value2, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andChoiceContentNotBetween(String value1, String value2) {
            addCriterion("choice_content not between", value1, value2, "choiceContent");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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