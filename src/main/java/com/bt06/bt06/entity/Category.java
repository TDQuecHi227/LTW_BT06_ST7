package com.bt06.bt06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="cateName", columnDefinition = "VARCHAR(255)")
    private String cateName;
    @Column(name="cateIcon", columnDefinition = "VARCHAR(255)")
    private String cateIcon;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
