/*
 * Course: CS1011 - Saikath Bhattacharya
 * Fall 2022
 * Lab 8 - Parking Lots
 * Name: Adam Haile
 * Created: 10/19/22
 */

package hailea;

/**
 * Manages parking lots within a district.
 * @author Adam Haile
 */
public class District {
    private ParkingLot lot1;
    private ParkingLot lot2;
    private ParkingLot lot3;

    int timeAllClosed = 0;
    int timeReopened = 0;

    /**
     * Set up a district with three parking lots.
     * @param name1 Name of the first parking lot
     * @param capacity1 Maximum number of vehicles for the first parking lot
     * @param name2 Name of the second parking lot
     * @param capacity2 Maximum number of vehicles for the second parking lot
     * @param name3 Name of the third parking lot
     * @param capacity3 Maximum number of vehicles for the third parking lot
     */
    public District(String name1, int capacity1, String name2, int capacity2,
                    String name3, int capacity3) {
        lot1 = new ParkingLot(name1, capacity1);
        lot2 = new ParkingLot(name2, capacity2);
        lot3 = new ParkingLot(name3, capacity3);
    }

    /**
     * Display status information for all three lots.
     * @see ParkingLot#displayStatus() for the format for each.
     */
    public void displayStatus() {
        System.out.println("District status:");
        System.out.print("  ");
        lot1.displayStatus();
        System.out.print("  ");
        lot2.displayStatus();
        System.out.print("  ");
        lot3.displayStatus();
        System.out.println();
    }

    /**
     * Returns the number of remaining parking spots in the district
     * @return the number of remaining parking spots in the district
     */
    public int getNumberOfSpotsRemaining() {
        int spots1 = lot1.getNumberOfSpotsRemaining();
        int spots2 = lot2.getNumberOfSpotsRemaining();
        int spots3 = lot3.getNumberOfSpotsRemaining();
        return spots1 + spots2 + spots3;
    }

    /**
     * Returns the amount of time all three lots have been
     * simultaneously closed.
     * @return number of minutes all three lots have been closed
     */
    public int getMinutesClosed() {
        return timeReopened - timeAllClosed;
    }

    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     * @return whether all three lots in the district are closed
     */
    public boolean isClosed() {
        boolean close1 = lot1.isClosed();
        boolean close2 = lot2.isClosed();
        boolean close3 = lot3.isClosed();
        return close1 && close2 && close3;
    }

    /**
     * Record a vehicle entering a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleEntry for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleEntry on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Entry timestamp in minutes since all lots were opened.
     */
    public void markVehicleEntry(int lotNumber, int timestamp) {
        switch (lotNumber) {
            case 1:
                lot1.markVehicleEntry(timestamp);
                if(isClosed()) {
                    timeAllClosed = timestamp;
                }
                break;
            case 2:
                lot2.markVehicleEntry(timestamp);
                if(isClosed()) {
                    timeAllClosed = timestamp;
                }
                break;
            case 3:
                lot3.markVehicleEntry(timestamp);
                if(isClosed()) {
                    timeAllClosed = timestamp;
                }
                break;
        }
    }

    /**
     * Record a vehicle exiting a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleExit for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleExit on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Exit timestamp in minutes since all lots were opened.
     */
    public void markVehicleExit(int lotNumber, int timestamp) {
        boolean closed = false;
        switch (lotNumber) {
            case 1:
                if(isClosed()) {
                    closed = true;
                }
                lot1.markVehicleExit(timestamp);
                if(closed) {
                    if(!isClosed()) {
                        timeReopened = timestamp;
                    }
                }
                break;
            case 2:
                if(isClosed()) {
                    closed = true;
                }
                lot2.markVehicleExit(timestamp);
                if(closed) {
                    if(!isClosed()) {
                        timeReopened = timestamp;
                    }
                }
                break;
            case 3:
                if(isClosed()) {
                    closed = true;
                }
                lot3.markVehicleExit(timestamp);
                if(closed) {
                    if(!isClosed()) {
                        timeReopened = timestamp;
                    }
                }
                break;
        }
    }
}
