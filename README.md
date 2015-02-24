# sonar-findbugs
[SonarQube FindBugs plugin](https://github.com/SonarSource/sonar-findbugs) fork with updated [FindBugs](http://findbugs.sourceforge.net/).

## ChangeLog

### 2015-02-24  
- Update to FindBugs-3.0.1-RC1 (+8 rules):
    - `DM_BOXED_PRIMITIVE_FOR_COMPARE`  
    - `DM_INVALID_MIN_MAX`  
    - `DC_PARTIALLY_CONSTRUCTED`  
    - `ME_MUTABLE_ENUM_FIELD`  
    - `ME_ENUM_FIELD_SETTER`  
    - `UC_USELESS_OBJECT`  
    - `UC_USELESS_OBJECT_STACK`  
    - `BSHIFT_WRONG_ADD_PRIORITY`  

### 2015-01-09  
- Update rule categories and descriptions (from FindBugs 3.0.0)  
- Add missing rules from FindBugs 3.0.0 (+13 rules):
    - `NOISE_NULL_DEREFERENCE`  
    - `NOISE_METHOD_CALL`  
    - `NOISE_FIELD_REFERENCE`  
    - `NOISE_OPERATION`  
    - `DMI_VACUOUS_CALL_TO_EASYMOCK_METHOD`  
    - `TLW_TWO_LOCK_NOTIFY`  
    - `LI_LAZY_INIT_INSTANCE`  
    - `JLM_JSR166_UTILCONCURRENT_MONITORENTER`  
    - `BRSA_BAD_RESULTSET_ACCESS`  
    - `BC_IMPOSSIBLE_CAST_PRIMITIVE_ARRAY`  
    - `BC_NULL_INSTANCEOF`  
    - `VR_UNRESOLVABLE_REFERENCE`  
    - `NP_METHOD_PARAMETER_RELAXING_ANNOTATION`  

- Update to FindBugs-3.0.1-SNAPSHOT-20150109 (+19 rules):  
    - `CAA_COVARIANT_ARRAY_FIELD`  
    - `CAA_COVARIANT_ARRAY_LOCAL`  
    - `CAA_COVARIANT_ARRAY_RETURN`  
    - `CAA_COVARIANT_ARRAY_ELEMENT_STORE`  
    - `MS_MUTABLE_COLLECTION`  
    - `MS_MUTABLE_COLLECTION_PKGPROTECT`  
    - `UC_USELESS_VOID_METHOD`  
    - `RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT`  
    - `IIL_PREPARE_STATEMENT_IN_LOOP`  
    - `IIL_ELEMENTS_GET_LENGTH_IN_LOOP`  
    - `IIL_PATTERN_COMPILE_IN_LOOP`  
    - `IIL_PATTERN_COMPILE_IN_LOOP_INDIRECT`  
    - `CO_COMPARETO_INCORRECT_FLOATING`  
    - `UC_USELESS_CONDITION`  
    - `UC_USELESS_CONDITION_TYPE`  
    - `RANGE_ARRAY_INDEX`  
    - `RANGE_ARRAY_OFFSET`  
    - `RANGE_ARRAY_LENGTH`  
    - `RANGE_STRING_INDEX`  

### 2015-01-08  
- Remove old and unused FindBugs rules:
    - `UOE_USE_OBJECT_EQUALS` (removed from FindBugs at 2010-09-14)
    - `RV_RETURN_VALUE_IGNORED2` (removed from FindBugs at 2011-08-05)

