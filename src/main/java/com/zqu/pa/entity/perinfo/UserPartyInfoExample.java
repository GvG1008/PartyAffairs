package com.zqu.pa.entity.perinfo;

import java.util.ArrayList;
import java.util.List;

public class UserPartyInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserPartyInfoExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andNameFormerIsNull() {
            addCriterion("name_former is null");
            return (Criteria) this;
        }

        public Criteria andNameFormerIsNotNull() {
            addCriterion("name_former is not null");
            return (Criteria) this;
        }

        public Criteria andNameFormerEqualTo(String value) {
            addCriterion("name_former =", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerNotEqualTo(String value) {
            addCriterion("name_former <>", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerGreaterThan(String value) {
            addCriterion("name_former >", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerGreaterThanOrEqualTo(String value) {
            addCriterion("name_former >=", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerLessThan(String value) {
            addCriterion("name_former <", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerLessThanOrEqualTo(String value) {
            addCriterion("name_former <=", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerLike(String value) {
            addCriterion("name_former like", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerNotLike(String value) {
            addCriterion("name_former not like", value, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerIn(List<String> values) {
            addCriterion("name_former in", values, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerNotIn(List<String> values) {
            addCriterion("name_former not in", values, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerBetween(String value1, String value2) {
            addCriterion("name_former between", value1, value2, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andNameFormerNotBetween(String value1, String value2) {
            addCriterion("name_former not between", value1, value2, "nameFormer");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andNationalIsNull() {
            addCriterion("national is null");
            return (Criteria) this;
        }

        public Criteria andNationalIsNotNull() {
            addCriterion("national is not null");
            return (Criteria) this;
        }

        public Criteria andNationalEqualTo(String value) {
            addCriterion("national =", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotEqualTo(String value) {
            addCriterion("national <>", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThan(String value) {
            addCriterion("national >", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThanOrEqualTo(String value) {
            addCriterion("national >=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThan(String value) {
            addCriterion("national <", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThanOrEqualTo(String value) {
            addCriterion("national <=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLike(String value) {
            addCriterion("national like", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotLike(String value) {
            addCriterion("national not like", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalIn(List<String> values) {
            addCriterion("national in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotIn(List<String> values) {
            addCriterion("national not in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalBetween(String value1, String value2) {
            addCriterion("national between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotBetween(String value1, String value2) {
            addCriterion("national not between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andNativePlaceIsNull() {
            addCriterion("native_place is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceIsNotNull() {
            addCriterion("native_place is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceEqualTo(String value) {
            addCriterion("native_place =", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotEqualTo(String value) {
            addCriterion("native_place <>", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceGreaterThan(String value) {
            addCriterion("native_place >", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("native_place >=", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceLessThan(String value) {
            addCriterion("native_place <", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceLessThanOrEqualTo(String value) {
            addCriterion("native_place <=", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceLike(String value) {
            addCriterion("native_place like", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotLike(String value) {
            addCriterion("native_place not like", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceIn(List<String> values) {
            addCriterion("native_place in", values, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotIn(List<String> values) {
            addCriterion("native_place not in", values, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceBetween(String value1, String value2) {
            addCriterion("native_place between", value1, value2, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotBetween(String value1, String value2) {
            addCriterion("native_place not between", value1, value2, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceIsNull() {
            addCriterion("birth_place is null");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceIsNotNull() {
            addCriterion("birth_place is not null");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceEqualTo(String value) {
            addCriterion("birth_place =", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotEqualTo(String value) {
            addCriterion("birth_place <>", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceGreaterThan(String value) {
            addCriterion("birth_place >", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("birth_place >=", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceLessThan(String value) {
            addCriterion("birth_place <", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceLessThanOrEqualTo(String value) {
            addCriterion("birth_place <=", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceLike(String value) {
            addCriterion("birth_place like", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotLike(String value) {
            addCriterion("birth_place not like", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceIn(List<String> values) {
            addCriterion("birth_place in", values, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotIn(List<String> values) {
            addCriterion("birth_place not in", values, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceBetween(String value1, String value2) {
            addCriterion("birth_place between", value1, value2, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotBetween(String value1, String value2) {
            addCriterion("birth_place not between", value1, value2, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredIsNull() {
            addCriterion("place_registered is null");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredIsNotNull() {
            addCriterion("place_registered is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredEqualTo(String value) {
            addCriterion("place_registered =", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredNotEqualTo(String value) {
            addCriterion("place_registered <>", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredGreaterThan(String value) {
            addCriterion("place_registered >", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredGreaterThanOrEqualTo(String value) {
            addCriterion("place_registered >=", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredLessThan(String value) {
            addCriterion("place_registered <", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredLessThanOrEqualTo(String value) {
            addCriterion("place_registered <=", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredLike(String value) {
            addCriterion("place_registered like", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredNotLike(String value) {
            addCriterion("place_registered not like", value, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredIn(List<String> values) {
            addCriterion("place_registered in", values, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredNotIn(List<String> values) {
            addCriterion("place_registered not in", values, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredBetween(String value1, String value2) {
            addCriterion("place_registered between", value1, value2, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andPlaceRegisteredNotBetween(String value1, String value2) {
            addCriterion("place_registered not between", value1, value2, "placeRegistered");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIsNull() {
            addCriterion("home_address is null");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIsNotNull() {
            addCriterion("home_address is not null");
            return (Criteria) this;
        }

        public Criteria andHomeAddressEqualTo(String value) {
            addCriterion("home_address =", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressNotEqualTo(String value) {
            addCriterion("home_address <>", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressGreaterThan(String value) {
            addCriterion("home_address >", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("home_address >=", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressLessThan(String value) {
            addCriterion("home_address <", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressLessThanOrEqualTo(String value) {
            addCriterion("home_address <=", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressLike(String value) {
            addCriterion("home_address like", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressNotLike(String value) {
            addCriterion("home_address not like", value, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIn(List<String> values) {
            addCriterion("home_address in", values, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressNotIn(List<String> values) {
            addCriterion("home_address not in", values, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressBetween(String value1, String value2) {
            addCriterion("home_address between", value1, value2, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andHomeAddressNotBetween(String value1, String value2) {
            addCriterion("home_address not between", value1, value2, "homeAddress");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundIsNull() {
            addCriterion("family_background is null");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundIsNotNull() {
            addCriterion("family_background is not null");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundEqualTo(String value) {
            addCriterion("family_background =", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundNotEqualTo(String value) {
            addCriterion("family_background <>", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundGreaterThan(String value) {
            addCriterion("family_background >", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("family_background >=", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundLessThan(String value) {
            addCriterion("family_background <", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundLessThanOrEqualTo(String value) {
            addCriterion("family_background <=", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundLike(String value) {
            addCriterion("family_background like", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundNotLike(String value) {
            addCriterion("family_background not like", value, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundIn(List<String> values) {
            addCriterion("family_background in", values, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundNotIn(List<String> values) {
            addCriterion("family_background not in", values, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundBetween(String value1, String value2) {
            addCriterion("family_background between", value1, value2, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andFamilyBackgroundNotBetween(String value1, String value2) {
            addCriterion("family_background not between", value1, value2, "familyBackground");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNull() {
            addCriterion("birth_date is null");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNotNull() {
            addCriterion("birth_date is not null");
            return (Criteria) this;
        }

        public Criteria andBirthDateEqualTo(String value) {
            addCriterion("birth_date =", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotEqualTo(String value) {
            addCriterion("birth_date <>", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThan(String value) {
            addCriterion("birth_date >", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThanOrEqualTo(String value) {
            addCriterion("birth_date >=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThan(String value) {
            addCriterion("birth_date <", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThanOrEqualTo(String value) {
            addCriterion("birth_date <=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLike(String value) {
            addCriterion("birth_date like", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotLike(String value) {
            addCriterion("birth_date not like", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateIn(List<String> values) {
            addCriterion("birth_date in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotIn(List<String> values) {
            addCriterion("birth_date not in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateBetween(String value1, String value2) {
            addCriterion("birth_date between", value1, value2, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotBetween(String value1, String value2) {
            addCriterion("birth_date not between", value1, value2, "birthDate");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNull() {
            addCriterion("marital_status is null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNotNull() {
            addCriterion("marital_status is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusEqualTo(String value) {
            addCriterion("marital_status =", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotEqualTo(String value) {
            addCriterion("marital_status <>", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThan(String value) {
            addCriterion("marital_status >", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThanOrEqualTo(String value) {
            addCriterion("marital_status >=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThan(String value) {
            addCriterion("marital_status <", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThanOrEqualTo(String value) {
            addCriterion("marital_status <=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLike(String value) {
            addCriterion("marital_status like", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotLike(String value) {
            addCriterion("marital_status not like", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIn(List<String> values) {
            addCriterion("marital_status in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotIn(List<String> values) {
            addCriterion("marital_status not in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusBetween(String value1, String value2) {
            addCriterion("marital_status between", value1, value2, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotBetween(String value1, String value2) {
            addCriterion("marital_status not between", value1, value2, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitIsNull() {
            addCriterion("organization_unit is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitIsNotNull() {
            addCriterion("organization_unit is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitEqualTo(String value) {
            addCriterion("organization_unit =", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitNotEqualTo(String value) {
            addCriterion("organization_unit <>", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitGreaterThan(String value) {
            addCriterion("organization_unit >", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitGreaterThanOrEqualTo(String value) {
            addCriterion("organization_unit >=", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitLessThan(String value) {
            addCriterion("organization_unit <", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitLessThanOrEqualTo(String value) {
            addCriterion("organization_unit <=", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitLike(String value) {
            addCriterion("organization_unit like", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitNotLike(String value) {
            addCriterion("organization_unit not like", value, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitIn(List<String> values) {
            addCriterion("organization_unit in", values, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitNotIn(List<String> values) {
            addCriterion("organization_unit not in", values, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitBetween(String value1, String value2) {
            addCriterion("organization_unit between", value1, value2, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andOrganizationUnitNotBetween(String value1, String value2) {
            addCriterion("organization_unit not between", value1, value2, "organizationUnit");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityIsNull() {
            addCriterion("personal_identity is null");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityIsNotNull() {
            addCriterion("personal_identity is not null");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityEqualTo(String value) {
            addCriterion("personal_identity =", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityNotEqualTo(String value) {
            addCriterion("personal_identity <>", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityGreaterThan(String value) {
            addCriterion("personal_identity >", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("personal_identity >=", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityLessThan(String value) {
            addCriterion("personal_identity <", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityLessThanOrEqualTo(String value) {
            addCriterion("personal_identity <=", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityLike(String value) {
            addCriterion("personal_identity like", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityNotLike(String value) {
            addCriterion("personal_identity not like", value, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityIn(List<String> values) {
            addCriterion("personal_identity in", values, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityNotIn(List<String> values) {
            addCriterion("personal_identity not in", values, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityBetween(String value1, String value2) {
            addCriterion("personal_identity between", value1, value2, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andPersonalIdentityNotBetween(String value1, String value2) {
            addCriterion("personal_identity not between", value1, value2, "personalIdentity");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundIsNull() {
            addCriterion("educational_background is null");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundIsNotNull() {
            addCriterion("educational_background is not null");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundEqualTo(String value) {
            addCriterion("educational_background =", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundNotEqualTo(String value) {
            addCriterion("educational_background <>", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundGreaterThan(String value) {
            addCriterion("educational_background >", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("educational_background >=", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundLessThan(String value) {
            addCriterion("educational_background <", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundLessThanOrEqualTo(String value) {
            addCriterion("educational_background <=", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundLike(String value) {
            addCriterion("educational_background like", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundNotLike(String value) {
            addCriterion("educational_background not like", value, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundIn(List<String> values) {
            addCriterion("educational_background in", values, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundNotIn(List<String> values) {
            addCriterion("educational_background not in", values, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundBetween(String value1, String value2) {
            addCriterion("educational_background between", value1, value2, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andEducationalBackgroundNotBetween(String value1, String value2) {
            addCriterion("educational_background not between", value1, value2, "educationalBackground");
            return (Criteria) this;
        }

        public Criteria andProfessionalIsNull() {
            addCriterion("professional is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalIsNotNull() {
            addCriterion("professional is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalEqualTo(String value) {
            addCriterion("professional =", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalNotEqualTo(String value) {
            addCriterion("professional <>", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalGreaterThan(String value) {
            addCriterion("professional >", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalGreaterThanOrEqualTo(String value) {
            addCriterion("professional >=", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalLessThan(String value) {
            addCriterion("professional <", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalLessThanOrEqualTo(String value) {
            addCriterion("professional <=", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalLike(String value) {
            addCriterion("professional like", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalNotLike(String value) {
            addCriterion("professional not like", value, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalIn(List<String> values) {
            addCriterion("professional in", values, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalNotIn(List<String> values) {
            addCriterion("professional not in", values, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalBetween(String value1, String value2) {
            addCriterion("professional between", value1, value2, "professional");
            return (Criteria) this;
        }

        public Criteria andProfessionalNotBetween(String value1, String value2) {
            addCriterion("professional not between", value1, value2, "professional");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIsNull() {
            addCriterion("graduate_school is null");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIsNotNull() {
            addCriterion("graduate_school is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolEqualTo(String value) {
            addCriterion("graduate_school =", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotEqualTo(String value) {
            addCriterion("graduate_school <>", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolGreaterThan(String value) {
            addCriterion("graduate_school >", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("graduate_school >=", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLessThan(String value) {
            addCriterion("graduate_school <", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLessThanOrEqualTo(String value) {
            addCriterion("graduate_school <=", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLike(String value) {
            addCriterion("graduate_school like", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotLike(String value) {
            addCriterion("graduate_school not like", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIn(List<String> values) {
            addCriterion("graduate_school in", values, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotIn(List<String> values) {
            addCriterion("graduate_school not in", values, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolBetween(String value1, String value2) {
            addCriterion("graduate_school between", value1, value2, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotBetween(String value1, String value2) {
            addCriterion("graduate_school not between", value1, value2, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyIsNull() {
            addCriterion("time_applicationforparty is null");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyIsNotNull() {
            addCriterion("time_applicationforparty is not null");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyEqualTo(String value) {
            addCriterion("time_applicationforparty =", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyNotEqualTo(String value) {
            addCriterion("time_applicationforparty <>", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyGreaterThan(String value) {
            addCriterion("time_applicationforparty >", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyGreaterThanOrEqualTo(String value) {
            addCriterion("time_applicationforparty >=", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyLessThan(String value) {
            addCriterion("time_applicationforparty <", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyLessThanOrEqualTo(String value) {
            addCriterion("time_applicationforparty <=", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyLike(String value) {
            addCriterion("time_applicationforparty like", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyNotLike(String value) {
            addCriterion("time_applicationforparty not like", value, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyIn(List<String> values) {
            addCriterion("time_applicationforparty in", values, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyNotIn(List<String> values) {
            addCriterion("time_applicationforparty not in", values, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyBetween(String value1, String value2) {
            addCriterion("time_applicationforparty between", value1, value2, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeApplicationforpartyNotBetween(String value1, String value2) {
            addCriterion("time_applicationforparty not between", value1, value2, "timeApplicationforparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyIsNull() {
            addCriterion("time_intoparty is null");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyIsNotNull() {
            addCriterion("time_intoparty is not null");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyEqualTo(String value) {
            addCriterion("time_intoparty =", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyNotEqualTo(String value) {
            addCriterion("time_intoparty <>", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyGreaterThan(String value) {
            addCriterion("time_intoparty >", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyGreaterThanOrEqualTo(String value) {
            addCriterion("time_intoparty >=", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyLessThan(String value) {
            addCriterion("time_intoparty <", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyLessThanOrEqualTo(String value) {
            addCriterion("time_intoparty <=", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyLike(String value) {
            addCriterion("time_intoparty like", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyNotLike(String value) {
            addCriterion("time_intoparty not like", value, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyIn(List<String> values) {
            addCriterion("time_intoparty in", values, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyNotIn(List<String> values) {
            addCriterion("time_intoparty not in", values, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyBetween(String value1, String value2) {
            addCriterion("time_intoparty between", value1, value2, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimeIntopartyNotBetween(String value1, String value2) {
            addCriterion("time_intoparty not between", value1, value2, "timeIntoparty");
            return (Criteria) this;
        }

        public Criteria andTimePositiveIsNull() {
            addCriterion("time_positive is null");
            return (Criteria) this;
        }

        public Criteria andTimePositiveIsNotNull() {
            addCriterion("time_positive is not null");
            return (Criteria) this;
        }

        public Criteria andTimePositiveEqualTo(String value) {
            addCriterion("time_positive =", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveNotEqualTo(String value) {
            addCriterion("time_positive <>", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveGreaterThan(String value) {
            addCriterion("time_positive >", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveGreaterThanOrEqualTo(String value) {
            addCriterion("time_positive >=", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveLessThan(String value) {
            addCriterion("time_positive <", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveLessThanOrEqualTo(String value) {
            addCriterion("time_positive <=", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveLike(String value) {
            addCriterion("time_positive like", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveNotLike(String value) {
            addCriterion("time_positive not like", value, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveIn(List<String> values) {
            addCriterion("time_positive in", values, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveNotIn(List<String> values) {
            addCriterion("time_positive not in", values, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveBetween(String value1, String value2) {
            addCriterion("time_positive between", value1, value2, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTimePositiveNotBetween(String value1, String value2) {
            addCriterion("time_positive not between", value1, value2, "timePositive");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentIsNull() {
            addCriterion("type_development is null");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentIsNotNull() {
            addCriterion("type_development is not null");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentEqualTo(String value) {
            addCriterion("type_development =", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentNotEqualTo(String value) {
            addCriterion("type_development <>", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentGreaterThan(String value) {
            addCriterion("type_development >", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentGreaterThanOrEqualTo(String value) {
            addCriterion("type_development >=", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentLessThan(String value) {
            addCriterion("type_development <", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentLessThanOrEqualTo(String value) {
            addCriterion("type_development <=", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentLike(String value) {
            addCriterion("type_development like", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentNotLike(String value) {
            addCriterion("type_development not like", value, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentIn(List<String> values) {
            addCriterion("type_development in", values, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentNotIn(List<String> values) {
            addCriterion("type_development not in", values, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentBetween(String value1, String value2) {
            addCriterion("type_development between", value1, value2, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTypeDevelopmentNotBetween(String value1, String value2) {
            addCriterion("type_development not between", value1, value2, "typeDevelopment");
            return (Criteria) this;
        }

        public Criteria andTototIsNull() {
            addCriterion("totot is null");
            return (Criteria) this;
        }

        public Criteria andTototIsNotNull() {
            addCriterion("totot is not null");
            return (Criteria) this;
        }

        public Criteria andTototEqualTo(String value) {
            addCriterion("totot =", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototNotEqualTo(String value) {
            addCriterion("totot <>", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototGreaterThan(String value) {
            addCriterion("totot >", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototGreaterThanOrEqualTo(String value) {
            addCriterion("totot >=", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototLessThan(String value) {
            addCriterion("totot <", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototLessThanOrEqualTo(String value) {
            addCriterion("totot <=", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototLike(String value) {
            addCriterion("totot like", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototNotLike(String value) {
            addCriterion("totot not like", value, "totot");
            return (Criteria) this;
        }

        public Criteria andTototIn(List<String> values) {
            addCriterion("totot in", values, "totot");
            return (Criteria) this;
        }

        public Criteria andTototNotIn(List<String> values) {
            addCriterion("totot not in", values, "totot");
            return (Criteria) this;
        }

        public Criteria andTototBetween(String value1, String value2) {
            addCriterion("totot between", value1, value2, "totot");
            return (Criteria) this;
        }

        public Criteria andTototNotBetween(String value1, String value2) {
            addCriterion("totot not between", value1, value2, "totot");
            return (Criteria) this;
        }

        public Criteria andOutUnitIsNull() {
            addCriterion("out_unit is null");
            return (Criteria) this;
        }

        public Criteria andOutUnitIsNotNull() {
            addCriterion("out_unit is not null");
            return (Criteria) this;
        }

        public Criteria andOutUnitEqualTo(String value) {
            addCriterion("out_unit =", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitNotEqualTo(String value) {
            addCriterion("out_unit <>", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitGreaterThan(String value) {
            addCriterion("out_unit >", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitGreaterThanOrEqualTo(String value) {
            addCriterion("out_unit >=", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitLessThan(String value) {
            addCriterion("out_unit <", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitLessThanOrEqualTo(String value) {
            addCriterion("out_unit <=", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitLike(String value) {
            addCriterion("out_unit like", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitNotLike(String value) {
            addCriterion("out_unit not like", value, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitIn(List<String> values) {
            addCriterion("out_unit in", values, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitNotIn(List<String> values) {
            addCriterion("out_unit not in", values, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitBetween(String value1, String value2) {
            addCriterion("out_unit between", value1, value2, "outUnit");
            return (Criteria) this;
        }

        public Criteria andOutUnitNotBetween(String value1, String value2) {
            addCriterion("out_unit not between", value1, value2, "outUnit");
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