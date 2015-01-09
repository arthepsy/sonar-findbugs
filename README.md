# sonar-findbugs
[SonarQube FindBugs plugin](https://github.com/SonarSource/sonar-findbugs) fork with:  
- updated [FindBugs](http://findbugs.sourceforge.net/)  
- updated [fb-contrib](https://github.com/mebigfatguy/fb-contrib)  
- integrated [find-sec-bugs](https://github.com/h3xstream/find-sec-bugs)  

## ChangeLog

### 2015-01-08  
- Rename fb-contrib rules bug category "Style" to "Dodgy code".  

### 2015-01-07  
- Update to fb-contrib-6.1.0-SNAPSHOT-20150107 (-1,+8 rules).  

    Added rules:
    - `CSI_CHAR_SET_ISSUES_USE_STANDARD_CHARSET_NAME`  
    - `CSI_CHAR_SET_ISSUES_USE_STANDARD_CHARSET`  
    - `CSI_CHAR_SET_ISSUES_UNKNOWN_ENCODING`  
    - `EXS_EXCEPTION_SOFTENING_RETURN_FALSE`  
    - `CBC_CONTAINS_BASED_CONDITIONAL`  
    - `CIS_TOSTRING_STORED_IN_FIELD`  
    - `CIS_STRING_PARSING_A_FIELD`  
    - `OPM_OVERLY_PERMISSIVE_METHOD`  
    
    Removed rules:
    - `CD_CIRCULAR_DEPENDENCY`  

- Integrate find-sec-bugs 1.3.0 (+56 rules)  

