package UIConstant;

public class ConstantRegister {
	
	public static final String ACCOUNT_PAGE = "//title[text()=\"My Account - PS Enterprise Gameshop\"]";
	
	//Register Form
	public static final String REGISTER_GENRE_FIELD = "//*[@id=\"acf-field_5e758bca4e8cc\"]";
	public static final String REGISTER_GENRE_INPUT = "//*[@id=\"acf-field_5e758bca4e8cc\"]/option[2]";
	public static final String REGISTER_CONSOLE_FIELD = "//*[@id=\"acf-field_5e75905014c81\"]";
	public static final String REGISTER_CONSOLE_INPUT = "//*[@id=\"acf-field_5e75905014c81\"]/option[2]";
	public static final String REGISTER_HOBBY_FIELD = "//*[@id=\"acf-field_5ea976d054e4e\"]";
	public static final String REGISTER_HOBBY_INPUT = "//*[@id=\"acf-field_5ea976d054e4e\"]/option[2]";
	public static final String REGISTER_MALE_OPTION = "//*[@value=\"Male\"]";
	public static final String REGISTER_EMAIL_FIELD = "//*[@id=\"reg_email\"]";
	public static final String REGISTER_PASSWORD_FIELD = "//*[@id=\"reg_password\"]";
	public static final String REGISTER_CONFIRM_PASSWORD_FIELD = "//*[@id=\"reg_confirm_password\"]";
	public static final String REGISTER_BTN = "//*[@value=\"Register\"]";
	public static final String REGISTER_ALERT = "//*[@role=\"alert\"]//div[1]";
	
	//Errors
	public static final String REGISTER_ERR_PASS = "//*[@aria-live=\"polite\"]";
	public static final String REGISTER_ERR_PASS_HINT = "//small[@class=\"woocommerce-password-hint\"]";


}
