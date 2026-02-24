package com.lab.springaimcpserverssewebmvc.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HotelBookings {
  private Long id;
  private String hotelName;
  private String location;
  private String checkInDate;
  private String checkOutDate;
  private String guestName;
  private String bookingRef;
}