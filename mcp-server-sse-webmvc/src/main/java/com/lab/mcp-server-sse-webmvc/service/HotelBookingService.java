package com.example.mcp-server-sse-webmvc.service;

import com.example.mcp_server_sse_webmvc.model.HotelBookings;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelBookingService {

    private final List<HotelBookings> bookData;

    public HotelBookingService() {
        bookData = new ArrayList<>();
        initializeDummyData();
    }

    private void initializeDummyData() {
        // 1. Luxury Downtown Stay (3 nights)
        bookData.add(new HotelBookings(
                5001L,
                "The Grand Regent",
                "New York, NY",
                "2025-12-18",
                "2025-12-21",
                "Michael Scott",
                "HRG8P3"
        ));

        // 2. Beach Resort Vacation (7 nights)
        bookData.add(new HotelBookings(
                5002L,
                "Sunset Bay Resort",
                "Cancun, Mexico",
                "2026-01-10",
                "2026-01-17",
                "Jane Smith",
                "SBK4W1"
        ));

        // 3. Short Business Trip (1 night)
        bookData.add(new HotelBookings(
                5003L,
                "Executive Inn",
                "San Francisco, CA",
                "2025-11-28",
                "2025-11-29",
                "Emily Chen",
                "EIF9Q2"
        ));

        // 4. Budget Hostel Stay (5 nights)
        bookData.add(new HotelBookings(
                5004L,
                "City Backpackers Hostel",
                "Berlin, Germany",
                "2026-03-01",
                "2026-03-06",
                "Omar Hassan",
                "CBH1A8"
        ));

        // 5. Family Road Trip Stay (2 nights)
        bookData.add(new HotelBookings(
                5005L,
                "Riverside Suites",
                "Austin, TX",
                "2025-12-26",
                "2025-12-28",
                "Jessica Brown",
                "RSA5T7"
        ));

    }

    @Tool(name = "get_all_hotel_bookings", description = "Get all hotel bookings")
    public List<HotelBookings> getAllBookings() {
        return bookData;
    }
}