package com.techelevator;

public class Airplane {
    private String planeNumber;
    private int totalFirstClassSeats;
    private int bookedFirstClassSeats;
    private int availableFirstClassSeats;  // derived
    private int totalCoachSeats;
    private int bookedCoachSeats;
    private int availableCoachSeats;  // derived

    public Airplane(String planeNumber, int totalFirstClassSeats, int totalCoachSeats) {
        this.planeNumber = planeNumber;
        this.totalFirstClassSeats = totalFirstClassSeats;
        this.totalCoachSeats = totalCoachSeats;
    }

    public boolean reserveSeats(boolean forFirstClass, int totalNumberOfSeats) {
        if (forFirstClass) {
            if (totalNumberOfSeats <= this.getAvailableFirstClassSeats()) {
                this.bookedFirstClassSeats += totalNumberOfSeats;
                this.availableFirstClassSeats -= totalNumberOfSeats;
                return true;
            } else {
                return false;
            }
        } else {
            if (totalNumberOfSeats <= this.getAvailableCoachSeats()) {
                this.bookedCoachSeats += totalNumberOfSeats;
                this.availableCoachSeats -= totalNumberOfSeats;
                return true;
            } else {
                return false;
            }
        }
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public int getTotalFirstClassSeats() {
        return totalFirstClassSeats;
    }

    public int getBookedFirstClassSeats() {
        return bookedFirstClassSeats;
    }

    public int getAvailableFirstClassSeats() {
        this.availableFirstClassSeats = this.totalFirstClassSeats - this.bookedFirstClassSeats;
        return this.availableFirstClassSeats;
    }

    public int getTotalCoachSeats() {
        return totalCoachSeats;
    }

    public int getBookedCoachSeats() {
        return bookedCoachSeats;
    }

    public int getAvailableCoachSeats() {
        this.availableCoachSeats = this.totalCoachSeats - this.bookedCoachSeats;
        return this.availableCoachSeats;
    }
}
