/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Payload;

/**
 *
 * @author JohnnyVan
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface passValidator {

    String message() default "{validators.passValidator}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}