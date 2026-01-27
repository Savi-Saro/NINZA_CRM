package genericUtilities;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

			public int generateRandomNumber() {
				// TODO Auto-generated method stub
		Random random = new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;
			}
			public String getCurrentDateAndTime() {	
		Date d = new Date();
		String date = d.toString().replace(" ","_").replace(":","_");
		return date;
			}
			public String getRequiredDate(int days)
			{
				Date d = new Date();
				SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
				sim.format(d);
				Calendar cal = sim.getCalendar();
				cal.add(cal.DAY_OF_MONTH,days);
				return sim.format(cal.getTime());
			}
			
			    public static String getRandomString(int length) {
			        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			        Random random = new Random();
			        StringBuilder sb = new StringBuilder();

			        for (int i = 0; i < length; i++) {
			            sb.append(letters.charAt(random.nextInt(letters.length())));
			        }
			        return sb.toString();
			    }
			}
	

