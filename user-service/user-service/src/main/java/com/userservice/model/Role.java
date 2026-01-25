//package com.userservice.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name = "role")
//@EntityListeners(AuditingEntityListener.class)
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "role_id")
//    private Long roleId;
//    private String roleName;
//    private String status;
//    @CreatedDate
//    @Column(nullable = false,updatable = false)
//    private LocalDateTime createdOn;
//    @LastModifiedDate
//    @Column(nullable = false)
//    private LocalDateTime updatedOn;
//    @ManyToMany(mappedBy = "roleList", fetch = FetchType.EAGER)
//    @JsonBackReference
//    private List<User> userList;
//}
