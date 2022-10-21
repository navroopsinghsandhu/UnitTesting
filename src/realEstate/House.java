package realEstate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class House {
	private String houseID;
    private String houseAddress;
    private String houseType;
    private double housePrice;
    private int numBedrooms;
    private int numBathrooms;
    private int numCarspace;
    private double sizeHouse;
    private String descriptionHouse;
    String delimeter = "~";

    public House (String id, String address, String type, double price, int bedrooms, int bathrooms, int carspace, double size, String description){
    	this.houseID = id;
    	this.houseAddress = address;
    	this.houseType = type;
    	this.housePrice = price;
    	this.numBedrooms = bedrooms;
    	this.numBathrooms = bathrooms;
    	this.numCarspace = carspace;
    	this.sizeHouse = size;
    	this.descriptionHouse = description;
    }

    public boolean AddHouse(){
    	//condition-1: check for houseID
    	String houseId = this.houseID;
    	if(houseId.length() == 10 ) {
    		for (int i = 0; i < houseId.length(); i++){
    		    char c = houseId.charAt(i);
    		    if(i < 3) {
    		    	if(c >= 'A' && c <= 'Z') {
    		    		continue;
    		    	} else {
    		    		return false;
    		    	}
    		    } else {
    		    	if((c >= '0' && c <= '9')) {
    		    		continue;
    		    	} else {
    		    		return false;
    		    	}
    		    }
    		    
    		}
    	} else {
    		return false;
    	}
    
    	//Condition-1: check words of description
    	int wordsDescription = countWords(this.descriptionHouse);
    	if(wordsDescription < 5 || wordsDescription > 10) {
    		return false;
    	}
    	
    	//Condition-2: number of bedrooms and bathrooms for each house
    	if(this.numBedrooms > 4 && this.numBathrooms <= 2) {
    		return false;
    	}
    	
    	//Condition-3: house size and house price
    	if(this.sizeHouse < 50 && this.housePrice > 350000) {
    		return false;
    	}
    	
    	//Condition-4
    	if(this.houseType == "Apartment" && this.numCarspace < 1) {
    		return false;
    	}
    	
    	//Condition-4: check for negative integers and float values
    	if(this.numCarspace < 0 || this.numBedrooms < 0 || this.numBathrooms < 0 || this.housePrice < 0 || this.sizeHouse < 0) {
    		return false;
    	}
    	
    	//Condition-5
    	if(this.housePrice < 100000 || this.housePrice > 1500000) {
    		return false;
    	}
    	
    	//Condition-6
    	if(this.numBedrooms < 3 && this.numBathrooms == 2 && this.housePrice > 750000) {
    		return false;
    	}
    	
    	String houseString = houseId + delimeter + this.houseAddress + delimeter + this.houseType + delimeter + this.housePrice + delimeter + this.numBedrooms + delimeter + this.numBathrooms + delimeter + this.numCarspace + delimeter+ this.sizeHouse + delimeter + this.descriptionHouse;
    	//write to the text file to add this house
    	try (BufferedWriter output = new BufferedWriter(new FileWriter("houses.txt", true));) {
    		output.append(houseString + "\n");
    		output.close();
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	        
        return true;
    }

    public boolean updateHouse(String newAddress, String newType, double newPrice, int newBedrooms, int newBathrooms, int newCarspace, double newSize, String newDescription){
    	String houseId = this.houseID;

    	//Condition-1: check words of description
    	int wordsDescription = countWords(newDescription);
    	if(wordsDescription < 5 || wordsDescription > 10) {
    		return false;
    	}

    	//Condition-2: number of bedrooms and bathrooms for each house
    	if(newBedrooms > 4 && newBathrooms <= 2) {
    		return false;
    	}
    	
    	//Condition-3: house size and house price
    	if(newSize < 50 && newPrice > 350000) {
    		return false;
    	}
    	
    	//Condition-4
    	if(newType == "Apartment" && newCarspace < 1) {
    		return false;
    	}
    	
    	//Condition-4: added my own
    	if(newCarspace < 0) {
    		return false;
    	}
    	
    	//Condition-5
    	if(newPrice < 100000 || newPrice > 1500000) {
    		return false;
    	}
    	
    	//Condition-6
    	if(newBedrooms < 3 && newBathrooms == 2 && newPrice > 750000) {
    		return false;
    	}
    	
    	//Condition-2
    	double currentPrice = this.housePrice;
		double increasePercentage = (currentPrice/newPrice)/100;
    	if(newBedrooms < 3 && increasePercentage > 10) {
    		return false;
    	} else if(increasePercentage > 20) {
    		return false;
    	}
    	
    	//Condition-4:
    	double currentSize = this.sizeHouse;
		double increasePercentageSize = (currentSize/newSize)/100;
		if(increasePercentageSize < 5 && increasePercentageSize > 10) {
			return false;
		}
		
		ArrayList<House> houseObjects = new ArrayList();
		//read the txt file and create an object for each line and store all those objects
		// then write each of those objects to the same txt file, overwriting the old data
		try(BufferedReader br = new BufferedReader(new FileReader("houses.txt"))) {
		    String line = br.readLine();

		    while (line != null) {
		        String[] house = line.split(delimeter);
				House houseObject = new House(house[0], house[1], house[2], Double.parseDouble(house[3]), Integer.parseInt(house[4]), Integer.parseInt(house[5]), Integer.parseInt(house[6]), Double.parseDouble(house[7]), house[8]);
				houseObjects.add(houseObject);
		        line = br.readLine();
		    }
		    
		    br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//update comes here
		//converts the array list items to their corresponding objects of class "House"
    	//write to the text file
    	try (PrintWriter output = new PrintWriter(new FileWriter("houses.txt", false));) {
    		output.flush();
    		for(int i = 0; i < houseObjects.size(); i++) {
    			String currentHouseId = houseObjects.get(i).getHouseID();
    			if(currentHouseId.equals(houseId)) {
    				House houseReference = houseObjects.get(i);
    				//changes come here:
    				if(houseReference.getHouseType() == "Townhouse") {
    					houseReference.setHouseAddress(newAddress);
    				}
    				houseReference.setHouseType(newType);
    				houseReference.setHousePrice(newPrice);
    				houseReference.setNumBedrooms(newBedrooms);
    				houseReference.setNumBathrooms(newBathrooms);
    				houseReference.setNumCarspace(newCarspace);
    				houseReference.setSizeHouse(newSize);
    				houseReference.setDescriptionHouse(newDescription);
    			}

    			String houseInfo = houseObjects.get(i).getHouseID() + delimeter + houseObjects.get(i).getHouseAddress() + delimeter + houseObjects.get(i).getHouseType() + delimeter + houseObjects.get(i).getHousePrice() + delimeter + houseObjects.get(i).getNumBedrooms() + delimeter + houseObjects.get(i).getNumBathrooms() + delimeter + houseObjects.get(i).getNumCarspace() + delimeter+houseObjects.get(i).getSizeHouse() + delimeter + houseObjects.get(i).getDescriptionHouse();
    			output.print(houseInfo + "\n");
    		}
    		output.close();
    		} catch (IOException e) {
				e.printStackTrace();
			}
				
        return true;
    }
    
    public static void createTextFile() {
    	try {
    	      File myObj = new File("./houses.txt");
    	      if (myObj.createNewFile()) {
    	        System.out.println("File created: " + myObj.getName());
    	      } else {
	    	  PrintWriter writer = new PrintWriter(myObj);
	    	  writer.print("");
	    	  writer.close();
    	      }
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    }
    
    public static int countWords(String str) {
    	String trim = str.trim();
    	if (trim.isEmpty())
    	    return 0;
    	return trim.split("\\s+").length;
    }

	private String getHouseID() {
		return houseID;
	}

	private void setHouseID(String houseID) {
		this.houseID = houseID;
	}

	private String getHouseAddress() {
		return houseAddress;
	}

	private void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	private String getHouseType() {
		return houseType;
	}

	private void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	private double getHousePrice() {
		return housePrice;
	}

	private void setHousePrice(double housePrice) {
		this.housePrice = housePrice;
	}

	private int getNumBedrooms() {
		return numBedrooms;
	}

	private void setNumBedrooms(int numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	private int getNumBathrooms() {
		return numBathrooms;
	}

	private void setNumBathrooms(int numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	private int getNumCarspace() {
		return numCarspace;
	}

	private void setNumCarspace(int numCarspace) {
		this.numCarspace = numCarspace;
	}

	private double getSizeHouse() {
		return sizeHouse;
	}

	private void setSizeHouse(double sizeHouse) {
		this.sizeHouse = sizeHouse;
	}

	private String getDescriptionHouse() {
		return descriptionHouse;
	}

	private void setDescriptionHouse(String descriptionHouse) {
		this.descriptionHouse = descriptionHouse;
	}
}
