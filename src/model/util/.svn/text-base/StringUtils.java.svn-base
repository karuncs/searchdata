package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aau.Tag;


public final class StringUtils {
	
	public static void main(String[] args) {
		
		//StringUtils.getDecimalFormat(45.656789769879f);
//		String stringTag="design,blog,video,software,tools,music,programming,webdesign,reference,tutorial,art,web,howto,javascript,free,linux,web2.0,development,google,inspiration,photography,news,food,flash,css,blogs,education,business,technology,travel";		
//		StringUtils.getToken(stringTag, ",");
		
		
		StringUtils.removeInvalidCharacteres("alzheimer\\");
	}
	
	
	/**
	 * @param tags
	 * @return
	 */
	public static String getTagString(List<Tag> tags){
		StringBuilder tagString = new StringBuilder();
		Set<String> singleTag = new HashSet<String>();	
		if (tags!=null) {
			for (Tag tag : tags) {
				if (singleTag!=null && !singleTag.equals("")&& !singleTag.contains(tag.getTagName())) {
					singleTag.add(tag.getTagName());
					tagString.append(tag.getTagName().toLowerCase());
					tagString.append(" ");
				}
			}
		}
		
		try {
			if (tags.size()>0 && tagString.length()>1 && (tagString.lastIndexOf(",")==tagString.length()-1)){
				tagString = new StringBuilder(tagString.substring(0, tagString.lastIndexOf(",")));
				//System.out.println("*******   "+tagString);
			}
		} catch (StringIndexOutOfBoundsException e) {
			tagString = new StringBuilder(tagString.substring(0, 5));
		}

		return tagString.toString();
	}
	
	/**
	 * @param tags
	 * @return
	 */
	public static String getStringList(List<String> tags){
		StringBuilder tagString = new StringBuilder();
		Set<String> singleTag = new HashSet<String>();	
		if (tags!=null) {
			for (String stringItem : tags) {
				if (singleTag!=null && !singleTag.equals("")&& !singleTag.contains(stringItem)) {
					singleTag.add(stringItem);
					tagString.append(stringItem.toLowerCase());
					tagString.append(" ");
				}
			}
		}
		
		try {
			if (tags.size()>0 && tagString.length()>1 && (tagString.lastIndexOf(",")==tagString.length()-1)){
				tagString = new StringBuilder(tagString.substring(0, tagString.lastIndexOf(",")));
				//System.out.println("*******   "+tagString);
			}
		} catch (StringIndexOutOfBoundsException e) {
			tagString = new StringBuilder(tagString.substring(0, 5));
		}

		return tagString.toString();
	}	
	
