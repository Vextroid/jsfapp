/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("numValidator")
public class numValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object userInput) throws ValidatorException {

        HtmlInputText htmlInputText = (HtmlInputText) uiComponent;
        String label;

        if (htmlInputText.getLabel() == null || htmlInputText.getLabel().trim().equals("")) {
            label = htmlInputText.getId();
        } else {
            label = htmlInputText.getLabel();
        }

        if ((Integer) userInput < 0) {
            FacesMessage facesMessage = new FacesMessage(label + ": the number can not be minus");
            throw new ValidatorException(facesMessage);
        }

    }
}
