package com.example.ktb.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    public String _id;
    public int index;
    public String guid;
    public boolean isActive;
    public String balance;
    public String picture;
    public int age;
    public String eyeColor;
    public String name;
    public String gender;
    public String company;
    public String email;
    public String phone;
    public String address;
    public String about;
    public String registered;
    public double latitude;
    public double longitude;
    public List<String> tags;
    public List<Friend> friends;
    public long promptPayId;
    public String memo;
    public String favoriteFruit;
}