	/**
	 * @param pNumber
	 * @param scale
	 * @return
	 */
	public static double getNumberFormated(double pNumber, int scale) {
		double currency = new BigDecimal(pNumber).setScale(scale,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		return currency;
	}	
		

	/**
	 * Verifies if the source string contains the value string
	 *
	 * @param source
	 * @param value
	 *
	 * @return
	 */
	public static boolean contains(String source, String value) {
		boolean contains;

		if (source == null || value == null) {
			contains = false;
		} else {
			contains = (source.indexOf(value) != -1);
		}
		return contains;
	}

	/**
	 * Replaces all ocurrences of oldValue for newValue in the source parameter
	 *
	 * @param source
	 * @param oldValue
	 * @param newValue
	 
	 * @return
	 */
	public static String replace(String source, String oldValue, String newValue) {
		if (source == null || oldValue == null || newValue == null) {
			throw new IllegalArgumentException(
					"source == null || oldValue == null || newValue == null");
		}

		StringBuffer newString = new StringBuffer(source.length());

		int beginIndex = 0;
		int oldValueIndex = source.indexOf(oldValue);

		while (oldValueIndex != -1) {
			newString.append(source.substring(beginIndex, oldValueIndex));
			newString.append(newValue);

			beginIndex = oldValueIndex + oldValue.length();
			oldValueIndex = source.indexOf(oldValue, beginIndex);
		}

		newString.append(source.substring(beginIndex));

		return newString.toString();
	}

	/**
	 * Verifies if two given strings are equals
	 *
	 * @param arg1
	 * @param arg2
	 *
	 * @return
	 */
	public static boolean equals(String arg1, String arg2) {
		boolean equals = false;

		if (arg1 == null || arg2 == null) {
			equals = (arg1 == null && arg2 == null);
		} else {
			equals = arg1.trim().equals(arg2.trim());
		}
		return equals;

	}

    /**
     * @param date
     * @return
     */
    public static String getFormatedDate(Date date) {
    	return getFormatedDate(date, Locale.getDefault());
    }

    public static String getFormatedDate(Date date, Locale locale) {
        //SimpleDateFormat formatter = new SimpleDateFormat(Resources.getString(Resources.DATE_FORMAT, locale));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-nn-aaaa");        
    	return formatter.format(date);
    }

    /**
     * @param date
     * @return
     */
    public static String getFormatedTime(Date date) {
        return getFormatedTime(date, Locale.getDefault());
    }

    /**
     * @param date
     * @return
     */
    public static String getFormatedTime(Date date, Locale locale) {
        //SimpleDateFormat formatter = new SimpleDateFormat(Resources.getString("Resources.TIME_FORMAT", locale));
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");        
        return formatter.format(date);
    }
    /**
     * Verifies if a given string is empty (null or equals to empty string)
     *
     * @param value
     *
     * @return
     */
    public static boolean isEmpty(String value) {
        return (value == null || value.trim().equals(""));
    }

    /**
     * Replaces the "\n" break lines with "<br/>" break lines.
     *
     * @param text Original text
     *
     * @return A new text containing HTML break lines insted normal break lines.
     */
	public static String replaceBreakLines(String text) {
		return text != null ? replace(text,  "\n", "<br/>") : null;
	}

	/**
     * Replaces the "<br/>" break lines with "\n" break lines.
     *
     * @param text Original text
     *
     * @return A new text containing HTML break lines insted normal break lines.
     */
	public static String replaceInverseBreakLine(String text) {
		return text != null ? replace(text,  "<br/>", "\n") : null;
	}

	public static String replaceURLEscapeChars(String value) {
//		value = replace(value, " ", "%20");
		value = replace(value, "#", "%23");
		value = replace(value, "$", "%24");
		value = replace(value, "%", "%25");
		value = replace(value, "&", "%26");
		value = replace(value, "@", "%40");
		value = replace(value, "'", "%60");
		value = replace(value, "/", "%2F");
		value = replace(value, ":", "%3A");
		value = replace(value, ";", "%3B");
		value = replace(value, "<", "%3C");
		value = replace(value, "=", "%3D");
		value = replace(value, ">", "%3E");
		value = replace(value, "?", "%3F");
		value = replace(value, "[", "%5B");
		value = replace(value, "\\", "%5C");
		value = replace(value, "]", "%5D");
		value = replace(value, "^", "%5E");
		value = replace(value, "{", "%7B");
		value = replace(value, "|", "%7C");
		value = replace(value, "}", "%7D");
		value = replace(value, "~", "%7E");
		return value;
	}
	
	
	public static String replaceInvalidChars(String value) {
//		
		value = replace(value, "#", "");
		value = replace(value, "$", "");
		value = replace(value, "%", "");
		value = replace(value, "&", "%26");
		value = replace(value, "@", "%40");
		value = replace(value, "'", "%60");
		value = replace(value, "/", "%2F");
		value = replace(value, ":", "%3A");
		value = replace(value, ";", "%3B");
		value = replace(value, "<", "%3C");
		value = replace(value, "=", "%3D");
		value = replace(value, ">", "%3E");
		value = replace(value, "?", "%3F");
		value = replace(value, "[", "%5B");
		value = replace(value, "\\", "%5C");
		value = replace(value, "]", "%5D");
		value = replace(value, "^", "%5E");
		value = replace(value, "{", "%7B");
		value = replace(value, "|", "%7C");
		value = replace(value, "}", "%7D");
		value = replace(value, "~", "%7E");
		return value;
	}	

	/**
	 * It validates the email format
	 * @param email
	 * @return
	 */
	public static boolean verifyEmailFormat(String email) {
		boolean isValid = false;

		if (email != null) {
			Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher m = p.matcher(email);
			isValid = m.matches();
		}

		return isValid;
	}


	/**
	 * @param message
	 * @return
	 */
	public static boolean validadeMessageLenth(String message) {
		//return (message != null && message.trim().length()>=Configuration.getMaxMessageLength());
		return (message != null && message.trim().length()>=10);
	}

	/**
	 * verify if the login contains some invalid character
	 * @param login
	 * @return
	 */
	public static boolean containsInvalidCharacterInLogin(String login) {
		String regex = "[|\"&*=+'@#$\\%\\/?{}?:;<>,\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C6\u00C7\u00C8\u00C9\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D0\u00D1\u00D2\u00D3\u00D4\u00D5\u00D6\u00D8\u0152\u00DE\u00D9\u00DA\u00DB\u00DC\u00DD\u0178\u00E0\u00E1\u00E2\u00E3\u00E4\u00E5\u00E6\u00E7\u00E8\u00E9\u00EA\u00EB\u00EC\u00ED\u00EE\u00EF\u00F0\u00F1\u00F2\u00F3\u00F4\u00F5\u00F6\u00F8\u0153\u00DF\u00FE\u00F9\u00FA\u00FB\u00FC\u00FD\u00FF]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(login);
		return m.find();
	}
	
	/**
	 * verify if there are and remove some invalid character
	 * @param login
	 * @return
	 */
	public static String removeInvalidCharacteres(String string) {
		String corretString = string;
		if (containsInvalidCharacterInLogin(string)) {
			String regex = "[|\"&*=+'@#$%\\/?{}?:;~<>,\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C6\u00C7\u00C8\u00C9\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D0\u00D1\u00D2\u00D3\u00D4\u00D5\u00D6\u00D8\u0152\u00DE\u00D9\u00DA\u00DB\u00DC\u00DD\u0178\u00E0\u00E1\u00E2\u00E3\u00E4\u00E5\u00E6\u00E7\u00E8\u00E9\u00EA\u00EB\u00EC\u00ED\u00EE\u00EF\u00F0\u00F1\u00F2\u00F3\u00F4\u00F5\u00F6\u00F8\u0153\u00DF\u00FE\u00F9\u00FA\u00FB\u00FC\u00FD\u00FF]";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(string);
			corretString = m.replaceAll("");
			//System.out.println(corretString);
		}
		
		corretString = corretString.replace("\\", "");
		
		return corretString;
	}	
	
	/**
	 * It compares null date values before comparing dates 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int validateNullDateValues(Date date1, Date date2) {
		int returnValue = 0;
		if ((date1 == null) & (date2 == null)){
			returnValue = 0;
		}else if ((date1 == null) & (date2 != null)) {
			returnValue = -1;
		}else if ((date1 != null) & (date2 == null)) {
			returnValue = 1;
		}
		return returnValue;
	}
	

	/**
	 * @param tagString
	 * @param delimiter
	 * @return
	 */
	public static Set<String> getToken(String tagString,String delimiter){
		Set<String> tagStrings = new HashSet<String>();
		StringTokenizer tempStringTokenizer = new StringTokenizer(tagString,delimiter);
        String term = null;
		while (tempStringTokenizer.hasMoreTokens()) {
			term = tempStringTokenizer.nextElement().toString();
			tagStrings.add(term);
        }
		return tagStrings;
     }
	
	/**
	 * @param url
	 * @return
	 */
	public static String getURLName(String url){
		try {
			url = new URL(url).getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		int index = 0;		
		if(url.contains("www")){
			index = 1;
		}
		StringTokenizer tempStringTokenizer = new StringTokenizer(url,".");
        int i = 0;
        String term = null;
		while (tempStringTokenizer.hasMoreTokens()) {
			term = tempStringTokenizer.nextElement().toString();
        	if(i==index){
        		break;
        	}
        	i++;	
        }
		
		return term;
	}	
	
	public static String getDecimalFormat(Object obj){
		DecimalFormat twoDigits = new DecimalFormat("##.##");
		return twoDigits.format(obj);
		
	}
	
	
	
	public static void saveFile(String fileName, String content){
		try{
			File file = new File("C:\\M-Eco Software\\medworm by jan\\medwo\\"+fileName+".sql");
			if (file.exists()) {
			    FileWriter fstream = new FileWriter(file,true);
			    BufferedWriter out = new BufferedWriter(fstream);
			    out.write(content);
			    out.close();				
			}
			 

	    }catch (Exception e){
	      System.err.println("Error: " + e.getMessage());
	    }
	 }
	
	
	
	public static String readFileAsString(String filename) 
	  throws IOException
	  {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line;
	    StringBuilder sb = new StringBuilder();
	    while ((line = reader.readLine()) != null)
	    {
	      sb.append(line + "\n");
	    }
	    reader.close();
	    return sb.toString();
	  }
	


	/**
	 * @param text
	 * @return String
	 */
	public static String escape(String text) {
		return text.replaceAll("'", "\\\\'").replaceAll("\"", "\\\\\"");
	}

	/**
	 * @param size
	 * @param text
	 * @return String
	 */
	public static String fillChar(int size, String text) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(text);
		}
		return sb.toString();
	}


