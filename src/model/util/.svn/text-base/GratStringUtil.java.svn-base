/**
 * Copyright (c) 2005, CESAR
 * Rua Bione, 220, Cais do Apolo, Bairro do Recife Antigo - Recife-PE
 * CEP 50030-390, BRASIL
 *
 */
package util;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe utilitaria para manipular texto.
 */
public class GratStringUtil {

	/**
	 * Verifica se o texto esta vazio.
	 *
	 * @param text
	 * @return boolean
	 */
	public static boolean isEmpty(String text) {
		return (text == null || text.trim().length() == 0);
	}

	/**
	 * @param array
	 * @param name
	 * @return
	 */
	public static boolean contains(String[] array, String name) {
		boolean retVal = false;
		for (String string : array) {
			if (string.equals(name)) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}

	/**
	 * Coloca um caractere de escape antes dos caracteres especiais.
	 *
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
	 * @param date
	 * @param format
	 * @return Timestamp
	 * @throws ParseException
	 * @author Leonardo Regis
	 */
	public static Timestamp str2Timestamp(String date, String format)
			throws java.text.ParseException {
		if (date == null || date.equals("")) {
			return null;
		}
		SimpleDateFormat defaultFormat = new SimpleDateFormat(format);
		return new Timestamp(defaultFormat.parse(date).getTime());
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

	private static String clearCharacter(String path, String comp, int qtd){
		String lReturn = path;
		//		 Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = lReturn.indexOf(comp);
			if (index < 0) {
				break;
			}
			lReturn = lReturn.substring(0, index)
					+ lReturn.substring(index + qtd);
		}
		return lReturn;
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

		normalized = clearCharacter(normalized,"//",1);
		normalized = clearCharacter(normalized,"%20",3);
		normalized = clearCharacter(normalized,"/./",2);
		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0) {
				break;
			}
			if (index == 0) {
				return (null); // Trying to go outside our context
			}
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2)
					+ normalized.substring(index + 3);
		}

		// Return the normalized path that we have completed
		return normalized;
	}


	/** TODO move this method to utilitary class
	 * @param path
	 * @return
	 */
	public static boolean deleteDirectoryRecursively(String path) {
		boolean success = false;
		File dir = new File(path);
		if (!dir.exists()) {
			return true;
		}
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				if (!new File(dir, children[i]).isDirectory()) {
					if ((new File(dir, children[i])).exists()) {
						success = new File(dir, children[i]).delete();
					}
				} else {
					success = deleteDirectoryRecursively(new File(dir,
							children[i]).getPath());
				}
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	/**
	 * @param f
	 * @return
	 */
	public static String getFileExtension( File f ){
		String lReturn = "";
		if( f != null ){
			String fileName = f.getName();
			int i = fileName.lastIndexOf( '.' );
			if( i > 0 && i < fileName.length() - 1 ){
				lReturn = fileName.substring( i + 1 ).toLowerCase();
			}
		}
		if ((lReturn == null) || (lReturn.equals(""))) {
			lReturn = "application/octet-stream";
		}
		return lReturn;
	}
}