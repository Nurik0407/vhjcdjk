package model;

import ClassException.UniqueConstraintException;
import enums.Gender;
import service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class User implements UserService {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private BigDecimal money;
    private List<Book> books;
    private List<User> usersDateBase = new ArrayList<>();

    public User(Long id, String name, String surname, String email, String phoneNumber, Gender gender, BigDecimal money, List<Book> books) throws UniqueConstraintException {
        infoId();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.money = money;
        this.books = books;
    }

    private void infoId() throws UniqueConstraintException {
        for (User user : usersDateBase) {
            if (user.getId() == id) {
                throw new UniqueConstraintException();
            } else {
                this.id = id;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String createUser(List<User> users) {
        usersDateBase.addAll(users);
        return "User successfully added";
    }

    @Override
    public List<User> findAllUsers() {
        return usersDateBase;
    }

    @Override
    public User findUserById(Long id) {
        for (User user : usersDateBase) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String removeUserByName(String name) {
        for (User user : usersDateBase) {
            if (user.getName().equals(name)) {
                usersDateBase.remove(user);
                return "User successfully deleted";
            }
        }
        return "User unsuccessfully deleted";
    }

    @Override
    public void updateUser(Long id) throws UniqueConstraintException {
        boolean idTrueFalse = false;
        for (User user : usersDateBase) {
            if (Objects.equals(user.getId(), id)) {
                System.out.println("""
                        1 = change id
                        2 = change name 
                        3 = change surname 
                        4 = change gmail
                        5 = change phoneNumber
                        6 = change gender
                        7 = change money
                        8 = change books
                        """);

                int i = new Scanner(System.in).nextInt();
                switch (i) {
                    case 1 -> {
                        System.out.println(user.getId());
                        System.out.println("Enter new id : ");
                        long newId = new Scanner(System.in).nextLong();
                        for (User user1 : usersDateBase) {
                            if (user1.getId() == newId) {
                                throw new UniqueConstraintException();
                            }
                        }
                        user.setId(newId);
                    }
                    case 2 -> {
                        System.out.println(user.getName());
                        System.out.println("Enter new name : ");
                        String newName = new Scanner(System.in).nextLine();
                        user.setName(newName);
                    }
                    case 3 -> {
                        System.out.println(user.getSurname());
                        System.out.println("Enter new surname : ");
                        String newSurname = new Scanner(System.in).nextLine();
                        user.setSurname(newSurname);
                    }
                    case 4 -> {
                        System.out.println(user.getEmail());
                        System.out.println("Enter new gmail : ");
                        String newEmail = new Scanner(System.in).nextLine();
                        user.setEmail(newEmail);
                    }
                    case 5 -> {
                        System.out.println(user.getPhoneNumber());
                        System.out.println("Enter new phone number : ");
                        String num = new Scanner(System.in).nextLine();
                        user.setPhoneNumber(num);
                    }
                    case 6 -> {
                        System.out.println(user.getGender());
                        System.out.println("Enter new gender");
                        String newGender = new Scanner(System.in).nextLine();
                        user.setGender(Gender.valueOf(newGender));
                    }
                    case 7 -> {
                        System.out.println(user.getMoney());
                        System.out.println("Enter new money");
                        long big = new Scanner(System.in).nextLong();
                        user.setMoney(BigDecimal.valueOf(big));
                    }
                }

            }
        }
    }

    @Override
    public void groupUsersByGender() {
        System.out.println("Female: ");
        System.out.println(usersDateBase.stream().filter(s -> s.getGender().equals(Gender.FEMALE)).toList());
        System.out.println("Male");
        System.out.println(usersDateBase.stream().filter(s -> s.getGender().equals(Gender.MALE)).toList());
    }

    @Override
    public String buyBooks( List<Book> books) {

        System.out.println("Entre id person");
        long idd = new Scanner(System.in).nextLong();
        for (User user : usersDateBase) {
            if (user.getId() == idd) {

                for (Book book : books) {
                    if (book.getName().equals(name)) {

                        user.setMoney(BigDecimal.valueOf(user.getMoney().intValue()-book.getPrice().intValue()));
                        return "Successfully ";
                    }
                }
            }
        }
        return " Unsuccessfully ";
    }
}
