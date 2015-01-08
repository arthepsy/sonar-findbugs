# sonar-findbugs
[SonarQube FindBugs plugin](https://github.com/SonarSource/sonar-findbugs) fork with updated [fb-contrib](https://github.com/mebigfatguy/fb-contrib).

## ChangeLog

### 2015-01-08  
- Rename bug category "Style" to "Dodgy code".  

### 2015-01-07  
- Update to fb-contrib-6.1.0-SNAPSHOT-20150107 (-1,+8 rules).  

    Added rules:
    - CSI_CHAR_SET_ISSUES_USE_STANDARD_CHARSET_NAME
    - CSI_CHAR_SET_ISSUES_USE_STANDARD_CHARSET
    - CSI_CHAR_SET_ISSUES_UNKNOWN_ENCODING
    - EXS_EXCEPTION_SOFTENING_RETURN_FALSE
    - CBC_CONTAINS_BASED_CONDITIONAL
    - CIS_TOSTRING_STORED_IN_FIELD
    - CIS_STRING_PARSING_A_FIELD
    - OPM_OVERLY_PERMISSIVE_METHOD
    
    Removed rules:
    - CD_CIRCULAR_DEPENDENCY
