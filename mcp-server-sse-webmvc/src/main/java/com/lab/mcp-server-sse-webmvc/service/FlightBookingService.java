package com.example.mcp-server-sse-webmvcservice;


import com.example.mcp_server_sse_webmvc.model.FlightBooking;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class FlightBookingService {
    private final List<FlightBooking> bookData;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    BiFunction<String, String, LocalDateTime> parseDateTime =
            (date, time) -> LocalDateTime.parse(date + "T" + time);


    public FlightBookingService() {
        bookData = new ArrayList<>();
        initializeDummyData();
    }

    private void initializeDummyData() {
        {
            bookData.add(new FlightBooking(
                    1001L, "Southwest Airlines", "DAL", "MDW",
                    parseDateTime.apply("2025-11-20", "07:30:00"),
                    parseDateTime.apply("2025-11-20", "09:45:00"),
                    "WN3014", "Alice Johnson", "SWK5P4"
            ));

            // 2. International Long Haul (LHR -> JFK)
            bookData.add(new FlightBooking(
                    1002L, "British Airways", "LHR", "JFK",
                    parseDateTime.apply("2025-12-05", "11:00:00"),
                    parseDateTime.apply("2025-12-05", "14:15:00"), // Note: Arrival is later due to time zone difference
                    "BA175", "Robert Davis", "UKJ8A9"
            ));

            // 3. Late Night / Red-Eye (LAX -> MIA)
            bookData.add(new FlightBooking(
                    1003L, "American Airlines", "LAX", "MIA",
                    parseDateTime.apply("2025-11-25", "23:55:00"),
                    parseDateTime.apply("2025-11-26", "08:10:00"),
                    "AA902", "Sarah Chen", "AAX4T2"
            ));

            // 4. Holiday Travel (DEN -> SFO)
            bookData.add(new FlightBooking(
                    1006L, "United Airlines", "DEN", "SFO",
                    parseDateTime.apply("2025-12-24", "09:05:00"),
                    parseDateTime.apply("2025-12-24", "11:00:00"),
                    "UA420", "Emily Wong", "UAD3F4"
            ));

            // 5. Morning International (CDG -> NRT)
            bookData.add(new FlightBooking(
                    1007L, "Air France", "CDG", "NRT",
                    parseDateTime.apply("2026-01-15", "06:30:00"),
                    parseDateTime.apply("2026-01-16", "08:55:00"),
                    "AF276", "Omar Hassan", "AFC2K3"
            ));
        }
    }

    @Tool(name="get_all_bookings",description = "Get all flight bookings")
    public List<FlightBooking> getAllBookings() {
        return bookData;
    }
}
