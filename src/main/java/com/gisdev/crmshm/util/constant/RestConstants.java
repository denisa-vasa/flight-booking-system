package com.gisdev.crmshm.util.constant;

public interface RestConstants {

    String ROOT = "/api";

    String ID_PATH = "/{id}";

    String FLIGHT_ID = "/{flightId}";
    String USER_ID = "/{userId}";
    String FLIGHT_REQUEST_ID = "/{flightRequestId}";

    String DATE = "/by-date";
    String FLIGHT_BY_USER = "/by-user";

    interface UserController{

        String BASE_PATH = ROOT + "/users";
        String SAVE_USER = "/save-user";
        String UPDATE_USER = USER_ID + "/update-user";
        String DELETE_USER = USER_ID + "/delete-user";
        String USER = "/user-name";
        String UPLOAD = "/user";
    }

    interface FlightController{

        String BASE_PATH = ROOT + "/flight";
        String SAVE_FLIGHT = "/save-flight";
        String UPDATE_FLIGHT = FLIGHT_ID + "/update-flight";
        String DELETE_FLIGHT = FLIGHT_ID + "/delete-flight";
        String FLIGHT_BY_DATE = DATE + "/{date}";
        String FLIGHT_USER_ID = FLIGHT_BY_USER + USER_ID;
        String FLIGHT_BY_DEPARTURE_DESTINATION = "/by-departure-destination";
        String FLIGHT_BY_TRAVEL_CLASS = "/by-travel-class";
        String ALL_FLIGHTS = "/all-flights";
        String EXPORT_FINAL_INPUT_FILE = "/export-flights";
        String EXPORT_FLIGHTS_BY_TRAVEL_CLASS = "/export-flight-by-travel-class";
        String UPLOAD = "/upload";
        String UPLOAD_FLIGHTS_AND_USER = "/upload-flights-and-user";
    }

    interface FlightRequestController{

        String BASE_PATH = ROOT + "/flight_request";
        String SAVE_FLIGHT_REQUEST = "/save-flight_request";
        String UPDATE_FLIGHT_REQUEST = FLIGHT_REQUEST_ID + "/update-flight_request";
        String DELETE_FLIGHT_REQUEST = FLIGHT_REQUEST_ID + "/delete-flight_request";
    }
}
