package util;

import java.io.File;

import sesame.IConstant;


public final class ProjectUtil {
	
	public static void main(String[] args) {

	}

	/**
	 * It gets the amount of resoureces available
	 * 
	 * @return
	 */
	public static int getAmountOfResourecesAvailable() {
		File dir = new File(IConstant.SANDBOX);
		return dir.list().length;
	}

	
}