	/**
	 * @param s
	 * @return String
	 */
	public static String removeDuplicatedEmptySpaces(String s) {
		StringBuffer toReturn = new StringBuffer();
		String[] arr = s.trim().split(" ");
		boolean emptySpaceAdded = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("")) {
				if (!emptySpaceAdded) {
					toReturn.append(" ");
					emptySpaceAdded = true;
				}
			} else {
				if (!emptySpaceAdded && i > 0) {
					toReturn.append(" ");
				}
				emptySpaceAdded = false;
				toReturn.append(arr[i]);
			}
		}
		return toReturn.toString();
	}

	/**
	 * Receive a string path and nomalize it.
	 * 
	 * @param path
	 */

	public static final String normalizePath(String path) {
		// Normalize the slashes and add leading slash if necessary
		String normalized = path;
		if (normalized.indexOf('\\') >= 0) {
			normalized = normalized.replace('\\', '/');
		}

		if (!normalized.startsWith("/")) {
			normalized = "/" + normalized;
		}

		// Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0) {
				break;
			}
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 1);
		}

		// Resolve occurrences of "%20" in the normalized path
		while (true) {
			int index = normalized.indexOf("%20");
			if (index < 0) {
				break;
			}
			normalized = normalized.substring(0, index) + " "
					+ normalized.substring(index + 3);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0) {
				break;
			}
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 2);
		}

		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0) {
				return (null); // Trying to go outside our context
			}
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2)
					+ normalized.substring(index + 3);
		}

		// Return the normalized path that we have completed
		return (normalized);
	}
	
	public static String getSimpleFormat(Date date){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(date);
		
	}	
	

}
