package realEstate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestHouse {
//	House.createTextFile();

;
	
	@Test
	void testHouse() {
		House.createTextFile();
		//addHouse()
		//Test Case: 1
		House myhouse = new House("NAV1234567", "27 Ben drive, Pakenham", "Townhouse", 340000, 4, 3, 1, 46.0,"This house offers spacious rooms" );
		//Test Case: 2
		House myhouse2 = new House ("Nav1234561", "27 Ben drive, Pakenham", "Townhouse", 340000, 4, 3, 1, 46.0,"This house offers spacious rooms" );
		//Test Case: 3
		House myhouse3 = new House("NAV1234562", "27 Ben drive, Pakenham", "Townhouse", 340000, 4, 3, 1, 46.0,"This house is great" );
		//Test Case: 4
		House myhouse4 = new House("NAV1234563", "27 Ben drive, Pakenham", "Townhouse", 340000, 5, 2, 1, 46.0,"This house offers spacious rooms" );
		//Test Case: 5
		House myhouse5 = new House ("NAV1234564", "27 Ben drive, Pakenham", "Townhouse", 360000, 4, 3, 1, 46.0,"This house offers spacious rooms" );
		//Test Case: 6
		House myhouse6 = new House ("NAV1234565", "27 Ben drive, Pakenham", "Apartment", 340000, 4, 3, 0, 46.0,"This house offers spacious rooms" );
		//Test Case: 7
		House myhouse7 = new House("NAV1234566", "27 Ben drive, Pakenham", "Townhouse", 90000, 4, 3, 1, 46.0,"This house offers spacious rooms" );	
		//Test Case: 8
		House myhouse8 = new House ("NAV1234567", "27 Ben drive, Pakenham", "Townhouse", 760000, 2, 2, 1, 46.0,"This house offers spacious rooms" );
		assertTrue(myhouse.AddHouse());
		assertFalse(myhouse2.AddHouse());
		assertFalse(myhouse3.AddHouse());
		assertFalse(myhouse4.AddHouse());
		assertFalse(myhouse5.AddHouse());
		assertFalse(myhouse6.AddHouse());
		assertFalse(myhouse7.AddHouse());
		assertFalse(myhouse8.AddHouse());
		
		//UpdateHouse()
		//Test Case:1
		assertTrue(myhouse.updateHouse( "59 Paisley street, Footscray", "Townhouse", 330000, 3, 2, 1, 46.0,"This house offers even more spacious rooms" ));
		//Test Case:2
		assertFalse(myhouse.updateHouse( "27 Ben drive, Pakenham", "Townhouse", 340000, 4, 3, 1, 46.0,"This house has heating" ));
		//Test Case:3
		assertFalse(myhouse.updateHouse("27 Ben drive, Pakenham", "Apartment", 340000, 4, 3, 0, 46.0,"This house offers spacious rooms"));
		//Test Case:4
		assertFalse(myhouse.updateHouse("27 Ben drive, Pakenham", "Townhouse", 90000, 4, 3, 1, 46.0,"This house offers spacious rooms"));
		//Test Case:5
		assertFalse(myhouse.updateHouse("27 Ben drive, Pakenham", "Townhouse", 760000, 2, 2, 1, 46.0,"This house offers spacious rooms"));
		//Test Case:6
		assertFalse(myhouse.updateHouse( "27 Ben drive, Pakenham", "Townhouse", 400000, 2, 1, 1, 46.0,"This house offers spacious rooms"));
		//Test Case:7
		assertFalse(myhouse.updateHouse( "27 Ben drive, Pakenham", "Townhouse", 500000, 2, 1, 1, 46.0,"This house offers spacious rooms" ));

	}

}
