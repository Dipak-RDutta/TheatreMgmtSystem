package com.dipak.theatre;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Theatre {
  public static void main(String[] args) {
    System.out.println("Welcome to the New Theatre");
    List<Integer> row1 = new ArrayList<>(12);
    row1 = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0);
    List<Integer> row2 = new ArrayList<>(16);
    row2 = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
    List<Integer> row3 = new ArrayList<>(20);
    row3 = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

    List<Ticket> tickets = new ArrayList<>();

    while (true) {
      System.out.println("---------------------------------");
      System.out.println("Please select an option:");
      System.out.println("1) Buy a ticket");
      System.out.println("2) Print seating area");
      System.out.println("3) Cancel ticket");
      System.out.println("4) List available seats");
      System.out.println("5) Save to file");
      System.out.println("6) Load from file");
      System.out.println("7) Print ticket information and total price");
      System.out.println("8) Sort tickets by price");
      System.out.println("0) Quit");
      System.out.println("---------------------------------");
      System.out.print("Enter option: ");

      Scanner sc= new Scanner(System.in);
      String str= sc.nextLine();

      switch(str) {
        case "1": buy_ticket(row1, row2, row3, tickets);
                  break;
        case "2": print_seating_area(row1, row2, row3);
                  break;
        case "3": cancel_ticket(row1, row2, row3, tickets);
                  break;
        case "4": show_available(row1, row2, row3);
                  break;
        case "5": save(row1, row2, row3);
                  break;
        case "6": load(row1, row2, row3);
                  break;
        case "7": show_tickets_info(tickets);
                  break;
        case "8": show_tickets_info(sort_tickets(tickets));
                  break;
        case "0": sc.close();
                  System.exit(0);
      }
    }
  }

  private static void buy_ticket(List<Integer> row1, List<Integer> row2, List<Integer> row3, List<Ticket> tickets) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter row number: ");
    String rowNo = sc.nextLine();
    if (Integer.parseInt(rowNo) > 3) {
      System.out.println("Theatre don't have that many ROWS");
      return;
    }

    System.out.print("Enter seat number: ");
    int seatNo = Integer.parseInt(sc.nextLine());

    if ("1".equals(rowNo)) {
      if (row1.get(seatNo-1) == 1) {
        System.out.println("Seat is already occupied.");
        return;
      } else {
        row1.set(seatNo-1, 1);
        
      }
    }
    if ("2".equals(rowNo)) {
      if (row2.get(seatNo-1) == 1) {
        System.out.println("Seat is already occupied.");
        return;
      } else {
        row2.set(seatNo-1, 1);
      }
    }
    if ("3".equals(rowNo)) {
      if (row3.get(seatNo-1) == 1) {
        System.out.println("Seat is already occupied.");
        return;
      } else {
        row3.set(seatNo-1, 1);
      }
    }

    System.out.print("Enter person's name: ");
    String name = sc.nextLine();
    System.out.print("Enter person's surname: ");
    String surname = sc.nextLine();
    System.out.print("Enter person's emailid: ");
    String email = sc.nextLine();

    double price = 0;
    if ("1".equals(rowNo)) {
      price = 10;
    } else if ("2".equals(rowNo)) {
      price = 20;
    } else if ("3".equals(rowNo)) {
      price = 30;
    }

    Person person = new Person(name, surname, email);
    Ticket ticket = new Ticket(Integer.parseInt(rowNo), seatNo, price, person);
    tickets.add(ticket);

    System.out.println("Ticket is bought.");
  }

  private static void print_seating_area(List<Integer> row1, List<Integer> row2, List<Integer> row3) {
    System.out.println("    ***********");
    System.out.println("    *  STAGE  *");
    System.out.println("    ***********");
    int noOfSpace = (row3.size()-row1.size())/2;
    for (int i=1; i<=noOfSpace; i++) {
      System.out.print(" ");
    }
    for (int i=0; i < row1.size(); i++) {
      if (i == row1.size()/2) {
        System.out.print(" ");
      }
      if (row1.get(i) == 1) {
        System.out.print("X");
      } else if (row1.get(i) == 0) {
        System.out.print("O");
      }
    }

    System.out.println();
    noOfSpace = (row3.size()-row2.size())/2;
    for (int i=1; i<=noOfSpace; i++) {
      System.out.print(" ");
    }
    for (int i=0; i < row2.size(); i++) {
      if (i == row2.size()/2) {
        System.out.print(" ");
      }
      if (row2.get(i) == 1) {
        System.out.print("X");
      } else {
        System.out.print("O");
      }
    }
    System.out.println();
    for (int i=0; i < row3.size(); i++) {
      if (i == row3.size()/2) {
        System.out.print(" ");
      }
      if (row3.get(i) == 1) {
        System.out.print("X");
      } else {
        System.out.print("O");
      }
    }
    System.out.println();
  }

  private static void cancel_ticket(List<Integer> row1, List<Integer> row2, List<Integer> row3, List<Ticket> tickets) {
    Scanner sc= new Scanner(System.in);
    System.out.print("Enter row number: ");
    String rowNo = sc.nextLine();
    if (Integer.parseInt(rowNo) > 3) {
      System.out.println("Theatre don't have that many ROWS");
      return;
    }

    System.out.print("Enter seat number: ");
    int seatNo = Integer.parseInt(sc.nextLine());

    if (Integer.parseInt(rowNo) > 3) {
      System.out.println("Theatre don't have that many ROWS");
      return;
    }

    List<Integer> row = null;
    if ("1".equals(rowNo)) {
      row = row1;
      if (seatNo > row.size()) {
        System.out.println("Theatre don't have so many seats in ROW"+rowNo);
        return;
      }
    } else if ("2".equals(rowNo)) {
      row = row2;
      if (seatNo > row.size()) {
        System.out.println("Theatre don't have so many seats in ROW"+rowNo);
        return;
      }
    } else if ("3".equals(rowNo)) {
      row = row3;
      if (seatNo > row.size()) {
        System.out.println("Theatre don't have so many seats in ROW"+rowNo);
        return;
      }
    }

    if (row != null) {
      if (row.get(seatNo-1) == 0) {
        System.out.println("Seat is not occupied.");
      } else {
        row.set(seatNo-1, 0);
        System.out.println("Seat booking is cancelled.");
      }
    }

    System.out.print("Enter person's emailid: ");
    String email = sc.nextLine();

    for (Iterator<Ticket> it = tickets.iterator(); it.hasNext();) {
      if (it.next().getPerson().getEmail().equals(email)) {
        it.remove();
      }
    }
  }

  private static void show_available(List<Integer> row1, List<Integer> row2, List<Integer> row3) {
    System.out.print("Seats available in row 1: ");
    for (int i=0; i < row1.size(); i++) {
      if (row1.get(i) == 0) {
        if (i+1 == row1.size()) {
          System.out.print(i+1 + ".");
        } else {
          System.out.print(i+1 + ", ");
        }
      }
    }
    System.out.print("\nSeats available in row 2: ");
    for (int i=0; i < row2.size(); i++) {
      if (row2.get(i) == 0) {
        if (i+1 == row2.size()) {
          System.out.print(i+1 + ".");
        } else {
          System.out.print(i+1 + ", ");
        }
      }
    }
    System.out.print("\nSeats available in row 3: ");
    for (int i=0; i < row3.size(); i++) {
      if (row3.get(i) == 0) {
        if (i+1 == row3.size()) {
          System.out.println(i+1 + ".");
        } else {
          System.out.print(i+1 + ", ");
        }
      }
    }
    System.out.println("\n");
  }

  private static void save(List<Integer> row1, List<Integer> row2, List<Integer> row3) {
    // Path output = Paths.get("output.txt");
    // Files.write(output, row1, Charset.defaultCharset(), null);

    /*try (FileWriter writer = new FileWriter("output.txt")) {
      for (Integer r1 : row1) {
        writer.write(r1 + " ");
      }
      writer.write(System.lineSeparator());
      for (Integer r2 : row2) {
        writer.write(r2 + " ");
      }
      writer.write(System.lineSeparator());
      for (Integer r3 : row3) {
        writer.write(r3 + " ");
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }*/

    Map<String, List<Integer>> rowMap = new HashMap<>();
    rowMap.put("row1", row1);
    rowMap.put("row2", row2);
    rowMap.put("row3", row3);

    try {
      FileOutputStream writeData = new FileOutputStream("Theatre.ser");
      ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

      writeStream.writeObject(rowMap);
      writeStream.flush();
      writeStream.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private static void load(List<Integer> row1, List<Integer> row2, List<Integer> row3) {
    /*try (Scanner sc = new Scanner(new File("output.txt"))) {
      while (sc.hasNextLine()) {
        String[] row = sc.nextLine().split(" ");
        for (int i=0; i<row.length; i++) {
          if ("1".equals(row[i])) {
            row1.add(i, Integer.parseInt(row[i]));
          }
        }
        System.out.println(row);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }*/

    Map<String, List<Integer>> rowMap = null;

    try {
      FileInputStream readData = new FileInputStream("Theatre.ser");
      ObjectInputStream readStream = new ObjectInputStream(readData);

      rowMap = (Map<String, List<Integer>>) readStream.readObject();

      Collections.copy(row1, rowMap.get("row1"));
      System.out.println(row1);
      Collections.copy(row2, rowMap.get("row2"));
      System.out.println(row2);
      Collections.copy(row3, rowMap.get("row3"));
      System.out.println(row3);
    
      readStream.close();
    } //catch (IOException | ClassCastException e) {
      catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private static void show_tickets_info(List<Ticket> tickets) {
    tickets.forEach(t -> t.print());

    Double totalPrice = tickets.stream().map(t -> t.getPrice()).reduce(0.0, Double::sum);
    System.out.println("Total price: " + totalPrice);
  }

  private static List<Ticket> sort_tickets(List<Ticket> tickets) {
    // return tickets.stream().sorted((ticket1, ticket2) -> new Double(ticket1.getPrice()).compareTo(ticket2.getPrice())).collect(Collectors.toList());

    return tickets.stream().sorted(Comparator.comparing(Ticket::getPrice)).collect(Collectors.toList());
  }
}
