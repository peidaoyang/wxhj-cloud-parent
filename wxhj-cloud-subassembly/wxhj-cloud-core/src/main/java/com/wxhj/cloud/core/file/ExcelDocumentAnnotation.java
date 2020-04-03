/** 
 * @fileName: ExcelAnnotation.java  
 * @author: pjf
 * @date: 2019年12月31日 上午11:29:46 
 */

package com.wxhj.cloud.core.file;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelDocumentAnnotation {

	String excelType() default ".xlsx";
}
