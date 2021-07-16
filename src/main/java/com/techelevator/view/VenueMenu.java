package com.techelevator.view;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;
import java.time.Month;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.techelevator.model.Venue;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.Space;
import com.techelevator.model.SpaceDAO;
import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;


// all usage of System.out or System.in will be in this class
public class VenueMenu {

    private final Scanner in = new Scanner(System.in);
    private VenueDAO venueDAO;
    private Venue venue;
    private SpaceDAO spaceDAO;
    private Space space;
    private ReservationDAO reservationDAO;
    private Reservation reservation;


    public String userChoice() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * main menu displayed when program runs, user chooses to see venue list or quit program
     */
    public void mainMenu() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("\t1) List Venues");
        System.out.println("\tQ) Quit");
    }


    /**
     * when option 1 from mainMenu is chosen, display View Venues question and take user input
     */
    public void viewVenuesMenu() {
        System.out.println();
        System.out.println("Which venue would you like to view?");
        System.out.println();
    }


    /**
     * lists venues in alphabetical order, used during display of Venues Menu
     * @param venues
     */
    public String listVenues(List<Venue> venues) {
        Scanner in = new Scanner(System.in);
        if (venues.size() > 0) {
            for (Venue venue : venues) {
                System.out.println("\t" + (venues.indexOf(venue) + 1) + ") " + venue.getName());
            }
        } else {
            System.out.println("*** No results ***");
        }
        System.out.println("\tR) Return to Previous Screen");
        String input = in.nextLine();
        return input;

    }


    /**
    * displays venue details
    */
    public String venueDetailsMenu(List<Venue> venues, String input) {
        Scanner in = new Scanner(System.in);
            int userChoice = Integer.parseInt(input) - 1;

            System.out.println();
            System.out.println(venues.get(userChoice).getName());
            System.out.println("Location: " + venues.get(userChoice).getCity_name() + ", " + venues.get(userChoice).getState_abbreviation());
            System.out.println("Categories: " + venues.get(userChoice).getCategories());
            System.out.println();
            System.out.println(venues.get(userChoice).getDescription());
            System.out.println();
            System.out.println("What would you like to do next?");
            System.out.println("\t1) View Spaces");
            //System.out.println("\t2) Search for Reservation");
            System.out.println("\tR) Return to Previous Screen");
            String venuesInput = in.nextLine();
            return venuesInput;
    }

    public int venueIndexToVenueId(List<Venue> venues, String input) {
            int userChoice = Integer.parseInt(input) - 1;
            return venues.get(userChoice).getId();
    }

    /**
     * displays spaces available for venue that was selected
     */
    public String venueSpaces(List<Venue> venues, List<Space> spaces, int venueId, String input) {
        Scanner in = new Scanner(System.in);
            int userChoice = Integer.parseInt(input) - 1;

            System.out.println();
            System.out.println(venues.get(userChoice).getName());
            System.out.println();
            System.out.printf("%-28s %-10s %-12s %-15s %-18s %-10s %n", "\tName", "Open", "Close", "Daily Rate", "Max. Occupancy", "Accessible?");
            if (spaces.size() > 0) {
                for (Space space : spaces) {
                    String getOpenFrom = space.getOpen_from();
                    String getOpenTo = space.getOpen_to();
                    boolean isAccessible = space.getIs_accessible();
                    String accessibility = "";

                    if(isAccessible = true) {
                        accessibility = "Yes";
                    } else {
                        accessibility = "No";
                    }
                    if (getOpenTo.equals("NA")) {
                        getOpenTo = "    ";
                    } else {
                        getOpenTo = Month.of(Integer.parseInt(space.getOpen_to())).name().toLowerCase();
                    }
                    if (getOpenFrom.equals("NA")) {
                        getOpenFrom = "    ";

                    } else {
                        getOpenFrom = Month.of(Integer.parseInt(space.getOpen_from())).name().toLowerCase();
                    }

                    String fromFirstLtr = getOpenFrom.substring(0, 1);
                    String fromRestLtr = getOpenFrom.substring(1, getOpenFrom.length());
                    String toFirstLtr = getOpenTo.substring(0, 1);
                    String toRestLtr = getOpenTo.substring(1, getOpenTo.length());
                    fromFirstLtr = fromFirstLtr.toUpperCase();
                    toFirstLtr = toFirstLtr.toUpperCase();
                    getOpenFrom = fromFirstLtr + fromRestLtr;
                    getOpenTo = toFirstLtr + toRestLtr;

                    System.out.printf("%-3s %-27s %-10s %-12s %-15s %-18s %-10s %n", ("#" + (spaces.indexOf(space) + 1)), space.getName(), getOpenFrom, getOpenTo, ("$" + space.getDaily_rate()), space.getMax_occupancy(), accessibility);
                }
            } else {
                System.out.println("*** No results ***");
            }

            System.out.println();
            System.out.println("What would you like to do next?");
            System.out.println("\t1) Reserve a Space");
            System.out.println("\tR) Return to Previous Screen");

            String venueSpacesInput = in.nextLine();
            return venueSpacesInput;
    }

    public void reserveSpace(List<Space> spaces, int venueId) {

        System.out.println("When do you need the space (YYYY-MM-DD)?");
        String startDate = in.nextLine();
        System.out.println("To what date will you need it (YYYY-MM-DD?");
        String endDate = in.nextLine();
        System.out.println("How many people will be in attendance?");
        int peopleAttending = in.nextInt();

        LocalDate userStartDate = LocalDate.parse(startDate);
        LocalDate userEndDate = LocalDate.parse(endDate);

//        try {
//            userStartDate = new SimpleDateFormat("YYYY-MM-DD").parse(startDate);
//        } catch (ParseException e) {
//            return;
//        }
//
//        try {
//            userEndDate = new SimpleDateFormat("YYYY-MM-DD").parse(endDate);
//        } catch (ParseException e) {
//            return;
//        }

        //int totalDays = Days.daysBetween(userStartDate, userEndDate).getDays();

        List<Space> spacesBySearch = spaceDAO.getAvailableSpacesByDateIdAndOccupancy(userStartDate, userEndDate, peopleAttending, venueId);

        System.out.println();
        System.out.println("The following spaces are available based on your needs:");
        System.out.println();
        System.out.printf("%-10s %-28s %-15s %-15s %-15s %-15s %n", "Space #", "Name", "Daily Rate", "Max. Occup.", "Accessible?", "Total Cost");

        if (spaces.size() > 0) {
            for (Space space : spacesBySearch) {
                boolean isAccessible = space.getIs_accessible();
                String accessibility = "";
                //BigDecimal totalCost = space.getDaily_rate().multiply(BigDecimal.valueOf(endDate));

                if(isAccessible = true) {
                    accessibility = "Yes";
                } else {
                    accessibility = "No";
                }
                System.out.printf("%-10s %-28s %-15s %-15s %-15s %-15s %n", space.getId(), space.getName(), "$" + space.getDaily_rate(), space.getMax_occupancy(), accessibility, "$");
            }
        } else {
            System.out.println("No results.");
        }

        System.out.println();
        System.out.println("Which space would you like to reserve (enter 0 to cancel)? ");
        String spaceToReserve = in.nextLine();
        System.out.println("Who is this reservation for?");
        String whoReservationIsFor = in.nextLine();
        System.out.println();
        System.out.println("Thanks for submitting your reservation! The details for your event are listed below: ");
        System.out.println();
        System.out.println("Confirmation #: " + reservation.getId());
        System.out.println("Venue: ");
        System.out.println("Space: ");
        System.out.println("Reserved for: " + whoReservationIsFor);
        System.out.println("Attendees: " + peopleAttending);
        System.out.println("Arrival Date: " + startDate);
        System.out.println("Depart Date: ");
        System.out.println("Total Cost: ");
    }
}
