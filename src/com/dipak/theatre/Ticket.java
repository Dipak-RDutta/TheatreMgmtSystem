package com.dipak.theatre;

public class Ticket {

  private int row;
  private int seat;
  private double price;
  private Person person;
  
  public Ticket(int row, int seat, double price, Person person) {
    this.row = row;
    this.seat = seat;
    this.price = price;
    this.person = person;
  }

  public double getPrice() {
    return price;
  }

  public Person getPerson() {
    return person;
  }

  @Override
  public String toString() {
    return "Ticket [row=" + row + ", seat=" + seat + ", price=" + price + "]";
  }

  public void print() {
    System.out.println(this.person);
    System.out.println(this);
  }
}
