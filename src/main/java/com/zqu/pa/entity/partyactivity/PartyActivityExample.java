package com.zqu.pa.entity.partyactivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartyActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PartyActivityExample() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitIsNull() {
            addCriterion("release_unit is null");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitIsNotNull() {
            addCriterion("release_unit is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitEqualTo(String value) {
            addCriterion("release_unit =", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitNotEqualTo(String value) {
            addCriterion("release_unit <>", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitGreaterThan(String value) {
            addCriterion("release_unit >", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitGreaterThanOrEqualTo(String value) {
            addCriterion("release_unit >=", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitLessThan(String value) {
            addCriterion("release_unit <", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitLessThanOrEqualTo(String value) {
            addCriterion("release_unit <=", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitLike(String value) {
            addCriterion("release_unit like", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitNotLike(String value) {
            addCriterion("release_unit not like", value, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitIn(List<String> values) {
            addCriterion("release_unit in", values, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitNotIn(List<String> values) {
            addCriterion("release_unit not in", values, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitBetween(String value1, String value2) {
            addCriterion("release_unit between", value1, value2, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andReleaseUnitNotBetween(String value1, String value2) {
            addCriterion("release_unit not between", value1, value2, "releaseUnit");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNull() {
            addCriterion("create_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNotNull() {
            addCriterion("create_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIdEqualTo(String value) {
            addCriterion("create_id =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotEqualTo(String value) {
            addCriterion("create_id <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThan(String value) {
            addCriterion("create_id >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_id >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThan(String value) {
            addCriterion("create_id <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThanOrEqualTo(String value) {
            addCriterion("create_id <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLike(String value) {
            addCriterion("create_id like", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotLike(String value) {
            addCriterion("create_id not like", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIn(List<String> values) {
            addCriterion("create_id in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotIn(List<String> values) {
            addCriterion("create_id not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdBetween(String value1, String value2) {
            addCriterion("create_id between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotBetween(String value1, String value2) {
            addCriterion("create_id not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartIsNull() {
            addCriterion("registration_start is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartIsNotNull() {
            addCriterion("registration_start is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartEqualTo(Long value) {
            addCriterion("registration_start =", value, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartNotEqualTo(Long value) {
            addCriterion("registration_start <>", value, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartGreaterThan(Long value) {
            addCriterion("registration_start >", value, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartGreaterThanOrEqualTo(Long value) {
            addCriterion("registration_start >=", value, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartLessThan(Long value) {
            addCriterion("registration_start <", value, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartLessThanOrEqualTo(Long value) {
            addCriterion("registration_start <=", value, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartIn(List<Long> values) {
            addCriterion("registration_start in", values, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartNotIn(List<Long> values) {
            addCriterion("registration_start not in", values, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartBetween(Long value1, Long value2) {
            addCriterion("registration_start between", value1, value2, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartNotBetween(Long value1, Long value2) {
            addCriterion("registration_start not between", value1, value2, "registrationStart");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndIsNull() {
            addCriterion("registration_end is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndIsNotNull() {
            addCriterion("registration_end is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndEqualTo(Long value) {
            addCriterion("registration_end =", value, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndNotEqualTo(Long value) {
            addCriterion("registration_end <>", value, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndGreaterThan(Long value) {
            addCriterion("registration_end >", value, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndGreaterThanOrEqualTo(Long value) {
            addCriterion("registration_end >=", value, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndLessThan(Long value) {
            addCriterion("registration_end <", value, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndLessThanOrEqualTo(Long value) {
            addCriterion("registration_end <=", value, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndIn(List<Long> values) {
            addCriterion("registration_end in", values, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndNotIn(List<Long> values) {
            addCriterion("registration_end not in", values, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndBetween(Long value1, Long value2) {
            addCriterion("registration_end between", value1, value2, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndNotBetween(Long value1, Long value2) {
            addCriterion("registration_end not between", value1, value2, "registrationEnd");
            return (Criteria) this;
        }

        public Criteria andActivityStartIsNull() {
            addCriterion("activity_start is null");
            return (Criteria) this;
        }

        public Criteria andActivityStartIsNotNull() {
            addCriterion("activity_start is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStartEqualTo(Long value) {
            addCriterion("activity_start =", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartNotEqualTo(Long value) {
            addCriterion("activity_start <>", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartGreaterThan(Long value) {
            addCriterion("activity_start >", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_start >=", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartLessThan(Long value) {
            addCriterion("activity_start <", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartLessThanOrEqualTo(Long value) {
            addCriterion("activity_start <=", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartIn(List<Long> values) {
            addCriterion("activity_start in", values, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartNotIn(List<Long> values) {
            addCriterion("activity_start not in", values, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartBetween(Long value1, Long value2) {
            addCriterion("activity_start between", value1, value2, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartNotBetween(Long value1, Long value2) {
            addCriterion("activity_start not between", value1, value2, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityEndIsNull() {
            addCriterion("activity_end is null");
            return (Criteria) this;
        }

        public Criteria andActivityEndIsNotNull() {
            addCriterion("activity_end is not null");
            return (Criteria) this;
        }

        public Criteria andActivityEndEqualTo(Long value) {
            addCriterion("activity_end =", value, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndNotEqualTo(Long value) {
            addCriterion("activity_end <>", value, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndGreaterThan(Long value) {
            addCriterion("activity_end >", value, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_end >=", value, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndLessThan(Long value) {
            addCriterion("activity_end <", value, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndLessThanOrEqualTo(Long value) {
            addCriterion("activity_end <=", value, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndIn(List<Long> values) {
            addCriterion("activity_end in", values, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndNotIn(List<Long> values) {
            addCriterion("activity_end not in", values, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndBetween(Long value1, Long value2) {
            addCriterion("activity_end between", value1, value2, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityEndNotBetween(Long value1, Long value2) {
            addCriterion("activity_end not between", value1, value2, "activityEnd");
            return (Criteria) this;
        }

        public Criteria andActivityResultIsNull() {
            addCriterion("activity_result is null");
            return (Criteria) this;
        }

        public Criteria andActivityResultIsNotNull() {
            addCriterion("activity_result is not null");
            return (Criteria) this;
        }

        public Criteria andActivityResultEqualTo(String value) {
            addCriterion("activity_result =", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultNotEqualTo(String value) {
            addCriterion("activity_result <>", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultGreaterThan(String value) {
            addCriterion("activity_result >", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultGreaterThanOrEqualTo(String value) {
            addCriterion("activity_result >=", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultLessThan(String value) {
            addCriterion("activity_result <", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultLessThanOrEqualTo(String value) {
            addCriterion("activity_result <=", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultLike(String value) {
            addCriterion("activity_result like", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultNotLike(String value) {
            addCriterion("activity_result not like", value, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultIn(List<String> values) {
            addCriterion("activity_result in", values, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultNotIn(List<String> values) {
            addCriterion("activity_result not in", values, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultBetween(String value1, String value2) {
            addCriterion("activity_result between", value1, value2, "activityResult");
            return (Criteria) this;
        }

        public Criteria andActivityResultNotBetween(String value1, String value2) {
            addCriterion("activity_result not between", value1, value2, "activityResult");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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