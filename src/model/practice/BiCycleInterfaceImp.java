package practice;

class BiCycleInterfaceImp implements Bicycle {
	
	int cadence = 0;
    int speed = 0;
    int gear = 1;
  
    int fred=3;
    
    public void printStates() {
        System.out.println("cadence:"+cadence+" speed:"+speed+" gear:"+gear);
   ++fred;
   
   }
//passing parameters
    public void changeCadence(int newValue) {
    	
         cadence = newValue;
    }

    public void chnageGear(int newValue) {
        // gear = newValue;
    	 
        gear = newValue+fred;
    }

    public void speedUp(int increment) {
         speed = speed + increment;   
    }

    public void applyBreaks(int decrement) {
         speed = speed - decrement;
    }


    
   
    
	public static void main(String[] args) {
//		Integer x = new Integer(5);
//		x.intValue()
//		int f = new int();
		int a = 1;
		int b = 1;
		Integer ab = 1;
		Integer cc = 3;
		if (ab.equals(cc)) {
			
		}
		
		// TODO Auto-generated method stub
		    ByCycle bike1 = new ByCycle();
	        ByCycle bike2 = new ByCycle();
	        

	          
	          
	          int[][] arrayOfInts = { { 32, 87, 3, 589 },
                      { 12, 1076, 2000, 8 },
                      { 622, 127, 77, 955 }
                    };

	          for (int i = 0; i < 3; i++) {
	        	  for (int j = 0; j < 4; j++) {
//	        		  for (int k = 0; k < 4; k++){
//	        	  }
	        	  System.out.println("for position i= "+ i+" and j "+ j + " the value is:"+ arrayOfInts[i][j]);
	        	  
	        	  }	
			}
	          
	          
	          




	        // Invoke methods on those objects
//	        bike1.changeCadence(50);
//	        bike1.speedUp(10);
//	        bike1.changeGear(2);
//	        bike1.printStates();
//
//	        bike2.changeCadence(50);
//	        bike2.speedUp(10);
//	        bike2.changeGear(2);
//	        bike2.changeCadence(40);
//	        bike2.speedUp(10);
//	        bike2.changeGear(3);
//	        bike2.printStates();
	   }

	}


