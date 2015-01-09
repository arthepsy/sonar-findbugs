# sonar-findbugs
[SonarQube FindBugs plugin](https://github.com/SonarSource/sonar-findbugs) fork with updated [FindBugs](http://findbugs.sourceforge.net/).

## ChangeLog

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

### 2015-01-08  
- Remove old and unused FindBugs rules:
    - `UOE_USE_OBJECT_EQUALS` (removed from FindBugs at 2010-09-14)
    - `RV_RETURN_VALUE_IGNORED2` (removed from FindBugs at 2011-08-05)

