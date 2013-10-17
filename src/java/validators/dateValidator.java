
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateValidator")
public class dateValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext,UIComponent uIComponent, Object value) throws ValidatorException {
        
        Pattern pattern = Pattern.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
        Matcher matcher = pattern.matcher((CharSequence)value);
        HtmlInputText htmlInputText = (HtmlInputText) uIComponent;
        String label;
        
        if (htmlInputText.getLabel() == null || htmlInputText.getLabel().trim().equals("")) {
            label = htmlInputText.getId();
        } else {
            label = htmlInputText.getLabel();
        }
        
        if (!matcher.matches()) {
            FacesMessage facesMessage = new FacesMessage(label + ": invalid format");
            throw new ValidatorException(facesMessage);
        }
		
        if(matcher.matches()){
            matcher.reset();
			
            if(matcher.find()){
		String day = matcher.group(2);
		String month = matcher.group(1);
		int year = Integer.parseInt(matcher.group(3));
                String currentDate = Calendar.getInstance().get(Calendar.DATE) + "/" + (Calendar.getInstance().get(Calendar.MONTH)) + "/" + Calendar.getInstance().get(Calendar.YEAR);
				
		if (day.equals("31") && (month.equals("4") || month .equals("6") || month.equals("9") ||
                  month.equals("11") || month.equals("04") || month .equals("06") ||
                  month.equals("09"))) {
                    
                    FacesMessage facesMessage = new FacesMessage(label + ": only month 1,3,5,7,8,10,12 has 31 days");
                    throw new ValidatorException(facesMessage);	  
				  
		}
		else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        
                        if (day.equals("30") || day.equals("31")) {
                            FacesMessage facesMessage = new FacesMessage(label + ": the leap year! Feb has 29 days");
                            throw new ValidatorException(facesMessage);
                        }
                            
                    }
                    else {
                        if (day.equals("29") || day.equals("30") || day.equals("31")) {
                            FacesMessage facesMessage = new FacesMessage(label + ": Feb has 28 days");
                            throw new ValidatorException(facesMessage);
                        }
                    }
		
		}
                
                if (year > Calendar.getInstance().get(Calendar.YEAR) || (year == Calendar.getInstance().get(Calendar.YEAR) && Integer.parseInt(month) > Calendar.getInstance().get(Calendar.MONTH))
                        || (year == Calendar.getInstance().get(Calendar.YEAR) && Integer.parseInt(month) == Calendar.getInstance().get(Calendar.MONTH) && Integer.parseInt(day) >= Calendar.getInstance().get(Calendar.DATE))) {
                    
                    FacesMessage facesMessage = new FacesMessage(label + ": you haven't been born yet");
                    throw new ValidatorException(facesMessage);
                    
                }
            
            
            }
			
        }
    }
}
